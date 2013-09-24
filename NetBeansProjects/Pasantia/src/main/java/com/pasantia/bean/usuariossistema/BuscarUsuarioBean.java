/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarUsuarioBean")
@SessionScoped
public class BuscarUsuarioBean implements Serializable {

    private Boolean buscador;
    private List<Persona> personas;    
    private ModeloUsuarios modeloUsuarios;
    private Persona personaSeleccionada;
    
    @Inject
    CrudDAO<Persona> crudDAO;
    
    private static Logger log = Logger.getLogger(BuscarUsuarioBean.class.getName());
    
    
    public void abrirBuscador(){
        buscador=true;        
        personaSeleccionada=new Persona();
        Utilidad.actualizarElemento("dlgbuscadorusuarios");
        Utilidad.actualizarElemento("tblusuarios");
    }
    
    public void cerrarBuscador(){
        buscador=false;
        personaSeleccionada=new Persona();
        Utilidad.actualizarElemento("dlgbuscadorusuarios");
    }
    
    public void cargarSeleccinado(){
        log.log(Level.INFO, "La persona seleccionada es la siguiente-->{0}", personaSeleccionada.getPnombre());
        buscador=false;
        Utilidad.actualizarElemento("dlgbuscadorusuarios");
    }
    
    public void cargarBuscador(){        
        personas=crudDAO.buscarTodos(Persona.class);
        modeloUsuarios=new ModeloUsuarios(personas);        
    }
    
    public Integer totalUsuarios(){
        return personas.size();
    }
    
    @PostConstruct
    public void Init(){
        cargarBuscador();
        totalUsuarios();
        
    }
    
    public BuscarUsuarioBean() {
        buscador=false;
        personaSeleccionada=new Persona();
    }

    public Boolean getBuscador() {
        return buscador;
    }

    public void setBuscador(Boolean buscador) {
        this.buscador = buscador;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public ModeloUsuarios getModeloUsuarios() {
        return modeloUsuarios;
    }

    public void setModeloUsuarios(ModeloUsuarios modeloUsuarios) {
        this.modeloUsuarios = modeloUsuarios;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }
    
    
    
    
    
    
    
    
}
