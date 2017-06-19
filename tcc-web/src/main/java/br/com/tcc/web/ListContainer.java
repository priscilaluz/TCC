/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.web;

import java.util.List;

public class ListContainer {

    private List<?> pagedList;
    private Integer pagina;
    private Integer totalGeral;
    private Integer itemsPerPage;

    public List<?> getPagedList() {
        return pagedList;
    }

    public void setPagedList(List<?> pagedList) {
        this.pagedList = pagedList;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }
    
    public Integer getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(Integer totalGeral) {
        this.totalGeral = totalGeral;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
    
}
