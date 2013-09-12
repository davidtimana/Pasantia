/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.rol;

import com.pasantia.articulos.categoria.CategoriaBean;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "buscarRolBean")
@SessionScoped
public class BuscarRolBean implements Serializable {

    private Boolean abrirBuscadorRol;
    private Integer idRol;
    private String nombreRol;
    
    @Inject
    RolBean rolBean;
    
    
    public void cargarBuscadorRoles() {
        abrirBuscadorRol=true;
        Utilidad.actualizarElemento("dlgbuscarroles");
        Utilidad.mensajeInfo("SICOVI", "Buscador de Roles. Por favor escriba los parametros con los cuales desea buscar.");
    }
    
    public void cancelarBuscadorRoles(){
        idRol=null;
        nombreRol="";
        abrirBuscadorRol=false;
        Utilidad.actualizarElemento("dlgbuscarroles");
    }
    
    public void buscarRoles(){
        rolBean.buscarRoles(idRol, nombreRol);
    }
    
    public BuscarRolBean() {
        abrirBuscadorRol=false;        
    }

    public Boolean getAbrirBuscadorRol() {
        return abrirBuscadorRol;
    }

    public void setAbrirBuscadorRol(Boolean abrirBuscadorRol) {
        this.abrirBuscadorRol = abrirBuscadorRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    
}
