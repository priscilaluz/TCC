/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarCurso<T extends Serializable> extends BusinessFluentQuery<T, BuscarCurso> {

    public static class Entities extends BuscarCurso<Curso> {
        public Entities() {
            appendText("select c from Curso c ");
        }
    }
    
    public static class Count extends BuscarCurso<Long> {
        public Count() {
            appendText("select count(c) from Curso c ");
        }
    }
    
    public BuscarCurso fetchEtapas(String fetch) {
        appendText(" left join "+fetch+" c.etapas e ");
        return this;
    }
    
    public BuscarCurso fetchEtapasPerguntas(String fetch) {
        appendText(" left join "+fetch+" e.etapasPerguntas ep ");
        return this;
    }
    
    public BuscarCurso fetchPergunta(String fetch) {
        appendText(" left join "+fetch+" ep.pergunta p ");
        return this;
    }
    
    public BuscarCurso fetchResposta(String fetch) {
        appendText(" left join "+fetch+" p.respostas r ");
        return this;
    }
    
    public BuscarCurso fetchAnexo(String fetch) {
        appendText(" left join "+fetch+" c.anexo a ");
        return this;
    }
    
    public BuscarCurso fetchAnexoEtapa(String fetch) {
        appendText(" left join "+fetch+" e.anexo a ");
        return this;
    }
    
    public BuscarCurso fetchUsuario(String fetch) {
        appendText(" left join "+fetch+" c.usuario u ");
        return this;
    }

    public BuscarCurso whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" c.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarCurso whereUsuario(Long idUsuario) {
        if (idUsuario != null) {
            appendText(getPreffixFilter());
            appendText(" c.usuario.id = :idUsuario ");
            addParameter("idUsuario", idUsuario);
        }    
        return this;
    }
    
    public BuscarCurso whereNomeLike(String nome) {
        if (nome != null) {
            appendText(getPreffixFilter());
            appendText(" upper(c.nome) like upper(:nome) ");
            addParameter("nome", "%"+nome+"%");
        }    
        return this;
    }
    
    public BuscarCurso whereCategoria(Categoria categoria) {
        if (categoria != null) {
            appendText(getPreffixFilter());
            appendText(" c.categoria = :categoria ");
            addParameter("categoria", categoria);
        }    
        return this;
    }
    
    public BuscarCurso whereSituacaoCurso(SituacaoCurso situacao) {
        if (situacao != null) {
            appendText(getPreffixFilter());
            appendText(" c.situacao = :situacao ");
            addParameter("situacao", situacao);
        }    
        return this;
    }
    
    public BuscarCurso orderByNivel() { 
        appendText(" order by e.nivel ");
        return this;
    }
}
