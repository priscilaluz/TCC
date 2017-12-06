package tcc.common.business;

import java.util.List;
import tcc.common.entity.Pergunta;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.TipoPergunta;

/**
 *
 * @author ADM
 */
public interface PerguntaService {
    
    Pergunta salvarPergunta(Pergunta pergunta);
    
    void excluirPergunta(Long idPergunta);
    
    Pergunta buscarPerguntaPorId(Long idPergunta);
    
    List<Pergunta> buscarPerguntaPorFiltro(Long idUsuario, String parteNome, Long idCategoria, TipoPergunta tipo, NivelPergunta nivel);
    
    Long buscarCountPerguntaPorFiltro(Long idUsuario);
    
}