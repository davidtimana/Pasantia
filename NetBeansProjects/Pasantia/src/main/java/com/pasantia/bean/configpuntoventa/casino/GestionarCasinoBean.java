/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.Casino;
import com.pasantia.entidades.Persona;
import com.pasantia.excepciones.CadenaVaciaException;
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
@Named(value = "gestionarCasinoBean")
@SessionScoped
public class GestionarCasinoBean implements Serializable {

    private static final long serialVersionUID = 7209127536625715045L;
    private static final Logger log = Logger.getLogger(GestionarCasinoBean.class.getName());
    private Casino casino;
    private Batallon batallon;
    private Persona persona;
    private List<Integer> listaControlBotones;
    private List<Boolean> listaControlReadonly;
    private Integer accordion;
    private Boolean estaEditando;
    
    @Inject
    ValidarCasinoBean validarCasinoBean;
    @Inject
    GuardarCasinoBean guardarCasinoBean;

    public void nuevo() {
        estaEditando=false;
        accordion=0;
        listaControlReadonly.set(0, false);
        validarCasinoBean.limpiarEstilos();
        deshabilitarBotonesEditaroNuevo();
        casino = new Casino();
        Utilidad.actualizarElemento("frmcasinos");
        
    }

    public void editar() {
        estaEditando=true;
        listaControlReadonly.set(0, false);
        validarCasinoBean.limpiarEstilos();
        deshabilitarBotonesEditaroNuevo();
    }

    public void cancelar() {
        accordion=0;
        listaControlReadonly.set(0, true);
        validarCasinoBean.limpiarEstilos();
        deshabilitarBotonesCancelar();
        casino = new Casino();
        Utilidad.actualizarElemento("frmcasinos");

    }

    public void guardar() {
        try {
            validarCasinoBean.validarCasino(casino);
            guardarCasinoBean.guardar(casino, estaEditando);
            cancelar();
        } catch (CadenaVaciaException ex) {
            log.info(ex.getMessage());
            Utilidad.mensajeError("SICOVI", "Sección Datos Basicos: " + ex.getMessage());
        }
        
    }

    public void iniciarBotones() {
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
    }

    public void deshabilitarBotonesEditaroNuevo() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(0);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        Utilidad.actualizarElemento("grupobotonescasino");
    }

    public void deshabilitarBotonesCancelar() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        Utilidad.actualizarElemento("grupobotonescasino");
    }

    public void iniciarReadonly() {
        listaControlReadonly.add(true);
        listaControlReadonly.add(false);        
    }

    @PostConstruct
    public void Init() {
        iniciarBotones();
        iniciarReadonly();
        iniciarBotones();
    }

    public GestionarCasinoBean() {
        casino = new Casino();
        batallon = new Batallon();
        persona = new Persona();
        listaControlBotones = new ArrayList<Integer>();
        listaControlReadonly=new ArrayList<Boolean>();
        accordion=0;
        estaEditando=false;
    }

    public Casino getCasino() {
        return casino;
    }

    public void setCasino(Casino casino) {
        this.casino = casino;
    }

    public Batallon getBatallon() {
        return batallon;
    }

    public void setBatallon(Batallon batallon) {
        this.batallon = batallon;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Integer> getListaControlBotones() {
        return listaControlBotones;
    }

    public void setListaControlBotones(List<Integer> listaControlBotones) {
        this.listaControlBotones = listaControlBotones;
    }

    public List<Boolean> getListaControlReadonly() {
        return listaControlReadonly;
    }

    public void setListaControlReadonly(List<Boolean> listaControlReadonly) {
        this.listaControlReadonly = listaControlReadonly;
    }

    public Integer getAccordion() {
        return accordion;
    }

    public void setAccordion(Integer accordion) {
        this.accordion = accordion;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }
    
    
    
    
}
