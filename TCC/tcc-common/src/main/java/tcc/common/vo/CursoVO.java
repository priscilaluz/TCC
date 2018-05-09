/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import tcc.common.enums.SituacaoCurso;

/**
 *
 * @author ADM
 */
public class CursoVO {

    private Long id;
    private String nome;
    private SituacaoCurso situacao;

    public CursoVO() {
    }

    public CursoVO(Long id, String nome, SituacaoCurso situacao) {
        this.id = id;
        this.nome = nome;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SituacaoCurso getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCurso situacao) {
        this.situacao = situacao;
    }
}
