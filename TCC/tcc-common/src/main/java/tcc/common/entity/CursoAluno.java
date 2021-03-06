/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.enums.SituacaoCursoAluno;
import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "CURSO_ALUNO", schema = ConstantesI18N.SCHEMA)
public class CursoAluno extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;
    
    @Column(name = "POSICAO_ATUAL", nullable = false)
    private Integer posicaoAtual;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "tcc.common.enums.SituacaoCursoAluno")})
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoCursoAluno situacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario aluno;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cursoAluno")
    private Set<EtapaAluno> etapasAlunos = new HashSet<EtapaAluno>();

    public CursoAluno(Long id) {
        this.id = id;
    }

    public CursoAluno() {}
    
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

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public SituacaoCursoAluno getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCursoAluno situacao) {
        this.situacao = situacao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public Set<EtapaAluno> getEtapasAlunos() {
        return etapasAlunos;
    }

    public void setEtapasAlunos(Set<EtapaAluno> etapasAlunos) {
        this.etapasAlunos = etapasAlunos;
    }
}
