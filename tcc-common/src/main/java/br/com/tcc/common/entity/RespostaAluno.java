/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.common.entity;

import br.com.tcc.common.support.AbstractIdBean;
import br.com.tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "RESPOSTA_ALUNO", schema = ConstantesI18N.SCHEMA)
public class RespostaAluno extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERGUNTA_ALUNO_ID", nullable = false)
    private PerguntaAluno perguntaAluno;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPOSTA_ID", nullable = false)
    private Resposta resposta;
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PerguntaAluno getPerguntaAluno() {
        return perguntaAluno;
    }

    public void setPerguntaAluno(PerguntaAluno perguntaAluno) {
        this.perguntaAluno = perguntaAluno;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }
}
