/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Premio;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.PremioService;

/**
 *
 * @author ADM
 */
@Path("/premio")
@Component
public class PremioResource {
    
    @Autowired
    private PremioService premioService;
    
    @GET
    @Path("/buscarPorIdAluno")
    @Produces(MediaType.APPLICATION_JSON)
    public Premio buscarPremioPorIdAluno(@QueryParam("idAluno") Long idAluno) {
        return premioService.buscarPremioPorFiltro(idAluno);
    }
}
