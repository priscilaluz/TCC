/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.entity.Categoria;
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
import tcc.common.business.CategoriaService;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
@Path("/categoria")
@Component
public class CategoriaResource {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Categoria save(Categoria categoria) {
        return categoriaService.salvarCategoria(categoria);
    }
    
    @DELETE
    @Path("/deletar")
    public Response excluirCategoria(@QueryParam("idCategoria") Long idCategoria) {
        categoriaService.excluirCategoria(idCategoria);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/buscarPorId")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria buscarCategoriaPorId(@QueryParam("idCategoria") Long idCategoria) {
        return categoriaService.buscarCategoriaPorId(idCategoria);
    }
    
    @GET
    @Path("/buscarPorFiltro")
    @Produces(MediaType.APPLICATION_JSON)
    public ListaPaginacao buscarCategoriaPorFiltro(@QueryParam("parteNome") String parteNome, @QueryParam("paginaAtual") Integer paginaAtual) {
        return categoriaService.buscarCategoriaPorFiltro(parteNome, paginaAtual);
    }
}
