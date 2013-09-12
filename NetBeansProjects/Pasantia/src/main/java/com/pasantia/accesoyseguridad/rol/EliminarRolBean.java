/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.rol;

import com.pasantia.dao.RolDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "eliminarRolBean")
@SessionScoped
public class EliminarRolBean implements Serializable {
    
    private Boolean abrirEliminarRol;
    private Rol rol;
    
    @Inject
    RolBean rolBean;
    
    @Inject
    RolDAO rolDAO;

   public void cargarEliminadoRol(Rol c){
        rol=c;
        abrirEliminarRol = true;
        Utilidad.actualizarElemento("dlgeliminarrol");
    }
    
    public void cancelarEliminarRol(){
        abrirEliminarRol = false;
        Utilidad.actualizarElemento("dlgeliminarrol");
    }
    
    public void eliminarRol(){
        if(rolDAO.eliminarRol(rol)){
            Utilidad.mensajeInfo("SICOVI", "Rol Eliminado Exitosamente");
            rolBean.cargarRoles();
            Utilidad.actualizarElemento("tblroles");
        }else{
            Utilidad.mensajeFatal("SICOVI", "Error al eliminar el Rol seleccionado");
        }     
        cancelarEliminarRol();
        
    }
    
    
    public EliminarRolBean() {
        rol = new Rol();
        abrirEliminarRol=false;
    }

    public Boolean getAbrirEliminarRol() {
        return abrirEliminarRol;
    }

    public void setAbrirEliminarRol(Boolean abrirEliminarRol) {
        this.abrirEliminarRol = abrirEliminarRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    
    
}
