/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.enums.Jogo;
import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Comparator;
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
import javax.persistence.Transient;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "ETAPA", schema = ConstantesI18N.SCHEMA)
public class Etapa extends AbstractIdBean<Long> implements Comparator<Etapa> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "ASSUNTO", nullable = false)
    private String assunto;
    
    @Column(name = "NIVEL", nullable = false)
    private Integer nivel;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "tcc.common.enums.Jogo")})
    @Column(name = "JOGO", nullable = false)
    private Jogo jogo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANEXO_ID", nullable = true)
    private Anexo anexo;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "etapa")
    private Set<EtapaPergunta> etapasPerguntas = new HashSet<EtapaPergunta>();
    
    @Transient
    private Long idAnexoExcluido;
    
    @Transient
    private Long idCurso;

    public Etapa(Long id) {
        this.id = id;
    }

    public Etapa() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<EtapaPergunta> getEtapasPerguntas() {
        if (etapasPerguntas == null) {
            etapasPerguntas = new HashSet<>();
        }
        return etapasPerguntas;
    }

    public void setEtapasPerguntas(Set<EtapaPergunta> etapasPerguntas) {
        this.etapasPerguntas = etapasPerguntas;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdAnexoExcluido() {
        return idAnexoExcluido;
    }

    public void setIdAnexoExcluido(Long idAnexoExcluido) {
        this.idAnexoExcluido = idAnexoExcluido;
    }

    @Override
    public int compare(Etapa e1, Etapa e2) {
        if (e1 == null || e2 == null) {
            return 0;
        }
        return (e1.getId() > e2.getId()) ? 1 : 1;
    }
}
