/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Categoria;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarCategoria<T extends Serializable> extends BusinessFluentQuery<T, BuscarCategoria> {

    public static class Entities extends BuscarCategoria<Categoria> {
        public Entities() {
            appendText("select c from Categoria c ");
        }
    }
    
    public static class Count extends BuscarCategoria<Long> {
        public Count() {
            appendText("select count(c) from Categoria c ");
        }
    }
    
    public BuscarCategoria whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarCategoria whereIdNot(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id != :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarCategoria whereNomeLike(String nome) {
        if (nome != null) {
            appendText(getPreffixFilter());
            appendText(" upper(c.nome) like upper(:nome) ");
            addParameter("nome", "%"+nome+"%");
        }    
        return this;
    }
    
    public BuscarCategoria whereNome(String nome) {
        if (nome != null) {
            appendText(getPreffixFilter());
            appendText(" upper(c.nome) = upper(:nome) ");
            addParameter("nome", nome);
        }    
        return this;
    }
    
    public BuscarCategoria orderByNome() { 
        appendText(" order by c.nome ");
        return this;
    }
}
