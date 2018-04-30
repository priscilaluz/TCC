package tcc.common.business;

import java.io.InputStream;
import java.util.List;
import tcc.common.entity.Anexo;
import tcc.common.entity.Categoria;

/**
 *
 * @author ADM
 */
public interface CategoriaService {
    
    Categoria salvarCategoria(Categoria categoria);
    
    void excluirCategoria(Long idCategoria);
    
    Categoria buscarCategoriaPorId(Long idCategoria);
    
    List<Categoria> buscarCategoriaPorFiltro(String parteNome);
    
}