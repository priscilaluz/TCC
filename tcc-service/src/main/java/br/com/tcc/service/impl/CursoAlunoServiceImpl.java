/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.SituacaoCursoAluno;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.vo.TabuleiroCurso;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarCursoAluno;
import br.com.tcc.service.validator.CursoValidator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("CursoAlunoServiceImpl")
public class CursoAlunoServiceImpl {

    @Autowired
    private GenericDao dao;

    @Autowired
    private CursoValidator validador;
    
    @Transactional(readOnly = false)
    public CursoAluno entrarCurso(Long idCurso, Long idAluno, String codAcesso) {
        CursoAluno cursoAlunoJaSalvo = (CursoAluno) dao.uniqueResult(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .whereIdAluno(idAluno).whereIdCurso(idCurso));
        Curso curso = dao.get(Curso.class, idCurso);
        validador.validarSalvarEtapa(curso, codAcesso, cursoAlunoJaSalvo);
        CursoAluno cursoAluno = new CursoAluno();
        cursoAluno.setAluno(new Usuario(idAluno));
        cursoAluno.setCurso(new Curso(idCurso));
        cursoAluno.setSituacao(SituacaoCursoAluno.EM_ANDAMENTO);
        cursoAluno.setPontuacao(0);
        cursoAluno.setPosicaoAtual(1);
        dao.saveOrUpdate(cursoAluno);
        return cursoAluno;
    }
    
    @Transactional(readOnly = true)
    public List<CursoAluno> buscarCursoAlunoPorAlunoSituacao(Long idAluno, SituacaoCursoAluno situacao) {
        List<CursoAluno> cursosAlunos = dao.list(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .fetchEtapas(ConstantesI18N.FETCH)
                .whereIdAluno(idAluno).whereSituacaoCursoAluno(situacao));
        Set<CursoAluno> list = new HashSet<>(cursosAlunos);
        return new ArrayList<>(list);
    }
    
    @Transactional(readOnly = true)
    public TabuleiroCurso buscarCursoAlunoPorIdCursoAluno(Long idCursoAluno) {
        CursoAluno cursosAlunos = (CursoAluno) dao.uniqueResult(new BuscarCursoAluno.Entities()
                .fetchAluno(ConstantesI18N.FETCH).fetchCurso(ConstantesI18N.FETCH)
                .fetchEtapas(ConstantesI18N.FETCH)
                .whereIdCursoAluno(idCursoAluno));
        return null;
    }
}
