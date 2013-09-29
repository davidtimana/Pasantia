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
 * @author David Timana
 */
@Named(value = "editarRolBean")
@SessionScoped
public class EditarRolBean implements Serializable {
    
    private static final long serialVersionUID = -9071477296666878183L;

   
    private Rol rol;
    private String estiloError;
    private Boolean abrirEditarRol;
    
    @Inject
    RolDAO rolDAO;
    @Inject
    RolBean rolBean;
    
    
    public void cargarEditarRols(Rol c){
        rol=c;
        abrirEditarRol = true;
        Utilidad.actualizarElemento("dlgEditarRoles");        
    }
    
    public void cancelarEdicionRol(){
        rol = new Rol();
        abrirEditarRol=false;
        estiloError="";
        Utilidad.actualizarElemento("txtdeseditrol"); 
        Utilidad.actualizarElemento("dlgEditarRoles");        
    }
    
    public void guardarEdicionRol(){        
        if (rol.getDescripcion().equals("")) {
            Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar El Rol De Los Usuarios descripcion requerida");
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtdeseditrol"); 

        } else {
            if (rolDAO.actualizarRol(rol)) {
                Utilidad.mensajeInfo("SICOVI", "Rol Actualizado Exitosamente");
                rolBean.cargarRoles();
                Utilidad.actualizarElemento("tblroles");
                abrirEditarRol = false;
                Utilidad.actualizarElemento("dlgEditarRoles");
            } else {
                Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar El Rol");
            }
            abrirEditarRol = false;
            Utilidad.actualizarElemento("dlgEditarRoles");
        }
        rol = new Rol();
        
    }
    
    
    public EditarRolBean() {
        rol = new Rol();
        abrirEditarRol = false;
        estiloError="";
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }

    public Boolean getAbrirEditarRol() {
        return abrirEditarRol;
    }

    public void setAbrirEditarRol(Boolean abrirEditarRol) {
        this.abrirEditarRol = abrirEditarRol;
    }
    
    
    
    
}
