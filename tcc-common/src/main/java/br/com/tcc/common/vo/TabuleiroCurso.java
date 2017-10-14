/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.vo;

import java.util.List;

/**
 *
 * @author ADM
 */
public class TabuleiroCurso {
    private Long idCurso;
    private String nome;
    private Long etapaAtual;
    private List<TabuleiroEtapa> etapas;

    public TabuleiroCurso() {}

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEtapaAtual() {
        return etapaAtual;
    }

    public void setEtapaAtual(Long etapaAtual) {
        this.etapaAtual = etapaAtual;
    }

    public List<TabuleiroEtapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<TabuleiroEtapa> etapas) {
        this.etapas = etapas;
    }
}
