package br.com.tcc.service.validator;

import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.exception.PendencyManager;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.support.AssertUtils;
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
        if (curso.getSituacao() != null && SituacaoCurso.CONCLUIDA.equals(curso.getSituacao())){
            pm.assertNotEmpty(curso.getEtapas()).orRegister(ConstantesI18N.CURSO_ETAPAS_OBRIGATORIO);
        }
        pm.verifyAll();
    }

    public void validarSalvarEtapa(Etapa etapa){
        AssertUtils.assertNotNull(etapa, ConstantesI18N.ETAPA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(etapa.getAssunto()).orRegister(ConstantesI18N.ETAPA_ASSUNTO_OBRIGATORIA);
        pm.assertNotNull(etapa.getNivel()).orRegister(ConstantesI18N.ETAPA_NIVEL_OBRIGATORIA);
        pm.assertNotNull(etapa.getJogo()).orRegister(ConstantesI18N.ETAPA_JOGO_OBRIGATORIA);
        pm.assertNotNull(etapa.getCurso()).orRegister(ConstantesI18N.ETAPA_CURSO_OBRIGATORIA);
        pm.assertNotEmpty(etapa.getPerguntas()).orRegister(ConstantesI18N.ETAPA_PERGUNTAS_OBRIGATORIA);
        pm.verifyAll();
    }
}
