/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.entity;

import br.com.tcc.common.enums.CategoriaEnum;
import br.com.tcc.common.enums.NivelPergunta;
import br.com.tcc.common.enums.TipoPergunta;
import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "PERGUNTA", schema = ConstantesI18N.SCHEMA)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pergunta extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "JUSTIFICATIVA", nullable = true)
    private String justificativa;

    @Column(name = "DICA", nullable = true)
    private String dica;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.tcc.common.enums.Categoria")})
    @Column(name = "CATEGORIA", nullable = false)
    private CategoriaEnum categoria;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.tcc.common.enums.TipoPergunta")})
    @Column(name = "TIPO", nullable = false)
    private TipoPergunta tipo;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "br.com.tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "br.com.tcc.common.enums.NivelPergunta")})
    @Column(name = "NIVEL", nullable = false)
    private NivelPergunta nivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANEXO_ID", nullable = true)
    private Anexo anexo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pergunta")
    private Set<Resposta> respostas = new HashSet<Resposta>();
    
    @Transient
    private Long idAnexo;
    
    @Transient
    private int posicao;

    public Pergunta() {}

    public Pergunta(Long id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Anexo getAnexo() {
        return anexo;
    }

    public void setAnexo(Anexo anexo) {
        this.anexo = anexo;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public TipoPergunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPergunta tipo) {
        this.tipo = tipo;
    }

    public NivelPergunta getNivel() {
        return nivel;
    }

    public void setNivel(NivelPergunta nivel) {
        this.nivel = nivel;
    }

    public Long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Long idAnexo) {
        this.idAnexo = idAnexo;
    }
}
