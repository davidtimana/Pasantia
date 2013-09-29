/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author David Timana
 */
@Named(value = "guardarSinFotoBean")
@SessionScoped
public class GuardarSinFotoBean implements Serializable {
    
    private static final long serialVersionUID = 6456698470907868098L;

    
    private Boolean abrirSinFoto;
    
    /*
     * Metodo que se encarga de abrir el dialogo de comfirmacion
     * si el usuario desea guardar sin foto;
     */
    public void abrirComfirmar(){
        abrirSinFoto=true;
        Utilidad.actualizarElemento("dlgsinfoto");
    }
    
    public void cerrarComfirmar(){
        abrirSinFoto=false;
        Utilidad.actualizarElemento("dlgsinfoto");
    }
    
    public GuardarSinFotoBean() {
        abrirSinFoto=false;
    }

    public Boolean getAbrirSinFoto() {
        return abrirSinFoto;
    }

    public void setAbrirSinFoto(Boolean abrirSinFoto) {
        this.abrirSinFoto = abrirSinFoto;
    }
    
    
}
