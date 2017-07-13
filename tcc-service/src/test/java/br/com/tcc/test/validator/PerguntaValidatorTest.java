package br.com.tcc.test.validator;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.common.exception.BusinessException;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.validator.PerguntaValidator;
import br.com.tcc.test.builder.PerguntaBuilder;
import br.com.tcc.test.builder.RespostaBuilder;
import br.com.tcc.test.builder.UsuarioBuilder;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

public class PerguntaValidatorTest {
    
    private final PerguntaValidator validator = new PerguntaValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar usuario">
    @Test
    public void naoDeveSalvarSemPergunta(){
        Pergunta p = null;
        try {
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemCategoria(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setCategoria(null);
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_CATEGORIA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemDescricao(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setDescricao(null);
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_DESCRICAO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemTipo(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setTipo(null);
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_TIPO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemNivel(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setNivel(null);
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_NIVEL_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemUsuario(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setUsuario(null);
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_USUARIO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarSemRespostas(){
        Pergunta p = obterPerguntaValida();
        try {
            p.setRespostas(new HashSet<Resposta>());
            validator.validarSalvarPergunta(p);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_RESPOSTA_OBRIGATORIA, ex.getMessage());
        }
    }
    //</editor-fold>
    
    private Pergunta obterPerguntaValida() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida1());
        respostas.add(obterRespostaValida2());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(Categoria.MATEMATICA)
                .comDescricao("Descricao")
                .comJustificativa("Justificativa")
                .comTipo(TipoPergunta.MULTIPLAS_ESCOLHAS)
                .comNivel(NivelPergunta.FACIL)
                .comDica("Dica")
                .comUsuario(obterUsuarioValido())
                .comRespostas(respostas)
                .build();
        return pergunta;
    }
    
    private Resposta obterRespostaValida1() {
        Resposta resposta = RespostaBuilder.nova()
                .comDescricao("Pergunta 1?")
                .comCorreta(Boolean.TRUE)
                .build();
        return resposta;
    }
    
    private Resposta obterRespostaValida2() {
        Resposta resposta = RespostaBuilder.nova()
                .comDescricao("Pergunta 2?")
                .comCorreta(Boolean.FALSE)
                .build();
        return resposta;
    }
    
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
