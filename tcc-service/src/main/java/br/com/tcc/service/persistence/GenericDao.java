/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import br.com.tcc.common.support.IEntity;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;

/**
 * DAO genérico.
 * 
 * @author 
 */
public class GenericDao extends HibernateCrudDao {
    private TransactionHandler transactionHandler;

    /**
     * Define o handler a ser usado para tratar transações programáticas.
     * O SNARF já disponibiliza um handler default, baseado na API do Spring
     * Framework, chamado de {@link SpringBasedTransactionHandler}.
     */
    public void setCustomTransactionHandler(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    /**
     * Descarrega as alterações pendentes no banco de dados.
     */
    public void flush() {
        getCurrentSession().flush();
    }

    /**
     * Limpa os objetos persistentes carregados na sessão corrente.
     */
    public void clear() {
        getCurrentSession().clear();
    }

    /**
     * Insere ou atualiza uma entidade, de acordo com o estado de seu id.
     * @param entity A entidade que será gravada.
     * @return A entidade atualizada.
     */
    public <T extends IEntity<? extends Serializable>> T saveOrUpdate(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * Atualiza uma entidade com os valores existentes no banco de dados.
     * @param entity A entidade que será atualizada.
     * @return A entidade atualizada.
     */
    public <T extends IEntity<? extends Serializable>> T refresh(T entity) {
        getCurrentSession().refresh(entity);
        return entity;
    }

    /**
     * Executa a query fornecida.
     * @param <T> Tipo de retorno
     * @param businessQuery businessQuery
     * @return Lista retornada pela execução da consulta
     */
    public <T extends Serializable> List<T> list(BusinessQuery<T> businessQuery) {
        return QueryHelper.list(businessQuery, getCurrentSession());
    }

    /**
     * Executa a query fornecida.
     * @param <T> Tipo de retorno
     * @param businessQuery businessQuery
     * @return Iterator retornado pela execução da consulta
     */
    public <T extends Serializable> Iterator<T> iterate(BusinessQuery<T> businessQuery) {
        return QueryHelper.iterate(businessQuery, getCurrentSession());
    }

    /**
     * Executa a query fornecida.
     * @param <T> Tipo de retorno
     * @param businessQuery businessQuery
     * @return Valor único retornado pela execução da consulta
     */
    public <T extends Serializable> T uniqueResult(BusinessQuery<T> businessQuery) {
        return QueryHelper.uniqueResult(businessQuery, getCurrentSession());
    }
    
    /**
     * Executa a query fornecida.
     * @param <T> Tipo de retorno
     * @param businessQuery businessQuery
     * @return Valor único retornado pela execução da consulta
     */
    public Query createHibernateQuery(BusinessQuery businessQuery) {
        return QueryHelper.createHibernateQuery(this.getCurrentSession(), businessQuery);
    }

    /**
     * Executa a DML (update, delete)
     * @param businessQuery businessQuery
     * @return quantidade de registros afetados
     */
    public int executeDML(BusinessQuery businessQuery) {
        return QueryHelper.executeDML(businessQuery, getCurrentSession());
    }

    /**
     * Executa o código definido pelo callback dentro de uma transação programática.
     * @param callback código que deve ser executado dentro da transação
     * @return objeto retornado pelo callback
     */
    public Object runInsideCustomTransaction(TransactionCallback callback) {
        return this.transactionHandler.handle(callback);
    }
}
