package tcc.service.impl;

import tcc.common.entity.Categoria;
import tcc.common.entity.Usuario;
import tcc.service.persistence.SimpleTestDao;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.CategoriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;
import tcc.common.business.CategoriaService;
import tcc.common.vo.ListaPaginacao;

@DataSet("/datasets/CategoriaServiceTest.xml")
public class CategoriaServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("CategoriaServiceImpl")
    private CategoriaService categoriaService;
    
    @Test
    public void deveSalvarCategoria(){
        Categoria categoria = obterCategoriaValida();
        
        categoria = categoriaService.salvarCategoria(categoria);
        assertNotNull(categoria.getId());
        assertEquals(dao.getById(Categoria.class, categoria.getId()), categoria);
    }
    
    @Test
    public void deveBuscarCategoriaPorNome(){        
        ListaPaginacao listaPaginacao = categoriaService.buscarCategoriaPorFiltro("ue", 0);
        List<Categoria> categorias = (List<Categoria>)(Object)listaPaginacao.getLista();
        assertTrue(categorias.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L, 4L));
        for (Categoria c : categorias) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveBuscarProfessorPorId(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(1L);
        assertNotNull(categoria);
        assertTrue(categoria.getId().equals(1L));
    }
    
    @Test
    public void deveExcluirProfessorePorId(){
        List<Categoria> categorias = dao.query("select c from Categoria c where c.id = 10");
        assertTrue(categorias.size() == 1);
        
        categoriaService.excluirCategoria(10L);
        
        List<Usuario> professoresExcluida = dao.query("select c from Categoria c where c.id = 10");
        assertTrue(professoresExcluida.isEmpty());
    }
    
    private Categoria obterCategoriaValida() {
        Categoria categoria = CategoriaBuilder.nova()
                .comNome("Nome")
                .build();
        return categoria;
    }
    
}
