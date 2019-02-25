package tcc.service.impl;

import tcc.service.persistence.SimpleTestDao;
import tcc.test.IntegrationBaseTestClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;
import tcc.common.business.PremioService;
import tcc.common.entity.Premio;
import tcc.common.entity.Usuario;
import tcc.test.builder.PremioBuilder;

@DataSet("/datasets/PremioServiceTest.xml")
public class PremioServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("PremioServiceImpl")
    private PremioService premioService;
    
    //<editor-fold defaultstate="collapsed" desc="Premio">
    @Test
    public void deveCadastrarPremioVazio(){
        Premio premioNaoExiste = premioService.buscarPremioPorFiltro(1L);
        assertNull(premioNaoExiste.getId());
        
        premioService.cadastrarPremio(new Usuario(1L));
        
        Premio premioExiste = premioService.buscarPremioPorFiltro(1L);
        assertNotNull(premioExiste.getId());
    }
    
    @Test
    public void deveRetornarPremioPorIdPremio(){
        Premio premio = premioService.buscarPremioPorId(1L);
        assertNotNull(premio);
        assertEquals(Long.valueOf("1"), premio.getId());
    }
    
    @Test
    public void deveRetornarPremioPorIdUsuario(){
        Premio premio = premioService.buscarPremioPorFiltro(2L);
        assertNotNull(premio);
        assertEquals(Long.valueOf("1"), premio.getId());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Entidades Validas">
    private Premio obterPremioValido() {
        Premio premio = PremioBuilder.novo()
                .comQntCursoConcluido(0)
                .comQntEtapaSemDica(0)
                .comQntEtapaSemPulo(0)
                .comQntEtapaVencida(0)
                .comQntPontosAcumulados(0)
                .comUsuario(new Usuario(1L))
                .build();
        return premio;
    }
    //</editor-fold>
}
