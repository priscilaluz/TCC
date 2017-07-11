/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.Etapa;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarEtapa<T extends Serializable> extends BusinessFluentQuery<T, BuscarEtapa> {

    public static class Entities extends BuscarEtapa<Etapa> {
        public Entities() {
            appendText("select e from Etapa e ");
        }
    }
    
    public BuscarEtapa fetchEtapaPergunta(String fetch) {
        appendText(" left join "+fetch+" e.etapasPerguntas ep ");
        return this;
    }
    
    public BuscarEtapa fetchPergunta(String fetch) {
        appendText(" left join "+fetch+" ep.pergunta p ");
        return this;
    }

    public BuscarEtapa whereIdCurso(Long idCurso) {
        if (idCurso != null) {
            appendText(getPreffixFilter());
            appendText(" e.curso.id = :idCurso ");
            addParameter("idCurso", idCurso);
        }    
        return this;
    }

    public BuscarEtapa whereNivel(Integer nivel) {
        if (nivel != null) {
            appendText(getPreffixFilter());
            appendText(" e.nivel = :nivel ");
            addParameter("nivel", nivel);
        }    
        return this;
    }
    
    public BuscarEtapa orderByNivel() { 
        appendText(" order by e.nivel ");
        return this;
    }
}