/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarCurso;
import br.com.tcc.service.query.ExcluirEtapaPerguntaPorEtapa;
import br.com.tcc.service.query.ExcluirEtapaPorCurso;
import br.com.tcc.service.validator.CursoValidator;
import java.util.ArrayList;
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

    @Transactional(readOnly = false)
    public Curso salvarCurso(Curso curso) {
        if (curso.getId() != null) {
            dao.executeDML(new ExcluirEtapaPerguntaPorEtapa(curso.getId()));
            dao.executeDML(new ExcluirEtapaPorCurso(curso.getId()));
        }
        validador.validarSalvarCurso(curso);
        dao.saveOrUpdate(curso);
        for (Etapa etapa : curso.getEtapas()) {
            etapa.setId(null);
            etapa.setCurso(curso);
            validador.validarSalvarEtapa(etapa);
            dao.saveOrUpdate(etapa);
            for (Pergunta pergunta : etapa.getPerguntas()) {
                EtapaPergunta etapaPergunta = new EtapaPergunta();
                etapaPergunta.setEtapa(etapa);
                etapaPergunta.setPergunta(pergunta);
                etapaPergunta.setPosicao(pergunta.getPosicao());
                dao.saveOrUpdate(etapaPergunta);
            }
        }
        return curso;
    }

    @Transactional(readOnly = false)
    public void excluirCurso(Long idCurso) {
        dao.executeDML(new ExcluirEtapaPerguntaPorEtapa(idCurso));
        dao.executeDML(new ExcluirEtapaPorCurso(idCurso));
        Curso curso = dao.get(Curso.class, idCurso);
        dao.remove(curso);
    }

    @Transactional(readOnly = true)
    public Curso buscarCursoPorId(Long idCurso) {
        Curso curso = (Curso) dao.uniqueResult(new BuscarCurso.Entities()
                .fetchEtapas(ConstantesI18N.FETCH)
                .fetchEtapasPerguntas(ConstantesI18N.FETCH)
                .fetchPergunta(ConstantesI18N.FETCH)
                .whereId(idCurso));
        if (curso != null && !curso.getEtapas().isEmpty()) {
            for (Etapa etapa : curso.getEtapas()) {
                List<Pergunta> perguntas = new ArrayList<>();
                for (EtapaPergunta etapasPergunta : etapa.getEtapasPerguntas()) {
                    Pergunta pergunta = etapasPergunta.getPergunta();
                    pergunta.setPosicao(etapasPergunta.getPosicao());
                    perguntas.add(pergunta);
                }
                etapa.setPerguntas(perguntas);
            }
        }
        return curso;
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarCursoPorFiltro(Long idUsuario, String parteNome, Categoria categoria) {
        return dao.list(new BuscarCurso.Entities()
                .whereUsuario(idUsuario)
                .whereNomeLike(parteNome)
                .whereCategoria(categoria));
    }
}
