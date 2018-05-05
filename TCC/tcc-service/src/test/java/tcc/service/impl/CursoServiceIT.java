package tcc.service.impl;

import tcc.common.entity.Categoria;
import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.entity.EtapaPergunta;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Usuario;
import tcc.common.enums.Jogo;
import tcc.common.enums.SituacaoCurso;
import tcc.service.persistence.SimpleTestDao;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.CursoBuilder;
import tcc.test.builder.EtapaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;
import tcc.common.business.CursoAlunoService;
import tcc.common.business.CursoService;
import tcc.common.entity.Aviso;
import tcc.common.entity.CursoAluno;
import tcc.common.enums.DisponibilidadeCurso;

@DataSet("/datasets/CursoServiceTest.xml")
public class CursoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("CursoServiceImpl")
    private CursoService cursoService;
    
    @SpringBean("CursoAlunoServiceImpl")
    private CursoAlunoService cursoAlunoService;
    
    //<editor-fold defaultstate="collapsed" desc="Curso">
    @Test
    public void deveSalvarRascunhoCurso(){
        Curso curso = obterCursoValida();
        curso.setSituacao(SituacaoCurso.RASCUNHO);
        curso.setEtapas(new HashSet<Etapa>());
        
        curso = cursoService.salvarCurso(curso);
        assertNotNull(curso.getId());
        assertEquals(dao.getById(Curso.class, curso.getId()), curso);
        List<Etapa> etapas = dao.query("select e from Etapa e where e.curso.id = "+curso.getId().toString());
        assertTrue(etapas.isEmpty());
    }
    
    @Test
    public void deveEditarCurso(){        
        Curso cursoAtual = cursoService.buscarCursoPorId(1L);
        assertEquals(cursoAtual.getNome(), "Curso História");
        
        cursoAtual.setNome("Curso Nome Novo");
        cursoService.salvarCurso(cursoAtual);
        
        Curso cursoEditado = cursoService.buscarCursoPorId(1L);
        assertEquals(cursoEditado.getNome(), "Curso Nome Novo");
    }
    
    @Test
    public void deveCopiarCurso(){        
        Curso copia = cursoService.copiarCurso("nome copia", 1L, 1L);
        assertEquals("AssuntoH", copia.getAssuntoGeral());
        assertEquals("ABCDE12345", copia.getCodAcesso());
    }
    
    @Test
    public void deveSalvarCurso(){
        Curso curso = obterCursoValida();
        
        curso = cursoService.salvarCurso(curso);
        assertNotNull(curso.getId());
        assertEquals(dao.getById(Curso.class, curso.getId()), curso);
    }
    
    @Test
    public void deveRetornarCursoPorId(){
        Curso curso = cursoService.buscarCursoPorId(1L);
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
        
        cursoService.excluirCurso(1L);
        
        List<Curso> cursoExcluida = dao.query("select c from Curso c where c.id = 1");
        assertTrue(cursoExcluida.isEmpty());
        List<Etapa> etapasExcluida = dao.query("select e from Etapa e where e.curso.id = 1");
        assertTrue(etapasExcluida.isEmpty());
    }
    
    @Test
    public void deveRetornarCursoPorUsuario(){
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(1L, null, null, null, null, null);
        assertTrue(cursos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorParteDaDescricao(){
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(null, "Matemática", null, null, null, null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorCategoria(){
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(null, null, 1L, null, null, null);
        assertTrue(cursos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoPorSituacao(){
        List<Curso> cursos = cursoService.buscarCursoPorFiltro(null, null, null, SituacaoCurso.CONCLUIDA, null, 1L);
        assertTrue(cursos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L, 3L));
        for (Curso c : cursos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoConcluidoPorId(){
        Curso curso = cursoService.buscarCursoPorIdConcluido(1L);
        assertNotNull(curso);
        assertTrue(curso.getEtapas().size() == 2);
        for (Etapa etapa : curso.getEtapas()) {
            assertTrue(etapa.getEtapasPerguntas().size() == 1);
        }
    }
    
    @Test
    public void deveRetornarEtapaPorId(){
        Etapa etapa = cursoService.buscarEtapaPorId(1L, true);
        assertNotNull(etapa);
        assertTrue(etapa.getEtapasPerguntas().size() == 1);
    }
    
    @Test
    public void deveUpdateDisponibilidadeCurso(){
        Curso cursoAntes = cursoService.buscarCursoPorId(1L);
        assertEquals(DisponibilidadeCurso.FECHADO, cursoAntes.getDisponibilidade());
        
        cursoService.updateDisponibilidadeCurso(1L, DisponibilidadeCurso.ABERTO);
       
        Curso cursoDepois = cursoService.buscarCursoPorId(1L);
        assertEquals(DisponibilidadeCurso.ABERTO, cursoDepois.getDisponibilidade());
    }
    
    @Test
    public void deveAddAlunosAoCurso(){
        List<CursoAluno> alunosAntes = cursoAlunoService.buscarCursoAlunoPorIdCurso(2L);
        assertEquals(2, alunosAntes.size());
        
        List<Long> ids = new ArrayList<>(Arrays.asList(3L));
        cursoService.addAlunosAoCurso(2L, ids);
        
        List<CursoAluno> alunosDepois = cursoAlunoService.buscarCursoAlunoPorIdCurso(2L);
        assertEquals(3, alunosDepois.size());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Etapa">
    @Test
    public void deveSalvarEtapa(){
        Curso curso = new Curso();
        curso.setId(1L);
        Etapa etapa = obterEtapaValida1();
        etapa.setCurso(curso);
        
        etapa = cursoService.salvarEtapa(etapa);
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
        
        cursoService.salvarEtapa(etapaAtual);
        
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
        
        cursoService.excluirEtapa(1L, 1L);
        
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
        List<Etapa> etapas = cursoService.buscarEtapa(1L, null);
        assertTrue(etapas.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 2L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    
    @Test
    public void deveRetornarEtapaPorNivel(){
        List<Etapa> etapas = cursoService.buscarEtapa(null, 1);
        assertTrue(etapas.size()==3);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 3L, 4L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    
    @Test
    public void deveRetornarEtapaPorIdCursoNivel(){
        List<Etapa> etapas = cursoService.buscarEtapa(1L, 1);
        assertTrue(etapas.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
        for (Etapa e : etapas) {
            assertTrue(ids.contains(e.getId()));
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Aviso">
    @Test
    public void deveSalvarAviso(){
        Aviso aviso = new Aviso();
        aviso.setTitulo("Titulo1");
        aviso.setDescricao("Descricao1");
        aviso.setDataModificao(new Date());
        aviso.setCurso(new Curso(1L));
        aviso = cursoService.salvarAviso(aviso);
        assertNotNull(aviso.getId());
        assertEquals(dao.getById(Aviso.class, aviso.getId()), aviso);
    }
    
    @Test
    public void deveExcluirAvisoPorId(){
        Aviso avisoAntes = dao.getById(Aviso.class, 1L);
        assertNotNull(avisoAntes);
        
        cursoService.excluirAviso(1L);
        
        Aviso avisoDepos = dao.getById(Aviso.class, 1L);
        assertNull(avisoDepos);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Curso obterCursoValida() {
        Set<Etapa> etapas = new HashSet<>();
        etapas.add(obterEtapaValida1());
        etapas.add(obterEtapaValida2());
        
        
        Curso curso = CursoBuilder.nova()
                .comUsuario(dao.getById(Usuario.class, 1L))
                .comCategoria(new Categoria(1L))
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
                .comJogo(Jogo.FORCA)
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
