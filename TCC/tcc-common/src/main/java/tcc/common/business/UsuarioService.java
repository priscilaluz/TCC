package tcc.common.business;

import java.util.List;
import tcc.common.entity.Usuario;
import tcc.common.vo.DadoProfessor;

/**
 *
 * @author ADM
 */
public interface UsuarioService {
    
    Usuario salvarUsuario(Usuario usuario);
    
    Usuario buscarUsuarioPorLoginSenha(String login, String senha);
    
    List<Usuario> buscarProfessores(String nome);
    
    Usuario buscarProfessorPorId(Long id);
    
    void excluirProfessores(Long idProfessores);
    
    DadoProfessor dadosProfessor(Long idProfessores);
    
}