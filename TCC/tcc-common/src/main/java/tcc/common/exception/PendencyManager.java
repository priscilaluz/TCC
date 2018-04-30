package tcc.common.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Objeto para encapsular a criação de uma lista de pendências. Deve
 * ser usado na camada de serviço para auxiliar na realização das
 * validações de negócio. Ao final de todas as validações, é preciso
 * chamar o método <code>verifyAll</code>. Caso exista alguma pendência
 * registrada, o manager lançará uma <code>PendingException</code> com
 * a lista de erros. Ex:
 * <blockquote><pre>
 * PendencyManager pm = new PendencyManager();
 *
 * pm.assertThat(pedido.getCliente().isValido()).orRegister("Cliente inválido");
 * pm.assertNotEmpty(pedido.getItens()).orRegister("Pedido sem itens");
 * pm.assertNotNull(pedido.getFormaPagamento()).orRegister("Pedido sem forma de pagamento");
 * pm.verifyAll();
 * </pre></blockquote>
 */
public class PendencyManager {
    private List<Pendency> pending = new ArrayList<Pendency>();

    public List<Pendency> getPending() {
        return Collections.unmodifiableList(pending);
    }
    
    /**
     * Verifica a condição recebida. Se a condição for verdadeira, a mensagem de 
     * erro não será registrada. 
     * 
     * @param condition a condição
     * @return this
     */
    public IntermediateContext assertThat(boolean condition) {
        return new IntermediateContext(condition);
    }

    public IntermediateContext assertNotNull(Object object) {
        return assertThat(object != null);
    }

    public IntermediateContext assertNotEmpty(Collection collection) {
        return assertThat(collection != null && !collection.isEmpty());
    }

    public IntermediateContext assertNotEmpty(String string) {
        return assertThat(string != null && !string.isEmpty());
    }
    
    public  void verifyAll() {
        if (!this.pending.isEmpty()) {
            throw new PendingException(this.pending);
        }
    }
    
    public class IntermediateContext {
        private boolean assertHasFailed;

        public IntermediateContext(boolean condition) {
            this.assertHasFailed = !condition;
        }
        
        /**
         * Define a mensagem de erro associada a uma falha de validação. Se a 
         * mensagem não for registrada devido a uma condição verdadeira na validação, 
         * habilita o aninhamento de validações.
         */
        public FinalContext orRegister(String message, Object... additionalData) {
            Pendency pendency = null;
            if (assertHasFailed) {
                pendency = new Pendency(message, additionalData);
                pending.add(pendency);
            }
            return new FinalContext(pendency);
        }
    }
    
    public class FinalContext {
        private Pendency pendency;

        public FinalContext(Pendency pendency) {
            this.pendency = pendency;
        }
        
        /**
         * Permite a definição de asserções aninhadas. Se a asserção original for 
         * bem sucedida, o objeto de callback será chamado.
         * 
         * <blockquote><pre>
         * pm.assertNotNull(obj).orRegister("m1").then(new NestedAssert<Example>(obj) {
         *     @Override void verify(PendencyManager pm, Example obj) {
         *         pm.assertThat(obj.val() > 0).orRegister("m2")
         *           .assertNotEmpty(obj.elements()).orRegister("m3");
         *     } 
         * });
         * </pre></blockquote>
         */
        public void then(NestedAssert na) {
            if (pendency == null) {
                na.doVerify(PendencyManager.this);
            }
        }
        
        /**
         * Permite a verificação imediata da última asserção. Se a asserção for
         * falsa, uma exceção será lançada contendo uma única pendência.
         */
        public void verify() {
            if (pendency != null) {
                throw new PendingException(Arrays.asList(pendency));
            }
        }
    }
    
    public static abstract class NestedAssert<T> {
        T obj;
        public NestedAssert(T obj) {
            this.obj = obj;
        }
        private void doVerify(PendencyManager manager) {
            verify(manager, obj);
        }
        public abstract void verify(PendencyManager manager, T obj);
    }
}