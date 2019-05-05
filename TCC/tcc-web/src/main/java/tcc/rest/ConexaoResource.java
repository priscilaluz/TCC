/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Conexao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcc.common.business.ConexaoService;

/**
 *
 * @author ADM
 */
@Path("/conexao")
@Component
public class ConexaoResource {
    
    @Autowired
    private ConexaoService conexaoService;
    
    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Conexao buscarConexao() {
        try {
            return conexaoService.buscar();
        } catch (Exception e) {
            Conexao c = new Conexao();
            c.setNome(e.getMessage());
            return c;
        }
        
    }
}
