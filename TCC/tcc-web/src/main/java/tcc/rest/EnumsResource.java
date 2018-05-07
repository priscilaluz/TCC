/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.rest;

import tcc.common.enums.Avatar;
import tcc.common.enums.Jogo;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.SituacaoCurso;
import tcc.common.enums.TipoPergunta;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;
import tcc.common.enums.TempoPergunta;

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
    public List<Jogo> findAllJogos() {
        return Arrays.asList(Jogo.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/situacaoCurso")
    public List<SituacaoCurso> findAllSituacoesCurso() {
        return Arrays.asList(SituacaoCurso.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nivelPergunta")
    public List<NivelPergunta> findAllNivelPergunta() {
        return Arrays.asList(NivelPergunta.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoPergunta")
    public List<TipoPergunta> findAllTipoPergunta() {
        return Arrays.asList(TipoPergunta.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tempoPergunta")
    public List<TempoPergunta> findAllTempoPergunta() {
        return Arrays.asList(TempoPergunta.values());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoPerguntaPorJogo")
    public TipoPergunta findTipoPerguntaPorJogo(@QueryParam("idJogo") String idJogo) {
        if (Jogo.CACA_PALAVRA.getId().equals(idJogo) || Jogo.FORCA.getId().equals(idJogo)) {
            return TipoPergunta.COMPLETAR_LACUNA;
        }
        return TipoPergunta.MULTIPLAS_ESCOLHAS;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/avatar")
    public List<Avatar> findAllAvatar() {
        return Arrays.asList(Avatar.values());
    }
}
