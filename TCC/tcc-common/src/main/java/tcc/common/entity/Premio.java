/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "PREMIO", schema = ConstantesI18N.SCHEMA)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Premio extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "QNT_QUIZ_VENCIDOS", nullable = false)
    private Integer qntQuizVencidos;

    @Column(name = "QNT_PERG_FACIL_CORRETA", nullable = false)
    private Integer qntPergFacilCorreta;
    
    @Column(name = "QNT_PERG_MEDIA_CORRETA", nullable = false)
    private Integer qntPergMediaCorreta;
    
    @Column(name = "QNT_PERG_DIFICIL_CORRETA", nullable = false)
    private Integer qntPergDificilCorreta;
    
    @Column(name = "QNT_APOSTA_VENCIDAS", nullable = false)
    private Integer qntApostaVencida;
    
    @Column(name = "QNT_APOSTA_TUDO_PERG_VENCIDAS", nullable = false)
    private Integer qntApostaTudoEmUmaPergVencida;
    
    @Column(name = "QNT_FORCA_VENCIDAS", nullable = false)
    private Integer qntForcaVencida;
    
    @Column(name = "QNT_CACA_PALAVRAS_VENCIDAS", nullable = false)
    private Integer qntCacaPalavrasVencida;
    
    @Column(name = "QNT_JOGO_VELHA_VENCIDAS", nullable = false)
    private Integer qntJogoDaVelhaVencida;
    
    @Column(name = "QNT_ETAPA_VENCIDA", nullable = false)
    private Integer qntEtapaVencida;
    
    @Column(name = "QNT_CURSO_CONCLUIDO", nullable = false)
    private Integer qntCursoConcluido;
    
    @Column(name = "QNT_ETAPA_SEM_PULO", nullable = false)
    private Integer qntEtapaSemPulo;
    
    @Column(name = "QNT_ETAPA_SEM_DICA", nullable = false)
    private Integer qntEtapaSemDica;
    
    @Column(name = "QNT_PONTOS_ACUMULADOS", nullable = false)
    private Integer qntPontosAcumulados;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;
    
    public Premio() {}

    public Premio(Long id) {
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

    public Integer getQntQuizVencidos() {
        return qntQuizVencidos!=null?qntQuizVencidos:0;
    }

    public void setQntQuizVencidos(Integer qntQuizVencidos) {
        this.qntQuizVencidos = qntQuizVencidos;
    }

    public Integer getQntPergFacilCorreta() {
        return qntPergFacilCorreta!=null?qntPergFacilCorreta:0;
    }

    public void setQntPergFacilCorreta(Integer qntPergFacilCorreta) {
        this.qntPergFacilCorreta = qntPergFacilCorreta;
    }

    public Integer getQntPergMediaCorreta() {
        return qntPergMediaCorreta!=null?qntPergMediaCorreta:0;
    }

    public void setQntPergMediaCorreta(Integer qntPergMediaCorreta) {
        this.qntPergMediaCorreta = qntPergMediaCorreta;
    }

    public Integer getQntPergDificilCorreta() {
        return qntPergDificilCorreta!=null?qntPergDificilCorreta:0;
    }

    public void setQntPergDificilCorreta(Integer qntPergDificilCorreta) {
        this.qntPergDificilCorreta = qntPergDificilCorreta;
    }

    public Integer getQntApostaVencida() {
        return qntApostaVencida!=null?qntApostaVencida:0;
    }

    public void setQntApostaVencida(Integer qntApostaVencida) {
        this.qntApostaVencida = qntApostaVencida;
    }

    public Integer getQntApostaTudoEmUmaPergVencida() {
        return qntApostaTudoEmUmaPergVencida!=null?qntApostaTudoEmUmaPergVencida:0;
    }

    public void setQntApostaTudoEmUmaPergVencida(Integer qntApostaTudoEmUmaPergVencida) {
        this.qntApostaTudoEmUmaPergVencida = qntApostaTudoEmUmaPergVencida;
    }

    public Integer getQntForcaVencida() {
        return qntForcaVencida!=null?qntForcaVencida:0;
    }

    public void setQntForcaVencida(Integer qntForcaVencida) {
        this.qntForcaVencida = qntForcaVencida;
    }

    public Integer getQntCacaPalavrasVencida() {
        return qntCacaPalavrasVencida!=null?qntCacaPalavrasVencida:0;
    }

    public void setQntCacaPalavrasVencida(Integer qntCacaPalavrasVencida) {
        this.qntCacaPalavrasVencida = qntCacaPalavrasVencida;
    }

    public Integer getQntJogoDaVelhaVencida() {
        return qntJogoDaVelhaVencida!=null?qntJogoDaVelhaVencida:0;
    }

    public void setQntJogoDaVelhaVencida(Integer qntJogoDaVelhaVencida) {
        this.qntJogoDaVelhaVencida = qntJogoDaVelhaVencida;
    }

    public Integer getQntEtapaVencida() {
        return qntEtapaVencida!=null?qntEtapaVencida:0;
    }

    public void setQntEtapaVencida(Integer qntEtapaVencida) {
        this.qntEtapaVencida = qntEtapaVencida;
    }

    public Integer getQntCursoConcluido() {
        return qntCursoConcluido!=null?qntCursoConcluido:0;
    }

    public void setQntCursoConcluido(Integer qntCursoConcluido) {
        this.qntCursoConcluido = qntCursoConcluido;
    }

    public Integer getQntEtapaSemPulo() {
        return qntEtapaSemPulo!=null?qntEtapaSemPulo:0;
    }

    public void setQntEtapaSemPulo(Integer qntEtapaSemPulo) {
        this.qntEtapaSemPulo = qntEtapaSemPulo;
    }

    public Integer getQntEtapaSemDica() {
        return qntEtapaSemDica!=null?qntEtapaSemDica:0;
    }

    public void setQntEtapaSemDica(Integer qntEtapaSemDica) {
        this.qntEtapaSemDica = qntEtapaSemDica;
    }

    public Integer getQntPontosAcumulados() {
        return qntPontosAcumulados!=null?qntPontosAcumulados:0;
    }

    public void setQntPontosAcumulados(Integer qntPontosAcumulados) {
        this.qntPontosAcumulados = qntPontosAcumulados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

}
