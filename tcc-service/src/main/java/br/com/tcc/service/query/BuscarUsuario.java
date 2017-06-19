/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.query;

import br.com.tcc.common.entity.Usuario;
import br.com.tcc.service.persistence.BusinessFluentQuery;
import java.io.Serializable;

public abstract class BuscarUsuario<T extends Serializable> extends BusinessFluentQuery<T, BuscarUsuario> {

    public static class Entities extends BuscarUsuario<Usuario> {
        public Entities() {
            appendText("select u from Usuario u ");
        }
    }
    
    public static class Count extends BuscarUsuario<Long> {
        public Count() {
            appendText("select count(u) from Usuario u ");
        }
    }

    public BuscarUsuario whereLogin(String login) {
        if (login != null && !login.isEmpty()) {
            appendText(getPreffixFilter());
            appendText(" u.login = :login ");
            addParameter("login", login);
        }    
        return this;
    }

    public BuscarUsuario whereSenha(String senha) {
        if (senha != null && !senha.isEmpty()) {
            appendText(getPreffixFilter());
            appendText(" u.senha = :senha ");
            addParameter("senha", senha);
        }    
        return this;
    }

    public BuscarUsuario whereEmail(String email) {
        if (email != null && !email.isEmpty()) {
            appendText(getPreffixFilter());
            appendText(" u.email = :email ");
            addParameter("email", email);
        }    
        return this;
    }

    
}
