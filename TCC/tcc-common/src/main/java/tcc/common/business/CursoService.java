package tcc.common.business;

import java.util.List;
import tcc.common.entity.Aviso;
import tcc.common.entity.Categoria;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.enums.DisponibilidadeCurso;
import tcc.common.enums.SituacaoCurso;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
public interface CursoService {
    
    Curso salvarCurso(Curso curso);
    
    Curso copiarCurso(String nomeCurso, Long idCurso, Long idUsuario);
    
    void excluirCurso(Long idCurso);
    
    void updateDisponibilidadeCurso(Long idCurso, DisponibilidadeCurso disponibilidade);
    
    Curso buscarCursoPorId(Long idCurso);
    
    Curso buscarCursoPorIdConcluido(Long idCurso);
    
    List<Curso> buscarCursoPorIdProfessor(Long idProfessor);
    
    ListaPaginacao buscarCursoPorFiltro(Long idUsuario, String parteNome, Long idCategoria, SituacaoCurso situacaoCurso, 
            DisponibilidadeCurso disponibilidade, Long idAluno, Integer paginaAtual);
    
    Long buscarCountCursoPorFiltro(Long idUsuario, SituacaoCurso situacaoCurso, Long idCategoria);
    
    Etapa salvarEtapa(Etapa etapa);
    
    void excluirEtapa(Long idCurso, Long idEtapa);
    
    List<Etapa> buscarEtapa(Long idCurso, Integer nivel);
    
    Etapa buscarEtapaPorId(Long idEtapa, boolean resposta);
    
    boolean addAlunosAoCurso(Long idCurso, List<Long> idsAluno);
    
    Aviso salvarAviso(Aviso aviso);
    
    void excluirAviso(Long idAviso);
    
    Aviso buscarAvisosPorId(Long id);
    
    List<Aviso> buscarAvisosPorCurso(Long idCurso);
    
}