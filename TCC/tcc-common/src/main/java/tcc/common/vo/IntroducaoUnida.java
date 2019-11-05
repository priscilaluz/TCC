/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import tcc.common.entity.Pergunta;
import java.util.List;

/**
 *
 * @author ADM
 */
public class IntroducaoUnida {
    private String Introducao;
    private List<Pergunta> perguntas;

    public IntroducaoUnida() {}

    public IntroducaoUnida(String Introducao, List<Pergunta> perguntas) {
        this.Introducao = Introducao;
        this.perguntas = perguntas;
    }

    public String getIntroducao() {
        return Introducao;
    }

    public void setIntroducao(String Introducao) {
        this.Introducao = Introducao;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }
    
    

}
