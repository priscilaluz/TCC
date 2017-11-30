package br.com.tcc.service.validator;

import br.com.tcc.common.entity.Categoria;
import br.com.tcc.common.exception.PendencyManager;
import br.com.tcc.common.util.ConstantesI18N;
import br.com.tcc.common.support.AssertUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValidator {

    public void validarSalvarCategoria(Categoria categoria, boolean nomeNaoExiste){
        AssertUtils.assertNotNull(categoria, ConstantesI18N.CATEGORIA_OBRIGATORIA);
        PendencyManager pm = new PendencyManager();
        pm.assertNotNull(categoria.getNome()).orRegister(ConstantesI18N.CATEGORIA_NOME_OBRIGATORIO);
        pm.assertThat(nomeNaoExiste).orRegister(ConstantesI18N.CATEGORIA_NOME_EXISTE);
        pm.verifyAll();
    }
}
