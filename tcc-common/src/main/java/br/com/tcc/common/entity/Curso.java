/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.entity;

import br.com.tcc.common.enums.CategoriaEnum;
import br.com.tcc.common.enums.SituacaoCurso;
import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.util.ConstantesI18N;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "CURSO", schema = ConstantesI18N.SCHEMA)
public class Curso extends AbstractIdBean<Long> {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "NOME", nullable = false)
    private String nome;
    
    @Column(name = "ASSUNTO_GERAL", nullable = true)
    private String assuntoGeral;
    
    @Column(name = "COD_ACESSO", nullable = false)
    private String codAcesso;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.tcc.common.support.GenericEnumUserType",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "enumClass",
                        value = "br.com.tcc.common.enums.Categoria")})
    @Column(name = "CATEGORIA", nullable = false)
    private CategoriaEnum categoria;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CATEGORIA_ID", nullable = false)
//    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANEXO_ID", nullable = true)
    private Anexo anexo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.tcc.common.support.GenericEnumUserType",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "enumClass",
                        value = "br.com.tcc.common.enums.SituacaoCurso")})
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoCurso situacao;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    private Set<Etapa> etapas = new HashSet<Etapa>();
    
    @Transient
    private Long idAnexo;
    
    @Transient
    private int ultimaEtapa;
    
    @Transient
    private List<Etapa> etapasLista = new ArrayList<Etapa>();
    
    public Curso() {
    }
    
    public Curso(Long id) {
        this.id = id;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAssuntoGeral() {
        return assuntoGeral;
    }
    
    public void setAssuntoGeral(String assuntoGeral) {
        this.assuntoGeral = assuntoGeral;
    }
    
    public String getCodAcesso() {
        return codAcesso;
    }
    
    public void setCodAcesso(String codAcesso) {
        this.codAcesso = codAcesso;
    }
    
    public CategoriaEnum getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }
    
    public Anexo getAnexo() {
        return anexo;
    }
    
    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Set<Etapa> getEtapas() {
        if (etapas == null) {
            etapas = new HashSet<>();
        }
        return etapas;
    }
    
    public void setEtapas(Set<Etapa> etapas) {
        this.etapas = etapas;
    }
    
    public SituacaoCurso getSituacao() {
        return situacao;
    }
    
    public void setSituacao(SituacaoCurso situacao) {
        this.situacao = situacao;
    }
    
    public Long getIdAnexo() {
        return idAnexo;
    }
    
    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }
    
    public int getUltimaEtapa() {
        return ultimaEtapa;
    }
    
    public void setUltimaEtapa(int ultimaEtapa) {
        this.ultimaEtapa = ultimaEtapa;
    }
    
    public List<Etapa> getEtapasLista() {
        if (Hibernate.isInitialized(etapas)) {
            etapasLista = new ArrayList<>(etapas);
            Collections.sort(etapasLista, new Comparator<Etapa>() {
                @Override
                public int compare(Etapa e1, Etapa e2) {
                    if (e1 == null || e2 == null) {
                        return 0;
                    }
                    return (e1.getId() > e2.getId()) ? 1 : -1;
                }
            });
        }
        return etapasLista;
    }
    
    public void setEtapasLista(List<Etapa> etapasLista) {
        this.etapasLista = etapasLista;
    }
}
