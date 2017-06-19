/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import java.io.Serializable;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Armazena os parâmetros de paginação a usar em uma busca por objetos (em um
 * banco de dados, por exemplo). Os parâmetros são a pagina atual, o numero de
 * itens por pagina, a quantidade de páginas e a lista de criterios de
 * ordenação. Este objeto também retém o total de itens encontrados em tal
 * busca. Instâncias dessa classe consideram que a primeira página é a 0 (zero),
 * a segunda é 1, etc. De forma similar, considera que o primeiro item é
 * indexado por 0.
 */
/**
 * @author UP23
 */
public class Pagination implements Serializable {

    /**
     * Tamanho padrão da página é 10.
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * Tamanho padrão da página é 10.
     */
    public static final int DEFAULT_TOTAL_ITEMS = 0;

    /**
     * O valor inteiro representante da primeira página. A contagem de páginas
     * começa em 0.
     */
    public static final int FIRST_PAGE = 0;

    /**
     * O valor inteiro representante do primeiro item. A contagem de itens
     * começa em 0.
     */
    public static final int FIRST_ITEM_INDEX = 0;

    private static final String EMPTY_PAGE_SIZE_MSG = "O tamanho da página informada é inválido. O valor esperado é um número maior que 0";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(Pagination.class);

    private static final long serialVersionUID = 4678377568559247820L;

    private static final int EMPTY = 0;
    private static final int DEFAULT_INCREASE = 1;
    private static final int DEFAULT_DECREASE = 1;

    private static final OrderBy[] NO_ORDERING = new OrderBy[] {};

    private final int pageSize;
    private final int currentPage;
    private final int totalItems;
    private final OrderBy[] ordering;

    /**
     * Instancia esta classe sem informar a ordenação a usar. Assume que a página
     * inicial equivale a constante FIRST_PAGE e que que o tamanho da página é
     * DEFAULT_PAGE_SIZE.
     */
    public Pagination() {
        this(NO_ORDERING);
    }

    /**
     * Instancia esta classe informando a ordenação a usar. Assume que a página
     * inicial equivale a constante FIRST_PAGE e que que o tamanho da página é
     * DEFAULT_PAGE_SIZE.
     *
     * @param ordering
     *            A lista de nomes dos campos pelos quais a ordenação deve ser
     *            feita.
     */
    public Pagination(OrderBy... ordering) {
        this(DEFAULT_PAGE_SIZE, FIRST_PAGE, DEFAULT_TOTAL_ITEMS, ordering);
    }

    /**
     * Instancia esta classe informando o tamanho da página e a ordenação a
     * usar. Assume que a página inicial equivale a constante FIRST_PAGE.
     *
     * @param pageSize
     *            o tamanho da página.
     * @param ordering
     *            a ordenação.
     */
    public Pagination(int pageSize, OrderBy... ordering) {
        this(pageSize, FIRST_PAGE, DEFAULT_TOTAL_ITEMS, ordering);
    }

    /**
     * Instancia esta classe informando o tamanho da pagina, qual a pagina atual
     * e possivelmente a ordenação. Assume que A quantidade de itens equivale a
     * constante DEFAULT_TOTAL_ITEMS.
     *
     * @param pageSize
     *            setar o tamanho da pagina
     * @param currentPage
     *            setar a pagina inicial
     * @param ordering
     *            opcionalmente setar as ordenacoes da paginacao
     */
    public Pagination(int pageSize, int currentPage, OrderBy... ordering) {

        this(pageSize, currentPage, DEFAULT_TOTAL_ITEMS, ordering);
    }

    /**
     * Construtor completo <br/>
     * Lanca um IllegalArgumentException caso seja informado uma pagina menor ou
     * igual a zero
     *
     * @param pageSize
     *            o tamanho da página.
     * @param currentPage
     *            a pagina atual.
     * @param totalItems
     *            o numero total de itens
     * @param ordering
     *            a ordenação
     */
    public Pagination(int pageSize, int currentPage, int totalItems,
            OrderBy... ordering) {

        if (pageSize <= EMPTY) {
            LOGGER.debug(EMPTY_PAGE_SIZE_MSG);
            throw new IllegalArgumentException(
                    "br.com.celulatronco.illegalPageSizeArgumentException");
        }

        this.pageSize = pageSize;
        this.totalItems = (totalItems < EMPTY) ? EMPTY : totalItems;
        this.ordering = (ordering == null) ? NO_ORDERING : ordering;

        if ((getTotalPages() > EMPTY) && (currentPage >= getTotalPages())) {
            this.currentPage = getTotalPages() - DEFAULT_DECREASE;
        } else {
            this.currentPage = ((currentPage < EMPTY) ? EMPTY : currentPage);
        }
    }

    /**
     * Methodo utilizado para obter o numero da pagina atual
     *
     * @return retorna o numero da pagina atual
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Methodo utilizado para obter o indice do primeiro item da pagina atual
     *
     * @return retorna o indice do primeiro item da pagina atual
     */
    public int getFirstItemIndex() {
        return pageSize * currentPage;
    }

    /**
     * methodo utilizado para retorna uma copia das ordenacoes da paginacao
     *
     * @return retorna uma copia das ordanecoes da paginacao
     */
    public OrderBy[] getOrdering() {
        return Arrays.copyOf(ordering, ordering.length);
    }

    /**
     * metodo que obtem o tamanho da pagina
     *
     * @return retorna o tamanho da pagina
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * metodo que obtem o totel de intens da consulta
     *
     * @return retorna o total de itens da consulta
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Metodo utilizado para obter o total de paginas da consulta
     *
     * @return retorna o total de paginas da consulta
     */
    public int getTotalPages() {
        int totalPages = EMPTY;
        if (pageSize != EMPTY) {
            totalPages = (totalItems % pageSize == EMPTY) ? totalItems
                    / pageSize : (totalItems / pageSize) + DEFAULT_INCREASE;
        }
        return totalPages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Pagination[").append("pageSize: ")
                .append(getPageSize()).append("; currentPage: ").append(
                        getCurrentPage()).append("; totalItems: ").append(
                        getTotalItems()).append("]");

        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + currentPage;
        result = prime * result + Arrays.hashCode(ordering);
        result = prime * result + pageSize;
        result = prime * result + totalItems;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
        Pagination other = (Pagination) obj;
        if (currentPage != other.currentPage) {
            return false;
        }
        if (!Arrays.equals(ordering, other.ordering)) {
            return false;
        }
        if (pageSize != other.pageSize) {
            return false;
        }
        if (totalItems != other.totalItems) {
            return false;
        }
        return true;
    }

    /**
     * Metodo utilizado para obter a proxima pagina da consulta
     *
     * @return retorna a proxima pagina da consulta
     */
    public Pagination nextPage() {
        return new Pagination(this.pageSize, this.currentPage
                + DEFAULT_INCREASE, this.totalItems, this.ordering);
    }

    /**
     * Metodo utilizado para obter a pagina anterior da consulta
     *
     * @return retorna a pagina anterior da consulta
     */
    public Pagination previousPage() {
        return new Pagination(this.pageSize, this.currentPage
                - DEFAULT_DECREASE, this.totalItems, this.ordering);
    }

    /**
     * Metodo utilizado para obter uma determinada pagina da consulta
     *
     * @param page
     *            pagina que se deseja retornar
     * @return retorna a pagina especificada da consulta
     */
    public Pagination toPage(int page) {
        return new Pagination(this.pageSize, page, this.totalItems,
                this.ordering);
    }

}
