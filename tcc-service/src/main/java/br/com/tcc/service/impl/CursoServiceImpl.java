/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarCurso;
import br.com.tcc.service.query.BuscarEtapa;
import br.com.tcc.service.query.ExcluirEtapaPerguntaPorCurso;
import br.com.tcc.service.query.ExcluirEtapaPerguntaPorEtapa;
import br.com.tcc.service.query.ExcluirEtapaPorCurso;
import br.com.tcc.service.validator.CursoValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("CursoServiceImpl")
public class CursoServiceImpl {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CursoValidator validador;

    @Autowired
    private AnexoServiceImpl anexoService;
    
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
        dao.saveOrUpdate(curso);
        return curso;
    }

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

    @Transactional(readOnly = true)
    public Curso buscarCursoPorId(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities()
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .fetchResposta(ConstantesI18N.FETCH)
                .fetchAnexo(ConstantesI18N.FETCH)
                .whereId(idCurso)
                .orderByNivel());
        curso.setIdAnexo(curso.getAnexo()!=null?curso.getAnexo().getId():null);
        curso.setUltimaEtapa(curso.getEtapas().size());
        return curso;
    }

    @Transactional(readOnly = true)
    public Curso buscarCursoPorIdConcluido(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities()
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
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

    @Transactional(readOnly = true)
    public List<Curso> buscarCursoPorFiltro(Long idUsuario, String parteNome, Categoria categoria, SituacaoCurso situacaoCurso) {
        return dao.list(new BuscarCurso.Entities()
                .whereUsuario(idUsuario)
                .whereNomeLike(parteNome)
                .whereCategoria(categoria)
                .whereSituacaoCurso(situacaoCurso));
    }
    
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
    
    @Transactional(readOnly = false)
    public CursoAluno entrarCurso(Long idCurso, Long idAluno, String codAcesso) {
        Curso curso = dao.get(Curso.class, idCurso);
        validador.validarSalvarEtapa(curso, codAcesso);
        CursoAluno cursoAluno = new CursoAluno();
        cursoAluno.setAluno(new Usuario(idAluno));
        cursoAluno.setCurso(new Curso(idCurso));
        cursoAluno.setPontuacao(0);
        cursoAluno.setPosicaoAtual(1);
        dao.saveOrUpdate(cursoAluno);
        return cursoAluno;
    }
}
