package tcc.service.validator;

import tcc.common.entity.Categoria;
import tcc.common.exception.PendencyManager;
import tcc.common.util.ConstantesI18N;
import tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidator {

    public void validarSalvarCategoria(Categoria categoria, boolean nomeNaoExiste){
        AssertUtils.assertNotNull(categoria, ConstantesI18N.CATEGORIA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(categoria.getNome()).orRegister(ConstantesI18N.CATEGORIA_NOME_OBRIGATORIO);
        pm.assertThat(categoria.getNome() == null || nomeNaoExiste).orRegister(ConstantesI18N.CATEGORIA_NOME_EXISTE);
        pm.verifyAll();
    }
}
