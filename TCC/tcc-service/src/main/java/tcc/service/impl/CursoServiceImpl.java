/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import tcc.common.entity.Aviso;
import tcc.common.entity.Usuario;
import tcc.common.vo.ListaPaginacao;
import tcc.common.vo.Paginacao;
import tcc.service.persistence.Pagination;
import tcc.service.query.BuscarAviso;

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
        validador.validarSalvarCurso(curso);
        Anexo anexo = curso.getAnexo();
        if (anexo != null && anexo.getId() == null) {
            curso.setAnexo(anexoService.salvarAnexo(anexo));
        }
        if (curso.getIdAnexoExcluido() != null) {
            anexoService.excluirAnexo(curso.getIdAnexoExcluido());
        }
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
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities(false)
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .fetchAvisos(ConstantesI18N.FETCH)
                .whereId(idCurso)
                .orderByNivel());
        curso.setUltimaEtapa(curso.getEtapas().size());
        return curso;
    }

    @Override
    @Transactional(readOnly = true)
    public Curso buscarCursoPorIdConcluido(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities(false)
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchCategoria(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchUsuario(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .fetchAnexoEtapa(ConstantesI18N.FETCH)
                .fetchAvisos(ConstantesI18N.FETCH)
                .whereId(idCurso)
                .orderByNivel());
        curso.setUltimaEtapa(curso.getEtapas().size());
        return curso;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> buscarCursoPorIdProfessor(Long idProfessor) {
        return dao.list(new BuscarCurso.Entities(false).whereUsuario(idProfessor));
    }

    @Override
    @Transactional(readOnly = true)
    public ListaPaginacao buscarCursoPorFiltro(Long idUsuario, String parteNome, Long idCategoria, SituacaoCurso situacaoCurso,
            DisponibilidadeCurso disponibilidade, Long idAluno, Integer paginaAtual) {
        BuscarCurso queryCont = queryBuscarCursoPorFiltro(idUsuario, parteNome, idCategoria, situacaoCurso, disponibilidade, true, "");
        Long numDeItens = (Long) dao.uniqueResult(queryCont);
        Pagination pagination = new Pagination(Paginacao.DEFAULT_QNT_POR_PAG, paginaAtual);
        BuscarCurso queryList = queryBuscarCursoPorFiltro(idUsuario, parteNome, idCategoria, situacaoCurso, disponibilidade, false, ConstantesI18N.FETCH);
        queryList.setPagination(pagination);
        List<Curso> cursos = dao.list(queryList);
        List<Object> lista = new ArrayList<>();
        for (Curso curso : cursos) {
            if (idAluno != null) {
                curso.setAlunoPertence(cursoAlunoService.alunoPertenceCurso(idAluno, curso.getId()));
            }
            lista.add((Object) curso);
        }
        return new ListaPaginacao(lista, new Paginacao(numDeItens, paginaAtual));
    }

    private BuscarCurso queryBuscarCursoPorFiltro(Long idUsuario, String parteNome, Long idCategoria, SituacaoCurso situacaoCurso,
            DisponibilidadeCurso disponibilidade, boolean cont, String fetch) {
        return new BuscarCurso.Entities(cont)
                .fetchCategoria(fetch)
                .whereUsuario(idUsuario)
                .whereNomeLike(parteNome)
                .whereCategoria(idCategoria)
                .whereDisponibilidadeCurso(disponibilidade)
                .whereSituacaoCurso(situacaoCurso);
    }

    @Override
    @Transactional(readOnly = true)
    public Long buscarCountCursoPorFiltro(Long idUsuario, SituacaoCurso situacaoCurso) {
        return (Long) dao.uniqueResult(new BuscarCurso.Entities(true)
                .whereUsuario(idUsuario)
                .whereSituacaoCurso(situacaoCurso));
    }

    @Override
    @Transactional(readOnly = false)
    public boolean addAlunosAoCurso(Long idCurso, List<Long> idsAluno) {
        Curso curso = buscarCursoPorId(idCurso);
        for (Long idAluno : idsAluno) {
            cursoAlunoService.entrarCurso(idCurso, idAluno, curso.getCodAcesso());
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public Etapa salvarEtapa(Etapa etapa) {
        validador.validarSalvarEtapa(etapa);
        Anexo anexo = etapa.getAnexo();
        if (anexo != null && anexo.getId() == null) {
            etapa.setAnexo(anexoService.salvarAnexo(anexo));
        }
        if (etapa.getIdAnexoExcluido() != null) {
            anexoService.excluirAnexo(etapa.getIdAnexoExcluido());
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

    @Override
    @Transactional(readOnly = false)
    public Aviso salvarAviso(Aviso aviso) {
        aviso.setDataModificao(new Date());
        return dao.saveOrUpdate(aviso);
    }

    @Override
    @Transactional(readOnly = false)
    public void excluirAviso(Long idAviso) {
        Aviso avisoRemover = dao.get(Aviso.class, idAviso);
        dao.remove(avisoRemover);
    }

    @Override
    @Transactional(readOnly = true)
    public Aviso buscarAvisosPorId(Long id) {
        return (Aviso) dao.uniqueResult(new BuscarAviso.Entities().whereId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Aviso> buscarAvisosPorCurso(Long idCurso) {
        return dao.list(new BuscarAviso.Entities().whereIdCurso(idCurso));
    }
}
