/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.vo;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.entity.Usuario;
import java.util.List;

/**
 *
 * @author ADM
 */
public class TabuleiroCurso {
    private Long idCurso;
    private String nome;
    private Anexo anexo;
    private String assuntoGeral;
    private Integer etapaAtual;
    private Integer pontuacao;
    private Usuario aluno;
    private List<TdHtmlEtapa> tdEtapas;

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

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public String getAssuntoGeral() {
        return assuntoGeral;
    }

    public void setAssuntoGeral(String assuntoGeral) {
        this.assuntoGeral = assuntoGeral;
    }

    public Integer getEtapaAtual() {
        return etapaAtual;
    }

    public void setEtapaAtual(Integer etapaAtual) {
        this.etapaAtual = etapaAtual;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public List<TdHtmlEtapa> getTdEtapas() {
        return tdEtapas;
    }

    public void setTdEtapas(List<TdHtmlEtapa> tdEtapas) {
        this.tdEtapas = tdEtapas;
    }
}
