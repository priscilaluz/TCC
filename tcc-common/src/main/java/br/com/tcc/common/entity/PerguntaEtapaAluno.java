package br.com.tcc.common.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.support.SimNaoType;
import br.com.tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PERGUNTA_ETAPA_ALUNO", schema = ConstantesI18N.SCHEMA)
public class PerguntaEtapaAluno extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PULO", nullable = false)
    private Boolean pulo;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DICA", nullable = false)
    private Boolean dica;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "TEMPO_ACABOU", nullable = false)
    private Boolean tempoAcabou;
    
    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;
    
    @Column(name = "APOSTAS", nullable = true)
    private String apostas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERGUNTA_ID", nullable = false)
    private Pergunta pergunta;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RELATORIO_ETAPA_ID", nullable = false)
    private RelatorioEtapa relatorioEtapa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPOSTA_ESCOLHIDA_ID", nullable = true)
    private Resposta respostaEscolhida;
    
    @Transient
    private Resposta respostaCorreta;
    
    @Transient
    private Boolean ganhou;
    
    @Transient
    private List<String> apostasFase;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPulo() {
        return pulo;
    }

    public void setPulo(Boolean pulo) {
        this.pulo = pulo;
    }

    public Boolean getDica() {
        return dica;
    }

    public void setDica(Boolean dica) {
        this.dica = dica;
    }

    public Boolean getTempoAcabou() {
        return tempoAcabou;
    }

    public void setTempoAcabou(Boolean tempoAcabou) {
        this.tempoAcabou = tempoAcabou;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getApostas() {
        return apostas;
    }

    public void setApostas(String apostas) {
        this.apostas = apostas;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public RelatorioEtapa getRelatorioEtapa() {
        return relatorioEtapa;
    }

    public void setRelatorioEtapa(RelatorioEtapa relatorioEtapa) {
        this.relatorioEtapa = relatorioEtapa;
    }

    public Resposta getRespostaEscolhida() {
        return respostaEscolhida;
    }

    public void setRespostaEscolhida(Resposta respostaEscolhida) {
        this.respostaEscolhida = respostaEscolhida;
    }

    public Resposta getRespostaCorreta() {
        if ((Hibernate.isInitialized(pergunta)) && (Hibernate.isInitialized(pergunta.getRespostas()))) {
            for (Resposta resposta : pergunta.getRespostas()) {
                if (resposta.getCorreta()){
                    respostaCorreta = resposta;
                }
            }
        }
        return respostaCorreta;
    }

    public void setRespostaCorreta(Resposta respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public Boolean getGanhou() {
        ganhou = getRespostaCorreta()!=null;
        return ganhou;
    }

    public void setGanhou(Boolean ganhou) {
        this.ganhou = ganhou;
    }

    public List<String> getApostasFase() {
        apostasFase = apostas!=null?Arrays.asList(apostas.split(",")):new ArrayList<String>();
        return apostasFase;
    }

    public void setApostasFase(List<String> apostasFase) {
        this.apostasFase = apostasFase;
    }
}
