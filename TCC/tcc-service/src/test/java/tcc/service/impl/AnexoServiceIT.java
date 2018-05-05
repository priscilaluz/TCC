package tcc.service.impl;

import tcc.common.entity.Anexo;
import tcc.service.persistence.SimpleTestDao;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.AnexoBuilder;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;
import tcc.common.business.AnexoService;

@DataSet("/datasets/AnexoServiceTest.xml")
public class AnexoServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("AnexoServiceImpl")
    private AnexoService anexoService;
    
    //<editor-fold defaultstate="collapsed" desc="Anexo">
//    @Test
//    public void deveSalvarAnexo(){
//        Anexo anexo = obterAnexoValida();
//        
//        anexo = anexoService.salvarAnexo(anexo);
//        assertNotNull(anexo.getId());
//        assertEquals(dao.getById(Anexo.class, anexo.getId()), anexo);
//    }
    
    @Test
    public void deveRetornarAnexoPorId(){
        Anexo anexo = anexoService.buscarAnexoPorId(1L);
        assertNotNull(anexo);
    }
    
//    @Test
//    public void deveExcluirAnexoPorId(){
//        List<Anexo> anexo = dao.query("select c from Anexo c where c.id = 1");
//        assertTrue(anexo.size() == 1);
//        
//        anexoService.excluirAnexo(1L);
//        
//        List<Anexo> anexoExcluida = dao.query("select c from Anexo c where c.id = 1");
//        assertTrue(anexoExcluida.isEmpty());
//    }
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
