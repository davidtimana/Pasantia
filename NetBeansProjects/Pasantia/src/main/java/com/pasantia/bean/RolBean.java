/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.RolDAO;
import com.pasantia.dao.impl.RolDAOImpl;
import com.pasantia.entidades.Rol;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.dialog.Dialog;

/**
 *
 * @author david
 */
@ManagedBean(name="rolBean")
@SessionScoped
public class RolBean implements Serializable{

    private Rol rol;
    private List<Rol> roles;
    private Dialog dlgnuevo,dlgeditar;
    
    public RolBean() {
        rol=new Rol();
        dlgeditar=new Dialog();
        dlgnuevo=new Dialog();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRoles() {
        RolDAO rolDAO = new RolDAOImpl();
        roles=rolDAO.buscartodosRoles();
        return roles;
    }
    public void prepararGuardadoDelRol(){
        rol=new Rol();        
    }

    public Dialog getDlgeditar() {
        return dlgeditar;
    }

    public void setDlgeditar(Dialog dlgeditar) {
        this.dlgeditar = dlgeditar;
    }

    public Dialog getDlgnuevo() {
        return dlgnuevo;
    }

    public void setDlgnuevo(Dialog dlgnuevo) {
        this.dlgnuevo = dlgnuevo;
    }
    
    public void guardarNuevoRol(){
        System.out.println("Guardado del nuevo rol: "+rol.getDescripcion());
        RolDAO rolDAO = new RolDAOImpl();
        rolDAO.insertarRol(rol);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Rol Guardado Exitosamente."));        
        rol=new Rol();        
    }
    
    public void prepararActualizadoDelRol(Integer Id){
        dlgeditar.setVisible(Boolean.TRUE);
        RolDAO rolDAO=new RolDAOImpl();
        rol=rolDAO.buscarRolPorId(Id);        
    }
    public void actualizarRol(){
        RolDAO rolDAO = new RolDAOImpl();
        rolDAO.actualizarRol(rol);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Rol Actualizado Exitosamente."));        
        rol=new Rol();                
    }
     public void eliminarRol(){
        RolDAO rolDAO = new RolDAOImpl();
        rolDAO.eliminarRol(rol);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Rol Eliminado Exitosamente."));        
        rol=new Rol();                
    }
     public void prepararEliminarDelRol(Integer Id){
        RolDAO rolDAO=new RolDAOImpl();
        rol=rolDAO.buscarRolPorId(Id);        
    }
     public int totalRoles(){
        int total=roles.size();
        return total;
    }
    
              
             
    
}
