/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Anexo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.AnexoService;

/**
 *
 * @author ADM
 */
@Path("/anexo")
@Component
public class AnexoResource {
    
    @Autowired
    private AnexoService anexoService;

    @POST
    @Path("/buscarAnexo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public Anexo buscarAnexo(
            @FormDataParam("nomeArquivo") String nomeArquivo,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader,
            @Context HttpServletRequest httpServletRequest) {
        Anexo anexo = null;
        try {
            anexo = new Anexo();
            anexo.setNomeArquivo(nomeArquivo);
            anexo.setArquivo(uploadedInputStream);
            anexo.setBytes(IOUtils.toByteArray(uploadedInputStream));
        } catch (IOException e) {
            String a = e.getMessage();
        }

        return anexo;
    }
    
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getAnexo(@QueryParam("idAnexo") Long idAnexo, @QueryParam("arquivo") String arquivo) throws FileNotFoundException {
        try {
            InputStream anexo = anexoService.obterBytesAnexo(idAnexo);
            return Response.ok(anexo, MediaType.APPLICATION_OCTET_STREAM_TYPE).
                    header("Content-Disposition", "attachment; filename=\"" + arquivo + "\"").build();
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_HTML).build();
        }
    }
}
