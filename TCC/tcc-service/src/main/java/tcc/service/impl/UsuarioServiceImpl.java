/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import java.util.Date;
import tcc.common.entity.Curso;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Usuario;
import tcc.common.enums.SituacaoCurso;
import tcc.common.enums.TipoUsuario;
import tcc.common.vo.DadoProfessor;
import tcc.service.persistence.GenericDao;
import tcc.service.query.BuscarUsuario;
import tcc.service.validator.UsuarioValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tcc.common.business.CursoAlunoService;
import tcc.common.business.CursoService;
import tcc.common.business.PerguntaService;
import tcc.common.business.UsuarioService;

/**
 *
 * @author ADM
 */
@Component("UsuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private GenericDao dao;
    
    @Autowired
    private UsuarioValidator validador;
    
    @Autowired
    private PerguntaService perguntaService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private CursoAlunoService cursoAlunoService;

    @Override
    @Transactional(readOnly = false)
    public Usuario salvarUsuario(Usuario usuario) {
        Long emailJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereEmail(usuario.getEmail()).whereIdNot(usuario.getId()));
        Long loginJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereLogin(usuario.getLogin()).whereIdNot(usuario.getId()));
        validador.validarSalvarUsuario(usuario, emailJaExistente, loginJaExistente);
        usuario.setDataCadastro(new Date());
        usuario.setDataUltimoAcesso(new Date());
        return dao.saveOrUpdate(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorLoginSenha(String login, String senha) {
        Usuario usuario = (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereLogin(login).whereSenha(senha));
        usuario.setDataUltimoAcesso(new Date());
        dao.saveOrUpdate(usuario);
        return usuario;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarios(String nome, TipoUsuario tipo, Long idCurso) {
        return dao.list(new BuscarUsuario.Entities().whereNomeLike(nome).whereTipo(tipo).whereCursoNaoTem(idCurso));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarProfessorPorId(Long id) {
        return (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereId(id).whereTipo(TipoUsuario.PROFESSOR));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void excluirProfessores(Long idProfessores) {
        List<Pergunta> perguntas = perguntaService.buscarPerguntaPorFiltro(idProfessores, null, null, null, null, null);
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(idProfessores, null, null, null, null, null);
        validador.validarExcluirUsuario(perguntas, cursos);
        Usuario professores = buscarProfessorPorId(idProfessores);
        dao.remove(professores);
    }
    
    @Override
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
