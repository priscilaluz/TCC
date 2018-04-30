/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.persistence;

import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;

/**
 * Critério usado para excluir da busca baseada em exemplos valores nulos ou
 * Strings vazias (comprimento 0 após serem "trimmed").
 */
public final class NotNullOrBlankProperySelector implements PropertySelector {

    private static final long serialVersionUID = -7631210699606563258L;

    private static final NotNullOrBlankProperySelector INSTANCE =
        new NotNullOrBlankProperySelector();
    private static final int EMPTY = 0;

    /**
     * Construtor private, evitando instancias desse objeto.
     */
    private NotNullOrBlankProperySelector() {
    };

    /**
     * {@inheritDoc}
     * @param value
     *            {@inheritDoc}
     * @param propertyName
     *            {@inheritDoc}
     * @param type
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean include(Object value, String propertyName, Type type) {
        if (value == null) {
            return false;
        }

        if (value instanceof String) {
            String str = (String) value;
            if (EMPTY == str.trim().length()) {
                return false;
            }
        }

        return true;
    }

    /**
     * retorna a instancia unica da classe.
     * @return instancia unica da classe
     */
    public static NotNullOrBlankProperySelector getInstance() {
        return INSTANCE;
    }

}
