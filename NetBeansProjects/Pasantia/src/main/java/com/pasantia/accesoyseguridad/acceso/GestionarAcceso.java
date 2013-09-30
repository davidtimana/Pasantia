/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.dao.RolDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.entidades.Usuario;
import com.pasantia.utilidades.CombosComunes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "gestionarAcceso")
@SessionScoped
public class GestionarAcceso extends CombosComunes implements Serializable {
    
    private static final long serialVersionUID = 4626973270409152048L;
    private static Logger log = Logger.getLogger(GestionarAcceso.class.getName());

    private Rol rol;  
    private Usuario usuario;
    private String verificarPass;
    
    @Inject
    RolDAO rolDAO;
    
    public void cargarRol(){
     if(rol.getIdRol()!=null){
         rol=rolDAO.buscarRolPorId(rol.getIdRol());
         log.log(Level.INFO, "el rol cargado es-->{0}", rol.getDescripcion());
     }
    }
    
    @PostConstruct
    public void Init(){
        cargarComboRoles();
    }
    
    public GestionarAcceso() {
        rol = new Rol();
        usuario=new Usuario();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }  

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getVerificarPass() {
        return verificarPass;
    }

    public void setVerificarPass(String verificarPass) {
        this.verificarPass = verificarPass;
    }
    
    
    
    
    
    
}
