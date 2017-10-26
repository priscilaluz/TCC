package br.com.tcc.test.builder;

import br.com.tcc.common.entity.EtapaAluno;
import br.com.tcc.common.entity.PerguntaEtapaAluno;
import br.com.tcc.common.entity.RelatorioEtapa;
import java.util.Set;

public class RelatorioEtapaBuilder {
    private final RelatorioEtapa relatorioEtapa;
    
    private RelatorioEtapaBuilder(RelatorioEtapa relatorioEtapa){
        this.relatorioEtapa = relatorioEtapa;
    }
    
    public static RelatorioEtapaBuilder novo(){
        return new RelatorioEtapaBuilder(new RelatorioEtapa());
    }
    
    public RelatorioEtapa build(){
        return this.relatorioEtapa;
    }
    
    public RelatorioEtapaBuilder comPontuacao(Integer pontuacao){
        relatorioEtapa.setPontuacao(pontuacao);
        return this;
    }
    
    public RelatorioEtapaBuilder comEtapaAluno(EtapaAluno etapaAluno){
        relatorioEtapa.setEtapaAluno(etapaAluno);
        return this;
    }
    
    public RelatorioEtapaBuilder comPerguntasEtapasAlunos(Set<PerguntaEtapaAluno> perguntasEtapasAlunos){
        relatorioEtapa.setPerguntasEtapasAlunos(perguntasEtapasAlunos);
        return this;
    }
    
    public RelatorioEtapaBuilder comGanhou(Boolean ganhou){
        relatorioEtapa.setGanhou(ganhou);
        return this;
    }
    
}
