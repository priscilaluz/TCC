/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Premio;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarPremio<T extends Serializable> extends BusinessFluentQuery<T, BuscarPremio> {

    public static class Entities extends BuscarPremio<Premio> {
        public Entities() {
            appendText("select p from Premio p ");
        }
    }
    
    public BuscarPremio whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" p.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarPremio whereIdUsuario(Long idUsuario) {
        if (idUsuario != null) {
            appendText(getPreffixFilter());
            appendText(" p.usuario.id = :idUsuario ");
            addParameter("idUsuario", idUsuario);
        }    
        return this;
    }
}
