package br.com.tcc.service.validator;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.exception.PendencyManager;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class AnexoValidator {

    public void validarSalvarAnexo(Anexo anexo){
        AssertUtils.assertNotNull(anexo, ConstantesI18N.ANEXO_OBRIGATORIO);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(anexo.getNomeArquivo()).orRegister(ConstantesI18N.ANEXO_NOME_ARQUIVO_OBRIGATORIO);
        pm.assertThat(anexo.getBytes()!=null).orRegister(ConstantesI18N.ANEXO_BYTES_OBRIGATORIO);
        pm.verifyAll();
    }
}
