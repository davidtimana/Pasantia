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
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "rolBean")
@SessionScoped
public class RolBean implements Serializable {

    /**
     * Creates a new instance of RolBean
     */
     private List<Rol> roles; 
    
    @Inject
    RolDAO rolDAO;
    
    public void buscarRoles(Integer idRol,String nombreRol){
        if(idRol==null && nombreRol.equals("")){
            roles=rolDAO.buscartodosRoles();
        }else{
            roles=rolDAO.buscartodasRolesxNombrexId(idRol, nombreRol);
        }
        if (roles.isEmpty()) {
            if(idRol==null){
                idRol=0;
            }
            Utilidad.mensajePeligro("SICOVI.", "La busqueda de Roles con codigo: " + idRol + " y Nombre: " + nombreRol + ". No tuvo Resultados");

        } else {
            Utilidad.mensajeInfo("SICOVI.", "La busqueda devolvio: " + roles.size() + " Resultados.");
        }
        Utilidad.actualizarElemento("tblroles");
        
    }
    
    public void cargarRoles(){
        roles=rolDAO.buscartodosRoles();
    }
    
    public Integer totalRoles(){
        return roles.size();
    }
    
    public RolBean() {
        roles = new ArrayList<Rol>();
                
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
    
}
