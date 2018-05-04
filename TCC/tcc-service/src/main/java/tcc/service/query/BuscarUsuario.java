/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.service.query;

import tcc.common.entity.Usuario;
import tcc.common.enums.TipoUsuario;
import tcc.service.persistence.BusinessFluentQuery;
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

    public BuscarUsuario whereId(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" u.id = :id ");
            addParameter("id", id);
        }    
        return this;
    }

    public BuscarUsuario whereIdNot(Long id) {
        if (id != null) {
            appendText(getPreffixFilter());
            appendText(" u.id != :id ");
            addParameter("id", id);
        }    
        return this;
    }
    
    public BuscarUsuario whereNomeLike(String nome) {
        if (nome != null && !nome.isEmpty()) {
            appendText(getPreffixFilter());
            appendText(" upper(u.nome) like upper(:nome) ");
            addParameter("nome", "%"+nome+"%");
        }    
        return this;
    }
    
    public BuscarUsuario whereTipo(TipoUsuario tipo) {
        if (tipo != null) {
            appendText(getPreffixFilter());
            appendText(" u.tipo = :tipo ");
            addParameter("tipo", tipo);
        }    
        return this;
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
    
    public BuscarUsuario whereCursoNaoTem(Long idCurso) {
        if (idCurso != null) {
            appendText(getPreffixFilter());
            appendText(" u.id not in ");
            appendText(" (select u1.id from Usuario u1 ");
            appendText(" left join u1.alunos a ");
            appendText(" left join a.curso c ");
            appendText(" where c.id = :idCurso) ");
            addParameter("idCurso", idCurso);
        }    
        return this;
    }

    
}
