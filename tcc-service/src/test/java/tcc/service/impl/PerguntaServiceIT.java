package tcc.service.impl;

import tcc.common.entity.Anexo;
import tcc.common.entity.Categoria;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Resposta;
import tcc.common.entity.Usuario;
import tcc.common.enums.NivelPergunta;
import tcc.common.enums.TipoPergunta;
import tcc.service.persistence.SimpleTestDao;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.AnexoBuilder;
import tcc.test.builder.PerguntaBuilder;
import tcc.test.builder.RespostaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/PerguntaServiceTest.xml")
public class PerguntaServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("PerguntaServiceImpl")
    private PerguntaServiceImpl perguntaServiceImpl;
    
    @Test
    public void deveSalvarPergunta(){
        Pergunta pergunta = obterPerguntaValida();
        
        pergunta.setAnexo(obterAnexoValida());
        pergunta = perguntaServiceImpl.salvarPergunta(pergunta);
        assertNotNull(pergunta.getId());
        assertEquals(dao.getById(Pergunta.class, pergunta.getId()), pergunta);
        List<Resposta> respostas = dao.query("select r from Resposta r where r.pergunta.id = "+pergunta.getId().toString());
        assertTrue(respostas.size() == 2);
    }
    
    @Test
    public void deveEditarPergunta(){
        Pergunta perguntaAntes = perguntaServiceImpl.buscarPerguntaPorId(4L);
        assertEquals(perguntaAntes.getDescricao(), "Pergunta 4 da atividade de matemática");
        List<Resposta> respostasAntes = dao.query("select r from Resposta r where r.pergunta.id = 4");
        assertTrue(respostasAntes.size() == 2);
        
        perguntaAntes.setDescricao("Pergunta 1 AAA");
        perguntaAntes.setRespostas(new HashSet<Resposta>());
        perguntaAntes.getRespostas().add(obterRespostaValida1());
        perguntaAntes.setIdAnexo(1L);
        perguntaServiceImpl.salvarPergunta(perguntaAntes);
        
        Pergunta perguntaEditado = perguntaServiceImpl.buscarPerguntaPorId(4L);
        assertEquals(perguntaEditado.getDescricao(), "Pergunta 1 AAA");
        List<Resposta> respostasDepois = dao.query("select r from Resposta r where r.pergunta.id = 4");
        assertTrue(respostasDepois.size() == 1);
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
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(1L, null, null, null, null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorParteDaDescricao(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, "Quanto é", null, null, null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorCategoria(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, null, 1L, null, null);
        assertTrue(perguntas.size()==3);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L, 4L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorTipo(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, null, null, TipoPergunta.MULTIPLAS_ESCOLHAS, null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorNivel(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(null, null, null, TipoPergunta.MULTIPLAS_ESCOLHAS, null);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorUsuarioDescricaoCategoria(){
        List<Pergunta> perguntas = perguntaServiceImpl.buscarPerguntaPorFiltro(1L, "Quanto é", 1L, TipoPergunta.MULTIPLAS_ESCOLHAS, NivelPergunta.FACIL);
        assertTrue(perguntas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Pergunta p : perguntas) {
            assertTrue(ids.contains(p.getId()));
        }
    }
    
    @Test
    public void deveRetornarPerguntaPorId(){
        Pergunta pergunta = perguntaServiceImpl.buscarPerguntaPorId(1L);
        assertNotNull(pergunta);
        assertTrue(pergunta.getRespostas().size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Resposta r : pergunta.getRespostas()) {
            assertTrue(ids.contains(r.getId()));
        }
    }
    
    private Pergunta obterPerguntaValida() {
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(obterRespostaValida1());
        respostas.add(obterRespostaValida2());
        
        Pergunta pergunta = PerguntaBuilder.nova()
                .comCategoria(new Categoria(1L))
                .comDescricao("Descricao")
                .comJustificativa("Justificativa")
                .comTipo(TipoPergunta.MULTIPLAS_ESCOLHAS)
                .comNivel(NivelPergunta.FACIL)
                .comDica("Dica")
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
    
    private Anexo obterAnexoValida() {
         byte[] bytes = "Teste".getBytes();
        
        Anexo anexo = AnexoBuilder.nova()
                .comNomeArquivo("nomeArquivo.jpg")
                .comBytes(bytes)
                .build();
        return anexo;
    }
    
}