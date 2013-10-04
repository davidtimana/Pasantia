/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarBatallonesBean")
@SessionScoped
public class BuscarBatallonesBean implements Serializable {
    
    private static final Logger log = Logger.getLogger(BuscarBatallonesBean.class.getName());    
    private static final long serialVersionUID = -9113906109286281398L;
    private List<Batallon> batallones;
    private ModeloBatallon modeloBatallon;
    private Batallon batallonSeleccionado;
    private Boolean abrirBatallon;
    @Inject
    CrudDAO<Batallon> crudDAO;

    public void abrirBuscador() {
        abrirBatallon = true;
        batallonSeleccionado=new Batallon();
        cargarBatallones();
        Utilidad.actualizarElemento("tblcomandantes");
        Utilidad.actualizarElemento("dlgbuscarBatallon");
    }
    
    public void cerrarBuscador(){
        abrirBatallon=false;
        batallonSeleccionado=new Batallon();
        Utilidad.actualizarElemento("dlgbuscarBatallon");
    }
    
    public void cargarBatallonSeleccionado(){
        log.log(Level.INFO, "El elemento seleccionado es el siguiente-->{0}", batallonSeleccionado.getNombreBatallon());
        abrirBatallon=false;
        Utilidad.actualizarElemento("dlgbuscarBatallon");
    }

    public void cargarBatallones() {
        batallones = crudDAO.buscarTodos(Batallon.class);
        modeloBatallon = new ModeloBatallon(batallones);
        
    }
    
    public Integer totalBatallones(){
        return batallones.size();
    }

    @PostConstruct
    public void Init() {
        cargarBatallones();
        totalBatallones();
    }

    public BuscarBatallonesBean() {

        batallones = new ArrayList<Batallon>();
        batallonSeleccionado = new Batallon();
        abrirBatallon = false;

    }

    public List<Batallon> getBatallones() {
        return batallones;
    }

    public void setBatallones(List<Batallon> batallones) {
        this.batallones = batallones;
    }

    public ModeloBatallon getModeloBatallon() {
        return modeloBatallon;
    }

    public void setModeloBatallon(ModeloBatallon modeloBatallon) {
        this.modeloBatallon = modeloBatallon;
    }

    public Batallon getBatallonSeleccionado() {
        return batallonSeleccionado;
    }

    public void setBatallonSeleccionado(Batallon batallonSeleccionado) {
        this.batallonSeleccionado = batallonSeleccionado;
    }

    public Boolean getAbrirBatallon() {
        return abrirBatallon;
    }

    public void setAbrirBatallon(Boolean abrirBatallon) {
        this.abrirBatallon = abrirBatallon;
    }
}
