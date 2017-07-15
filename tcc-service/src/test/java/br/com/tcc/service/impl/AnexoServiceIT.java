package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.AnexoBuilder;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/AnexoServiceTest.xml")
public class AnexoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("AnexoServiceImpl")
    private AnexoServiceImpl anexoServiceImpl;
    
    //<editor-fold defaultstate="collapsed" desc="Anexo">
    @Test
    public void deveSalvarAnexo(){
        Anexo anexo = obterAnexoValida();
        
        anexo = anexoServiceImpl.salvarAnexo(anexo);
        assertNotNull(anexo.getId());
        assertEquals(dao.getById(Anexo.class, anexo.getId()), anexo);
    }
    
    @Test
    public void deveRetornarAnexoPorId(){
        Anexo anexo = anexoServiceImpl.buscarAnexoPorId(1L);
        assertNotNull(anexo);
    }
    
    @Test
    public void deveExcluirAnexoPorId(){
        List<Anexo> anexo = dao.query("select c from Anexo c where c.id = 1");
        assertTrue(anexo.size() == 1);
        
        anexoServiceImpl.excluirAnexo(1L);
        
        List<Anexo> anexoExcluida = dao.query("select c from Anexo c where c.id = 1");
        assertTrue(anexoExcluida.isEmpty());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Anexo obterAnexoValida() {
         byte[] bytes = "Teste".getBytes();
        
        Anexo anexo = AnexoBuilder.nova()
                .comNomeArquivo("nomeArquivo.jpg")
                .comBytes(bytes)
                .build();
        return anexo;
    }
    //</editor-fold>
}
