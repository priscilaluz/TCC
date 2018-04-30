/*
 * 
 */
package tcc.service.query;

import tcc.service.persistence.BusinessQuery;

/**
 * ExcluirRespostaPorPergunta
 * @author Priscila
 */
public class ExcluirEtapaPorCurso extends BusinessQuery {
    
    public ExcluirEtapaPorCurso(Long idCurso) {
        appendText(" delete from Etapa e where e.curso.id = :idCurso ");
        addParameter("idCurso", idCurso);
    }
    
}
