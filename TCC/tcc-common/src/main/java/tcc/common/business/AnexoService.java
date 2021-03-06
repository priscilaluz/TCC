package tcc.common.business;

import java.io.InputStream;
import tcc.common.entity.Anexo;

/**
 *
 * @author ADM
 */
public interface AnexoService {
    
    Anexo salvarAnexo(Anexo anexo);
    
    void excluirAnexo(Long idAnexo);
    
    Anexo buscarAnexoPorId(Long idAnexo);
    
    byte[] obterOsBytesAnexo(Long idAnexo);
    
    InputStream obterBytesAnexo(Long idAnexo);
    
    InputStream downloadImgMonaLisa(String imagem);
    
    InputStream imprimirManual(String manual);
}