package tcc.test.validator;

import tcc.common.entity.Anexo;
import tcc.common.exception.BusinessException;
import tcc.common.util.ConstantesI18N;
import tcc.service.validator.AnexoValidator;
import tcc.test.builder.AnexoBuilder;
import static org.junit.Assert.*;
import org.junit.Test;

public class AnexoValidatorTest {
    
    private final AnexoValidator validator = new AnexoValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar anexo">
    @Test
    public void naoDeveSalvarSemAnexo(){
        Anexo c = null;
        try {
            validator.validarSalvarAnexo(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ANEXO_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemBytes(){
        Anexo c = obterAnexoValida();
        try {
            c.setBytes(null);
            validator.validarSalvarAnexo(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ANEXO_BYTES_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemNomeArquivo(){
        Anexo c = obterAnexoValida();
        try {
            c.setNomeArquivo(null);
            validator.validarSalvarAnexo(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ANEXO_NOME_ARQUIVO_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarComFormatoInvalido(){
        Anexo c = obterAnexoValida();
        try {
            c.setNomeArquivo("a.pdf");
            validator.validarSalvarAnexo(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ANEXO_EXTENSAO_INVALIDA, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarComFormatoInvalido2(){
        Anexo c = obterAnexoValida();
        try {
            c.setNomeArquivo("apdf");
            validator.validarSalvarAnexo(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ANEXO_EXTENSAO_INVALIDA, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Anexo obterAnexoValida() {
         byte[] bytes = "Teste".getBytes();
        
        Anexo anexo = AnexoBuilder.nova()
                .comNomeArquivo("nomeArquivo.jpg")
                .comBytes(bytes)
                .build();
        return anexo;
    }
    //</editor-fold>
}
