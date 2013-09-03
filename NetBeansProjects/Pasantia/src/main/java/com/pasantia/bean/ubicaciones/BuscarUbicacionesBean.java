/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.ubicaciones;

import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;



/**
 *
 * @author david
 */
@Named(value = "buscabicacionesBean")
@SessionScoped
public class BuscarUbicacionesBean implements Serializable{

    private Boolean abrirBuscadorUbicacion;
    private String nombreBuscar;
    private Integer idBuscar;

    public void cargarBuscadorUbicaciones() {
        abrirBuscadorUbicacion=true;
        Utilidad.actualizarElemento("dlgbuscarUbicaciones");
    }

    public void cancelarBuscador() {
        abrirBuscadorUbicacion = false;
    }

    public BuscarUbicacionesBean() {
        abrirBuscadorUbicacion = false;
    }

    public Boolean getAbrirBuscadorUbicacion() {
        return abrirBuscadorUbicacion;
    }

    public void setAbrirBuscadorUbicacion(Boolean abrirBuscadorUbicacion) {
        this.abrirBuscadorUbicacion = abrirBuscadorUbicacion;
    }

    public String getNombreBuscar() {
        return nombreBuscar;
    }

    public void setNombreBuscar(String nombreBuscar) {
        this.nombreBuscar = nombreBuscar;
    }

    public Integer getIdBuscar() {
        return idBuscar;
    }

    public void setIdBuscar(Integer idBuscar) {
        this.idBuscar = idBuscar;
    }
}
