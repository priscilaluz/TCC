package tcc.test.validator;

import tcc.common.entity.Usuario;
import tcc.common.exception.BusinessException;
import tcc.common.util.ConstantesI18N;
import tcc.service.validator.UsuarioValidator;
import tcc.test.builder.UsuarioBuilder;
import static org.junit.Assert.*;
import org.junit.Test;

public class UsuarioValidatorTest {
    
    private final UsuarioValidator validator = new UsuarioValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar usuario">
    @Test
    public void naoDeveSalvarSemEmail(){
        Usuario c = obterUsuarioValido();
        try {
            c.setEmail(null);
            validator.validarSalvarUsuario(c, 0L, 0L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_EMAIL_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemLogin(){
        Usuario c = obterUsuarioValido();
        try {
            c.setLogin(null);
            validator.validarSalvarUsuario(c, 0L, 0L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_LOGIN_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemNome(){
        Usuario c = obterUsuarioValido();
        try {
            c.setNome(null);
            validator.validarSalvarUsuario(c, 0L, 0L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_NOME_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemSenha(){
        Usuario c = obterUsuarioValido();
        try {
            c.setSenha(null);
            validator.validarSalvarUsuario(c, 0L, 0L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_SENHA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarComEmailJaCadastrado(){
        Usuario c = obterUsuarioValido();
        try {
            validator.validarSalvarUsuario(c, 1L, 0L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_EMAIL_JA_CADASTRADO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarComLoginJaCadastrado(){
        Usuario c = obterUsuarioValido();
        try {
            validator.validarSalvarUsuario(c, 0L, 1L);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.USUARIO_LOGIN_JA_CADASTRADO, ex.getMessage());
        }
    }
    
    //</editor-fold>
    
    private Usuario obterUsuarioValido() {
        Usuario usuario = UsuarioBuilder.nova()
                .comNome("Usuario 1")
                .comEmail("teste@a.com")
                .comLogin("TesteLogin")
                .comSenha("1234")
                .build();
        return usuario;
    }
    

}
