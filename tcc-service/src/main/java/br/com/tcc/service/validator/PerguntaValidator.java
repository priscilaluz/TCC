package br.com.tcc.service.validator;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.exception.PendencyManager;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class PerguntaValidator {

    public void validarSalvarPergunta(Pergunta pergunta){
        AssertUtils.assertNotNull(pergunta, ConstantesI18N.PERGUNTA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(pergunta.getUsuario()).orRegister(ConstantesI18N.PERGUNTA_USUARIO_OBRIGATORIA);
        pm.assertNotNull(pergunta.getCategoria()).orRegister(ConstantesI18N.PERGUNTA_CATEGORIA_OBRIGATORIA);
        pm.assertNotNull(pergunta.getDescricao()).orRegister(ConstantesI18N.PERGUNTA_DESCRICAO_OBRIGATORIA);
        pm.assertNotEmpty(pergunta.getRespostas()).orRegister(ConstantesI18N.PERGUNTA_RESPOSTA_OBRIGATORIA);
        pm.verifyAll();
    }
    
}
