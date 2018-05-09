package tcc.common.business;

import tcc.common.entity.Usuario;
import tcc.common.enums.TipoUsuario;
import tcc.common.vo.DadoProfessor;
import tcc.common.vo.ListaPaginacao;

/**
 *
 * @author ADM
 */
public interface UsuarioService {
    
    Usuario salvarUsuario(Usuario usuario);
    
    Usuario buscarUsuarioPorLoginSenha(String login, String senha);
    
    ListaPaginacao buscarUsuarios(String nome, TipoUsuario tipo, Long idCurso, Integer paginaAtual);
    
    Usuario buscarProfessorPorId(Long id);
    
    void excluirProfessores(Long idProfessores);
    
    DadoProfessor dadosProfessor(Long idProfessores);
    
}