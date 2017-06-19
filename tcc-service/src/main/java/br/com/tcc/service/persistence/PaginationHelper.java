/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

/**
 * <p>Classe utilitária para simplificar a criação e modificação de criterias paginadas.</p>
 *
 * @author UP21
 */
public final class PaginationHelper {

    /**
     * Construtor privado, como helper deve ser utilizado somente através
     * de seus métodos estáticos.
     */
    private PaginationHelper() {

    }

    /**
     * <p>
     * Executa o criteria dado retornando o resultado em uma PagedList de T.
     * </p>
     * <p>
     * <b>ATENÇÃO:</b> Este método <b>altera permanentemente</b> o objeto {@link Criteria} recebido como parâmetro. Qualquer {@link Projection} ou {@link ResultTransformer} configurado no criteria
     * <b>será substituído</b>.
     * </p>
     * <p>Utilize o método {@code executeCriteriaForPagination(Pagination,
     *              Criteria, boolean)} caso sua consulta precise de <code>SQL DISTINCT</code>.</p>
     *
     * @param <T> Tipo de Entidade que será retornada na PagedList
     * @param pagination Paginação utilizada
     * @param criteria criteria
     * @return pagedList de T
     */
    public static <T> PagedList<T>
            executeCriteriaForPagination(final Pagination pagination,
                    final Criteria criteria) {
        return executeCriteriaForPagination(pagination, criteria, false);
    }

    /**
     * <p>
     * Executa o criteria dado retornando o resultado em uma PagedList de T.
     * </p>
     * <p>
     * <b>ATENÇÃO:</b> Este método <b>altera permanentemente</b> o objeto {@link Criteria} recebido como parâmetro. Qualquer {@link Projection} ou {@link ResultTransformer} configurado no criteria
     * <b>será substituído</b>.
     * </p>
     * <p>
     * Use o parâmetro <code>distinct</code> para definir se o {@link ResultTransformer} deve ser
     * {@link Criteria.DISTINCT_ROOT_ENTITY} ou apenas {@link Criteria.ROOT_ENTITY}
     * e se deve ser usado <code>distinct count(*)</code> ou apenas <code>count(*)</code>.
     * </p>
     *
     * @param <T> Tipo de Entidade que será retornada na PagedList
     * @param pagination Paginação utilizada
     * @param criteria criteria
     * @param distinct Se a consulta deve usar <code>SQL DISTINCT</code>.
     * @return pagedList de T
     */
    public static <T> PagedList<T>
            executeCriteriaForPagination(final Pagination pagination,
                    final Criteria criteria, boolean distinct) {

        criteria.setFirstResult(0);
        Pagination paginationToReturn = getTotalItemsForCriteria(criteria, pagination, distinct);
        CriteriaHelper.addOrderCriteria(criteria, pagination.getOrdering());
        final List<T> list = criteria.setFirstResult(pagination.getFirstItemIndex())
                .setMaxResults(pagination.getPageSize()).list();

        return new PagedList<T>(list, paginationToReturn);
    }

    /**
     * <p>
     * <b>ATENÇÃO:</b> Este método <b>altera permanentemente</b> o objeto {@link Criteria} recebido como parâmetro. Qualquer {@link Projection} ou {@link ResultTransformer} configurado no criteria
     * <b>será substituído</b>.
     * </p>
     * <p>
     * Use o parâmetro <code>distinct</code> para definir se o {@link ResultTransformer} deve ser
     * {@link Criteria.DISTINCT_ROOT_ENTITY} ou apenas {@link Criteria.ROOT_ENTITY}
     * e se deve ser usado <code>distinct count(*)</code> ou apenas <code>count(*)</code>.
     * </p>
     * @param criteria O Criteria a ser utilizado
     * @param pagination O objeto com a paginação desejada.
     * @param distinct Se a consulta deve usar <code>SQL DISTINCT</code>.
     * @return Um novo objeto de paginação referente à consulta atual.
     */
    private static Pagination getTotalItemsForCriteria(Criteria criteria, Pagination pagination, boolean distinct) {

        Projection count = Projections.rowCount();
        if(distinct) {
            count = Projections.distinct(count);
        }
        int totalItems = (Integer) criteria.setProjection(count).uniqueResult();
        criteria.setProjection(null).setResultTransformer(distinct ? Criteria.DISTINCT_ROOT_ENTITY : Criteria.ROOT_ENTITY);
        return new Pagination(
                pagination.getPageSize(),
                pagination.getCurrentPage(),
                totalItems,
                pagination.getOrdering());

    }

}
