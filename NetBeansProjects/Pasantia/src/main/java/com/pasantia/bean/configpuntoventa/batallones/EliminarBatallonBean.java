/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "eliminarBatallonBean")
@SessionScoped
public class EliminarBatallonBean implements Serializable {
    
    private static final long serialVersionUID = 6348374067147221403L;
    private static final Logger log = Logger.getLogger(EliminarBatallonBean.class.getName());
    
    
    private Boolean abrirComfirmar;

    @Inject
    CrudDAO<Batallon> crudDAO;
    
    public void abrirComfirmar(){
        abrirComfirmar=true;
        Utilidad.actualizarElemento("dlgcomfirbat");
    }
    
    public void eliminarBatallon(Batallon b){        
        if(crudDAO.remover(b)){
            Utilidad.mensajeInfo("SICOVI", "Batallon: "+b.getNombreBatallon()+". Eliminado Correctamente.");
        }else{
            Utilidad.mensajeError("SICOVI", "Batallon: "+b.getNombreBatallon()+". No pudo ser Eliminado.");
        }
    }
    
    public void cerrarComfirmar(){
        abrirComfirmar=false;
        Utilidad.actualizarElemento("dlgcomfirbat");
    }
    
    public EliminarBatallonBean() {
        abrirComfirmar=false;
    }

    public Boolean getAbrirComfirmar() {
        return abrirComfirmar;
    }

    public void setAbrirComfirmar(Boolean abrirComfirmar) {
        this.abrirComfirmar = abrirComfirmar;
    }
    
    
}
