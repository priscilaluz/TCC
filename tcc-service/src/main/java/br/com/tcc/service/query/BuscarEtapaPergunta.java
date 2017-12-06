/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarEtapaPergunta<T extends Serializable> extends BusinessFluentQuery<T, BuscarEtapaPergunta> {

    public static class Entities extends BuscarEtapaPergunta<EtapaPergunta> {
        public Entities() {
            appendText("select ep from EtapaPergunta ep ");
        }
    }
    
    public static class Count extends BuscarEtapaPergunta<Long> {
        public Count() {
            appendText("select count(ep) from EtapaPergunta ep ");
        }
    }
    
    public BuscarEtapaPergunta fetchPergunta(String fetch) {
        appendText(" left join "+fetch+" ep.pergunta p ");
        return this;
    }

    public BuscarEtapaPergunta wherePergunta(Long idPergunta) {
        if (idPergunta != null) {
            appendText(getPreffixFilter());
            appendText(" p.id = :idPergunta ");
            addParameter("idPergunta", idPergunta);
        }    
        return this;
    }
    
    public BuscarEtapaPergunta orderByNivel() { 
        appendText(" order by e.nivel ");
        return this;
    }
}
