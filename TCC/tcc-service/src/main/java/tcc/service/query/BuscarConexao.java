/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Conexao;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarConexao<T extends Serializable> extends BusinessFluentQuery<T, BuscarConexao> {

    public static class Entities extends BuscarConexao<Conexao> {
        public Entities() {
            appendText("select c from Conexao c ");
        }
    }

    public BuscarConexao whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
}
