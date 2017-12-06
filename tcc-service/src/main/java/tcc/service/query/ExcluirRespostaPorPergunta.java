/*
 * 
 */
package tcc.service.query;

import tcc.service.persistence.BusinessQuery;

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
