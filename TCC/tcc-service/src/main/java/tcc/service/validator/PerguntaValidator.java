package tcc.service.validator;

import tcc.common.entity.Pergunta;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class PerguntaValidator {

    public void validarSalvarPergunta(Pergunta pergunta, Long qntEtapasPerguntas){
        AssertUtils.assertNotNull(pergunta, ConstantesI18N.PERGUNTA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(pergunta.getUsuario()).orRegister(ConstantesI18N.PERGUNTA_USUARIO_OBRIGATORIA);
        pm.assertNotNull(pergunta.getCategoria()).orRegister(ConstantesI18N.PERGUNTA_CATEGORIA_OBRIGATORIA);
        pm.assertNotNull(pergunta.getDescricao()).orRegister(ConstantesI18N.PERGUNTA_DESCRICAO_OBRIGATORIA);
        pm.assertNotNull(pergunta.getTipo()).orRegister(ConstantesI18N.PERGUNTA_TIPO_OBRIGATORIA);
        pm.assertNotNull(pergunta.getNivel()).orRegister(ConstantesI18N.PERGUNTA_NIVEL_OBRIGATORIA);
        pm.assertNotEmpty(pergunta.getRespostas()).orRegister(ConstantesI18N.PERGUNTA_RESPOSTA_OBRIGATORIA);
        pm.verifyAll();
    }
    
}
