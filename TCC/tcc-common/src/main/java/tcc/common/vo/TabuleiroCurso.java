/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import tcc.common.entity.Anexo;
import tcc.common.entity.Usuario;
import tcc.common.enums.SituacaoCursoAluno;
import java.util.List;
import java.util.Map;

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
    private SituacaoCursoAluno situacao;
    private boolean cursoEncerrado;
    private Integer qntTabuleiro;
    private Map<Integer,List<TdHtmlEtapa>> tabuleiros;

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

    public SituacaoCursoAluno getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCursoAluno situacao) {
        this.situacao = situacao;
    }

    public boolean isCursoEncerrado() {
        return cursoEncerrado;
    }

    public void setCursoEncerrado(boolean cursoEncerrado) {
        this.cursoEncerrado = cursoEncerrado;
    }

    public Map<Integer, List<TdHtmlEtapa>> getTabuleiros() {
        return tabuleiros;
    }

    public void setTabuleiros(Map<Integer, List<TdHtmlEtapa>> tabuleiros) {
        this.tabuleiros = tabuleiros;
    }

    public Integer getQntTabuleiro() {
        return tabuleiros.entrySet().size();
    }

    public void setQntTabuleiro(Integer qntTabuleiro) {
        this.qntTabuleiro = qntTabuleiro;
    }
}
