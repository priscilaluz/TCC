package br.com.tcc.test.builder;

import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Avatar;
import br.com.tcc.common.enums.TipoUsuario;

public class UsuarioBuilder {
    private Usuario usuario;
    
    private UsuarioBuilder(Usuario usuario){
        this.usuario = usuario;
    }
    
    public static UsuarioBuilder nova(){
        return new UsuarioBuilder(new Usuario());
    }
    
    public Usuario build(){
        return this.usuario;
    }
    
    public UsuarioBuilder comNome(String nome){
        usuario.setNome(nome);
        return this;
    }
    
    public UsuarioBuilder comEmail(String email){
        usuario.setEmail(email);
        return this;
    }
    
    public UsuarioBuilder comLogin(String login){
        usuario.setLogin(login);
        return this;
    }
    
    public UsuarioBuilder comSenha(String senha){
        usuario.setSenha(senha);
        return this;
    }
    
    public UsuarioBuilder comTipoUsuario(TipoUsuario tipo){
        usuario.setTipo(tipo);
        return this;
    }
    
    public UsuarioBuilder comAvatar(Avatar avatar){
        usuario.setAvatar(avatar);
        return this;
    }
}
