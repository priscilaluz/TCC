package tcc.service.validator;

import tcc.common.entity.Anexo;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import tcc.common.support.AssertUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AnexoValidator {

    public void validarSalvarAnexo(Anexo anexo) {
        AssertUtils.assertNotNull(anexo, ConstantesI18N.ANEXO_OBRIGATORIO);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(anexo.getNomeArquivo()).orRegister(ConstantesI18N.ANEXO_NOME_ARQUIVO_OBRIGATORIO);
        pm.assertThat(validarFormatoAnexo(anexo.getNomeArquivo())).orRegister(ConstantesI18N.ANEXO_EXTENSAO_INVALIDA);
        pm.assertThat(anexo.getBytes() != null).orRegister(ConstantesI18N.ANEXO_BYTES_OBRIGATORIO);
        pm.verifyAll();
    }

    private boolean validarFormatoAnexo(String nomeArquivo) {
        if (nomeArquivo != null) {
            List<String> extensoesOk = new ArrayList<>(Arrays.asList(".gif",".jpg",".jpeg",".png",".gif",".bmp"));
            int posicaoInicial = nomeArquivo.lastIndexOf(".")>0?nomeArquivo.lastIndexOf("."):0;
            String extensao = nomeArquivo.substring(posicaoInicial).toLowerCase();
            return extensoesOk.contains(extensao);
        }
        return true;
    }
}
