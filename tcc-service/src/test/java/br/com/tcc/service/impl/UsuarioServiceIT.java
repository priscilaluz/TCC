package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.TipoUsuario;
import br.com.tcc.service.persistence.SimpleTestDao;
import br.com.tcc.service.query.BuscarUsuario;
import br.com.tcc.test.IntegrationBaseTestClass;
import br.com.tcc.test.builder.UsuarioBuilder;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet("/datasets/UsuarioServiceTest.xml")
public class UsuarioServiceIT extends IntegrationBaseTestClass{
    
    @SpringBeanByType
    private SimpleTestDao dao;
    
    @SpringBean("UsuarioServiceImpl")
    private UsuarioServiceImpl usuarioServiceImpl;
    
    @Test
    public void deveSalvarUsuario(){
        Usuario usuario = obterUsuarioValido();
        
        usuario = usuarioServiceImpl.salvarUsuario(usuario);
        assertNotNull(usuario.getId());
        assertEquals(dao.getById(Usuario.class, usuario.getId()), usuario);
    }
    
    @Test
    public void deveRetornarUsuariosPorLogin(){
        List<Usuario> usuarios = dao.executeBusinnesQuery(new BuscarUsuario.Entities().whereLogin("JoaoL"));
        assertTrue(usuarios.size()==1);
    }
    
    @Test
    public void deveRetornarUsuariosPorEmail(){
        List<Usuario> usuarios = dao.executeBusinnesQuery(new BuscarUsuario.Entities().whereEmail("joao@email.com"));
        assertTrue(usuarios.size()==1);
    }
    
    @Test
    public void deveRetornarUsuarioPorLoginSenha(){
        Usuario usuario = usuarioServiceImpl.buscarUsuarioPorLoginSenha("JoaoL", "1234");
        assertNotNull(usuario);
        assertTrue(usuario.getId().equals(1L));
    }
    
    private Usuario obterUsuarioValido() {
        Usuario categoria = UsuarioBuilder.nova()
                .comNome("Usuario 1")
                .comEmail("teste@a.com")
                .comLogin("TesteLogin")
                .comSenha("1234")
                .comTipoUsuario(TipoUsuario.PROFESSOR)
                .build();
        return categoria;
    }
    
}
