/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Usuario;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarAccesosBean")
@SessionScoped
public class BuscarAccesosBean implements Serializable {
    
    private static final long serialVersionUID = 622822818523873426L;
    private static Logger log = Logger.getLogger(BuscarAccesosBean.class.getName());

    private Boolean buscador;
    private List<Usuario> usuarios;
    private ModeloAccesos modeloAccesos;
    private Usuario usuarioSeleccionado;    
    
    @Inject
    CrudDAO<Usuario> crudDAOUsur;
    @Inject
    GestionarAcceso gestionarAcceso;
    
    public void cargarSeleccinado(){
        log.log(Level.INFO, "el usuario seleccionado es la siguiente-->{0}", usuarioSeleccionado.getNomusuario());
        buscador=false;
        Utilidad.actualizarElemento("dlgbuscadorusuariosaccesologin");        
        gestionarAcceso.cargarObjetoUsuario(usuarioSeleccionado);
        Utilidad.actualizarElemento("frmaccesousur");
        log.log(Level.INFO, "*******************Cargando objeto buscado-->{0}", usuarioSeleccionado.getNomusuario());
    }
    
    public void abrirBuscador(){
        log.info("Llegue a buscador generico Usuarios");
        buscador=true;        
        usuarioSeleccionado = new Usuario();
        Utilidad.actualizarElemento("dlgbuscadorusuariosaccesologin");
        cargarBuscador();
        Utilidad.actualizarElemento("tblusuarioaccesologin");
    }
    
    public void cerrarBuscador(){
        buscador=false;
        usuarioSeleccionado = new Usuario();
        Utilidad.actualizarElemento("dlgbuscadorusuariosaccesologin");
    }
    
    public void cargarBuscador(){
        usuarios=crudDAOUsur.buscarTodos(Usuario.class);
        modeloAccesos=new ModeloAccesos(usuarios);
    }
    
    public Integer totalAccesos(){
        return usuarios.size();
    }

    
    @PostConstruct
    public void Init(){
        cargarBuscador();
        totalAccesos();        
    }
    
    public BuscarAccesosBean() {
        buscador=false;
        usuarioSeleccionado = new Usuario();
        
    }

    public Boolean getBuscador() {
        return buscador;
    }

    public void setBuscador(Boolean buscador) {
        this.buscador = buscador;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ModeloAccesos getModeloAccesos() {
        return modeloAccesos;
    }

    public void setModeloAccesos(ModeloAccesos modeloAccesos) {
        this.modeloAccesos = modeloAccesos;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

 

   
    
    
    
    
}
