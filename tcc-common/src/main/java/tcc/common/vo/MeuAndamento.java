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
public class MeuAndamento {

    private Long idCursoAluno;
    private String nomeCurso;
    private Integer etapa;
    private Integer pontuacao;
    private Long colocacao;

    public MeuAndamento() {
    }

    public Long getIdCursoAluno() {
        return idCursoAluno;
    }

    public void setIdCursoAluno(Long idCursoAluno) {
        this.idCursoAluno = idCursoAluno;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Integer getEtapa() {
        return etapa;
    }

    public void setEtapa(Integer etapa) {
        this.etapa = etapa;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Long getColocacao() {
        return colocacao;
    }

    public void setColocacao(Long colocacao) {
        this.colocacao = colocacao;
    }

}
