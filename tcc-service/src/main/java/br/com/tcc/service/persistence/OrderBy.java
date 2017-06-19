/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.persistence;

import java.io.Serializable;

/**
 * Retém informações que poderão ser usadas para ordenar buscas por objetos.
 */
public class OrderBy implements Serializable {

    private static final long serialVersionUID = 3160187263932074085L;
    private static final int EMPTY = 0;

    private String name;
    private boolean ascending;
    private boolean caseSensitive;

    /**
     * Cria uma ordenação <b>ascendente</b>. Assume case sensitive falso e atributo é "id".
     */
    public OrderBy() {
        this("id");
    }

    /**
     * Cria uma ordenação <b>ascendente</b> para o atributo fornecido como
     * parâmetro. Assume case sensitive falso.
     *
     * @param name
     *            O nome do atributo pelo qual se deseja ordenar.
     */
    public OrderBy(String name) {
        this(name, true, false);
    }

    /**
     * Instancia esta classe com um nome de atributo e uma direção de ordenação.
     * Assume case sensitive falso.
     *
     * @param name
     *            O nome do atributo pelo qual se deseja ordenar.
     * @param ascending
     *            Informa se a ordenacao eh ascendente ou nao
     */
    public OrderBy(String name, boolean ascending) {
        setName(name);
        setAscending(ascending);
        setCaseSensitive(false);
    }

    /**
     * Instancia esta classe com um nome de atributo e uma direção de ordenação.
     *
     * @param name
     *            O nome do atributo pelo qual se deseja ordenar.
     * @param ascending
     *            informa se a ordenacao eh ascendente ou nao
     * @param caseSensitive
     *            informa se a ordenacao deve considerar a caixa.
     */
    public OrderBy(String name, boolean ascending, boolean caseSensitive) {
        setName(name);
        setAscending(ascending);
        setCaseSensitive(caseSensitive);
    }

    /**
     * Retorna o nome do campo no banco de dados.
     *
     * @return nome do campo
     */
    public String getName() {
        return name;
    }

    /**
     * Informa se a ordenação é ascendente ou descendente.
     *
     * @return true se for ascendente ou false se descendente.
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * Verifica se a ordenação é case-sensitive.
     *
     * @return <code>true</code> se for case-sensitive e <code>false</code>,
     *         caso contrário.
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Verifica se um objeto é igual a este. Uma instância de OrderBy só é igual
     * a outra se os atributos <code>name</code> e <code>sort</code> forem
     * iguais.
     *
     * @param obj
     *            Objeto que sera comparado com a instancia atual
     * @return informa se os objetos sao iguais ou nao
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OrderBy)) {
            return false;
        }

        OrderBy other = (OrderBy) obj;

        if (!name.equals(other.name)) {
            return false;
        }

        if (ascending != other.ascending) {
            return false;
        }

        return true;
    }

    /**
     * Baseado no nome do campo e na direção da ordenação.
     *
     * @return valor do hashcode do objeto
     */
    @Override
    public int hashCode() {
        return name.hashCode() * Boolean.valueOf(ascending).hashCode()
                * Boolean.valueOf(caseSensitive).hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return (new StringBuilder())
                .append("Sort:")
                .append(getName())
                .append("/")
                .append(ascending)
                .toString();
    }

    /**
     * Atribui o nome do field da ordenacao
     *
     * @param name
     *            nome do field da ordenacao
     */
    private void setName(String name) {
        if (name == null || name.trim().length() == EMPTY) {
            throw new IllegalArgumentException(
                    "Parameter 'name' cannot be null or an empty String.");
        }

        this.name = name;
    }

    private void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    private void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

}
