/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
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
@Named(value = "buscarCasinoBean")
@SessionScoped
public class BuscarCasinoBean implements Serializable {
    
    private static final long serialVersionUID = -2694411595842645180L;
    private static final Logger log = Logger.getLogger(BuscarCasinoBean.class.getName());
    
    private List<Casino> casinos;
    private ModeloCasino modeloCasino;
    private Casino casinoSelect;
    private Boolean abrir;
    
    @Inject
    CrudDAO<Casino> crudDAO;
    @Inject
    GestionarCasinoBean casinoBean;
    
    public void abrirBuscador(){
        abrir=true;
        casinoSelect = new Casino();
        cargarCasinos();
        Utilidad.actualizarElemento("tblcasinos");
        Utilidad.actualizarElemento("dlgbuscarcasino");
    }
    
    public void cerrarBuscador(){
        abrir=false;
        casinoSelect = new Casino();
        Utilidad.actualizarElemento("dlgbuscarcasino");
    }
    
    public void cargarCasinoSeleccionado(){
        log.log(Level.INFO, "el casino seleccionado es el siguiente-->{0}", casinoSelect.getNombre());
        abrir=false;
        Utilidad.actualizarElemento("dlgbuscarcasino");
        casinoBean.cargarObjetoCasino(casinoSelect);
        Utilidad.actualizarElemento("frmcasinos");
        
    }
    
    public void cargarCasinos(){
        casinos=crudDAO.buscarTodos(Casino.class);
        modeloCasino=new ModeloCasino(casinos);
    }
    
    public Integer totalCasinos(){
        return casinos.size();
    }
    
    @PostConstruct
    public void Init(){
        cargarCasinos();
        totalCasinos();
        
    }
    
    public BuscarCasinoBean() {
        casinoSelect=new Casino();
        casinos=new ArrayList<Casino>();
        abrir=false;
    }

    public List<Casino> getCasinos() {
        return casinos;
    }

    public void setCasinos(List<Casino> casinos) {
        this.casinos = casinos;
    }

    public ModeloCasino getModeloCasino() {
        return modeloCasino;
    }

    public void setModeloCasino(ModeloCasino modeloCasino) {
        this.modeloCasino = modeloCasino;
    }

    public Casino getCasinoSelect() {
        return casinoSelect;
    }

    public void setCasinoSelect(Casino casinoSelect) {
        this.casinoSelect = casinoSelect;
    }

    public Boolean getAbrir() {
        return abrir;
    }

    public void setAbrir(Boolean abrir) {
        this.abrir = abrir;
    }
    
    
}
