/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import tcc.common.support.IEntity;
import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

/**
 * Helper para implementação das chamadas ao Criteria API do Hibernate
 * que não são feitas diretamente por customizações.
 * @author Y5DJ
 */
public final class CriteriaHelper {

    /**
     * Construtor privado, como helper deve ser utilizado somente através
     * de seus métodos estáticos.
     */
    private CriteriaHelper() {
    }

    /**
     * Adiciona na {@link Criteria} vários critérios de ordenação.
     * Sempre será adicionado uma ordenação pelo id da Entity
     * como último critério de ordenação das entidades.
     *
     * @param criteria
     *          a instância de {@link Criteria} na qual se
     *          adicionarão critérios de ordenação.
     * @param orderByList
     *          a lista de critérios de ordenação a adicionar.
     */
    public static void addOrderCriteria(final Criteria criteria,
            final OrderBy... orderByList) {
           if (orderByList != null) {
               for (OrderBy orderBy : orderByList) {
                   criteria.addOrder(CriteriaHelper.convertOrderByToOrder(orderBy));
               }
           }
           //sempre adiciona o id como ultima ordenacao
           //default do orderBy vazio é id
           criteria.addOrder(convertOrderByToOrder(new OrderBy()));
    }

    /**
     * Cria uma instancia de {@link Order} do Hibernate
     * baseado em um {@link OrderBy} do CelulaTronco.
     * @param orderBy instancia de {@link OrderBy}
     * @return instancia de {@link Order}
     */
    public static Order convertOrderByToOrder(final OrderBy orderBy) {
        Order order = null;
        if (orderBy.isAscending()) {
            order = Order.asc(orderBy.getName());
        } else {
            order = Order.desc(orderBy.getName());
        }
        if (!orderBy.isCaseSensitive()) {
            order = order.ignoreCase();
        }
        return order;
    }

    /**
     * Cria um exemplo da etidade para buscas. O padrão de buscas por exemplo do framework Celula Tronco Java segue os seguintes termos:
     * <ol>
     * <li> Case é igonorado para propriedades String</li>
     * <li>O operador "like" é habilitado para todos os atributos String, buscando o termo em qualquer parte da String.</li>
     * <li>PropertySelector será {@link NotNullOrBlankProperySelector}.</li>
     * </ol>
     * @param <T> Tipo de Entidade
     * @param entity entidade
     * @return exemplo de T
     */
    public static <T extends IEntity<? extends Serializable>> Example
             createExample(final T entity) {
        final Example example = Example.create(entity);
        example.ignoreCase();
        example.enableLike(MatchMode.ANYWHERE);
        example.setPropertySelector(
                NotNullOrBlankProperySelector.getInstance());
        return example;
    }
}
