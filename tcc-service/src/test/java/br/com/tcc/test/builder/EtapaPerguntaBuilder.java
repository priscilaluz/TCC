package br.com.tcc.test.builder;

import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;

public class EtapaPerguntaBuilder {
    private EtapaPergunta etapaPergunta;
    
    private EtapaPerguntaBuilder(EtapaPergunta etapaPergunta){
        this.etapaPergunta = etapaPergunta;
    }
    
    public static EtapaPerguntaBuilder nova(){
        return new EtapaPerguntaBuilder(new EtapaPergunta());
    }
    
    public EtapaPergunta build(){
        return this.etapaPergunta;
    }
    
    public EtapaPerguntaBuilder comPosicao(Integer posicao){
        etapaPergunta.setPosicao(posicao);
        return this;
    }
    
    public EtapaPerguntaBuilder comEtapa(Etapa etapa){
        etapaPergunta.setEtapa(etapa);
        return this;
    }
    
    public EtapaPerguntaBuilder comPergunta(Pergunta pergunta){
        etapaPergunta.setPergunta(pergunta);
        return this;
    }
}
