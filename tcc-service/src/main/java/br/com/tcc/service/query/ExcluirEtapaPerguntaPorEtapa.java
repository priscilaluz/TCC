/*
 * 
 */
package br.com.tcc.service.query;

import br.com.tcc.service.persistence.BusinessQuery;

/**
 * ExcluirEtapaPerguntaPorEtapa
 * @author Priscila
 */
public class ExcluirEtapaPerguntaPorEtapa extends BusinessQuery {
    
    public ExcluirEtapaPerguntaPorEtapa(Long idEtapa) {
        appendText(" delete from EtapaPergunta ep where ep.etapa.id = :idEtapa");
        addParameter("idEtapa", idEtapa);
    }
    
}