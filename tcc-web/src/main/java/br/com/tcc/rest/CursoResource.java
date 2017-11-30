/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.enums.SituacaoCurso;
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
        etapa.setCurso(new Curso(etapa.getIdCurso()));
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
        if (!etapas.isEmpty()) {
            return etapas.get(0);
        }
        return null;
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
            @QueryParam("parteNome") String parteNome, @QueryParam("categoria") Long idCategoria,
            @QueryParam("situacao") String idSituacao) {
        SituacaoCurso situacaoCurso = idSituacao!=null?SituacaoCurso.from(idSituacao):null;
        return cursoService.buscarCursoPorFiltro(idUsuario, parteNome, idCategoria, situacaoCurso);
    }
    
    @GET
    @Path("/buscarCursosAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Curso> buscarCursoPorFiltro(@QueryParam("parteNome") String parteNome, 
            @QueryParam("categoria") Long idCategoria) {
        return cursoService.buscarCursoPorFiltro(null, parteNome, idCategoria, SituacaoCurso.CONCLUIDA);
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Curso buscarCursoPorId(@QueryParam("idCurso") Long idCurso) {
        return cursoService.buscarCursoPorId(idCurso);
    }
    
    @GET
    @Path("/buscarCompletoPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Curso buscarCursoCompletoPorId(@QueryParam("idCurso") Long idCurso) {
        return cursoService.buscarCursoPorIdConcluido(idCurso);
    }
}
