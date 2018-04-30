/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Anexo;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarAnexo<T extends Serializable> extends BusinessFluentQuery<T, BuscarAnexo> {

    public static class Entities extends BuscarAnexo<Anexo> {
        public Entities() {
            appendText("select a from Anexo a ");
        }
    }

    public BuscarAnexo whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" a.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
}
