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
public class TdHtmlEtapa {
    private TabuleiroEtapa etapa1;
    private TabuleiroEtapa etapa2;
    private TabuleiroEtapa etapa3;
    private TabuleiroEtapa etapa4;
    private TabuleiroEtapa etapa5;

    public TdHtmlEtapa() {}

    public TdHtmlEtapa(TabuleiroEtapa etapa1, TabuleiroEtapa etapa2, TabuleiroEtapa etapa3, TabuleiroEtapa etapa4, TabuleiroEtapa etapa5) {
        this.etapa1 = etapa1;
        this.etapa2 = etapa2;
        this.etapa3 = etapa3;
        this.etapa4 = etapa4;
        this.etapa5 = etapa5;
    }
    
    public TabuleiroEtapa getEtapa1() {
        return etapa1;
    }

    public void setEtapa1(TabuleiroEtapa etapa1) {
        this.etapa1 = etapa1;
    }

    public void setEtapa1(List<TabuleiroEtapa> etapas, int posicao) {
        this.etapa1 = (posicao >= etapas.size()) ? new TabuleiroEtapa() : etapas.get(posicao);
    }

    public TabuleiroEtapa getEtapa2() {
        return etapa2;
    }

    public void setEtapa2(TabuleiroEtapa etapa2) {
        this.etapa2 = etapa2;
    }

    public void setEtapa2(List<TabuleiroEtapa> etapas, int posicao) {
        this.etapa2 = (posicao >= etapas.size()) ? new TabuleiroEtapa() : etapas.get(posicao);
    }

    public TabuleiroEtapa getEtapa3() {
        return etapa3;
    }

    public void setEtapa3(TabuleiroEtapa etapa3) {
        this.etapa3 = etapa3;
    }

    public void setEtapa3(List<TabuleiroEtapa> etapas, int posicao) {
        this.etapa3 = (posicao >= etapas.size()) ? new TabuleiroEtapa() : etapas.get(posicao);
    }

    public TabuleiroEtapa getEtapa4() {
        return etapa4;
    }

    public void setEtapa4(TabuleiroEtapa etapa4) {
        this.etapa4 = etapa4;
    }

    public void setEtapa4(List<TabuleiroEtapa> etapas, int posicao) {
        this.etapa4 = (posicao >= etapas.size()) ? new TabuleiroEtapa() : etapas.get(posicao);
    }

    public TabuleiroEtapa getEtapa5() {
        return etapa5;
    }

    public void setEtapa5(TabuleiroEtapa etapa5) {
        this.etapa5 = etapa5;
    }

    public void setEtapa5(List<TabuleiroEtapa> etapas, int posicao) {
        this.etapa5 = (posicao >= etapas.size()) ? new TabuleiroEtapa() : etapas.get(posicao);
    }
}
