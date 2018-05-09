/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Pergunta;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.TipoPergunta;
import tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarPergunta<T extends Serializable> extends BusinessFluentQuery<T, BuscarPergunta> {

    public static class Entities extends BuscarPergunta<Pergunta> {
        public Entities(boolean cont) {
            if (cont) {
                appendText("select count(p) from Pergunta p ");
            } else {
                appendText("select p from Pergunta p ");
            }
        }
    }
    
    public BuscarPergunta fetchResposta(String fetch) {
        appendText(" left join "+fetch+" p.respostas r ");
        return this;
    }
    
    public BuscarPergunta fetchAnexo(String fetch) {
        appendText(" left join "+fetch+" p.anexo a ");
        return this;
    }
    
    public BuscarPergunta fetchUsuario(String fetch) {
        appendText(" left join "+fetch+" p.usuario u ");
        return this;
    }
    
    public BuscarPergunta fetchCategoria(String fetch) {
        appendText(" left join "+fetch+" p.categoria ca ");
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
            appendText(" u.id = :idUsuario ");
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
    
    public BuscarPergunta whereCategoria(Long idCategoria) {
        if (idCategoria != null) {
            appendText(getPreffixFilter());
            appendText(" p.categoria.id = :idCategoria ");
            addParameter("idCategoria", idCategoria);
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
    
    public BuscarPergunta whereCursoNaoTem(Long idCurso) {
        if (idCurso != null) {
            appendText(getPreffixFilter());
            appendText(" p.id not in ");
            appendText(" (select pe.id from Pergunta pe ");
            appendText(" left join pe.etapasPerguntas ep ");
            appendText(" left join ep.etapa e ");
            appendText(" left join e.curso c ");
            appendText(" where c.id = :idCurso) ");
            addParameter("idCurso", idCurso);
        }    
        return this;
    }
}
