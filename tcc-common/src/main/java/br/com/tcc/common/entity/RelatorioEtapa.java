package br.com.tcc.common.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.util.ConstantesI18N;
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

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "RELATORIO_ETAPA", schema = ConstantesI18N.SCHEMA)
public class RelatorioEtapa extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ETAPA_ALUNO_ID", nullable = false)
    private EtapaAluno etapaAluno;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "relatorioEtapa")
    private Set<PerguntaEtapaAluno> perguntasEtapasAlunos = new HashSet<PerguntaEtapaAluno>();
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public EtapaAluno getEtapaAluno() {
        return etapaAluno;
    }

    public void setEtapaAluno(EtapaAluno etapaAluno) {
        this.etapaAluno = etapaAluno;
    }

    public Set<PerguntaEtapaAluno> getPerguntasEtapasAlunos() {
        return perguntasEtapasAlunos;
    }

    public void setPerguntasEtapasAlunos(Set<PerguntaEtapaAluno> perguntasEtapasAlunos) {
        this.perguntasEtapasAlunos = perguntasEtapasAlunos;
    }

}
