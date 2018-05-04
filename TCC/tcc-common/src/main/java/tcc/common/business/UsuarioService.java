package tcc.common.business;

import java.util.List;
import tcc.common.entity.Usuario;
import tcc.common.enums.TipoUsuario;
import tcc.common.vo.DadoProfessor;

/**
 *
 * @author ADM
 */
public interface UsuarioService {
    
    Usuario salvarUsuario(Usuario usuario);
    
    Usuario buscarUsuarioPorLoginSenha(String login, String senha);
    
    List<Usuario> buscarUsuarios(String nome, TipoUsuario tipo, Long idCurso);
    
    Usuario buscarProfessorPorId(Long id);
    
    void excluirProfessores(Long idProfessores);
    
    DadoProfessor dadosProfessor(Long idProfessores);
    
}