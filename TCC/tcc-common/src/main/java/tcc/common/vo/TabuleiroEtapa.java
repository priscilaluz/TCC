/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.common.vo;

/**
 *
 * @author ADM
 */
public class TabuleiroEtapa {
    private Long idEtapa;
    private String imagemOff;
    private String imagemOn;
    private String imagemDesabilitado;
    private boolean desbloquada;

    public TabuleiroEtapa() {}

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getImagemOff() {
        return imagemOff;
    }

    public void setImagemOff(String imagemOff) {
        this.imagemOff = imagemOff;
    }

    public String getImagemOn() {
        return imagemOn;
    }

    public void setImagemOn(String imagemOn) {
        this.imagemOn = imagemOn;
    }

    public String getImagemDesabilitado() {
        return imagemDesabilitado;
    }

    public void setImagemDesabilitado(String imagemDesabilitado) {
        this.imagemDesabilitado = imagemDesabilitado;
    }

    public boolean isDesbloquada() {
        return desbloquada;
    }

    public void setDesbloquada(boolean desbloquada) {
        this.desbloquada = desbloquada;
    }

    public String getImagem() {
        if (desbloquada){
            return imagemOff;
        }
        return imagemDesabilitado;
    }

    public void setImagem(String imagem) {}
}
