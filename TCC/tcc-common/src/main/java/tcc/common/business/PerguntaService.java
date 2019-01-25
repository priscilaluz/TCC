package tcc.common.business;

import tcc.common.entity.Pergunta;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.TipoPergunta;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
public interface PerguntaService {
    
    Pergunta salvarPergunta(Pergunta pergunta);
    
    void excluirPergunta(Long idPergunta);
    
    Pergunta buscarPerguntaPorId(Long idPergunta);
    
    ListaPaginacao buscarPerguntaPorFiltro(Long idUsuario, String parteNome, Long idCategoria, 
            TipoPergunta tipo, NivelPergunta nivel, Long idCurso, Integer paginaAtual);
    
    Long buscarCountPerguntaPorFiltro(Long idUsuario, Long idCategoria);
    
}