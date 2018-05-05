/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.enums.DisponibilidadeCurso;
import tcc.common.enums.SituacaoCurso;
import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIA_ID", nullable = false)
    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANEXO_ID", nullable = true)
    private Anexo anexo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "enumClass",
                        value = "tcc.common.enums.SituacaoCurso")})
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoCurso situacao;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "enumClass",
                        value = "tcc.common.enums.DisponibilidadeCurso")})
    @Column(name = "DISPONIBILIDADE", nullable = false)
    private DisponibilidadeCurso disponibilidade;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    private Set<Etapa> etapas = new HashSet<Etapa>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    private Set<Aviso> avisos = new HashSet<Aviso>();
    
    @Transient
    private Long idAnexoExcluido;
    
    @Transient
    private int ultimaEtapa;
    
    @Transient
    private List<Etapa> etapasLista = new ArrayList<Etapa>();
    
    @Transient
    private List<Aviso> avisosLista = new ArrayList<Aviso>();
    
    @Transient
    private boolean alunoPertence;
    
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
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
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

    public DisponibilidadeCurso getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(DisponibilidadeCurso disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Long getIdAnexoExcluido() {
        return idAnexoExcluido;
    }

    public void setIdAnexoExcluido(Long idAnexoExcluido) {
        this.idAnexoExcluido = idAnexoExcluido;
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

    public boolean isAlunoPertence() {
        return alunoPertence;
    }

    public void setAlunoPertence(boolean alunoPertence) {
        this.alunoPertence = alunoPertence;
    }

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public void setAvisos(Set<Aviso> avisos) {
        this.avisos = avisos;
    }

    public List<Aviso> getAvisosLista() {
        if (Hibernate.isInitialized(avisos)) {
            avisosLista = new ArrayList<>(avisos);
            Collections.sort(avisosLista, new Comparator<Aviso>() {
                @Override
                public int compare(Aviso a1, Aviso a2) {
                    if (a1 == null || a2 == null) {
                        return 0;
                    }
                    return (a1.getId() > a2.getId()) ? 1 : -1;
                }
            });
        }
        return avisosLista;
    }

    public void setAvisosLista(List<Aviso> avisosLista) {
        this.avisosLista = avisosLista;
    }
}
