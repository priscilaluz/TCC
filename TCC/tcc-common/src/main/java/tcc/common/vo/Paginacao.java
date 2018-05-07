/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

/**
 *
 * @author ADM
 */
public class Paginacao {
    public static final int DEFAULT_QNT_BOTAO_PAG_TELA = 5;
    public static final int DEFAULT_QNT_POR_PAG = 10;
    private Integer qntPaginaMostrarTela;
    private Integer qntPorPagina;
    private Long numDeItens;
    private Integer paginaAtual;

    public Paginacao() {
        paginaAtual = 0;
        qntPaginaMostrarTela = DEFAULT_QNT_BOTAO_PAG_TELA;
        qntPorPagina = DEFAULT_QNT_POR_PAG;
    }

    public Paginacao(Long numDeItens, Integer paginaAtual) {
        this.qntPaginaMostrarTela = DEFAULT_QNT_BOTAO_PAG_TELA;
        this.qntPorPagina = DEFAULT_QNT_POR_PAG;
        this.numDeItens = numDeItens;
        this.paginaAtual = paginaAtual;
    }

    public Integer getQntPaginaMostrarTela() {
        return qntPaginaMostrarTela;
    }

    public void setQntPaginaMostrarTela(Integer qntPaginaMostrarTela) {
        this.qntPaginaMostrarTela = qntPaginaMostrarTela;
    }

    public Integer getQntPorPagina() {
        return qntPorPagina;
    }

    public void setQntPorPagina(Integer qntPorPagina) {
        this.qntPorPagina = qntPorPagina;
    }

    public Long getNumDeItens() {
        return numDeItens;
    }

    public void setNumDeItens(Long numDeItens) {
        this.numDeItens = numDeItens;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}