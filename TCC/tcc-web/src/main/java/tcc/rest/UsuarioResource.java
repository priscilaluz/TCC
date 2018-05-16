/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Usuario;
import tcc.common.vo.DadoProfessor;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.UsuarioService;
import tcc.common.enums.TipoUsuario;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
@Path("/usuario")
@Component
public class UsuarioResource {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario save(Usuario usuario) {
        usuario.setTipo(usuario.getTipo()!=null?usuario.getTipo():TipoUsuario.ALUNO);
        return usuarioService.salvarUsuario(usuario);
    }
    
    @POST
    @Path("/saveProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario saveProfessor(Usuario usuario) {
        usuario.setTipo(TipoUsuario.PROFESSOR);
        return usuarioService.salvarUsuario(usuario);
    }
    
    @GET
    @Path("/buscarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarUsuarioPorLoginSenha(@QueryParam("login") String login, @QueryParam("senha") String senha) {
        return usuarioService.buscarUsuarioPorLoginSenha(login, senha);
    }
    
    @GET
    @Path("/buscarProfessores")
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginacao buscarProfessores(@QueryParam("nome") String nome, @QueryParam("paginaAtual") Integer paginaAtual) {
        return usuarioService.buscarUsuarios(nome, TipoUsuario.PROFESSOR, null, paginaAtual);
    }
    
    @GET
    @Path("/buscarAlunos")
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginacao buscarAlunos(@QueryParam("nome") String nome, @QueryParam("idCurso") Long idCurso, @QueryParam("paginaAtual") Integer paginaAtual) {
        return usuarioService.buscarUsuarios(nome, TipoUsuario.ALUNO, idCurso, paginaAtual);
    }
    
    @DELETE
    @Path("/deletarProfessor")
    public Response excluirProfessores(@QueryParam("idProfessores") Long idProfessores) {
        usuarioService.excluirProfessores(idProfessores);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/buscarProfessorPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario buscarProfessorPorId(@QueryParam("idProfessores") Long idProfessores) {
        return usuarioService.buscarProfessorPorId(idProfessores);
    }
    
    @GET
    @Path("/dadosProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public DadoProfessor dadosProfessor(@QueryParam("idProfessores") Long idProfessores) {
        return usuarioService.dadosProfessor(idProfessores);
    }
    
}
