/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.bean.usuariossistema.ModeloUsuarios;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarComandanteBatallonBean")
@SessionScoped
public class BuscarComandanteBatallonBean implements Serializable {
    
    private static final long serialVersionUID = 3861301746105492641L;
    private static final Logger log = Logger.getLogger(BuscarComandanteBatallonBean.class.getName());
    
    private List<Persona> personas;
    private ModeloUsuarios modeloUsuarios;
    private Boolean abrirBuscador;
    private Persona comandanteSeleccionado;
    @Inject
    PersonaDAO personaDAO;
    @Inject
    BatallonControllerBean batallonControllerBean;

    public void abrirBuscador(){
        abrirBuscador=true;
        comandanteSeleccionado=new Persona();
        cargarComandante();
        Utilidad.actualizarElemento("tblcomandantes1");
        Utilidad.actualizarElemento("dlgcomandante");
        
    }
    
    public void cerrarBuscador(){
        abrirBuscador=false;
        comandanteSeleccionado=new Persona();
        Utilidad.actualizarElemento("dlgcomandante");
    }
    
    public void cargarComandante(){
        personas=personaDAO.buscarComandanteSinAsignar();
        modeloUsuarios = new ModeloUsuarios(personas);
        
    }
    
    public void cargarComandanteSeleccionado(){
        log.log(Level.INFO, "el comandante seleccionado es el siguiente-->{0}", comandanteSeleccionado.getPnombre());
        abrirBuscador=false;
        Utilidad.actualizarElemento("dlgcomandante");
        batallonControllerBean.cargarPersona(comandanteSeleccionado);
        Utilidad.actualizarElemento("comencontrada");
    }
    
    public Integer totalComandantes(){
        return personas.size();
    }
    
    @PostConstruct
    public void Init(){
        cargarComandante();
        totalComandantes();
    }
    
    
    public BuscarComandanteBatallonBean() {
        personas=new ArrayList<Persona>();
        abrirBuscador=false;
        comandanteSeleccionado=new Persona();
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

    public Boolean getAbrirBuscador() {
        return abrirBuscador;
    }

    public void setAbrirBuscador(Boolean abrirBuscador) {
        this.abrirBuscador = abrirBuscador;
    }

    public Persona getComandanteSeleccionado() {
        return comandanteSeleccionado;
    }

    public void setComandanteSeleccionado(Persona comandanteSeleccionado) {
        this.comandanteSeleccionado = comandanteSeleccionado;
    }
    
    
    
    
}
