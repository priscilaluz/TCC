/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

import java.util.List;

/**
 *
 * @author ADM
 */
public class CacaPalavraLista {
    private List<CacaPalavra> cacaPalavra;
    private Integer qntPergunta;

    public CacaPalavraLista() {}

    public CacaPalavraLista(List<CacaPalavra> cacaPalavra, Integer qntPergunta) {
        this.cacaPalavra = cacaPalavra;
        this.qntPergunta = qntPergunta;
    }

    public List<CacaPalavra> getCacaPalavra() {
        return cacaPalavra;
    }

    public void setCacaPalavra(List<CacaPalavra> cacaPalavra) {
        this.cacaPalavra = cacaPalavra;
    }

    public Integer getQntPergunta() {
        return qntPergunta;
    }

    public void setQntPergunta(Integer qntPergunta) {
        this.qntPergunta = qntPergunta;
    }
}
