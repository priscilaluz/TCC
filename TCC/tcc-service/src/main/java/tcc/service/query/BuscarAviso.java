/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Aviso;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarAviso<T extends Serializable> extends BusinessFluentQuery<T, BuscarAviso> {

    public static class Entities extends BuscarAviso<Aviso> {
        public Entities() {
            appendText("select DISTINCT a from Aviso a ");
        }
    }
    
    public BuscarAviso whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" a.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarAviso whereIdCurso(Long idCurso) {
        if (idCurso != null) {
            appendText(getPreffixFilter());
            appendText(" a.curso.id = :idCurso ");
            addParameter("idCurso", idCurso);
        }    
        return this;
    }
    
    public BuscarAviso orderByNivel() { 
        appendText(" order by a.dataModificao ");
        return this;
    }
}
