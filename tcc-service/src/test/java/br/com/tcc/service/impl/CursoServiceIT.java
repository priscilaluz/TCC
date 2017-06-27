package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
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
        List<Etapa> etapasAntes = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(etapasAntes.size() == 2);
        
        cursoAtual.setNome("Curso Nome Novo");
        cursoAtual.setEtapas(new HashSet<Etapa>());
        cursoAtual.getEtapas().add(obterEtapaValida1());
        cursoServiceImpl.salvarCurso(cursoAtual);
        
        Curso cursoEditado = cursoServiceImpl.buscarCursoPorId(1L);
        assertEquals(cursoEditado.getNome(), "Curso Nome Novo");
        List<Etapa> etapasDepois = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(etapasDepois.size() == 1);
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
            assertTrue(etapa.getPerguntas().size() == 1);
        }
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
    public void deveRetornarCursoPorUsuario(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(1L, null, null);
        assertTrue(cursos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorParteDaDescricao(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, "Matemática", null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorCategoria(){
        List<Curso> cursos = cursoServiceImpl.buscarCursoPorFiltro(null, null, Categoria.GEOGRAFIA);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(3L));
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
        List<Pergunta> perguntas = new ArrayList<>();
        Pergunta pergunta1 = dao.getById(Pergunta.class, 1L);
        pergunta1.setPosicao(1);
        perguntas.add(pergunta1);
        Pergunta pergunta2 = dao.getById(Pergunta.class, 2L);
        pergunta1.setPosicao(2);
        perguntas.add(pergunta2);
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 1.")
                .comNivel(1)
                .comJogo(Jogo.POKER)
                .comCurso(new Curso())
                .comPerguntas(perguntas)
                .build();
        return etapa;
    }
    
    private Etapa obterEtapaValida2() {
        List<Pergunta> perguntas = new ArrayList<>();
        Pergunta pergunta1 = dao.getById(Pergunta.class, 1L);
        pergunta1.setPosicao(1);
        perguntas.add(pergunta1);
        Pergunta pergunta2 = dao.getById(Pergunta.class, 2L);
        pergunta1.setPosicao(2);
        perguntas.add(pergunta2);
        
        Etapa etapa = EtapaBuilder.nova()
                .comAssunto("Assunto 2.")
                .comNivel(2)
                .comJogo(Jogo.QUIZ)
                .comPerguntas(perguntas)
                .build();
        return etapa;
    }
    //</editor-fold>
}
