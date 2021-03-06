package tcc.test.validator;

import tcc.common.entity.Curso;
import tcc.common.entity.Categoria;
import tcc.common.entity.Etapa;
import tcc.common.entity.EtapaPergunta;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Resposta;
import tcc.common.entity.Usuario;
import tcc.common.enums.Jogo;
import tcc.common.enums.SituacaoCurso;
import tcc.common.exception.BusinessException;
import tcc.common.util.ConstantesI18N;
import tcc.service.validator.CursoValidator;
import tcc.test.builder.CursoBuilder;
import tcc.test.builder.EtapaBuilder;
import tcc.test.builder.EtapaPerguntaBuilder;
import tcc.test.builder.PerguntaBuilder;
import tcc.test.builder.RespostaBuilder;
import tcc.test.builder.UsuarioBuilder;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

public class CursoValidatorTest {
    
    private final CursoValidator validator = new CursoValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar curso">
    @Test
    public void naoDeveSalvarSemCurso(){
        Curso c = null;
        try {
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarCursoSemUsuario(){
        Curso c = obterCursoValida();
        try {
            c.setUsuario(null);
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_USUARIO_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarCursoSemNome(){
        Curso c = obterCursoValida();
        try {
            c.setNome(null);
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_NOME_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarCursoSemCodAcesso(){
        Curso c = obterCursoValida();
        try {
            c.setCodAcesso(null);
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_CODACESSO_OBRIGATORIO, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarCursoSemCategoria(){
        Curso c = obterCursoValida();
        try {
            c.setCategoria(null);
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_CATEGORIA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarCursoSemSituacao(){
        Curso c = obterCursoValida();
        try {
            c.setSituacao(null);
            validator.validarSalvarCurso(c);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_SITUACAO_OBRIGATORIA, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Salvar etapa">
    @Test
    public void naoDeveSalvarSemEtapa(){
        Etapa e = null;
        try {
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarEtapaSemAssunto(){
        Etapa e = obterEtapaValida1();
        try {
            e.setAssunto(null);
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_ASSUNTO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarEtapaSemNivel(){
        Etapa e = obterEtapaValida1();
        try {
            e.setNivel(null);
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_NIVEL_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarEtapaSemJogo(){
        Etapa e = obterEtapaValida1();
        try {
            e.setJogo(null);
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_JOGO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarEtapaSemCurso(){
        Etapa e = obterEtapaValida1();
        try {
            e.setCurso(null);
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_CURSO_OBRIGATORIA, ex.getMessage());
        }
    }
    @Test
    public void naoDeveSalvarEtapaSemPerguntas(){
        Etapa e = obterEtapaValida1();
        try {
            e.setEtapasPerguntas(new HashSet<EtapaPergunta>());
            validator.validarSalvarEtapa(e);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.ETAPA_PERGUNTAS_OBRIGATORIA, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Curso obterCursoValida() {
        Set<Etapa> etapas = new HashSet<>();
        etapas.add(obterEtapaValida1());
        etapas.add(obterEtapaValida2());
        
        Curso curso = CursoBuilder.nova()
                .comUsuario(obterUsuarioValido())
                .comCategoria(new Categoria(1L))
                .comNome("Nome")
                .comCodAcesso("CodAcesso")
                .comSituacao(SituacaoCurso.CONCLUIDA)
                .comEtapas(etapas)
                .build();
        return curso;
    }
    
    private Etapa obterEtapaValida1() {
        Set<EtapaPergunta> etapasperguntas = new HashSet<>();
        etapasperguntas.add(obterEtapaPerguntaValida1());
        etapasperguntas.add(obterEtapaPerguntaValida1());
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 1.")
                .comNivel(1)
                .comJogo(Jogo.FORCA)
                .comCurso(new Curso())
                .comEtapasPerguntas(etapasperguntas)
                .build();
        return etapa;
    }
    
    private Etapa obterEtapaValida2() {
        Set<EtapaPergunta> etapasperguntas = new HashSet<>();
        etapasperguntas.add(obterEtapaPerguntaValida1());
        etapasperguntas.add(obterEtapaPerguntaValida1());
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 2.")
                .comNivel(2)
                .comJogo(Jogo.QUIZ)
                .comEtapasPerguntas(etapasperguntas)
                .build();
        return etapa;
    }
    
    private EtapaPergunta obterEtapaPerguntaValida1() {
        EtapaPergunta etapaPergunta = EtapaPerguntaBuilder.nova()
                .comPergunta(obterPerguntaValida1())
                .comPosicao(1)
                .build();
        return etapaPergunta;
    }
    
    private Pergunta obterPerguntaValida1() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida1());
        respostas.add(obterRespostaValida2());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(new Categoria(1L))
                .comDescricao("Descricao")
                .comJustificativa("Justificativa")
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
    //</editor-fold>
}
