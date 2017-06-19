package br.com.tcc.test.builder;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;
import br.com.tcc.common.entity.Usuario;
import br.com.tcc.common.enums.Categoria;
import java.util.Set;

public class PerguntaBuilder {
    private Pergunta pergunta;
    
    private PerguntaBuilder(Pergunta pergunta){
        this.pergunta = pergunta;
    }
    
    public static PerguntaBuilder nova(){
        return new PerguntaBuilder(new Pergunta());
    }
    
    public Pergunta build(){
        return this.pergunta;
    }
    
    public PerguntaBuilder comDescricao(String descricao){
        pergunta.setDescricao(descricao);
        return this;
    }
    
    public PerguntaBuilder comJustificativa(String justificativa){
        pergunta.setJustificativa(justificativa);
        return this;
    }
    
    public PerguntaBuilder comCategoria(Categoria categoria){
        pergunta.setCategoria(categoria);
        return this;
    }
    
    public PerguntaBuilder comUsuario(Usuario usuario){
        pergunta.setUsuario(usuario);
        return this;
    }
    
    public PerguntaBuilder comRespostas(Set<Resposta> respostas){
        pergunta.setRespostas(respostas);
        return this;
    }
    
}
