/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.CursoAluno;
import tcc.common.entity.EtapaAluno;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.enums.SituacaoCursoAluno;
import tcc.common.vo.MeuAndamento;
import tcc.common.vo.TabuleiroCurso;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.CursoAlunoService;

/**
 *
 * @author ADM
 */
@Path("/curso-aluno")
@Component
public class CursoAlunoResource {
    
    @Autowired
    private CursoAlunoService cursoAlunoService;
    
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
        return cursoAlunoService.buscarEtapaAlunoPorCursoAlunoEEtapa(null, idCursoAluno, idEtapa);
    }
    
    @GET
    @Path("/salvarEtapaAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public EtapaAluno salvarEtapaAluno(@QueryParam("idCursoAluno") Long idCursoAluno, @QueryParam("idEtapa") Long idEtapa) {
        return cursoAlunoService.salvarEtapaAluno(idCursoAluno, idEtapa);
    }
    
    @POST
    @Path("/relatorioEtapa/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RelatorioEtapa salvarRelatorioEtapa(RelatorioEtapa relatorioEtapa) {
        EtapaAluno etapaAluno = cursoAlunoService.buscarEtapaAlunoPorCursoAlunoEEtapa(relatorioEtapa.getEtapaAluno().getId(), null, null);
        relatorioEtapa.setEtapaAluno(etapaAluno);
        return cursoAlunoService.salvarRelatorioEtapa(relatorioEtapa);
    }
    
    @GET
    @Path("/relatoriosPorIdEtapaAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelatorioEtapa> buscarRelatoriosEtapaPorIdEtapaAluno(@QueryParam("idEtapaAluno") Long idEtapaAluno) {
        return cursoAlunoService.buscarRelatoriosEtapaPorIdEtapaAluno(idEtapaAluno);
    }
    
    @GET
    @Path("/relatoriosPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public RelatorioEtapa buscarRelatoriosEtapaPorId(@QueryParam("idRelatorio") Long idRelatorio) {
        return cursoAlunoService.buscarRelatoriosEtapaPorId(idRelatorio);
    }
    
    @GET
    @Path("/alunosPorIdCurso")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CursoAluno> buscarCursoAlunoPorIdCurso(@QueryParam("idCurso") Long idCurso) {
        return cursoAlunoService.buscarCursoAlunoPorIdCurso(idCurso);
    }
    
    @GET
    @Path("/proprioAndamento")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MeuAndamento> buscarProprioAndamento(@QueryParam("idAluno") Long idAluno) {
        return cursoAlunoService.buscarProprioAndamento(idAluno);
    }
}
