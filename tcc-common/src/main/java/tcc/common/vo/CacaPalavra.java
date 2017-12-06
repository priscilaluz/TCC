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
public class CacaPalavra {
    private Letra[][] matriz;
    private List<Pergunta> perguntas;
    private Integer tamanhoMatriz;

    public CacaPalavra() {}

    public CacaPalavra(Letra[][] matriz, List<Pergunta> perguntas, Integer tamanhoMatriz) {
        this.matriz = matriz;
        this.perguntas = perguntas;
        this.tamanhoMatriz = tamanhoMatriz;
    }

    public Letra[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Letra[][] matriz) {
        this.matriz = matriz;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public Integer getTamanhoMatriz() {
        return tamanhoMatriz;
    }

    public void setTamanhoMatriz(Integer tamanhoMatriz) {
        this.tamanhoMatriz = tamanhoMatriz;
    }
}
