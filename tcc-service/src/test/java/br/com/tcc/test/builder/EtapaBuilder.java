package br.com.tcc.test.builder;

import br.com.tcc.common.entity.Anexo;
import br.com.tcc.common.entity.Curso;
import br.com.tcc.common.entity.Etapa;
import br.com.tcc.common.entity.EtapaPergunta;
import br.com.tcc.common.entity.Pergunta;
import br.com.tcc.common.enums.Jogo;
import java.util.List;
import java.util.Set;

public class EtapaBuilder {
    private Etapa etapa;
    
    private EtapaBuilder(Etapa etapa){
        this.etapa = etapa;
    }
    
    public static EtapaBuilder nova(){
        return new EtapaBuilder(new Etapa());
    }
    
    public Etapa build(){
        return this.etapa;
    }
    
    public EtapaBuilder comAssunto(String assunto){
        etapa.setAssunto(assunto);
        return this;
    }
    
    public EtapaBuilder comNivel(Integer nivel){
        etapa.setNivel(nivel);
        return this;
    }
    
    public EtapaBuilder comPulo(Integer pulo){
        etapa.setPulo(pulo);
        return this;
    }
    
    public EtapaBuilder comJogo(Jogo jogo){
        etapa.setJogo(jogo);
        return this;
    }
    
    public EtapaBuilder comAnexo(Anexo anexo){
        etapa.setAnexo(anexo);
        return this;
    }
    
    public EtapaBuilder comCurso(Curso curso){
        etapa.setCurso(curso);
        return this;
    }
    
    public EtapaBuilder comEtapasPerguntas(Set<EtapaPergunta> etapasPerguntas){
        etapa.setEtapasPerguntas(etapasPerguntas);
        return this;
    }
}
