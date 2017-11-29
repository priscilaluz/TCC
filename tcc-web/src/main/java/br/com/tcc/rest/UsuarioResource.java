/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Usuario;
import br.com.tcc.service.impl.UsuarioServiceImpl;
import java.util.List;
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

/**
 *
 * @author ADM
 */
@Path("/usuario")
@Component
public class UsuarioResource {
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario save(Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }
    
    @POST
    @Path("/saveProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario saveProfessor(Usuario usuario) {
        return usuarioService.salvarProfessor(usuario);
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
    public List<Usuario> buscarProfessores(@QueryParam("nome") String nome) {
        return usuarioService.buscarProfessores(nome);
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
    
}
