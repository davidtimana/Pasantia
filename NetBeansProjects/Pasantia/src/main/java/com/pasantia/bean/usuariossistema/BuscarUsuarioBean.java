/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarUsuarioBean")
@SessionScoped
public class BuscarUsuarioBean implements Serializable {
    
    private static final long serialVersionUID = -6597846946216270432L;

    private Boolean buscador;
    private List<Persona> personas;    
    private ModeloUsuarios modeloUsuarios;
    private Persona personaSeleccionada;
    private StreamedContent foto;
    
    @Inject
    CrudDAO<Persona> crudDAO;
    @Inject
    GestionarUsuarioBean gestionarUsuarioBean;
    
    private static Logger log = Logger.getLogger(BuscarUsuarioBean.class.getName());
    
    
    public void abrirBuscador(){
        log.info("Llegue a buscador generico Usuarios");
        buscador=true;        
        personaSeleccionada=new Persona();
        Utilidad.actualizarElemento("dlgbuscadorusuarios");
        cargarBuscador();
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
        gestionarUsuarioBean.cargarObjetoPersona(personaSeleccionada);
        Utilidad.actualizarElemento("gestionarusuarios");
        log.log(Level.INFO, "*******************Cargando objeto buscando-->{0}", personaSeleccionada.getPnombre());
    }
    
        
    public void cargarBuscador(){        
        personas=crudDAO.buscarTodos(Persona.class);
        modeloUsuarios=new ModeloUsuarios(personas);        
    }
    
    public void cargarImagen(){
        
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

    public StreamedContent getFoto() {
        return foto;
    }

    public void setFoto(StreamedContent foto) {
        this.foto = foto;
    }
    
    
    
    
    
    
    
    
    
    
}
