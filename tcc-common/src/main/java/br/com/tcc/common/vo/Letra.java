/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.vo;

/**
 *
 * @author ADM
 */
public class Letra {
    private int posicao;
    private String letra;
    private boolean pintada;
    private boolean selecionada;

    public Letra(int posicao, String letra) {
        this.posicao = posicao;
        this.letra = letra;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean isPintada() {
        return pintada;
    }

    public void setPintada(boolean pintada) {
        this.pintada = pintada;
    }

    public boolean isSelecionada() {
        return selecionada;
    }

    public void setSelecionada(boolean selecionada) {
        this.selecionada = selecionada;
    }
}
