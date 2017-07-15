/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.service.impl.PerguntaServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
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
            @QueryParam("parteNome") String parteNome, @QueryParam("categoria") String idCategoria, 
            @QueryParam("nivel") String idNivel, @QueryParam("tipo") String idTipo) {
        Categoria categoria = idCategoria!=null?Categoria.from(idCategoria):null;
        NivelPergunta nivel = idNivel!=null?NivelPergunta.from(idNivel):null;
        TipoPergunta tipo = idTipo!=null?TipoPergunta.from(idTipo):null;
        return perguntaService.buscarPerguntaPorFiltro(idUsuario, parteNome, categoria, tipo, nivel);
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Pergunta buscarPerguntaPorId(@QueryParam("idPergunta") Long idPergunta) {
        return perguntaService.buscarPerguntaPorId(idPergunta);
    }

}
