/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.entity;

import java.util.Date;
import tcc.common.enums.Avatar;
import tcc.common.enums.TipoUsuario;
import tcc.common.support.AbstractIdBean;
import tcc.common.util.ConstantesI18N;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Type;

/**
 *
 * @author ADM
 */
@Entity
@Table(name = "USUARIO", schema = ConstantesI18N.SCHEMA)
public class Usuario extends AbstractIdBean<Long> {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;
    
    @Column(name = "LOGIN", nullable = false)
    private String login;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Column(name = "SENHA", nullable = false)
    private String senha;
    
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Column(name = "DATA_ULTIMO_ACESSO", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataUltimoAcesso;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "tcc.common.enums.TipoUsuario")})
    @Column(name = "TIPO", nullable = false)
    private TipoUsuario tipo;
    
    @Enumerated(EnumType.STRING)
    @Type(type = "tcc.common.support.GenericEnumUserType",
        parameters = {@org.hibernate.annotations.Parameter(name = "enumClass",
                    value = "tcc.common.enums.Avatar")})
    @Column(name = "AVATAR", nullable = false)
    private Avatar avatar;

    public Usuario() {
    }

    public Usuario(Long id) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Date dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Avatar getAvatar() {
        return avatar!=null?avatar:Avatar.PESSOA;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
