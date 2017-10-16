/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarEtapaAluno<T extends Serializable> extends BusinessFluentQuery<T, BuscarEtapaAluno> {

    public static class Entities extends BuscarEtapaAluno<EtapaAluno> {
        public Entities() {
            appendText("select ea from EtapaAluno ea ");
        }
    }
    
    public BuscarEtapaAluno fetchCursoAluno(String fetch) {
        appendText(" left join "+fetch+" ea.cursoAluno c ");
        return this;
    }
    
    public BuscarEtapaAluno fetchEtapa(String fetch) {
        appendText(" left join "+fetch+" ea.etapa e ");
        return this;
    }
    
    public BuscarEtapaAluno whereIdCursoAluno(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id = :idCursoAluno ");
            addParameter("idCursoAluno", id);
        }    
        return this;
    }
    
    public BuscarEtapaAluno whereIdEtapa(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" e.id = :idEtapa ");
            addParameter("idEtapa", id);
        }    
        return this;
    }

}
