package tcc.test.validator;

import tcc.common.entity.Categoria;
import tcc.common.exception.BusinessException;
import tcc.common.util.ConstantesI18N;
import tcc.service.validator.CategoriaValidator;
import tcc.test.builder.CategoriaBuilder;
import static org.junit.Assert.*;
import org.junit.Test;

public class CategoriaValidatorTest {
    
    private final CategoriaValidator validator = new CategoriaValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar curso">
    @Test
    public void naoDeveSalvarSemCategoria(){
        Categoria c = null;
        try {
            validator.validarSalvarCategoria(c, true);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CATEGORIA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemNome(){
        Categoria c = obterCategoriaValida();
        try {
            c.setNome(null);
            validator.validarSalvarCategoria(c, true);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CATEGORIA_NOME_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarComNomeExistente(){
        Categoria c = obterCategoriaValida();
        try {
            validator.validarSalvarCategoria(c, false);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CATEGORIA_NOME_EXISTE, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Categoria obterCategoriaValida() {
        Categoria categoria = CategoriaBuilder.nova()
                .comNome("Nome")
                .build();
        return categoria;
    }
    //</editor-fold>
}
