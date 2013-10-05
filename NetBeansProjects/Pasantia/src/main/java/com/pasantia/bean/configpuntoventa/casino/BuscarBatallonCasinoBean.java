/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.bean.configpuntoventa.batallones.ModeloBatallon;
import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Batallon;
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
 * @author david
 */
@Named(value = "buscarBatallonCasinoBean")
@SessionScoped
public class BuscarBatallonCasinoBean implements Serializable {
    
    private static final long serialVersionUID = 1250003537982055763L;
    private static final Logger log = Logger.getLogger(BuscarBatallonCasinoBean.class.getName());
    
    private List<Batallon> batallones;
    private ModeloBatallon modeloBatallon;
    private Batallon batallonSelect;
    private Boolean abrir;
    @Inject
    CrudDAO<Batallon> crudDAO;
    @Inject
    GestionarCasinoBean casinoBean;
    
    public void abrirBuscador(){
        abrir=true;
        batallonSelect=new Batallon();
        cargarBatallones();
        Utilidad.actualizarElemento("tblbusbatcas");
        Utilidad.actualizarElemento("dlgbuscarbatcas");        
    }
    
     public void cerrarBuscador(){
        abrir=false;
        batallonSelect=new Batallon();
        Utilidad.actualizarElemento("dlgbuscarbatcas");        
    }
     
     public void cargarComandanteSeleccionado(){
        log.log(Level.INFO, "el comandante seleccionado es el siguiente-->{0}", batallonSelect.getNombreBatallon());
        abrir=false;
        Utilidad.actualizarElemento("dlgbuscarbatcas");   
        casinoBean.cargarBatallon(batallonSelect);
        Utilidad.actualizarElemento("pnlbatalloncas");
    }

    public void cargarBatallones(){
        batallones=crudDAO.buscarTodos(Batallon.class);
        modeloBatallon=new ModeloBatallon(batallones);
    }
    
    public Integer totalBatallones(){
        return batallones.size();
    }
    
    @PostConstruct
    public void Init(){
        cargarBatallones();
        totalBatallones();
    }
    
    public BuscarBatallonCasinoBean() {
        abrir=false;
        batallonSelect=new Batallon();
        batallones=new ArrayList<Batallon>();
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

    public Batallon getBatallonSelect() {
        return batallonSelect;
    }

    public void setBatallonSelect(Batallon batallonSelect) {
        this.batallonSelect = batallonSelect;
    }

    public Boolean getAbrir() {
        return abrir;
    }

    public void setAbrir(Boolean abrir) {
        this.abrir = abrir;
    }
    
    
}
