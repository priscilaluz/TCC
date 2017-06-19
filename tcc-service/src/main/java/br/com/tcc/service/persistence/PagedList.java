/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * DTO usado para transferir para o cliente de algum método de consulta aos DAOs
 * o resultado de uma pesquisa paginada. Contém referências a lista de
 * resultados da página atual e as informações da página atual.
 *
 * @param <T>
 *            Tipo do objeto contido na lista
 */
public class PagedList<T> implements Serializable {
    private static final long serialVersionUID = 8989035031264381609L;

    private final List<T> list;
    private final Pagination pagination;

    /**
     * COnstroi um objeto desse tipo
     *
     * @param list
     *            Lista de objetos contidos no wrapper
     * @param pagination
     *            Paginacao contida no wrapper, relativo a lista
     */
    public PagedList(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    /**
     * Retorna uma cópia não modificável da lista de items de items da PagedList.
     *
     * @return Lista com objetos resultantes da consulta
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Obtém as informações sobre a paginação da consulta feita. Este método não
     * é protegido contra efeitos colaterais, ou seja, a lista obtida através
     * deste método é a referência da lista mantida internamente neste objeto.
     *
     * @return {@link Pagination} associado ao pagedList
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((list == null) ? 0 : list.hashCode());
        result = prime * result
                + ((pagination == null) ? 0 : pagination.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        PagedList other = (PagedList) obj;
        if (list == null) {
            if (other.list != null) {
                return false;
            }
        } else if (!list.equals(other.list)) {
            return false;
        }
        if (pagination == null) {
            if (other.pagination != null) {
                return false;
            }
        } else if (!pagination.equals(other.pagination)) {
            return false;
        }
        return true;
    }

}
