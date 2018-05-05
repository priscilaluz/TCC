/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.enums.DisponibilidadeCurso;
import tcc.common.enums.SituacaoCurso;
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
import tcc.common.business.CursoService;
import tcc.common.entity.Aviso;

/**
 *
 * @author ADM
 */
@Path("/curso")
@Component
public class CursoResource {
    
    @Autowired
    private CursoService cursoService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Curso save(Curso curso) {
        return cursoService.salvarCurso(curso);
    }
    
    @GET
    @Path("/copiarCurso")
    @Produces(MediaType.APPLICATION_JSON)
    public Curso copiarCurso(@QueryParam("nomeCurso") String nomeCurso, @QueryParam("idCurso") Long idCurso, @QueryParam("idUsuario") Long idUsuario) {
        return cursoService.copiarCurso(nomeCurso, idCurso, idUsuario);
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
        return cursoService.buscarCursoPorFiltro(idUsuario, parteNome, idCategoria, situacaoCurso, null, null);
    }
    
    @GET
    @Path("/buscarCursosAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Curso> buscarCursoPorFiltro(@QueryParam("idAluno") Long idAluno, @QueryParam("parteNome") String parteNome, 
            @QueryParam("categoria") Long idCategoria) {
        return cursoService.buscarCursoPorFiltro(null, parteNome, idCategoria, SituacaoCurso.CONCLUIDA, DisponibilidadeCurso.ABERTO, idAluno);
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
    
    @GET
    @Path("/updateDisponibilidade")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateDisponibilidadeCurso(@QueryParam("idCurso") Long idCurso, @QueryParam("idDisponibilidade") String idDisponibilidade) {
        if (idDisponibilidade != null) {
            cursoService.updateDisponibilidadeCurso(idCurso, DisponibilidadeCurso.from(idDisponibilidade));
        }
        return true;
    }
    
    @GET
    @Path("/addAlunosAoCurso")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addAlunosAoCurso(@QueryParam("idCurso") Long idCurso, @QueryParam("idAlunos") String idAlunos) {
        List<Long> idsAluno = new ArrayList<>();
        List<String> idsAlunoString = Arrays.asList(idAlunos.split(","));
        for (String string : idsAlunoString) {
            idsAluno.add(Long.valueOf(string));
        }
        return cursoService.addAlunosAoCurso(idCurso, idsAluno);
    }
    
    @POST
    @Path("/aviso/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Aviso save(Aviso aviso) {
        aviso.setDataModificao(new Date());
        aviso.setCurso(new Curso(aviso.getIdCurso()));
        return cursoService.salvarAviso(aviso);
    }
    
    @DELETE
    @Path("/aviso/deletar")
    public Response excluirAviso(@QueryParam("idAviso") Long idAviso) {
        cursoService.excluirAviso(idAviso);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/aviso/buscarById")
    @Produces(MediaType.APPLICATION_JSON)
    public Aviso buscarAvisoPorId(@QueryParam("idAviso") Long idAviso) {
        return cursoService.buscarAvisosPorId(idAviso);
    }
    
    @GET
    @Path("/aviso/buscarTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Aviso> buscarTodosAvisosPorCurso(@QueryParam("idCurso") Long idCurso) {
        return cursoService.buscarAvisosPorCurso(idCurso);
    }
}
