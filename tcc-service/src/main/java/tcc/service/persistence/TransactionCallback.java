/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

/**
 * Define o bloco de código que deve ser executado dentro de uma transação
 * programática gerenciada pelo <code>GenericDao</code>. O modelo de uso
 * esperado é que a interface seja implementada por uma classe anônima dentro
 * do Service. Ex:
 * <blockquote><pre>
 * // Método com transação declarativa
 * @Transactional
 * public Pedido salvarPedido(Pedido pedido) {
 *     return getDao().saveOrUpdate(pedido);
 * }
 *
 * // Método com transação programática
 * public List<Pedido> processarPedidos(final List<Pedido> pedidos) {
 *     List<Pedido> pedidosProcessados = getDao().runInsideCustomTransaction(new TransactionCallback() {
 *         public Object doInsideTransaction() {
 *             List<Pedido> processados = new ArrayList<Pedido>();
 *             foreach(pedido pedido : pedidos) {
 *                 if (validar(pedido)) {
 *                     processados.add(pedido);
 *                 }
 *             }
 *             return processados;
 *         }
 *     });
 *     enviarNotificacaoPorEmail(pedidosProcessados);
 *     return pedidosProcessados;
 * }
 * </pre></blockquote>
 * 
 * @author 
 */
public interface TransactionCallback {

    Object doInsideTransaction();

}
