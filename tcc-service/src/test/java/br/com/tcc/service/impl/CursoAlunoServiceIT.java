package br.com.tcc.service.impl;

import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.PerguntaEtapaAluno;
import br.com.tcc.common.entity.RelatorioEtapa;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.SituacaoCursoAluno;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.vo.TabuleiroCurso;
import br.com.tcc.common.vo.TabuleiroEtapa;
import br.com.tcc.common.vo.TdHtmlEtapa;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.PerguntaEtapaAlunoBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/CursoAlunoServiceTest.xml")
public class CursoAlunoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("CursoAlunoServiceImpl")
    private CursoAlunoServiceImpl cursoAlunoServiceImpl;
    
    //<editor-fold defaultstate="collapsed" desc="CursoAluno">
    @Test
    public void deveEntrarNoCurso(){
        CursoAluno cursoAluno = cursoAlunoServiceImpl.entrarCurso(1L, 1L, "ABCDE12345");
        assertNotNull(cursoAluno.getId());
        assertEquals(dao.getById(CursoAluno.class, cursoAluno.getId()), cursoAluno);
    }
    
    @Test
    public void deveRetornarCursoAlunoPorUsuario(){
        List<CursoAluno> cursosAlunos = cursoAlunoServiceImpl.buscarCursoAlunoPorAlunoSituacao(3L, null);
        assertTrue(cursosAlunos.size()==4);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 3L, 4L, 5L));
        for (CursoAluno c : cursosAlunos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarCursoAlunoPorSituacao(){
        List<CursoAluno> cursosAlunos = cursoAlunoServiceImpl.buscarCursoAlunoPorAlunoSituacao(null, SituacaoCursoAluno.EM_ANDAMENTO);
        assertTrue(cursosAlunos.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 3L));
        for (CursoAluno c : cursosAlunos) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveRetornarAlunoPorIdCursoAluno1(){
        TabuleiroCurso tabuleiroCursoAtual = cursoAlunoServiceImpl.buscarCursoAlunoPorIdCursoAluno(1L);
        TabuleiroCurso tabuleiroEsperado = obterTabuleiroCursoCorreto1();
        validarTabuleiro(tabuleiroCursoAtual, tabuleiroEsperado);
    }
    
    @Test
    public void deveRetornarAlunoPorIdCursoAluno2(){
        TabuleiroCurso tabuleiroCursoAtual = cursoAlunoServiceImpl.buscarCursoAlunoPorIdCursoAluno(2L);
        TabuleiroCurso tabuleiroEsperado = obterTabuleiroCursoCorreto2();
        validarTabuleiro(tabuleiroCursoAtual, tabuleiroEsperado);
    }
    
    @Test
    public void deveRetornarAlunoPorIdCursoAluno3(){
        TabuleiroCurso tabuleiroCursoAtual = cursoAlunoServiceImpl.buscarCursoAlunoPorIdCursoAluno(3L);
        TabuleiroCurso tabuleiroEsperado = obterTabuleiroCursoCorreto3();
        validarTabuleiro(tabuleiroCursoAtual, tabuleiroEsperado);
    }
    
    @Test
    public void deveRetornarAlunoPorIdCursoAluno4(){
        TabuleiroCurso tabuleiroCursoAtual = cursoAlunoServiceImpl.buscarCursoAlunoPorIdCursoAluno(4L);
        TabuleiroCurso tabuleiroEsperado = obterTabuleiroCursoCorreto4();
        validarTabuleiro(tabuleiroCursoAtual, tabuleiroEsperado);
    }
    
    @Test
    public void deveRetornarAlunoPorIdCursoAluno5(){
        TabuleiroCurso tabuleiroCursoAtual = cursoAlunoServiceImpl.buscarCursoAlunoPorIdCursoAluno(5L);
        TabuleiroCurso tabuleiroEsperado = obterTabuleiroCursoCorreto5();
        validarTabuleiro(tabuleiroCursoAtual, tabuleiroEsperado);
    }
    
    @Test
    public void deveRetornarEtapaAlunoPorCursoAlunoEEtapaNovo(){
        EtapaAluno etapaAluno = cursoAlunoServiceImpl.buscarEtapaAlunoPorCursoAlunoEEtapa(null, 1L, 2L);
        assertNotNull(etapaAluno);
        assertTrue(etapaAluno.getId()==null);
    }
    
    @Test
    public void deveRetornarEtapaAlunoPorCursoAlunoEEtapa(){
        EtapaAluno etapaAluno = cursoAlunoServiceImpl.buscarEtapaAlunoPorCursoAlunoEEtapa(null, 1L, 1L);
        assertNotNull(etapaAluno);
        assertEquals(new Long(1), etapaAluno.getId());
    }
    
    @Test
    public void deveRetornarEtapaAlunoPorEtapaAlunoId(){
        EtapaAluno etapaAluno = cursoAlunoServiceImpl.buscarEtapaAlunoPorCursoAlunoEEtapa(1L, null, null);
        assertNotNull(etapaAluno);
        assertEquals(new Long(1), etapaAluno.getId());
    }
    
    @Test
    public void deveSalvarEtapaAluno(){
        EtapaAluno etapaAluno = cursoAlunoServiceImpl.buscarEtapaAlunoPorCursoAlunoEEtapa(null, 1L, 2L);
        assertNotNull(etapaAluno);
        assertTrue(etapaAluno.getId()==null);
        
        cursoAlunoServiceImpl.salvarEtapaAluno(1L, 2L);
        
        EtapaAluno etapaAlunaSalva = cursoAlunoServiceImpl.buscarEtapaAlunoPorCursoAlunoEEtapa(null, 1L, 2L);
        assertNotNull(etapaAlunaSalva);
        assertTrue(etapaAlunaSalva.getId()!=null);
    }
    
    @Test
    public void deveSalvarRelatorioEtapa(){
        RelatorioEtapa relatorioEtapa = new RelatorioEtapa();
        relatorioEtapa.setEtapaAluno(new EtapaAluno(1L));
        relatorioEtapa.setPontuacao(100);
        Set<PerguntaEtapaAluno> perguntasEtapasAlunos = new HashSet<>();
        perguntasEtapasAlunos.add(obterPerguntaEtapaAlunoValida1());
        perguntasEtapasAlunos.add(obterPerguntaEtapaAlunoValida2());
        relatorioEtapa.setPerguntasEtapasAlunos(perguntasEtapasAlunos);
        RelatorioEtapa salvo = cursoAlunoServiceImpl.salvarRelatorioEtapa(relatorioEtapa);
        assertNotNull(salvo);
        assertTrue(salvo.getId()!=null);
    }
    //</editor-fold>
    
    private void validarTabuleiro(TabuleiroCurso tabuleirosCursoAtual, TabuleiroCurso tabuleirosEsperado) {
        assertEquals(tabuleirosEsperado.getAluno().getId(), tabuleirosCursoAtual.getAluno().getId());
        assertEquals(tabuleirosEsperado.getAssuntoGeral(), tabuleirosCursoAtual.getAssuntoGeral());
        assertEquals(tabuleirosEsperado.getEtapaAtual(), tabuleirosCursoAtual.getEtapaAtual());
        assertEquals(tabuleirosEsperado.getIdCurso(), tabuleirosCursoAtual.getIdCurso());
        assertEquals(tabuleirosEsperado.getNome(), tabuleirosCursoAtual.getNome());
        assertTrue(tabuleirosEsperado.getTabuleiros().size()==tabuleirosCursoAtual.getTabuleiros().size());
        for (Integer numTabuleiro : tabuleirosEsperado.getTabuleiros().keySet()) {
            List<TdHtmlEtapa> tabuleiroEsperado = tabuleirosEsperado.getTabuleiros().get(numTabuleiro);
            List<TdHtmlEtapa> tabuleiroCursoAtual = tabuleirosCursoAtual.getTabuleiros().get(numTabuleiro);
            assertTrue(tabuleiroEsperado.size()==tabuleiroCursoAtual.size());
            for (int i = 0; i < tabuleiroEsperado.size(); i++) {
                TdHtmlEtapa tdHtmlEtapaEsperado = tabuleiroEsperado.get(i);
                TdHtmlEtapa tdHtmlEtapaAtual = tabuleiroCursoAtual.get(i);
                assertEquals(tdHtmlEtapaEsperado.getEtapa1().getIdEtapa(), tdHtmlEtapaAtual.getEtapa1().getIdEtapa());
                assertEquals(tdHtmlEtapaEsperado.getEtapa1().getImagemDesabilitado(), tdHtmlEtapaAtual.getEtapa1().getImagemDesabilitado());
                assertEquals(tdHtmlEtapaEsperado.getEtapa1().getImagemOff(), tdHtmlEtapaAtual.getEtapa1().getImagemOff());
                assertEquals(tdHtmlEtapaEsperado.getEtapa1().getImagemOn(), tdHtmlEtapaAtual.getEtapa1().getImagemOn());
                assertEquals(tdHtmlEtapaEsperado.getEtapa1().isDesbloquada(), tdHtmlEtapaAtual.getEtapa1().isDesbloquada());

                assertEquals(tdHtmlEtapaEsperado.getEtapa2().getIdEtapa(), tdHtmlEtapaAtual.getEtapa2().getIdEtapa());
                assertEquals(tdHtmlEtapaEsperado.getEtapa2().getImagemDesabilitado(), tdHtmlEtapaAtual.getEtapa2().getImagemDesabilitado());
                assertEquals(tdHtmlEtapaEsperado.getEtapa2().getImagemOff(), tdHtmlEtapaAtual.getEtapa2().getImagemOff());
                assertEquals(tdHtmlEtapaEsperado.getEtapa2().getImagemOn(), tdHtmlEtapaAtual.getEtapa2().getImagemOn());
                assertEquals(tdHtmlEtapaEsperado.getEtapa2().isDesbloquada(), tdHtmlEtapaAtual.getEtapa2().isDesbloquada());

                assertEquals(tdHtmlEtapaEsperado.getEtapa3().getIdEtapa(), tdHtmlEtapaAtual.getEtapa3().getIdEtapa());
                assertEquals(tdHtmlEtapaEsperado.getEtapa3().getImagemDesabilitado(), tdHtmlEtapaAtual.getEtapa3().getImagemDesabilitado());
                assertEquals(tdHtmlEtapaEsperado.getEtapa3().getImagemOff(), tdHtmlEtapaAtual.getEtapa3().getImagemOff());
                assertEquals(tdHtmlEtapaEsperado.getEtapa3().getImagemOn(), tdHtmlEtapaAtual.getEtapa3().getImagemOn());
                assertEquals(tdHtmlEtapaEsperado.getEtapa3().isDesbloquada(), tdHtmlEtapaAtual.getEtapa3().isDesbloquada());

                assertEquals(tdHtmlEtapaEsperado.getEtapa4().getIdEtapa(), tdHtmlEtapaAtual.getEtapa4().getIdEtapa());
                assertEquals(tdHtmlEtapaEsperado.getEtapa4().getImagemDesabilitado(), tdHtmlEtapaAtual.getEtapa4().getImagemDesabilitado());
                assertEquals(tdHtmlEtapaEsperado.getEtapa4().getImagemOff(), tdHtmlEtapaAtual.getEtapa4().getImagemOff());
                assertEquals(tdHtmlEtapaEsperado.getEtapa4().getImagemOn(), tdHtmlEtapaAtual.getEtapa4().getImagemOn());
                assertEquals(tdHtmlEtapaEsperado.getEtapa4().isDesbloquada(), tdHtmlEtapaAtual.getEtapa4().isDesbloquada());

                assertEquals(tdHtmlEtapaEsperado.getEtapa5().getIdEtapa(), tdHtmlEtapaAtual.getEtapa5().getIdEtapa());
                assertEquals(tdHtmlEtapaEsperado.getEtapa5().getImagemDesabilitado(), tdHtmlEtapaAtual.getEtapa5().getImagemDesabilitado());
                assertEquals(tdHtmlEtapaEsperado.getEtapa5().getImagemOff(), tdHtmlEtapaAtual.getEtapa5().getImagemOff());
                assertEquals(tdHtmlEtapaEsperado.getEtapa5().getImagemOn(), tdHtmlEtapaAtual.getEtapa5().getImagemOn());
                assertEquals(tdHtmlEtapaEsperado.getEtapa5().isDesbloquada(), tdHtmlEtapaAtual.getEtapa5().isDesbloquada());
            }
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private TabuleiroCurso obterTabuleiroCursoCorreto1() {
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setNome("Curso História");
        tabuleiroCurso.setIdCurso(1L);
        tabuleiroCurso.setEtapaAtual(1);
        tabuleiroCurso.setAssuntoGeral("AssuntoH");
        tabuleiroCurso.setAluno(new Usuario(3L));
        List<TdHtmlEtapa> tdHtmlEtapas = new ArrayList<>();
        TdHtmlEtapa tdHtmlEtapa = new TdHtmlEtapa();
        tdHtmlEtapa.setEtapa1(objTabuleiroEtapa(1L, true, "1"));
        tdHtmlEtapa.setEtapa2(objTabuleiroEtapa(2L, false, "15"));
        tdHtmlEtapa.setEtapa3(new TabuleiroEtapa());
        tdHtmlEtapa.setEtapa4(new TabuleiroEtapa());
        tdHtmlEtapa.setEtapa5(new TabuleiroEtapa());
        tdHtmlEtapas.add(tdHtmlEtapa);
        
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        tabuleiros.put(1, tdHtmlEtapas);
        tabuleiroCurso.setTabuleiros(tabuleiros);
        return tabuleiroCurso;
    }
    
    private TabuleiroCurso obterTabuleiroCursoCorreto2() {
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setEtapaAtual(1);
        tabuleiroCurso.setIdCurso(2L);
        tabuleiroCurso.setNome("Curso Matemática");
        tabuleiroCurso.setAssuntoGeral("AssuntoM");
        tabuleiroCurso.setAluno(new Usuario(4L));
        List<TdHtmlEtapa> tdHtmlEtapas = new ArrayList<>();
        TdHtmlEtapa tdHtmlEtapa = new TdHtmlEtapa();
        tdHtmlEtapa.setEtapa1(objTabuleiroEtapa(3L, true, "19"));
        tdHtmlEtapa.setEtapa2(new TabuleiroEtapa());
        tdHtmlEtapa.setEtapa3(new TabuleiroEtapa());
        tdHtmlEtapa.setEtapa4(new TabuleiroEtapa());
        tdHtmlEtapa.setEtapa5(new TabuleiroEtapa());
        tdHtmlEtapas.add(tdHtmlEtapa);
        
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        tabuleiros.put(1, tdHtmlEtapas);
        
        tabuleiroCurso.setTabuleiros(tabuleiros);
        return tabuleiroCurso;
    }
    
    private TabuleiroCurso obterTabuleiroCursoCorreto3() {
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setEtapaAtual(3);
        tabuleiroCurso.setIdCurso(3L);
        tabuleiroCurso.setNome("Curso Geografia");
        tabuleiroCurso.setAssuntoGeral("AssuntoG");
        tabuleiroCurso.setAluno(new Usuario(3L));
        
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        
        List<TdHtmlEtapa> tdHtmlEtapas1 = new ArrayList<>();
        TdHtmlEtapa tdHtmlEtapa1 = new TdHtmlEtapa();
        tdHtmlEtapa1.setEtapa1(objTabuleiroEtapa(4L, true, "1"));
        tdHtmlEtapa1.setEtapa2(objTabuleiroEtapa(5L, true, "2"));
        tdHtmlEtapa1.setEtapa3(objTabuleiroEtapa(6L, true, "3"));
        tdHtmlEtapa1.setEtapa4(objTabuleiroEtapa(7L, false, "4"));
        tdHtmlEtapa1.setEtapa5(objTabuleiroEtapa(8L, false, "5"));
        tdHtmlEtapas1.add(tdHtmlEtapa1);
        
        TdHtmlEtapa tdHtmlEtapa2 = new TdHtmlEtapa();
        tdHtmlEtapa2.setEtapa1(objTabuleiroEtapa(13L, false, "10"));
        tdHtmlEtapa2.setEtapa2(objTabuleiroEtapa(12L, false, "9"));
        tdHtmlEtapa2.setEtapa3(objTabuleiroEtapa(11L, false, "8"));
        tdHtmlEtapa2.setEtapa4(objTabuleiroEtapa(10L, false, "7"));
        tdHtmlEtapa2.setEtapa5(objTabuleiroEtapa(9L, false, "6"));
        tdHtmlEtapas1.add(tdHtmlEtapa2);
        
        TdHtmlEtapa tdHtmlEtapa3 = new TdHtmlEtapa();
        tdHtmlEtapa3.setEtapa1(objTabuleiroEtapa(14L, false, "11"));
        tdHtmlEtapa3.setEtapa2(objTabuleiroEtapa(15L, false, "12"));
        tdHtmlEtapa3.setEtapa3(objTabuleiroEtapa(16L, false, "13"));
        tdHtmlEtapa3.setEtapa4(objTabuleiroEtapa(17L, false, "14"));
        tdHtmlEtapa3.setEtapa5(objTabuleiroEtapa(18L, false, "2"));
        tdHtmlEtapas1.add(tdHtmlEtapa3);
        
        tabuleiros.put(1, tdHtmlEtapas1);
        List<TdHtmlEtapa> tdHtmlEtapas2 = new ArrayList<>();
        
        TdHtmlEtapa tdHtmlEtapa4 = new TdHtmlEtapa();
        tdHtmlEtapa4.setEtapa1(objTabuleiroEtapa(19L, false, "2"));
        tdHtmlEtapa4.setEtapa2(objTabuleiroEtapa(20L, false, "2"));
        tdHtmlEtapa4.setEtapa3(objTabuleiroEtapa(21L, false, "3"));
        tdHtmlEtapa4.setEtapa4(objTabuleiroEtapa(22L, false, "4"));
        tdHtmlEtapa4.setEtapa5(objTabuleiroEtapa(23L, false, "5"));
        tdHtmlEtapas2.add(tdHtmlEtapa4);
        
        TdHtmlEtapa tdHtmlEtapa5 = new TdHtmlEtapa();
        tdHtmlEtapa5.setEtapa1(new TabuleiroEtapa());
        tdHtmlEtapa5.setEtapa2(new TabuleiroEtapa());
        tdHtmlEtapa5.setEtapa3(new TabuleiroEtapa());
        tdHtmlEtapa5.setEtapa4(new TabuleiroEtapa());
        tdHtmlEtapa5.setEtapa5(objTabuleiroEtapa(24L, false, "17"));
        tdHtmlEtapas2.add(tdHtmlEtapa5);
        
        tabuleiros.put(2, tdHtmlEtapas2);
        
        tabuleiroCurso.setTabuleiros(tabuleiros);
        return tabuleiroCurso;
    }
    
    private TabuleiroCurso obterTabuleiroCursoCorreto4() {
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setEtapaAtual(10);
        tabuleiroCurso.setIdCurso(4L);
        tabuleiroCurso.setNome("Curso Teste Tabuleiro");
        tabuleiroCurso.setAssuntoGeral("AssuntoG Curso Teste Tabuleiro");
        tabuleiroCurso.setAluno(new Usuario(3L));
        List<TdHtmlEtapa> tdHtmlEtapas = new ArrayList<>();
        
        TdHtmlEtapa tdHtmlEtapa1 = new TdHtmlEtapa();
        tdHtmlEtapa1.setEtapa1(objTabuleiroEtapa(25L, true, "1"));
        tdHtmlEtapa1.setEtapa2(objTabuleiroEtapa(26L, true, "2"));
        tdHtmlEtapa1.setEtapa3(objTabuleiroEtapa(27L, true, "3"));
        tdHtmlEtapa1.setEtapa4(objTabuleiroEtapa(28L, true, "4"));
        tdHtmlEtapa1.setEtapa5(objTabuleiroEtapa(29L, true, "5"));
        tdHtmlEtapas.add(tdHtmlEtapa1);
        
        TdHtmlEtapa tdHtmlEtapa2 = new TdHtmlEtapa();
        tdHtmlEtapa2.setEtapa1(objTabuleiroEtapa(34L, true, "10"));
        tdHtmlEtapa2.setEtapa2(objTabuleiroEtapa(33L, true, "9"));
        tdHtmlEtapa2.setEtapa3(objTabuleiroEtapa(32L, true, "8"));
        tdHtmlEtapa2.setEtapa4(objTabuleiroEtapa(31L, true, "7"));
        tdHtmlEtapa2.setEtapa5(objTabuleiroEtapa(30L, true, "6"));
        tdHtmlEtapas.add(tdHtmlEtapa2);
        
        TdHtmlEtapa tdHtmlEtapa3 = new TdHtmlEtapa();
        tdHtmlEtapa3.setEtapa1(objTabuleiroEtapa(35L, false, "18"));
        tdHtmlEtapa3.setEtapa2(new TabuleiroEtapa());
        tdHtmlEtapa3.setEtapa3(new TabuleiroEtapa());
        tdHtmlEtapa3.setEtapa4(new TabuleiroEtapa());
        tdHtmlEtapa3.setEtapa5(new TabuleiroEtapa());
        tdHtmlEtapas.add(tdHtmlEtapa3);
        
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        tabuleiros.put(1, tdHtmlEtapas);
        
        tabuleiroCurso.setTabuleiros(tabuleiros);
        return tabuleiroCurso;
    }
    
    private TabuleiroCurso obterTabuleiroCursoCorreto5() {
        TabuleiroCurso tabuleiroCurso = new TabuleiroCurso();
        tabuleiroCurso.setEtapaAtual(4);
        tabuleiroCurso.setIdCurso(8L);
        tabuleiroCurso.setNome("Curso 8");
        tabuleiroCurso.setAssuntoGeral("AssuntoG 8");
        tabuleiroCurso.setAluno(new Usuario(3L));
        List<TdHtmlEtapa> tdHtmlEtapas = new ArrayList<>();
        
        TdHtmlEtapa tdHtmlEtapa1 = new TdHtmlEtapa();
        tdHtmlEtapa1.setEtapa1(objTabuleiroEtapa(36L, true, "1"));
        tdHtmlEtapa1.setEtapa2(objTabuleiroEtapa(37L, true, "2"));
        tdHtmlEtapa1.setEtapa3(objTabuleiroEtapa(38L, true, "3"));
        tdHtmlEtapa1.setEtapa4(objTabuleiroEtapa(39L, true, "4"));
        tdHtmlEtapa1.setEtapa5(objTabuleiroEtapa(40L, false, "5"));
        tdHtmlEtapas.add(tdHtmlEtapa1);
        
        TdHtmlEtapa tdHtmlEtapa2 = new TdHtmlEtapa();
        tdHtmlEtapa2.setEtapa1(new TabuleiroEtapa());
        tdHtmlEtapa2.setEtapa2(new TabuleiroEtapa());
        tdHtmlEtapa2.setEtapa3(new TabuleiroEtapa());
        tdHtmlEtapa2.setEtapa4(objTabuleiroEtapa(42L, false, "16"));
        tdHtmlEtapa2.setEtapa5(objTabuleiroEtapa(41L, false, "6"));
        tdHtmlEtapas.add(tdHtmlEtapa2);
        
        Map<Integer,List<TdHtmlEtapa>> tabuleiros = new HashMap<>();
        tabuleiros.put(1, tdHtmlEtapas);
        
        tabuleiroCurso.setTabuleiros(tabuleiros);
        return tabuleiroCurso;
    }
    
    private TabuleiroEtapa objTabuleiroEtapa(Long idEtapa, boolean desbloqueado, String posicao){
        TabuleiroEtapa e1 = new TabuleiroEtapa();
        e1.setIdEtapa(idEtapa);
        e1.setImagemDesabilitado(ConstantesI18N.TABULEIRO_IMG_DESABILITADO.replace("*", posicao));
        e1.setImagemOff(ConstantesI18N.TABULEIRO_IMG_OFF.replace("*", posicao));
        e1.setImagemOn(ConstantesI18N.TABULEIRO_IMG_ON.replace("*", posicao));
        e1.setDesbloquada(desbloqueado);
        return e1;
    }
    
    private PerguntaEtapaAluno obterPerguntaEtapaAlunoValida1() {
        PerguntaEtapaAluno perguntaEtapaAluno = PerguntaEtapaAlunoBuilder.novo()
                .comPontuacao(100)
                .comDica(Boolean.FALSE)
                .comPulo(Boolean.FALSE)
                .comTempoAcabou(Boolean.FALSE)
                .comPergunta(new Pergunta(1L))
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
                .comPergunta(new Pergunta(2L))
                .comRelatorioEtapa(new RelatorioEtapa())
                .build();
        return perguntaEtapaAluno;
    }
    //</editor-fold>
}
