/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.enums.TipoUsuario;
import br.com.tcc.common.vo.DadoProfessor;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarUsuario;
import br.com.tcc.service.validator.UsuarioValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("UsuarioServiceImpl")
public class UsuarioServiceImpl {

    @Autowired
    private GenericDao dao;
    
    @Autowired
    private UsuarioValidator validador;
    
    @Autowired
    private PerguntaServiceImpl perguntaService;
    
    @Autowired
    private CursoServiceImpl cursoService;
    
    @Autowired
    private CursoAlunoServiceImpl cursoAlunoService;

    @Transactional(readOnly = false)
    public Usuario salvarUsuario(Usuario usuario) {
        Long emailJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereEmail(usuario.getEmail()).whereIdNot(usuario.getId()));
        Long loginJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereLogin(usuario.getLogin()).whereIdNot(usuario.getId()));
        usuario.setTipo(TipoUsuario.ALUNO);
        validador.validarSalvarUsuario(usuario, emailJaExistente, loginJaExistente);
        return dao.saveOrUpdate(usuario);
    }

    @Transactional(readOnly = false)
    public Usuario salvarProfessor(Usuario usuario) {
        Long emailJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereEmail(usuario.getEmail()).whereIdNot(usuario.getId()));
        Long loginJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereLogin(usuario.getLogin()).whereIdNot(usuario.getId()));
        usuario.setTipo(TipoUsuario.PROFESSOR);
        validador.validarSalvarUsuario(usuario, emailJaExistente, loginJaExistente);
        return dao.saveOrUpdate(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorLoginSenha(String login, String senha) {
        return (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereLogin(login).whereSenha(senha));
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarProfessores(String nome) {
        return dao.list(new BuscarUsuario.Entities().whereNomeLike(nome).whereTipo(TipoUsuario.PROFESSOR));
    }
    
    @Transactional(readOnly = true)
    public Usuario buscarProfessorPorId(Long id) {
        return (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereId(id).whereTipo(TipoUsuario.PROFESSOR));
    }
    
    @Transactional(readOnly = false)
    public void excluirProfessores(Long idProfessores) {
        List<Pergunta> perguntas = perguntaService.buscarPerguntaPorFiltro(idProfessores, null, null, null, null);
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(idProfessores, null, null, null, null);
        validador.validarExcluirUsuario(perguntas, cursos);
        Usuario professores = buscarProfessorPorId(idProfessores);
        dao.remove(professores);
    }
    
    @Transactional(readOnly = false)
    public DadoProfessor dadosProfessor(Long idProfessores) {
        DadoProfessor dado = new DadoProfessor();
        dado.setQntAluno(cursoAlunoService.buscarCountCursoAlunoPorProfessor(idProfessores));
        dado.setQntCursoRascunho(cursoService.buscarCountCursoPorFiltro(idProfessores, SituacaoCurso.RASCUNHO));
        dado.setQntCursoConcluidos(cursoService.buscarCountCursoPorFiltro(idProfessores, SituacaoCurso.CONCLUIDA));
        dado.setQntPerguntas(perguntaService.buscarCountPerguntaPorFiltro(idProfessores));
        return dado;
    }
}
