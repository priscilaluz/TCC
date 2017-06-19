/*
 * 
 */
package br.com.tcc.service.query;

import br.com.tcc.service.persistence.BusinessQuery;

/**
 * ExcluirRespostaPorPergunta
 * @author Priscila
 */
public class ExcluirRespostaPorPergunta extends BusinessQuery {
    
    public ExcluirRespostaPorPergunta(Long idPergunta) {
        appendText(" delete from Resposta r where r.pergunta.id = :idPergunta ");
        addParameter("idPergunta", idPergunta);
    }
    
}
