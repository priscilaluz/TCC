/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import java.util.List;

/**
 *
 * @author ADM
 */
public class ListaPaginacao {
    private List<Object> lista;
    private Paginacao paginacao;

    public ListaPaginacao(List<Object> lista, Paginacao paginacao) {
        this.lista = lista;
        this.paginacao = paginacao;
    }

    public ListaPaginacao() {
        paginacao = new Paginacao();
    }

    public List<Object> getLista() {
        return lista;
    }

    public void setLista(List<Object> lista) {
        this.lista = lista;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }

}
