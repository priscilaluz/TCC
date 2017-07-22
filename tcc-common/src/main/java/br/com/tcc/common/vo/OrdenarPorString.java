/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.vo;

import br.com.tcc.common.enums.OrderByArrayTipo;

/**
 *
 * @author ADM
 */
public class OrdenarPorString implements java.util.Comparator<String> {

    private final OrderByArrayTipo tipo;
    private final boolean crescente;

    public OrdenarPorString(OrderByArrayTipo tipo, boolean crescente) {
        super();
        this.tipo = tipo;
        this.crescente = crescente;
    }

    @Override
    public int compare(String s1, String s2) {
        if (OrderByArrayTipo.TAMANHO.equals(tipo)) {
            return (s1.length() - s2.length())*(crescente?1:-1);
        }
        return 0;
    }
}