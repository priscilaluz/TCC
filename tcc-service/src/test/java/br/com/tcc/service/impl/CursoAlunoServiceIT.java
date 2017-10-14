package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.CursoAluno;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import br.com.tcc.common.enums.Jogo;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.enums.SituacaoCursoAluno;
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
        List<CursoAluno> cursosAlunos = cursoAlunoServiceImpl.buscarCursoAlunoPorAlunoSituacao(2L, null);
        assertTrue(cursosAlunos.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L));
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    
    //</editor-fold>
}
