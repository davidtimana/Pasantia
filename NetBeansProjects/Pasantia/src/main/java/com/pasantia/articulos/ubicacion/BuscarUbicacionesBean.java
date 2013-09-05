/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.ubicacion;

import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;



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
    
    @Inject
    UbicacionBean ubicacionesBean;

    public void buscarUbicaciones(){        
        ubicacionesBean.buscarUbicaciones(idBuscar, nombreBuscar);        
    }
    
    public void cargarBuscadorUbicaciones() {
        abrirBuscadorUbicacion=true;
        Utilidad.actualizarElemento("dlgbuscarUbicaciones");
        Utilidad.mensajeInfo("SICOVI", "Buscador de Ubicaciones. Por favor escriba los parametros con los cuales desea buscar.");
    }
    
    public void cancelarBuscador() {
        abrirBuscadorUbicacion = false;
        idBuscar=null;
        nombreBuscar="";
        Utilidad.actualizarElemento("dlgbuscarUbicaciones");
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
