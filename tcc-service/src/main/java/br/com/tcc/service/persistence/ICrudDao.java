/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import br.com.tcc.common.support.IEntity;
import java.io.Serializable;
import java.util.List;


/**
 * Interface que define os métodos para operações CRUD.
 *
 * @author Y3ZZ
 */
public interface ICrudDao {
 // SUPPRESS CHECKSTYLE StrictDuplicateCodeCheck
    /**
     * Retorna a instância da classe persistente de determinada entidade, dado
     * identificador, ou nulo se não for encontrado.
     *
     * @param <T>
     *        Tipo da Entidade de retorno do método.
     * @param entityClass
     *        classe da entidade de retorno do método
     * @param id
     *        atributo identificador da entidade
     * @return entidade
     */
    <T extends IEntity<? extends Serializable>> T get(
            final Class<T> entityClass, final Serializable id);

    /**
     * Retorna uma coleção de todas as instâncias de determinada entidade.
     *
     * @param <T>
     *        Tipo da Entidade de retorno do método.
     * @param entityClass
     *        classe da entidade de retorno do método
     * @return lista de entidades
     */
    <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass);

    /**
     * Persiste uma instancia da entidade T.
     *
     * @param <T>
     *        Tipo da Entidade de retorno do método.
     * @param entity
     *        Instancia de T a ser persistida
     * @return entidade persistida
     */
    <T extends IEntity<? extends Serializable>> T persist(
            T entity);

    /**
     * Atualiza uma instancia persistente da entidade T.
     *
     * @param <T>
     *        Tipo da entidade atualizada
     * @param entity
     *        Instancia de T a ser atualizada.
     */
    <T extends IEntity<? extends Serializable>> void update(
            T entity);

    /**
     * Realiza o merge de uma instancia persistente de T.
     *
     * @param <T>
     *        Tipo da Entidade de retorno do método.
     * @param entity
     *        Instancia de T a ser persistida
     * @return entidade
     */
    <T extends IEntity<? extends Serializable>> T merge(T entity);

    /**
     * Remove uma instancia persistente de T.
     *
     * @param <T>
     *        Tipo da Entidade a ser removida
     * @param entity
     *        Instancia de T a ser removida
     */
    <T extends IEntity<? extends Serializable>> void remove(
            T entity);

    /**
     * Retorna uma coleção de todas as instâncias de determinada entidade
     * ordenando de acordo com os critérios informados.
     *
     * @param <T>
     *        Tipo da Entidade
     * @param entityClass
     *        classe da Entidade
     * @param orderByList
     *        Lista opcional de {@link OrderBy}
     * @return list de T
     */
    <T extends IEntity<? extends Serializable>> List<T> findAll(
            final Class<T> entityClass, final OrderBy... orderByList);

    /**
     * Retorna uma lista paginada de todas as instâncias de determinada
     * entidadede acordo com o objeto de paginação dado,
     * incluindo ordenação.
     *
     * @param <T>
     *        Tipo da Entidade
     * @param entityClass
     *        classe da Entidade
     * @param pagination
     *        paginação utilizada incluindo ordenação
     * @return pagedList de T
     */
    <T extends IEntity<? extends Serializable>> PagedList<T> findAll(
            final Class<T> entityClass, final Pagination pagination);

    /**
     * Retorna uma lista paginada das instâncias de determinada entidade que
     * correspondam ao exemplo, ordenando de acordo com os critérios
     * informados.
     *
     * @param <T>
     *        Tipo da Entidade
     * @param entity
     *        exemplo de T para consulta
     * @param orderByList
     *        lista de ordenação utilizada
     * @return list de T
     */
    <T extends IEntity<? extends Serializable>> List<T> findByExample(
            T entity, OrderBy... orderByList);

    /**
     * Retorna uma lista paginada das instâncias de determinada entidade que
     * correspondam ao exemplo, de acordo com o objeto de paginação dado,
     * incluindo ordenação.
     *
     * @param <T>
     *        Tipo da Entidade
     * @param entity
     *        exemplo de T para consulta
     * @param pagination
     *        paginação utilizada incluindo ordenação
     * @return pagedList de T.
     *
     */ // SUPPRESS CHECKSTYLE StrictDuplicateCodeCheck
    <T extends IEntity<? extends Serializable>> PagedList<T> findByExample(
            T entity, Pagination pagination);

    /**
     * Retorna uma lista de todas as instâncias de determinada entidade de
     * acordo com a lista de chaves da entidade que foram informadas.
     *
     * @param <T>
     *        Tipo de Entidade
     * @param entityClass
     *        classe da Entidade
     * @param idList
     *        lista de chaves da Entidade
     * @return list de T
     */
    <T extends IEntity<? extends Serializable>> List<T> findAllInIdList(
            final Class<T> entityClass, Serializable... idList);

    /**
     * Retorna uma lista de todas as instâncias de determinada entidade de
     * acordo com a lista de chaves da entidade que foram informadas, incluindo
     * ordenação.
     *
     * @param <T>
     *        Tipo de Entidade
     * @param entityClass
     *        classe da Entidade
     * @param orderByList
     *        lista de ordenação utilizada
     * @param idList
     *        lista de chaves da Entidade
     * @return list de T
     */
    <T extends IEntity<? extends Serializable>> List<T> findAllInIdListByOrder(
            final Class<T> entityClass, OrderBy[] orderByList,
            Serializable... idList);

    /**
     * Retorna uma lista paginada das instâncias de determinada entidade que
     * correspondam ao exemplo, de acordo com o objeto de paginação dado,
     * incluindo ordenação. Retorna uma lista paginada das instâncias de
     * determinada entidade de acordo com a lista de chaves da entidade que
     * foram informadas, incluindo paginação.
     *
     * @param <T>
     *        Tipo da Entidade
     * @param entityClass
     *        classe da Entidade
     * @param pagination
     *        paginação utilizada incluindo ordenação
     * @param idList
     *        lista de chaves da Entidade
     * @return pagedList de T
     */
    <T extends IEntity<? extends Serializable>> PagedList<T>
            findAllInIdListByPagination(final Class<T> entityClass,
            final Pagination pagination, Serializable... idList);
}
