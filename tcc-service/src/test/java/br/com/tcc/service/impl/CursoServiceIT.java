package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.Jogo;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.CursoBuilder;
import br.com.tcc.test.builder.EtapaBuilder;
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
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/CursoServiceTest.xml")
public class CursoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("CursoServiceImpl")
    private CursoServiceImpl cursoServiceImpl;
    
    //<editor-fold defaultstate="collapsed" desc="Curso">
    @Test
    public void deveSalvarRascunhoCurso(){
        Curso curso = obterCursoValida();
        curso.setSituacao(SituacaoCurso.RASCUNHO);
        curso.setEtapas(new HashSet<Etapa>());
        
        curso = cursoServiceImpl.salvarCurso(curso);
        assertNotNull(curso.getId());
        assertEquals(dao.getById(Curso.class, curso.getId()), curso);
        List<Etapa> etapas = dao.query("select e from Etapa e where e.curso.id = "+curso.getId().toString());
        assertTrue(etapas.isEmpty());
    }
    
    @Test
    public void deveEditarCurso(){        
        Curso cursoAtual = cursoServiceImpl.buscarCursoPorId(1L);
        assertEquals(cursoAtual.getNome(), "Curso História");
        
        cursoAtual.setNome("Curso Nome Novo");
        cursoServiceImpl.salvarCurso(cursoAtual);
        
        Curso cursoEditado = cursoServiceImpl.buscarCursoPorId(1L);
        assertEquals(cursoEditado.getNome(), "Curso Nome Novo");
    }
    
    @Test
    public void deveSalvarCurso(){
        Curso curso = obterCursoValida();
        
        curso = cursoServiceImpl.salvarCurso(curso);
        assertNotNull(curso.getId());
        assertEquals(dao.getById(Curso.class, curso.getId()), curso);
        List<Etapa> etapas = dao.query("select e from Etapa e where e.curso.id = "+curso.getId().toString());
        assertTrue(etapas.size() == 2);
    }
    
    @Test
    public void deveRetornarCursoPorId(){
        Curso curso = cursoServiceImpl.buscarCursoPorId(1L);
        assertNotNull(curso);
        assertTrue(curso.getEtapas().size() == 2);
        for (Etapa etapa : curso.getEtapas()) {
            assertTrue(etapa.getEtapasPerguntas().size() == 1);
        }
    }
    
    @Test
    public void deveExcluirCursoPorId(){
        List<Curso> curso = dao.query("select c from Curso c where c.id = 1");
        assertTrue(curso.size() == 1);
        List<Etapa> etapa = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(etapa.size() == 2);
        
        cursoServiceImpl.excluirCurso(1L);
        
        List<Curso> cursoExcluida = dao.query("select c from Curso c where c.id = 1");
        assertTrue(cursoExcluida.isEmpty());
        List<Etapa> etapasExcluida = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(etapasExcluida.isEmpty());
    }
    
    @Test
    public void deveRetornarCursoPorUsuario(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(1L, null, null, null);
        assertTrue(cursos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorParteDaDescricao(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, "Matemática", null, null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorCategoria(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, null, Categoria.GEOGRAFIA, null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(3L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorSituacao(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, null, null, SituacaoCurso.CONCLUIDA);
        assertTrue(cursos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L, 3L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Etapa">
    @Test
    public void deveSalvarEtapa(){
        Curso curso = new Curso();
        curso.setId(1L);
        Etapa etapa = obterEtapaValida1();
        etapa.setCurso(curso);
        
        etapa = cursoServiceImpl.salvarEtapa(etapa);
        assertNotNull(etapa.getId());
        assertEquals(dao.getById(Etapa.class, etapa.getId()), etapa);
        List<EtapaPergunta> perguntas = dao.query("select e from EtapaPergunta e where e.etapa.id = "+etapa.getId().toString());
        assertTrue(perguntas.size() == 2);
    }
    
    @Test
    public void deveEditarEtapa(){
        Etapa etapaAtual = dao.getById(Etapa.class, 1L);
        assertEquals(etapaAtual.getAssunto(), "Assunto C1 E1");
        List<EtapaPergunta> perguntasAntes = dao.query("select e from EtapaPergunta e where e.etapa.id = 1");
        assertTrue(perguntasAntes.size() == 1);
        
        etapaAtual.setAssunto("Etapa Nome Novo");
        etapaAtual.setEtapasPerguntas(new HashSet<EtapaPergunta>());
        
        EtapaPergunta ep1 = new EtapaPergunta();
        ep1.setPergunta(dao.getById(Pergunta.class, 1L));
        etapaAtual.getEtapasPerguntas().add(ep1);
        
        EtapaPergunta ep2 = new EtapaPergunta();
        ep2.setPergunta(dao.getById(Pergunta.class, 1L));
        etapaAtual.getEtapasPerguntas().add(ep2);
        
        cursoServiceImpl.salvarEtapa(etapaAtual);
        
        Etapa etapaEditado = dao.getById(Etapa.class, 1L);
        assertEquals(etapaEditado.getAssunto(), "Etapa Nome Novo");
        List<EtapaPergunta> perguntasDepois = dao.query("select e from EtapaPergunta e where e.etapa.id = 1");
        assertTrue(perguntasDepois.size() == 2);
    }
    
    @Test
    public void deveExcluirEtapaPorId(){
        List<Etapa> etapa = dao.query("select e from Etapa e where e.id = 1");
        assertTrue(etapa.size() == 1);
        List<EtapaPergunta> etapasPerguntas = dao.query("select ep from EtapaPergunta ep where ep.etapa.id = 1");
        assertTrue(etapasPerguntas.size() == 1);
        
        List<Etapa> etapaComNivelAtualizado = dao.query("select e from Etapa e where e.id = 2");
        assertTrue(!etapaComNivelAtualizado.isEmpty());
        assertEquals(etapaComNivelAtualizado.get(0).getNivel(), new Integer(2));
        
        cursoServiceImpl.excluirEtapa(1L, 1L);
        
        List<Etapa> etapaExcluida = dao.query("select e from Etapa e where e.id = 1");
        assertTrue(etapaExcluida.isEmpty());
        List<EtapaPergunta> etapasPerguntasExcluida = dao.query("select ep from EtapaPergunta ep where ep.etapa.id = 1");
        assertTrue(etapasPerguntasExcluida.isEmpty());
        
        etapaComNivelAtualizado = dao.query("select e from Etapa e where e.id = 2");
        assertTrue(!etapaComNivelAtualizado.isEmpty());
        assertEquals(etapaComNivelAtualizado.get(0).getNivel(), new Integer(1));
    }
    
    @Test
    public void deveRetornarEtapaPorIdCurso(){
        List<Etapa> etapas = cursoServiceImpl.buscarEtapa(1L, null);
        assertTrue(etapas.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    
    @Test
    public void deveRetornarEtapaPorNivel(){
        List<Etapa> etapas = cursoServiceImpl.buscarEtapa(null, 1);
        assertTrue(etapas.size()==3);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 3L, 4L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    
    @Test
    public void deveRetornarEtapaPorIdCursoNivel(){
        List<Etapa> etapas = cursoServiceImpl.buscarEtapa(1L, 1);
        assertTrue(etapas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Curso obterCursoValida() {
        Set<Etapa> etapas = new HashSet<>();
        etapas.add(obterEtapaValida1());
        etapas.add(obterEtapaValida2());
        
        Curso curso = CursoBuilder.nova()
                .comUsuario(dao.getById(Usuario.class, 1L))
                .comCategoria(Categoria.MATEMATICA)
                .comNome("Nome")
                .comCodAcesso("CodAcesso")
                .comSituacao(SituacaoCurso.CONCLUIDA)
                .comEtapas(etapas)
                .build();
        return curso;
    }
    
    private Etapa obterEtapaValida1() {
        Set<EtapaPergunta> etapaPergunta = new HashSet<>();
        
        EtapaPergunta ep1 = new EtapaPergunta();
        Pergunta pergunta1 = dao.getById(Pergunta.class, 1L);
        pergunta1.setPosicao(1);
        ep1.setPergunta(pergunta1);
        etapaPergunta.add(ep1);
                
        EtapaPergunta ep2 = new EtapaPergunta();
        Pergunta pergunta2 = dao.getById(Pergunta.class, 2L);
        pergunta1.setPosicao(2);
        ep2.setPergunta(pergunta2);
        etapaPergunta.add(ep2);
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 1.")
                .comNivel(1)
                .comJogo(Jogo.POKER)
                .comCurso(new Curso())
                .comEtapasPerguntas(etapaPergunta)
                .build();
        return etapa;
    }
    
    private Etapa obterEtapaValida2() {
        Set<EtapaPergunta> etapaPergunta = new HashSet<>();
        
        EtapaPergunta ep1 = new EtapaPergunta();
        Pergunta pergunta1 = dao.getById(Pergunta.class, 1L);
        pergunta1.setPosicao(1);
        ep1.setPergunta(pergunta1);
        etapaPergunta.add(ep1);
                
        EtapaPergunta ep2 = new EtapaPergunta();
        Pergunta pergunta2 = dao.getById(Pergunta.class, 2L);
        pergunta1.setPosicao(2);
        ep2.setPergunta(pergunta2);
        etapaPergunta.add(ep2);
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 2.")
                .comNivel(2)
                .comJogo(Jogo.QUIZ)
                .comEtapasPerguntas(etapaPergunta)
                .build();
        return etapa;
    }
    //</editor-fold>
}
