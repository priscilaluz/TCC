package tcc.service.validator;

import tcc.common.entity.Curso;
import tcc.common.entity.Pergunta;
import tcc.common.entity.Usuario;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import tcc.common.support.AssertUtils;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    public void validarSalvarUsuario(Usuario usuario, Long emailJaExistente, Long loginJaExistente){
        AssertUtils.assertNotNull(usuario, ConstantesI18N.USUARIO_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotEmpty(usuario.getNome()).orRegister(ConstantesI18N.USUARIO_NOME_OBRIGATORIA);
        pm.assertNotEmpty(usuario.getEmail()).orRegister(ConstantesI18N.USUARIO_EMAIL_OBRIGATORIA);
        pm.assertNotEmpty(usuario.getLogin()).orRegister(ConstantesI18N.USUARIO_LOGIN_OBRIGATORIA);
        pm.assertNotEmpty(usuario.getSenha()).orRegister(ConstantesI18N.USUARIO_SENHA_OBRIGATORIA);
        pm.assertThat(emailJaExistente==0).orRegister(ConstantesI18N.USUARIO_EMAIL_JA_CADASTRADO);
        pm.assertThat(loginJaExistente==0).orRegister(ConstantesI18N.USUARIO_LOGIN_JA_CADASTRADO);
        pm.verifyAll();
    }

    public void validarExcluirUsuario(List<Pergunta> perguntas, List<Curso> cursos){
        PendencyManager pm = new PendencyManager();
        pm.assertThat(perguntas.isEmpty()).orRegister(ConstantesI18N.PROFESSOR_EXCLUIR_PERGUNTA);
        pm.assertThat(cursos.isEmpty()).orRegister(ConstantesI18N.PROFESSOR_EXCLUIR_CURSO);
        pm.verifyAll();
    }
    
}
