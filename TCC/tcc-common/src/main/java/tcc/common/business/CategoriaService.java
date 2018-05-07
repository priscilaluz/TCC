package tcc.common.business;

import tcc.common.entity.Categoria;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
public interface CategoriaService {
    
    Categoria salvarCategoria(Categoria categoria);
    
    void excluirCategoria(Long idCategoria);
    
    Categoria buscarCategoriaPorId(Long idCategoria);
    
    ListaPaginacao buscarCategoriaPorFiltro(String parteNome, Integer paginaAtual);
}