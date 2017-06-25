package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.Jogo;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.CursoBuilder;
import br.com.tcc.test.builder.EtapaBuilder;
import br.com.tcc.test.builder.EtapaPerguntaBuilder;
import br.com.tcc.test.builder.PerguntaBuilder;
import br.com.tcc.test.builder.RespostaBuilder;
import br.com.tcc.test.builder.UsuarioBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/CursoServiceTest.xml")
public class CursoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBean("SimpleTestDao")
    private SimpleTestDao dao;
    
    @SpringBean("CursoServiceImpl")
    private CursoServiceImpl cursoServiceImpl;
    
    @Test
    public void deveSalvarCurso(){
        Curso curso = obterCursoValida();
        
        curso = cursoServiceImpl.salvarCurso(curso);
        assertNotNull(curso.getId());
        assertEquals(dao.getById(Pergunta.class, curso.getId()), curso);
        List<Etapa> etapas = dao.query("select e from Etapa e where e.curso.id = "+curso.getId().toString());
        assertTrue(etapas.size() == 2);
    }
    
    @Test
    public void deveExcluirCursoPorId(){
        List<Curso> curso = dao.query("select c from Curso c where c.id = 1");
        assertTrue(curso.size() == 1);
        List<Resposta> respostas = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(respostas.size() == 2);
        
        cursoServiceImpl.excluirCurso(1L);
        
        List<Pergunta> perguntaExcluida = dao.query("select c from Curso c where c.id = 1");
        assertTrue(perguntaExcluida.isEmpty());
        List<Resposta> respostasExcluida = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(respostasExcluida.isEmpty());
    }
    
    @Test
    public void deveRetornarCursoPorId(){
        Curso curso = cursoServiceImpl.buscarCursoPorId(1L);
        assertNotNull(curso);
        assertTrue(curso.getEtapas().size() == 2);
        for (Etapa etapa : curso.getEtapas()) {
            assertTrue(etapa.getEtapasPerguntas().size() == 2);
            assertTrue(etapa.getPerguntas().size() == 2);
        }
    }
    
    @Test
    public void deveRetornarCursoPorUsuario(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(1L, null, null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorParteDaDescricao(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, "Curso", null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorCategoria(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, null, Categoria.BIOLOGIA);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Curso obterCursoValida() {
        Set<Etapa> etapas = new HashSet<>();
        etapas.add(obterEtapaValida1());
        etapas.add(obterEtapaValida2());
        
        Curso curso = CursoBuilder.nova()
                .comUsuario(obterUsuarioValido())
                .comCategoria(Categoria.MATEMATICA)
                .comNome("Nome")
                .comCodAcesso("CodAcesso")
                .comSituacao(SituacaoCurso.CONCLUIDA)
                .comEtapas(etapas)
                .build();
        return curso;
    }
    
    private Etapa obterEtapaValida1() {
        Set<EtapaPergunta> etapasPerguntas = new HashSet<>();
        etapasPerguntas.add(obterEtapaPerguntaValida1());
        etapasPerguntas.add(obterEtapaPerguntaValida2());
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 1.")
                .comNivel(1)
                .comJogo(Jogo.POKER)
                .comCurso(new Curso())
                .comEtapasPerguntas(etapasPerguntas)
                .build();
        return etapa;
    }
    
    private Etapa obterEtapaValida2() {
        Set<EtapaPergunta> etapasPerguntas = new HashSet<>();
        etapasPerguntas.add(obterEtapaPerguntaValida1());
        etapasPerguntas.add(obterEtapaPerguntaValida2());
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 2.")
                .comNivel(2)
                .comJogo(Jogo.QUIZ)
                .comEtapasPerguntas(etapasPerguntas)
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
    
    private EtapaPergunta obterEtapaPerguntaValida2() {
        EtapaPergunta etapaPergunta = EtapaPerguntaBuilder.nova()
                .comPergunta(obterPerguntaValida2())
                .comPosicao(2)
                .build();
        return etapaPergunta;
    }
    
    private Pergunta obterPerguntaValida1() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida1());
        respostas.add(obterRespostaValida2());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(Categoria.MATEMATICA)
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
    
    private Pergunta obterPerguntaValida2() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida3());
        respostas.add(obterRespostaValida4());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(Categoria.BIOLOGIA)
                .comDescricao("Descricao BIOLOGIA")
                .comJustificativa("Justificativa BIOLOGIA")
                .comUsuario(obterUsuarioValido())
                .comRespostas(respostas)
                .build();
        return pergunta;
    }
    
    private Resposta obterRespostaValida3() {
        Resposta resposta = RespostaBuilder.nova()
                .comDescricao("Pergunta 1?")
                .comCorreta(Boolean.TRUE)
                .build();
        return resposta;
    }
    
    private Resposta obterRespostaValida4() {
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
