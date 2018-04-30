package tcc.common.business;

import java.util.List;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.enums.DisponibilidadeCurso;
import tcc.common.enums.SituacaoCurso;

/**
 *
 * @author ADM
 */
public interface CursoService {
    
    Curso salvarCurso(Curso curso);
    
    void excluirCurso(Long idCurso);
    
    void updateDisponibilidadeCurso(Long idCurso, DisponibilidadeCurso disponibilidade);
    
    Curso buscarCursoPorId(Long idCurso);
    
    Curso buscarCursoPorIdConcluido(Long idCurso);
    
    List<Curso> buscarCursoPorFiltro(Long idUsuario, String parteNome, Long idCategoria, SituacaoCurso situacaoCurso, DisponibilidadeCurso disponibilidade, Long idAluno);
    
    Long buscarCountCursoPorFiltro(Long idUsuario, SituacaoCurso situacaoCurso);
    
    Etapa salvarEtapa(Etapa etapa);
    
    void excluirEtapa(Long idCurso, Long idEtapa);
    
    List<Etapa> buscarEtapa(Long idCurso, Integer nivel);
    
    Etapa buscarEtapaPorId(Long idEtapa, boolean resposta);
    
}