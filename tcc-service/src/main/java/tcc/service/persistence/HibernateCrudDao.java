/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import tcc.common.support.IEntity;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


/**
 * Implementação da interface {@link ICrudDao} para o Hibernate.
 * Seus métodos realizam as operações de CRUD básica através da session corrente do Hibernate, sem demarcar uma transação, fazer commit ou rollback.
 * A responsabilidade de declarar gerenciar o contexto transacional deve ficar na camada superior.
 *
 * @author Y3ZZ
 */
public class HibernateCrudDao extends HibernateSupport implements ICrudDao {

    private static final String ID_LIST_IS_NULL = "idList is null.";
    private static final String ID_PROPERTY_NAME = "id";

    /**
     * Construtor padrão da classe.
     */
    public HibernateCrudDao() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEntity<? extends Serializable>> T get(
            final Class<T> entityClass, final Serializable id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> T persist(
            final T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> void update(
            final T entity) {
        getCurrentSession().update(entity);

    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEntity<? extends Serializable>> T merge(
            final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> void remove(
            final T entity) {
        getCurrentSession().delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass) {
        final OrderBy[]orderByList = new OrderBy[0];
        return this.findAll(entityClass, orderByList);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass, final OrderBy... orderByList) {
        final Criteria criteria = getCurrentSession().createCriteria(entityClass);
        CriteriaHelper.addOrderCriteria(criteria, orderByList);
        return criteria.list();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> PagedList<T> findAll(
            final Class<T> entityClass, final Pagination pagination) {
        final Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return PaginationHelper.executeCriteriaForPagination(pagination, criteria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> PagedList<T>
            findByExample(final T entity, final Pagination pagination) {
        final Criteria criteria = getCurrentSession()
                .createCriteria(entity.getClass())
                .add(CriteriaHelper.createExample(entity));
        return PaginationHelper.executeCriteriaForPagination(pagination, criteria);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEntity<? extends Serializable>> List<T> findByExample(
            final T entity, final OrderBy... orderByList) {
        final Criteria criteria = getCurrentSession()
                .createCriteria(entity.getClass())
                .add(CriteriaHelper.createExample(entity));
        CriteriaHelper.addOrderCriteria(criteria, orderByList);
        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> List<T>
            findAllInIdList(final Class<T> entityClass,
                            final Serializable... idList) {
        final OrderBy[]orderByList = new OrderBy[0];
        return findAllInIdListByOrder(entityClass, orderByList, idList);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends IEntity<? extends Serializable>> List<T>
            findAllInIdListByOrder(final Class<T> entityClass,
                                   final OrderBy[] orderByList,
                                   final Serializable... idList) {
        if (idList == null) {
            throw new NullPointerException(ID_LIST_IS_NULL);
        }
        final Criteria criteria = getCurrentSession()
                .createCriteria(entityClass)
                .add(Restrictions.in(ID_PROPERTY_NAME, idList));

        CriteriaHelper.addOrderCriteria(criteria, orderByList);
        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends IEntity<? extends Serializable>> PagedList<T>
            findAllInIdListByPagination(final Class<T> entityClass,
                                        final Pagination pagination,
                                        final Serializable... idList) {
        if (idList == null) {
            throw new NullPointerException(ID_LIST_IS_NULL);
        }
        final Criteria criteria =
                getCurrentSession()
                   .createCriteria(entityClass)
                   .add(Restrictions.in(ID_PROPERTY_NAME, idList));
        return PaginationHelper.executeCriteriaForPagination(pagination, criteria);
    }

}
