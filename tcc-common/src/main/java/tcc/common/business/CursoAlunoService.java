package tcc.common.business;

import java.util.List;
import tcc.common.entity.CursoAluno;
import tcc.common.entity.EtapaAluno;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.enums.SituacaoCursoAluno;
import tcc.common.vo.MeuAndamento;
import tcc.common.vo.TabuleiroCurso;

/**
 *
 * @author ADM
 */
public interface CursoAlunoService {

    CursoAluno entrarCurso(Long idCurso, Long idAluno, String codAcesso);

    List<CursoAluno> buscarCursoAlunoPorAlunoSituacao(Long idAluno, SituacaoCursoAluno situacao);

    Long buscarCountCursoAlunoPorProfessor(Long idProfessor);

    TabuleiroCurso buscarCursoAlunoPorIdCursoAluno(Long idCursoAluno);

    EtapaAluno buscarEtapaAlunoPorCursoAlunoEEtapa(Long idEtapaAluno, Long idCursoAluno, Long idEtapa);

    EtapaAluno salvarEtapaAluno(Long idCursoAluno, Long idEtapa);

    RelatorioEtapa salvarRelatorioEtapa(RelatorioEtapa relatorioEtapa);

    List<RelatorioEtapa> buscarRelatoriosEtapaPorIdEtapaAluno(Long idEtapaAluno);

    RelatorioEtapa buscarRelatoriosEtapaPorId(Long idRelatorioEtapa);

    List<CursoAluno> buscarCursoAlunoPorIdCurso(Long idCurso);

    List<MeuAndamento> buscarProprioAndamento(Long idAluno);

    boolean alunoPertenceCurso(Long idAluno, Long idCurso);
}
