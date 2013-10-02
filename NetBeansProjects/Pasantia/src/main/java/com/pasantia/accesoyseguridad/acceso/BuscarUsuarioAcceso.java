/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.bean.usuariossistema.BuscarUsuarioBean;
import com.pasantia.bean.usuariossistema.GestionarUsuarioBean;
import com.pasantia.bean.usuariossistema.ModeloUsuarios;
import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
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
@Named(value = "buscarUsuarioAcceso")
@SessionScoped
public class BuscarUsuarioAcceso implements Serializable {

    private static final long serialVersionUID = -8293231830187845388L;
    private Boolean buscador;
    private List<Persona> personas;
    private ModeloUsuarios modeloUsuarios;
    private Persona personaSeleccionada;
    @Inject
    CrudDAO<Persona> crudDAO;    
    @Inject
    GestionarAcceso acceso;
    
    private static Logger log = Logger.getLogger(BuscarUsuarioBean.class.getName());

    public void abrirBuscador() {
        log.info("Llegue a buscador generico Usuarios en acceso");
        buscador = true;
        personaSeleccionada = new Persona();
        Utilidad.actualizarElemento("dlgbuscadorusuariosacceso");
        cargarBuscador();
        Utilidad.actualizarElemento("tblusuariosacceso");
    }

    public void cerrarBuscador() {
        buscador = false;
        personaSeleccionada = new Persona();
        Utilidad.actualizarElemento("dlgbuscadorusuariosacceso");
    }

   

    public void cargarSeleccinadoAcceso() {
        log.log(Level.INFO, "La persona seleccionada es la siguiente-->{0}", personaSeleccionada.getPnombre());
        buscador = false;
        Utilidad.actualizarElemento("dlgbuscadorusuariosacceso");
        acceso.cargarPersona(personaSeleccionada);
        Utilidad.actualizarElemento("frmaccesousur");
        log.log(Level.INFO, "*******************Cargando objeto buscando-->{0}", personaSeleccionada.getPnombre());
    }

    public void cargarBuscador() {
        personas = crudDAO.buscarTodos(Persona.class);
        modeloUsuarios = new ModeloUsuarios(personas);
    }

    public void cargarImagen() {
    }

    public Integer totalUsuarios() {
        return personas.size();
    }

    @PostConstruct
    public void Init() {
        cargarBuscador();
        totalUsuarios();

    }

    public BuscarUsuarioAcceso() {
        buscador = false;
        personaSeleccionada = new Persona();
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
