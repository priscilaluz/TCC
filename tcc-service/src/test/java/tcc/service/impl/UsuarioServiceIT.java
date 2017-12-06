package tcc.service.impl;

import tcc.common.entity.Usuario;
import tcc.common.enums.Avatar;
import tcc.common.enums.TipoUsuario;
import tcc.service.persistence.SimpleTestDao;
import tcc.service.query.BuscarUsuario;
import tcc.test.IntegrationBaseTestClass;
import tcc.test.builder.UsuarioBuilder;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void deveSalvarProfessor(){
        Usuario usuario = obterUsuarioValido();
        
        usuario = usuarioServiceImpl.salvarProfessor(usuario);
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
    public void deveBuscarTodosProfessores(){        
        List<Usuario> usuarios = usuarioServiceImpl.buscarProfessores(null);
        assertTrue(usuarios.size()==2);
        List<Long> ids = new ArrayList<>(Arrays.asList(1L, 3L));
        for (Usuario u : usuarios) {
            assertTrue(ids.contains(u.getId()));
        }
    }
    
    @Test
    public void deveBuscarProfessoresPorNome(){        
        List<Usuario> usuarios = usuarioServiceImpl.buscarProfessores("Cla");
        assertTrue(usuarios.size()==1);
        List<Long> ids = new ArrayList<>(Arrays.asList(3L));
        for (Usuario u : usuarios) {
            assertTrue(ids.contains(u.getId()));
        }
    }
    
    @Test
    public void deveBuscarProfessorPorId(){
        Usuario usuario = usuarioServiceImpl.buscarProfessorPorId(1L);
        assertNotNull(usuario);
        assertTrue(usuario.getId().equals(1L));
    }
    
    @Test
    public void deveExcluirProfessorePorId(){
        List<Usuario> professores = dao.query("select p from Usuario p where p.id = 3");
        assertTrue(professores.size() == 1);
        
        usuarioServiceImpl.excluirProfessores(3L);
        
        List<Usuario> professoresExcluida = dao.query("select p from Usuario p where p.id = 3");
        assertTrue(professoresExcluida.isEmpty());
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
                .comAvatar(Avatar.PESSOA)
                .comTipoUsuario(TipoUsuario.PROFESSOR)
                .build();
        return categoria;
    }
    
}
