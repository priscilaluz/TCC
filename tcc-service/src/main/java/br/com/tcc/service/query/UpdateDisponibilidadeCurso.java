/*
 * 
 */
package br.com.tcc.service.query;

import br.com.tcc.common.enums.DisponibilidadeCurso;
import br.com.tcc.service.persistence.BusinessQuery;

/**
 * ExcluirEtapaPerguntaPorCurso
 *
 * @author Priscila
 */
public class UpdateDisponibilidadeCurso extends BusinessQuery {

    public UpdateDisponibilidadeCurso(Long idCurso, DisponibilidadeCurso disponibilidade) {
        appendText(" update Curso c ");
        appendText(" set disponibilidade = :disponibilidade ");
        appendText(" where c.id = :idCurso ");
        addParameter("idCurso", idCurso);
        addParameter("disponibilidade", disponibilidade);

    }

}
