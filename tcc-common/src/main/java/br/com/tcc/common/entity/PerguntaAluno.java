/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.entity;

import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.support.SimNaoType;
import br.com.tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "PERGUNTA_ALUNO", schema = ConstantesI18N.SCHEMA)
public class PerguntaAluno extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ETAPA_ALUNO_ID", nullable = false)
    private EtapaAluno etapaAluno;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERGUNTA_ID", nullable = false)
    private Pergunta pergunta;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "PULO", nullable = false)
    private Boolean pulo;
    
    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "DICA", nullable = false)
    private Boolean dica;
    
    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perguntaAluno")
    private Set<RespostaAluno> respostasAlunos = new HashSet<RespostaAluno>();
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public EtapaAluno getEtapaAluno() {
        return etapaAluno;
    }

    public void setEtapaAluno(EtapaAluno etapaAluno) {
        this.etapaAluno = etapaAluno;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
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

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Set<RespostaAluno> getRespostasAlunos() {
        return respostasAlunos;
    }

    public void setRespostasAlunos(Set<RespostaAluno> respostasAlunos) {
        this.respostasAlunos = respostasAlunos;
    }
}
