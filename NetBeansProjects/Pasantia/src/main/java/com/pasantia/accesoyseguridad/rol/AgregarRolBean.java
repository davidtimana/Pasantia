/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.rol;

import com.pasantia.dao.CrudDAO;
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
@Named(value = "agregarRolBean")
@SessionScoped
public class AgregarRolBean implements Serializable {

    private Boolean abrirGuardarRol;
    private Rol rol;
    private String estiloError;
    
    @Inject
    RolDAO rolDAO;
    @Inject
    RolBean rolBean;
    @Inject
    CrudDAO crudDAO;
    
    public void abrirGuardadoRol(){        
        abrirGuardarRol=true;        
        Utilidad.actualizarElemento("dlgnuevorol");        
    }
    
      
    
    public void guardarRol(){        
        if (rol.getDescripcion().equals("")) {
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnombrerol"); 
            Utilidad.mensajeFatal("SICOVI", "Error al guardar el rol de los usuarios descripcion requerida");
        } else {
            estiloError="";
            if (rolDAO.insertarRol(rol)) {
                Utilidad.mensajeInfo("SICOVI", "Rol Guardado Exitosamente");
                rolBean.cargarRoles();
                Utilidad.actualizarElemento("tblroles"); 
                rol=new Rol();
                Utilidad.actualizarElemento("txtnombrerol"); 
            } else {
                
                Utilidad.mensajeFatal("SICOVI", "Error Al Guardar EL Rol");
                
            }
        }
    }
    
    public void cancelarGuardado(){
        rol=new Rol();
        abrirGuardarRol=false;        
        estiloError="";
        Utilidad.actualizarElemento("dlgnuevorol");
    }
    
     
    public AgregarRolBean() {
        
        rol = new Rol();
        estiloError="";
        abrirGuardarRol=false;
    }

    public Boolean getAbrirGuardarRol() {
        return abrirGuardarRol;
    }

    public void setAbrirGuardarRol(Boolean abrirGuardarRol) {
        this.abrirGuardarRol = abrirGuardarRol;
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
    
    
    
    
    
    
}
