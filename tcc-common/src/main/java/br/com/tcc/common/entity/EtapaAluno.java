/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.entity;

import br.com.tcc.common.support.AbstractIdBean;
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

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "ETAPA_ALUNO", schema = ConstantesI18N.SCHEMA)
public class EtapaAluno extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ALUNO_ID", nullable = false)
    private CursoAluno cursoAluno;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ETAPA_ID", nullable = false)
    private Etapa etapa;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "etapaAluno")
    private Set<RelatorioEtapa> relatorios = new HashSet<RelatorioEtapa>();

    public EtapaAluno(Long id) {
        this.id = id;
    }

    public EtapaAluno() {}
    
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

    public CursoAluno getCursoAluno() {
        return cursoAluno;
    }

    public void setCursoAluno(CursoAluno cursoAluno) {
        this.cursoAluno = cursoAluno;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Set<RelatorioEtapa> getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(Set<RelatorioEtapa> relatorios) {
        this.relatorios = relatorios;
    }
}
