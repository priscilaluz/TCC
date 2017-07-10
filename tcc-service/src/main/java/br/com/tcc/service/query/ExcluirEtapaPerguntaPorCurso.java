/*
 * 
 */
package br.com.tcc.service.query;

import br.com.tcc.service.persistence.BusinessQuery;

/**
 * ExcluirEtapaPerguntaPorCurso
 *
 * @author Priscila
 */
public class ExcluirEtapaPerguntaPorCurso extends BusinessQuery {

    public ExcluirEtapaPerguntaPorCurso(Long idCurso) {
        appendText(" delete from EtapaPergunta ep where ep.etapa.id in ");
        appendText(" (select e.id from Etapa e where e.curso.id = :idCurso) ");
        addParameter("idCurso", idCurso);

    }

}
