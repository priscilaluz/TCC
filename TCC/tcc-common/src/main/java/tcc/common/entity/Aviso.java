/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Comparator;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "AVISO", schema = ConstantesI18N.SCHEMA)
public class Aviso extends AbstractIdBean<Long> implements Comparator<Aviso> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "TITULO", nullable = false)
    private String titulo;
    
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;
    
    @Column(name = "DATA_MODIFICACAO", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataModificao;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso;
    
    public Aviso(Long id) {
        this.id = id;
    }

    public Aviso() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataModificao() {
        return dataModificao;
    }

    public void setDataModificao(Date dataModificao) {
        this.dataModificao = dataModificao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int compare(Aviso e1, Aviso e2) {
        if (e1 == null || e2 == null) {
            return 0;
        }
        return (e1.getId() > e2.getId()) ? 1 : 1;
    }
}
