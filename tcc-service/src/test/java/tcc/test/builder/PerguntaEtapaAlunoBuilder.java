package tcc.test.builder;

import tcc.common.entity.Pergunta;
import tcc.common.entity.PerguntaEtapaAluno;
import tcc.common.entity.RelatorioEtapa;
import tcc.common.entity.Resposta;

public class PerguntaEtapaAlunoBuilder {
    private final PerguntaEtapaAluno perguntaEtapaAluno;
    
    private PerguntaEtapaAlunoBuilder(PerguntaEtapaAluno perguntaEtapaAluno){
        this.perguntaEtapaAluno = perguntaEtapaAluno;
    }
    
    public static PerguntaEtapaAlunoBuilder novo(){
        return new PerguntaEtapaAlunoBuilder(new PerguntaEtapaAluno());
    }
    
    public PerguntaEtapaAluno build(){
        return this.perguntaEtapaAluno;
    }
    
    public PerguntaEtapaAlunoBuilder comPulo(Boolean pulo){
        perguntaEtapaAluno.setPulo(pulo);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comDica(Boolean dica){
        perguntaEtapaAluno.setDica(dica);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comTempoAcabou(Boolean tempoAcabou){
        perguntaEtapaAluno.setTempoAcabou(tempoAcabou);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comPontuacao(Integer pontuacao){
        perguntaEtapaAluno.setPontuacao(pontuacao);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comPergunta(Pergunta pergunta){
        perguntaEtapaAluno.setPergunta(pergunta);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comRelatorioEtapa(RelatorioEtapa relatorioEtapa){
        perguntaEtapaAluno.setRelatorioEtapa(relatorioEtapa);
        return this;
    }
    
    public PerguntaEtapaAlunoBuilder comRespostaEscolhida(Resposta respostaEscolhida){
        perguntaEtapaAluno.setRespostaEscolhida(respostaEscolhida);
        return this;
    }
    
}
