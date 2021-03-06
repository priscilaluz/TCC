package tcc.service.validator;

import tcc.common.entity.Curso;
import tcc.common.entity.Etapa;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class CursoValidator {

    public void validarSalvarCurso(Curso curso){
        AssertUtils.assertNotNull(curso, ConstantesI18N.CURSO_OBRIGATORIO);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(curso.getUsuario()).orRegister(ConstantesI18N.CURSO_USUARIO_OBRIGATORIO);
        pm.assertNotNull(curso.getNome()).orRegister(ConstantesI18N.CURSO_NOME_OBRIGATORIO);
        pm.assertNotNull(curso.getCodAcesso()).orRegister(ConstantesI18N.CURSO_CODACESSO_OBRIGATORIO);
        pm.assertNotNull(curso.getCategoria()).orRegister(ConstantesI18N.CURSO_CATEGORIA_OBRIGATORIA);
        pm.assertNotNull(curso.getSituacao()).orRegister(ConstantesI18N.CURSO_SITUACAO_OBRIGATORIA);
        pm.verifyAll();
    }

    public void validarSalvarEtapa(Etapa etapa){
        AssertUtils.assertNotNull(etapa, ConstantesI18N.ETAPA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(etapa.getAssunto()).orRegister(ConstantesI18N.ETAPA_ASSUNTO_OBRIGATORIA);
        pm.assertNotNull(etapa.getNivel()).orRegister(ConstantesI18N.ETAPA_NIVEL_OBRIGATORIA);
        pm.assertNotNull(etapa.getJogo()).orRegister(ConstantesI18N.ETAPA_JOGO_OBRIGATORIA);
        pm.assertNotNull(etapa.getCurso()).orRegister(ConstantesI18N.ETAPA_CURSO_OBRIGATORIA);
        pm.assertNotEmpty(etapa.getEtapasPerguntas()).orRegister(ConstantesI18N.ETAPA_PERGUNTAS_OBRIGATORIA);
        pm.verifyAll();
    }
}
