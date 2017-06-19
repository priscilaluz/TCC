package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.PerguntaBuilder;
import br.com.tcc.test.builder.RespostaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;

@DataSet("/datasets/PerguntaServiceTest.xml")
public class PerguntaServiceIT extends IntegrationBaseTestClass{
    
    @SpringBean("SimpleTestDao")
    private SimpleTestDao dao;
    
    @SpringBean("PerguntaServiceImpl")
    private PerguntaServiceImpl perguntaServiceImpl;
    
    @Test
    public void deveSalvarPergunta(){
        Pergunta pergunta = obterPerguntaValida();
        
        pergunta = perguntaServiceImpl.salvarPergunta(pergunta);
        assertNotNull(pergunta.getId());
        assertEquals(dao.getById(Pergunta.class, pergunta.getId()), pergunta);
        List<Resposta> respostas = dao.query("select r from Resposta r where r.pergunta.id = "+pergunta.getId().toString());
        assertTrue(respostas.size() == 2);
    }
    
    @Test
    public void deveExcluirPerguntaPorId(){
        List<Pergunta> pergunta = dao.query("select p from Pergunta p where p.id = 1");
        assertTrue(pergunta.size() == 1);
        List<Resposta> respostas = dao.query("select r from Resposta r where r.pergunta.id = 1");
        assertTrue(respostas.size() == 2);
        
        perguntaServiceImpl.excluirPergunta(1L);
        
        List<Pergunta> perguntaExcluida = dao.query("select p from Pergunta p where p.id = 1");
        assertTrue(perguntaExcluida.isEmpty());
        List<Resposta> respostasExcluida = dao.query("select r from Resposta r where r.pergunta.id = 1");
        assertTrue(respostasExcluida.isEmpty());
    }
    
    @Test
    public void deveRetornarPerguntaPorUsuario(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(1L, null, null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorParteDaDescricao(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, "Quanto é", null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorCategoria(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, null, Categoria.MATEMATICA);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorUsuarioDescricaoCategoria(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(1L, "Quanto é", Categoria.MATEMATICA);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    private Pergunta obterPerguntaValida() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida1());
        respostas.add(obterRespostaValida2());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(Categoria.MATEMATICA)
                .comDescricao("Descricao")
                .comJustificativa("Justificativa")
                .comUsuario(dao.getById(Usuario.class, 1L))
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
    
}
