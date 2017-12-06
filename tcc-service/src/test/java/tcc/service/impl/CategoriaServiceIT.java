package tcc.service.impl;

import tcc.common.entity.Categoria;
import tcc.common.entity.Usuario;
import tcc.common.enums.TipoUsuario;
import tcc.service.persistence.SimpleTestDao;
import tcc.service.query.BuscarUsuario;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.CategoriaBuilder;
import tcc.test.builder.UsuarioBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/CategoriaServiceTest.xml")
public class CategoriaServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("CategoriaServiceImpl")
    private CategoriaServiceImpl categoriaServiceImpl;
    
    @Test
    public void deveSalvarCategoria(){
        Categoria categoria = obterCategoriaValida();
        
        categoria = categoriaServiceImpl.salvarCategoria(categoria);
        assertNotNull(categoria.getId());
        assertEquals(dao.getById(Categoria.class, categoria.getId()), categoria);
    }
    
    @Test
    public void deveBuscarCategoriaPorNome(){        
        List<Categoria> categorias = categoriaServiceImpl.buscarCategoriaPorFiltro("ue");
        assertTrue(categorias.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(2L, 4L));
        for (Categoria c : categorias) {
            assertTrue(ids.contains(c.getId()));
        }
    }
    
    @Test
    public void deveBuscarProfessorPorId(){
        Categoria categoria = categoriaServiceImpl.buscarCategoriaPorId(1L);
        assertNotNull(categoria);
        assertTrue(categoria.getId().equals(1L));
    }
    
    @Test
    public void deveExcluirProfessorePorId(){
        List<Categoria> categorias = dao.query("select c from Categoria c where c.id = 3");
        assertTrue(categorias.size() == 1);
        
        categoriaServiceImpl.excluirCategoria(3L);
        
        List<Usuario> professoresExcluida = dao.query("select c from Categoria c where c.id = 3");
        assertTrue(professoresExcluida.isEmpty());
    }
    
    private Categoria obterCategoriaValida() {
        Categoria categoria = CategoriaBuilder.nova()
                .comNome("Nome")
                .build();
        return categoria;
    }
    
}
