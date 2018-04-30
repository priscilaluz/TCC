/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.support.AbstractIdBean;
import tcc.common.support.SimNaoType;
import tcc.common.util.ConstantesI18N;
import tcc.common.vo.Letra;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
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
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "RESPOSTA", schema = ConstantesI18N.SCHEMA)
public class Resposta extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Type(type = SimNaoType.TYPE_CLASS)
    @Column(name = "CORRETA", nullable = false)
    private Boolean correta;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERGUNTA_ID", nullable = false)
    private Pergunta pergunta;
    
    @Transient
    private int posicao;
    
    @Transient
    private List<Letra> letras;

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

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<Letra> getLetras() {
        letras = new ArrayList<>();
        if (descricao != null) {
            for (int i = 0; i < descricao.length(); i++) {
                String letra = ""+descricao.charAt(i);
                letras.add(new Letra(i, letra.toUpperCase()));
            }
        }
        return letras;
    }

    public void setLetras(List<Letra> letras) {}
}
