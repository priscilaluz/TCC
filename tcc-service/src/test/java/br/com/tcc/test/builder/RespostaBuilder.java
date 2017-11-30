package br.com.tcc.test.builder;

import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.entity.Resposta;

public class RespostaBuilder {
    private Resposta resposta;
    
    private RespostaBuilder(Resposta resposta){
        this.resposta = resposta;
    }
    
    public static RespostaBuilder nova(){
        return new RespostaBuilder(new Resposta());
    }
    
    public Resposta build(){
        return this.resposta;
    }
    
    public RespostaBuilder comDescricao(String descricao){
        resposta.setDescricao(descricao);
        return this;
    }
    
    public RespostaBuilder comCorreta(Boolean correta){
        resposta.setCorreta(correta);
        return this;
    }
    
    public RespostaBuilder comPergunta(Pergunta pergunta){
        resposta.setPergunta(pergunta);
        return this;
    }
}
