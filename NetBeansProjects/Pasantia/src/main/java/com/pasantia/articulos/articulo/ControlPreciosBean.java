/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.entidades.PrecioCompra;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author David Timana
 */
@Named(value = "controlPreciosBean")
@SessionScoped
public class ControlPreciosBean implements Serializable {

    private static final long serialVersionUID = -353555958411021048L;
    private List<PrecioCompra> precios;
    private ModeloPrecioCompra modeloPrecioCompra;
    private PrecioCompra precioSeleccionado;
    private PrecioCompra precioCompra;
    
    public Integer totalPrecios(){
        return precios.size();
    }
    
    public void agregar(){
        Date fechaActual = new Date();
        precioCompra.setFecha(fechaActual);
        precios.add(precioCompra);
        modeloPrecioCompra = new ModeloPrecioCompra(precios);
        Utilidad.actualizarElemento("tblprecios");
    }

    @PostConstruct
    public void Init() {
        totalPrecios();
        
    }

    public ControlPreciosBean() {
        precios = new ArrayList<PrecioCompra>();
        modeloPrecioCompra = new ModeloPrecioCompra(precios);
        precioSeleccionado=new PrecioCompra();
        precioCompra=new PrecioCompra();
    }

    public List<PrecioCompra> getPrecios() {
        return precios;
    }

    public void setPrecios(List<PrecioCompra> precios) {
        this.precios = precios;
    }

    public ModeloPrecioCompra getModeloPrecioCompra() {
        return modeloPrecioCompra;
    }

    public void setModeloPrecioCompra(ModeloPrecioCompra modeloPrecioCompra) {
        this.modeloPrecioCompra = modeloPrecioCompra;
    }

    public PrecioCompra getPrecioSeleccionado() {
        return precioSeleccionado;
    }

    public void setPrecioSeleccionado(PrecioCompra precioSeleccionado) {
        this.precioSeleccionado = precioSeleccionado;
    }

    public PrecioCompra getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(PrecioCompra precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    
    
    
}
