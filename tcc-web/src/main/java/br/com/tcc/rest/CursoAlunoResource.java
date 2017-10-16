/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.common.enums.SituacaoCursoAluno;
import br.com.tcc.common.vo.TabuleiroCurso;
import br.com.tcc.service.impl.CursoAlunoServiceImpl;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/curso-aluno")
@Component
public class CursoAlunoResource {
    
    @Autowired
    private CursoAlunoServiceImpl cursoAlunoService;
    
    @GET
    @Path("/entrarCurso")
    @Produces(MediaType.APPLICATION_JSON)
    public CursoAluno entrarCurso(@QueryParam("idCurso") Long idCurso, @QueryParam("idAluno") Long idAluno,
            @QueryParam("codAcesso") String codAcesso) {
        return cursoAlunoService.entrarCurso(idCurso, idAluno, codAcesso);
    }
    
    @GET
    @Path("/buscarCursoAlunoPorAlunoSituacao")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CursoAluno> buscarCursoAlunoPorAlunoSituacao(@QueryParam("idAluno") Long idAluno,
            @QueryParam("idSituacao") String idSituacao) {
        return cursoAlunoService.buscarCursoAlunoPorAlunoSituacao(idAluno, SituacaoCursoAluno.from(idSituacao));
    }
    
    @GET
    @Path("/buscarCursoAlunoPorIdCursoAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public TabuleiroCurso buscarCursoAlunoPorIdCursoAluno(@QueryParam("idCursoAluno") Long idCursoAluno) {
        return cursoAlunoService.buscarCursoAlunoPorIdCursoAluno(idCursoAluno);
    }
    
    @GET
    @Path("/buscarEtapaAlunoPorCursoAlunoEEtapa")
    @Produces(MediaType.APPLICATION_JSON)
    public EtapaAluno buscarEtapaAlunoPorCursoAlunoEEtapa(@QueryParam("idCursoAluno") Long idCursoAluno,
            @QueryParam("idEtapa") Long idEtapa) {
        return cursoAlunoService.buscarEtapaAlunoPorCursoAlunoEEtapa(idCursoAluno, idEtapa);
    }
}
