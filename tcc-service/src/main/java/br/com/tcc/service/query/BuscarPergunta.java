/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarPergunta<T extends Serializable> extends BusinessFluentQuery<T, BuscarPergunta> {

    public static class Entities extends BuscarPergunta<Pergunta> {
        public Entities() {
            appendText("select p from Pergunta p ");
        }
    }
    
    public static class Count extends BuscarPergunta<Long> {
        public Count() {
            appendText("select count(p) from Pergunta p ");
        }
    }
    
    public BuscarPergunta fetchResposta(String fetch) {
        appendText(" left join "+fetch+" p.respostas r ");
        return this;
    }

    public BuscarPergunta whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" p.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarPergunta whereUsuario(Long idUsuario) {
        if (idUsuario != null) {
            appendText(getPreffixFilter());
            appendText(" p.usuario.id = :idUsuario ");
            addParameter("idUsuario", idUsuario);
        }    
        return this;
    }
    
    public BuscarPergunta whereDescricaoLike(String descricao) {
        if (descricao != null) {
            appendText(getPreffixFilter());
            appendText(" upper(p.descricao) like upper(:descricao) ");
            addParameter("descricao", "%"+descricao+"%");
        }    
        return this;
    }
    
    public BuscarPergunta whereCategoria(Categoria categoria) {
        if (categoria != null) {
            appendText(getPreffixFilter());
            appendText(" p.categoria = :categoria ");
            addParameter("categoria", categoria);
        }    
        return this;
    }
    
    public BuscarPergunta whereTipo(TipoPergunta tipo) {
        if (tipo != null) {
            appendText(getPreffixFilter());
            appendText(" p.tipo = :tipo ");
            addParameter("tipo", tipo);
        }    
        return this;
    }
    
    public BuscarPergunta whereNivel(NivelPergunta nivel) {
        if (nivel != null) {
            appendText(getPreffixFilter());
            appendText(" p.nivel = :nivel ");
            addParameter("nivel", nivel);
        }    
        return this;
    }
}
