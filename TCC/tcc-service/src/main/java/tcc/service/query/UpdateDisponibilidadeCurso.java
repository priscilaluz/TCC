/*
 * 
 */
package tcc.service.query;

import tcc.common.enums.DisponibilidadeCurso;
import tcc.service.persistence.BusinessQuery;

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
