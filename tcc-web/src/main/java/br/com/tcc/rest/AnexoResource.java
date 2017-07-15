/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.service.impl.AnexoServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/anexo")
@Component
public class AnexoResource {
//    
//    @Autowired
//    private AnexoServiceImpl anexoService;

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
}
