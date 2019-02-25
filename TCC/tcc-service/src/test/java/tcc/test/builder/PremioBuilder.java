package tcc.test.builder;

import tcc.common.entity.Premio;
import tcc.common.entity.Usuario;

public class PremioBuilder {
    private Premio premio;
    
    private PremioBuilder(Premio premio){
        this.premio = premio;
    }
    
    public static PremioBuilder novo(){
        return new PremioBuilder(new Premio());
    }
    
    public Premio build(){
        return this.premio;
    }
    
    public PremioBuilder comQntEtapaVencida(Integer qntEtapaVencida) {
        premio.setQntEtapaVencida(qntEtapaVencida);
        return this;
    }
    
    public PremioBuilder comQntCursoConcluido(Integer qntCursoConcluido) {
        premio.setQntCursoConcluido(qntCursoConcluido);
        return this;
    }
    
    public PremioBuilder comQntEtapaSemPulo(Integer qntEtapaSemPulo) {
        premio.setQntEtapaSemPulo(qntEtapaSemPulo);
        return this;
    }
    
    public PremioBuilder comQntEtapaSemDica(Integer qntEtapaSemDica) {
        premio.setQntEtapaSemDica(qntEtapaSemDica);
        return this;
    }
    
    public PremioBuilder comQntPontosAcumulados(Integer qntPontosAcumulados) {
        premio.setQntPontosAcumulados(qntPontosAcumulados);
        return this;
    }
    
    public PremioBuilder comUsuario(Usuario usuario) {
        premio.setUsuario(usuario);
        return this;
    }
}
