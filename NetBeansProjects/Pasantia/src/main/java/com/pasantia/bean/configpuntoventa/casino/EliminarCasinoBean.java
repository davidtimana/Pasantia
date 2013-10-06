/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "eliminarCasinoBean")
@SessionScoped
public class EliminarCasinoBean implements Serializable {
    
    private static final long serialVersionUID = -8482382600869484228L;
    private static final Logger log = Logger.getLogger(EliminarCasinoBean.class.getName());
    
    private Boolean abrirComfirmar;
    @Inject
    CrudDAO<Casino> crudDAO;
    
    public void eliminarCasino(Casino c){
        if(crudDAO.remover(c)){
            Utilidad.mensajeInfo("SICOVI", "Casino: "+c.getNombre()+". Eliminado Correctamente.");
        }else{
            Utilidad.mensajeError("SICOVI", "Casino: "+c.getNombre()+". No pudo ser Eliminado.");
        }
    }
    
    public void abrirComfirmar(){
        abrirComfirmar=true;
        Utilidad.actualizarElemento("dlgcomcas");
    }
    
     public void cerrarComfirmar(){
        abrirComfirmar=false;
        Utilidad.actualizarElemento("dlgcomcas");
    }
    
    public EliminarCasinoBean() {
        abrirComfirmar=false;
    }

    public Boolean getAbrirComfirmar() {
        return abrirComfirmar;
    }

    public void setAbrirComfirmar(Boolean abrirComfirmar) {
        this.abrirComfirmar = abrirComfirmar;
    }
    
    
}
