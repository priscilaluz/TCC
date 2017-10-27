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
            appendText("select r from RelatorioEtapa r ");
        }
    }
    
    public BuscarRelatorioEtapa fetchPerguntaEtapaAluno(String fetch) {
        appendText(" left join "+fetch+" r.perguntasEtapasAlunos p ");
        return this;
    }
    
    public BuscarRelatorioEtapa whereIdEtapaAluno(Long idEtapaAluno) {
        if (idEtapaAluno != null) {
            appendText(getPreffixFilter());
            appendText(" r.etapaAluno.id = :idEtapaAluno ");
            addParameter("idEtapaAluno", idEtapaAluno);
        }    
        return this;
    }
}
