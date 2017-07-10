/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.service.impl.CursoServiceImpl;
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
@Path("/curso")
@Component
public class CursoResource {
    
    @Autowired
    private CursoServiceImpl cursoService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Curso save(Curso curso) {
        return cursoService.salvarCurso(curso);
    }
    
    @POST
    @Path("/etapa/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Etapa save(Etapa etapa) {
        return cursoService.salvarEtapa(etapa);
    }
    
    @DELETE
    @Path("/etapa/deletar")
    public Response excluirEtapa(@QueryParam("idCurso") Long idCurso, @QueryParam("idEtapa") Long idEtapa) {
        cursoService.excluirEtapa(idCurso, idEtapa);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/etapa/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Etapa buscarEtapaPorId(@QueryParam("idCurso") Long idCurso, @QueryParam("nivel") Integer nivel) {
        List<Etapa> etapas = cursoService.buscarEtapa(idCurso, nivel);
        return etapas.get(0);
    }
    
    @DELETE
    @Path("/deletarCurso")
    public Response excluirCurso(@QueryParam("id") Long idCurso) {
        cursoService.excluirCurso(idCurso);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/buscarCurso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Curso> buscarCursoPorFiltro(@QueryParam("idUsuario") Long idUsuario, 
            @QueryParam("parteNome") String parteNome, @QueryParam("categoria") String idCategoria) {
        Categoria categoria = idCategoria!=null?Categoria.from(idCategoria):null;
        return cursoService.buscarCursoPorFiltro(idUsuario, parteNome, categoria);
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Curso buscarCursoPorId(@QueryParam("idCurso") Long idCurso) {
        return cursoService.buscarCursoPorId(idCurso);
    }
}
