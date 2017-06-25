/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.service.impl.PerguntaServiceImpl;
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
@Path("/pergunta")
@Component
public class PerguntaResource {
    
    @Autowired
    private PerguntaServiceImpl perguntaService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Pergunta save(Pergunta pergunta) {
        return perguntaService.salvarPergunta(pergunta);
    }
    
    @DELETE
    @Path("/deletarPergunta")
    public Response excluirPergunta(@QueryParam("id") Long idPergunta) {
        perguntaService.excluirPergunta(idPergunta);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/buscarPergunta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pergunta> buscarPerguntaPorFiltro(@QueryParam("idUsuario") Long idUsuario, 
            @QueryParam("parteNome") String parteNome, @QueryParam("categoria") String idCategoria) {
        Categoria categoria = idCategoria!=null?Categoria.from(idCategoria):null;
        return perguntaService.buscarPerguntaPorFiltro(idUsuario, parteNome, categoria);
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Pergunta buscarPerguntaPorId(@QueryParam("idPergunta") Long idPergunta) {
        return perguntaService.buscarPerguntaPorId(idPergunta);
    }
//    
//    @POST
//    @Path("/buscarAnexo")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces("application/json")
//    public PerguntaAnexo postUnidadeSegurancaAnexo(
//            @FormDataParam("idPergunta") String idPergunta,
//            @FormDataParam("file") InputStream uploadedInputStream,
//            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
//            @Context HttpServletRequest httpServletRequest) {
//        PerguntaAnexo perguntaAnexo = new PerguntaAnexo();
//        try {
//            Pergunta pergunta = new Pergunta();
//            pergunta.setId(Long.valueOf(idPergunta));
//            perguntaAnexo.setPergunta(pergunta);
//            perguntaAnexo.setBytes(uploadedInputStream);
//            //liresService.salvarPerguntaAnexo(unidadeAnexo);
//        } catch (Exception e) {
//            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
//        }
//
//        return perguntaAnexo;
//    }
}
