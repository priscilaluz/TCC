/*
 * 
 */
package tcc.service.query;

import tcc.service.persistence.BusinessQuery;

/**
 * ContarAlunosPosicaoNoCurso
 *
 * @author Priscila
 */
public class ContarAlunosPosicaoNoCurso extends BusinessQuery {

    public ContarAlunosPosicaoNoCurso(Long idAluno, Long idCurso, Integer pontuacaoAluno, Integer posicaoAtualAluno) {
        appendText(" select count(ca) from CursoAluno ca");
        appendText(" where ca.aluno.id != :idAluno and ca.curso.id = :idCurso and  ");
        appendText(" ((ca.pontuacao > :pontuacaoAluno) or  ");
        appendText(" (ca.pontuacao = :pontuacaoAluno and ca.posicaoAtual < :posicaoAtualAluno)) ");
        addParameter("idAluno", idAluno);
        addParameter("idCurso", idCurso);
        addParameter("pontuacaoAluno", pontuacaoAluno);
        addParameter("posicaoAtualAluno", posicaoAtualAluno);

    }

}
