/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.dao.CrudDAO;
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
@Named(value = "buscarComandanteCasinoBean")
@SessionScoped
public class BuscarComandanteCasinoBean implements Serializable {
    
    private static final long serialVersionUID = 2867410063400349218L;
    private static final Logger log = Logger.getLogger(BuscarComandanteCasinoBean.class.getName());
    
    private List<Persona> personas;
    private ModeloComandanteCasino comandanteCasino;
    private Persona comandante;
    private Boolean abrir;
    @Inject
    PersonaDAO personaDAO;
    @Inject
    GestionarCasinoBean casinoBean;
    
    public void abrirBuscador(){
        abrir=true;
        comandante=new Persona();
        cargarComandanteCasino();
        Utilidad.actualizarElemento("tblcomancasino");
        Utilidad.actualizarElemento("dlgcomancasino");        
    }
    
    public void cerrarBuscador(){
        abrir=false;
        comandante=new Persona();
        Utilidad.actualizarElemento("dlgcomancasino");        
    }
    
    public void cargarComandanteSeleccionado(){
        log.log(Level.INFO, "el comandante seleccionado es el siguiente-->{0}", comandante.getPnombre());
        abrir=false;
        Utilidad.actualizarElemento("dlgcomancasino");   
        casinoBean.cargarComandante(comandante);
        Utilidad.actualizarElemento("comencontrado");
    }
    
    public Integer totalComandantesCasino(){
        return personas.size();
    }
    
    
    public void cargarComandanteCasino(){
        personas=personaDAO.buscarComandanteCasinoSinAsignar();
        comandanteCasino=new ModeloComandanteCasino(personas);
    }
    
    @PostConstruct
    public void Init(){
        cargarComandanteCasino();
        totalComandantesCasino();
    }
    
    public BuscarComandanteCasinoBean() {
        personas=new ArrayList<Persona>();
        comandante=new Persona();
        abrir=false;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public ModeloComandanteCasino getComandanteCasino() {
        return comandanteCasino;
    }

    public void setComandanteCasino(ModeloComandanteCasino comandanteCasino) {
        this.comandanteCasino = comandanteCasino;
    }

    public Persona getComandante() {
        return comandante;
    }

    public void setComandante(Persona comandante) {
        this.comandante = comandante;
    }

    public Boolean getAbrir() {
        return abrir;
    }

    public void setAbrir(Boolean abrir) {
        this.abrir = abrir;
    }

    
    
    
}
