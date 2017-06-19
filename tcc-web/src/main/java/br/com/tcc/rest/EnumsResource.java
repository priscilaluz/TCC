/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.rest;

import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.Jogos;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADM
 */
@Path("/enums")
@Component
public class EnumsResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/jogos")
    public List<Jogos> findAllJogos() {
        return Arrays.asList(Jogos.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categorias")
    public List<Categoria> findAllCategorias() {
        return Arrays.asList(Categoria.values());
    }
}
