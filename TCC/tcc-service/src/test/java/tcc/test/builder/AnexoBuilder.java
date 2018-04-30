package tcc.test.builder;

import tcc.common.entity.Anexo;

public class AnexoBuilder {
    private Anexo anexo;
    
    private AnexoBuilder(Anexo anexo){
        this.anexo = anexo;
    }
    
    public static AnexoBuilder nova(){
        return new AnexoBuilder(new Anexo());
    }
    
    public Anexo build(){
        return this.anexo;
    }
    
    public AnexoBuilder comNomeArquivo(String nomeArquivo){
        anexo.setNomeArquivo(nomeArquivo);
        return this;
    }
    
    public AnexoBuilder comBytes(byte[] bytes){
        anexo.setBytes(bytes);
        return this;
    }
}
