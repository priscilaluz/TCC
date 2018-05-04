/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import java.util.HashSet;
import tcc.common.entity.Anexo;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.entity.EtapaPergunta;
import tcc.common.enums.DisponibilidadeCurso;
import tcc.common.enums.SituacaoCurso;
import tcc.common.util.ConstantesI18N;
import tcc.service.persistence.GenericDao;
import tcc.service.query.BuscarCurso;
import tcc.service.query.BuscarEtapa;
import tcc.service.query.ExcluirEtapaPerguntaPorCurso;
import tcc.service.query.ExcluirEtapaPerguntaPorEtapa;
import tcc.service.query.ExcluirEtapaPorCurso;
import tcc.service.query.UpdateDisponibilidadeCurso;
import tcc.service.validator.CursoValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.AnexoService;
import tcc.common.business.CursoAlunoService;
import tcc.common.business.CursoService;
import tcc.common.entity.Usuario;

/**
 *
 * @author ADM
 */
@Component("CursoServiceImpl")
public class CursoServiceImpl implements CursoService {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CursoValidator validador;

    @Autowired
    private AnexoService anexoService;
    
    @Autowired
    private CursoAlunoService cursoAlunoService;
    
    @Override
    @Transactional(readOnly = false)
    public Curso salvarCurso(Curso curso) {
        Anexo anexo = curso.getAnexo();
        if (anexo != null) {
            anexo.setId(curso.getIdAnexo());
            curso.setAnexo(anexoService.salvarAnexo(anexo));
        } else if (curso.getIdAnexo() != null) {
            Anexo anexoRemover = dao.get(Anexo.class, curso.getIdAnexo());
            dao.remove(anexoRemover);
        }
        validador.validarSalvarCurso(curso);
        curso.setDisponibilidade(DisponibilidadeCurso.FECHADO);
        dao.saveOrUpdate(curso);
        return curso;
    }
    
    @Override
    @Transactional(readOnly = false)
    public Curso copiarCurso(String nomeCurso, Long idCurso, Long idUsuario) {
        Curso curso = buscarCursoPorId(idCurso);
        Curso cursoCopia = new Curso();
        cursoCopia.setNome(nomeCurso);
        cursoCopia.setAssuntoGeral(curso.getAssuntoGeral());
        cursoCopia.setCodAcesso(curso.getCodAcesso());
        cursoCopia.setCategoria(curso.getCategoria());
        cursoCopia.setUsuario(new Usuario(idUsuario));
        cursoCopia.setSituacao(SituacaoCurso.RASCUNHO);
        cursoCopia.setDisponibilidade(DisponibilidadeCurso.FECHADO);
        if (curso.getAnexo() != null) {
            Anexo anexo = new Anexo();
            anexo.setNomeArquivo(curso.getAnexo().getNomeArquivo());
            anexo.setArquivo(anexoService.obterBytesAnexo(curso.getAnexo().getId()));
        }
        cursoCopia = salvarCurso(cursoCopia);
        if (!curso.getEtapasLista().isEmpty()) {
            for (Etapa etapa : curso.getEtapasLista()) {
                Etapa etapaNova = new Etapa();
                etapaNova.setAssunto(etapa.getAssunto());
                etapaNova.setNivel(etapa.getNivel());
                etapaNova.setJogo(etapa.getJogo());
                etapaNova.setCurso(cursoCopia);
                if (etapa.getAnexo() != null) {
                    Anexo anexo = new Anexo();
                    anexo.setNomeArquivo(etapa.getAnexo().getNomeArquivo());
                    anexo.setArquivo(anexoService.obterBytesAnexo(etapa.getAnexo().getId()));
                }
                etapaNova.setEtapasPerguntas(new HashSet<EtapaPergunta>());
                for (EtapaPergunta etapaPergunta : etapa.getEtapasPerguntas()) {
                    EtapaPergunta etapaPerguntaNova = new EtapaPergunta();
                    etapaPerguntaNova.setPergunta(etapaPergunta.getPergunta());
                    etapaNova.getEtapasPerguntas().add(etapaPerguntaNova);
                }
                salvarEtapa(etapaNova);
            }
        }
    
        return cursoCopia;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirCurso(Long idCurso) {
        Curso curso = buscarCursoPorId(idCurso);
        dao.executeDML(new ExcluirEtapaPerguntaPorCurso(idCurso));
        dao.executeDML(new ExcluirEtapaPorCurso(idCurso));
        if (curso.getAnexo() != null) {
            dao.remove(curso.getAnexo());
        }
        dao.remove(curso);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateDisponibilidadeCurso(Long idCurso, DisponibilidadeCurso disponibilidade) {
        dao.executeDML(new UpdateDisponibilidadeCurso(idCurso, disponibilidade));
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscarCursoPorId(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities()
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .whereId(idCurso)
                .orderByNivel());
        curso.setIdAnexo(curso.getAnexo()!=null?curso.getAnexo().getId():null);
        curso.setUltimaEtapa(curso.getEtapas().size());
        return curso;
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscarCursoPorIdConcluido(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities()
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .fetchAnexoEtapa(ConstantesI18N.FETCH)
                .whereId(idCurso)
                .orderByNivel());
        curso.setIdAnexo(curso.getAnexo()!=null?curso.getAnexo().getId():null);
        curso.setUltimaEtapa(curso.getEtapas().size());
        return curso;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> buscarCursoPorFiltro(Long idUsuario, String parteNome, Long idCategoria, SituacaoCurso situacaoCurso, 
            DisponibilidadeCurso disponibilidade, Long idAluno) {
        List<Curso> cursos =  dao.list(new BuscarCurso.Entities()
                .fetchCategoria(ConstantesI18N.FETCH)
                .whereUsuario(idUsuario)
                .whereNomeLike(parteNome)
                .whereCategoria(idCategoria)
                .whereDisponibilidadeCurso(disponibilidade)
                .whereSituacaoCurso(situacaoCurso));
        if (idAluno != null) {
            for (Curso curso : cursos) {
                curso.setAlunoPertence(cursoAlunoService.alunoPertenceCurso(idAluno, curso.getId()));
            }
        }
        return cursos;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long buscarCountCursoPorFiltro(Long idUsuario, SituacaoCurso situacaoCurso) {
        return (Long) dao.uniqueResult(new BuscarCurso.Count()
                .whereUsuario(idUsuario)
                .whereSituacaoCurso(situacaoCurso));
    }
    
    @Override
    @Transactional(readOnly = false)
    public Etapa salvarEtapa(Etapa etapa) {
        validador.validarSalvarEtapa(etapa);
        Anexo anexo = etapa.getAnexo();
        if (anexo != null) {
            anexo.setId(etapa.getIdAnexo());
            etapa.setAnexo(anexoService.salvarAnexo(anexo));
        } else if (etapa.getIdAnexo() != null) {
            Anexo anexoRemover = dao.get(Anexo.class, etapa.getIdAnexo());
            dao.remove(anexoRemover);
        }
        if (etapa.getId() != null) {
            dao.executeDML(new ExcluirEtapaPerguntaPorEtapa(etapa.getId()));
        }
        dao.saveOrUpdate(etapa);
        for (EtapaPergunta etapaPergunta : etapa.getEtapasPerguntas()) {
            etapaPergunta.setEtapa(etapa);
            etapaPergunta.setPosicao(etapaPergunta.getPergunta().getPosicao());
            dao.saveOrUpdate(etapaPergunta);
        }
        return etapa;
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirEtapa(Long idCurso, Long idEtapa) {
        dao.executeDML(new ExcluirEtapaPerguntaPorEtapa(idEtapa));
        Etapa etapaRemover = dao.get(Etapa.class, idEtapa);
        dao.remove(etapaRemover);
        
        int nivel = 1;
        List<Etapa> etapas = buscarEtapa(idCurso, null);
        for (Etapa etapa : etapas) {
            etapa.setNivel(nivel);
            dao.saveOrUpdate(etapa);
            nivel++;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Etapa> buscarEtapa(Long idCurso, Integer nivel) {
        List<Etapa> etapas = dao.list(new BuscarEtapa.Entities()
                .fetchEtapaPergunta(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .whereIdCurso(idCurso)
                .whereNivel(nivel)
                .orderByNivel());
        for (Etapa etapa : etapas) {
            etapa.setIdAnexo(etapa.getAnexo()!=null?etapa.getAnexo().getId():null);
        }
        return etapas;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Etapa buscarEtapaPorId(Long idEtapa, boolean resposta) {
        return (Etapa) dao.uniqueResult(new BuscarEtapa.Entities()
                .fetchEtapaPergunta(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchPerguntaAnexo(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH, resposta)
                .fetchAnexo(ConstantesI18N.FETCH)
                .whereIdEtapa(idEtapa));
    }
}
