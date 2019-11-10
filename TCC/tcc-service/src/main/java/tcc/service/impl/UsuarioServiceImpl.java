/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.impl;

import java.util.Date;
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
import tcc.common.business.PremioService;
import tcc.common.business.UsuarioService;
import tcc.common.vo.ListaPaginacao;
import tcc.common.vo.Paginacao;
import tcc.service.persistence.Pagination;

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
    
    @Autowired
    private PremioService premioService;

    @Override
    @Transactional(readOnly = false)
    public Usuario salvarUsuario(Usuario usuario) {
        boolean novo = usuario.getId()==null;
        Long emailJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereEmail(usuario.getEmail()).whereIdNot(usuario.getId()));
        Long loginJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereLogin(usuario.getLogin()).whereIdNot(usuario.getId()));
        validador.validarSalvarUsuario(usuario, emailJaExistente, loginJaExistente);
        usuario.setDataCadastro(new Date());
        usuario.setDataUltimoAcesso(new Date());
        usuario = dao.saveOrUpdate(usuario);
        if (TipoUsuario.ALUNO.equals(usuario.getTipo()) && novo) {
            premioService.cadastrarPremio(usuario);
        }
        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorLoginSenha(String login, String senha) {
        if (login != null && senha != null) {
            Usuario usuario = (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereLogin(login).whereSenha(senha));
            if (usuario != null) {
                usuario.setDataUltimoAcesso(new Date());
                dao.saveOrUpdate(usuario);
            }
            return usuario;
        }
        return null;
    }
    
    @Override
    @Transactional(readOnly = true)
    public ListaPaginacao buscarUsuarios(String nome, TipoUsuario tipo, Long idCurso, Integer paginaAtual) {
        Long numDeItens = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereNomeLike(nome).whereTipo(tipo).whereCursoNaoTem(idCurso));
        BuscarUsuario query = new BuscarUsuario.Entities().whereNomeLike(nome).whereTipo(tipo).whereCursoNaoTem(idCurso);
        Pagination pagination = new Pagination(Paginacao.DEFAULT_QNT_POR_PAG, paginaAtual);
        query.setPagination(pagination);
        List usuarios = dao.list(query);
        return new ListaPaginacao(usuarios, new Paginacao(numDeItens, paginaAtual));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarProfessorPorId(Long id) {
        return (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereId(id).whereTipo(TipoUsuario.PROFESSOR));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void excluirProfessores(Long idProfessores) {
        Long qndPerguntas = perguntaService.buscarCountPerguntaPorFiltro(idProfessores, null);
        Long qndCursos = cursoService.buscarCountCursoPorFiltro(idProfessores, null, null);
        validador.validarExcluirUsuario(qndPerguntas, qndCursos);
        Usuario professores = buscarProfessorPorId(idProfessores);
        dao.remove(professores);
    }
    
    @Override
    @Transactional(readOnly = false)
    public DadoProfessor dadosProfessor(Long idProfessores) {
        DadoProfessor dado = new DadoProfessor();
        dado.setQntAluno(cursoAlunoService.buscarCountCursoAlunoPorProfessor(idProfessores));
        dado.setQntCursoRascunho(cursoService.buscarCountCursoPorFiltro(idProfessores, SituacaoCurso.RASCUNHO, null));
        dado.setQntCursoConcluidos(cursoService.buscarCountCursoPorFiltro(idProfessores, SituacaoCurso.CONCLUIDA, null));
        dado.setQntPerguntas(perguntaService.buscarCountPerguntaPorFiltro(idProfessores, null));
        return dado;
    }
}
