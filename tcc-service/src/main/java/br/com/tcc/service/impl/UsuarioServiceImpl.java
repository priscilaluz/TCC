/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.service.impl;

import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.TipoUsuario;
import br.com.tcc.service.persistence.GenericDao;
import br.com.tcc.service.query.BuscarUsuario;
import br.com.tcc.service.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADM
 */
@Component("UsuarioServiceImpl")
public class UsuarioServiceImpl {

    @Autowired
    private GenericDao dao;
    
    @Autowired
    private UsuarioValidator validador;

    @Transactional(readOnly = false)
    public Usuario salvarUsuario(Usuario usuario) {
        Long emailJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereEmail(usuario.getEmail()));
        Long loginJaExistente = (Long) dao.uniqueResult(new BuscarUsuario.Count().whereLogin(usuario.getLogin()));
        usuario.setTipo(TipoUsuario.ALUNO);
        validador.validarSalvarUsuario(usuario, emailJaExistente, loginJaExistente);
        return dao.saveOrUpdate(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorLoginSenha(String login, String senha) {
        return (Usuario) dao.uniqueResult(new BuscarUsuario.Entities().whereLogin(login).whereSenha(senha));
    }
    
    
}
