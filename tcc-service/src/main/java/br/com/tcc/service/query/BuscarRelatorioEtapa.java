/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.RelatorioEtapa;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarRelatorioEtapa<T extends Serializable> extends BusinessFluentQuery<T, BuscarRelatorioEtapa> {

    public static class Entities extends BuscarRelatorioEtapa<RelatorioEtapa> {
        public Entities() {
            appendText("select re from RelatorioEtapa re ");
        }
    }
    
    public BuscarRelatorioEtapa fetchPerguntaEtapaAluno(String fetch) {
        appendText(" left join "+fetch+" re.perguntasEtapasAlunos pea ");
        return this;
    }
    
    public BuscarRelatorioEtapa fetchPergunta(String fetch) {
        appendText(" left join "+fetch+" pea.pergunta p ");
        return this;
    }
    
    public BuscarRelatorioEtapa fetchResposta(String fetch) {
        appendText(" left join "+fetch+" p.respostas r ");
        return this;
    }
    
    public BuscarRelatorioEtapa whereIdRelatorioEtapa(Long idRelatorioEtapa) {
        if (idRelatorioEtapa != null) {
            appendText(getPreffixFilter());
            appendText(" re.id = :idRelatorioEtapa ");
            addParameter("idRelatorioEtapa", idRelatorioEtapa);
        }    
        return this;
    }
    
    public BuscarRelatorioEtapa whereIdEtapaAluno(Long idEtapaAluno) {
        if (idEtapaAluno != null) {
            appendText(getPreffixFilter());
            appendText(" re.etapaAluno.id = :idEtapaAluno ");
            addParameter("idEtapaAluno", idEtapaAluno);
        }    
        return this;
    }
    
    public BuscarRelatorioEtapa orderById() { 
        appendText(" order by re.id desc ");
        return this;
    }
}
