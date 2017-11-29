package br.com.tcc.test.validator;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.PerguntaEtapaAluno;
import br.com.tcc.common.entity.RelatorioEtapa;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.CategoriaEnum;
import br.com.tcc.common.enums.Jogo;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.exception.BusinessException;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.service.validator.CursoAlunoValidator;
import br.com.tcc.test.builder.CursoBuilder;
import br.com.tcc.test.builder.EtapaBuilder;
import br.com.tcc.test.builder.EtapaPerguntaBuilder;
import br.com.tcc.test.builder.PerguntaBuilder;
import br.com.tcc.test.builder.PerguntaEtapaAlunoBuilder;
import br.com.tcc.test.builder.RelatorioEtapaBuilder;
import br.com.tcc.test.builder.RespostaBuilder;
import br.com.tcc.test.builder.UsuarioBuilder;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

public class CursoAlunoValidatorTest {
    
    private final CursoAlunoValidator validator = new CursoAlunoValidator();
    
    //<editor-fold defaultstate="collapsed" desc="Salvar curso aluno">
    @Test
    public void naoDeveSalvarCursoAlunoSemCodAcesso(){
        try {
            validator.validarAlunoEntrarCurso(obterCursoValida(), null, null);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CODIGO_ACESSO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarCursoAlunoComCodAcessoInvalido(){
        try {
            validator.validarAlunoEntrarCurso(obterCursoValida(), "CodInvalido", null);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CODIGO_ACESSO_INVALIDO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarCursoAlunoJaSalvo(){
        try {
            validator.validarAlunoEntrarCurso(obterCursoValida(), "CodAcesso", new CursoAluno());
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.CURSO_ALUNO_JA_SALVO, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Relatorio etapa">
    @Test
    public void naoDeveSalvarRelatorioEtapaSemEtapaAluno(){
        try {
            RelatorioEtapa relatorioEtapa = obterRelatorioEtapaValida();
            relatorioEtapa.setEtapaAluno(null);
            validator.validarRelatorioEtapa(relatorioEtapa);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.RELATORIO_ETAPA_ETAPA_ALUNO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarRelatorioEtapaSemPontuacao(){
        try {
            RelatorioEtapa relatorioEtapa = obterRelatorioEtapaValida();
            relatorioEtapa.setPontuacao(null);
            validator.validarRelatorioEtapa(relatorioEtapa);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.RELATORIO_ETAPA_PONTUACAO_OBRIGATORIO, ex.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Pergunta etapa aluno">
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemPulo(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setPulo(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PULO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemDica(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setDica(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_DICA_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemTempoAcabou(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setTempoAcabou(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_TEMPOACABOU_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemPontuacao(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setPontuacao(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PONTUACAO_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemPergunta(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setPergunta(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_PERGUNTA_OBRIGATORIO, ex.getMessage());
        }
    }
    
    @Test
    public void naoDeveSalvarPerguntaEtapaAlunoSemRelatorioEtapa(){
        try {
            PerguntaEtapaAluno perguntaEtapaAluno = obterPerguntaEtapaAlunoValida1();
            perguntaEtapaAluno.setRelatorioEtapa(null);
            validator.validarPerguntaEtapaAluno(perguntaEtapaAluno);
            fail();
        } catch (BusinessException ex) {
            assertEquals(ConstantesI18N.PERGUNTA_ETAPA_ALUNO_RELATORIOETAPA_OBRIGATORIO, ex.getMessage());
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
                .comCategoria(CategoriaEnum.MATEMATICA)
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
                .comCategoria(CategoriaEnum.MATEMATICA)
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
    
    private RelatorioEtapa obterRelatorioEtapaValida() {
        Set<PerguntaEtapaAluno> perguntasEtapasAlunos = new HashSet<>();
        perguntasEtapasAlunos.add(obterPerguntaEtapaAlunoValida1());
        perguntasEtapasAlunos.add(obterPerguntaEtapaAlunoValida2());
        
        RelatorioEtapa relatorioEtapa = RelatorioEtapaBuilder.novo()
                .comPontuacao(0)
                .comEtapaAluno(new EtapaAluno(1L))
                .comGanhou(Boolean.TRUE)
                .comPerguntasEtapasAlunos(perguntasEtapasAlunos)
                .build();
        return relatorioEtapa;
    }
    
    private PerguntaEtapaAluno obterPerguntaEtapaAlunoValida1() {
        PerguntaEtapaAluno perguntaEtapaAluno = PerguntaEtapaAlunoBuilder.novo()
                .comPontuacao(100)
                .comDica(Boolean.FALSE)
                .comPulo(Boolean.FALSE)
                .comTempoAcabou(Boolean.FALSE)
                .comPergunta(new Pergunta())
                .comRelatorioEtapa(new RelatorioEtapa())
                .build();
        return perguntaEtapaAluno;
    }
    
    private PerguntaEtapaAluno obterPerguntaEtapaAlunoValida2() {
        PerguntaEtapaAluno perguntaEtapaAluno = PerguntaEtapaAlunoBuilder.novo()
                .comPontuacao(0)
                .comDica(Boolean.FALSE)
                .comPulo(Boolean.FALSE)
                .comTempoAcabou(Boolean.FALSE)
                .comPergunta(new Pergunta())
                .comRelatorioEtapa(new RelatorioEtapa())
                .build();
        return perguntaEtapaAluno;
    }
    //</editor-fold>
}
