/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.ubicaciones;

import com.pasantia.utilidades.Utilidad;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "buscabicacionesBean")
@ApplicationScoped
public class BuscarUbicacionesBean {

    private List<String> opciones;
    private List<SelectItem> comboOpciones;
    private Boolean abrirBuscadorUbicaciones;
    private String opcionSeleccionada, textoBusqueda, oculto;

    public void cargarBuscadorUbicaciones() {
        abrirBuscadorUbicaciones = true;
        Utilidad.actualizarElemento("dlgbuscarUbicaciones");
        Utilidad.mensajeInfo("Buscador De Ubicaciones", "Por favor Seleccione una opcion para empezar a realizar la busqueda.");
    }
    
    public void cancelarBuscador() {
        abrirBuscadorUbicaciones = false;
        Utilidad.actualizarElemento("dlgbuscarUbicaciones");        
    }

    public void cargarOpciones() {
        opciones.add("Codigo");
        opciones.add("Descripcion");
    }

    public void cargarComboOpcionesBuscador() {
        cargarOpciones();
        comboOpciones = new ArrayList<SelectItem>();
        for (int i = 0; i < opciones.size(); i++) {
            comboOpciones.add(new SelectItem(i, opciones.get(i)));
        }
    }

    public BuscarUbicacionesBean() {
        opciones = new ArrayList<String>();
        comboOpciones = new ArrayList<SelectItem>();
        cargarComboOpcionesBuscador();
        abrirBuscadorUbicaciones = false;
        oculto = "display:none";
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public List<SelectItem> getComboOpciones() {
        return comboOpciones;
    }

    public void setComboOpciones(List<SelectItem> comboOpciones) {
        this.comboOpciones = comboOpciones;
    }

    public Boolean getAbrirBuscadorUbicaciones() {
        return abrirBuscadorUbicaciones;
    }

    public void setAbrirBuscadorUbicaciones(Boolean abrirBuscadorUbicaciones) {
        this.abrirBuscadorUbicaciones = abrirBuscadorUbicaciones;
    }

    public String getOpcionSeleccionada() {
        return opcionSeleccionada;
    }

    public void setOpcionSeleccionada(String opcionSeleccionada) {
        this.opcionSeleccionada = opcionSeleccionada;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public String getOculto() {
        return oculto;
    }

    public void setOculto(String oculto) {
        this.oculto = oculto;
    }
}
