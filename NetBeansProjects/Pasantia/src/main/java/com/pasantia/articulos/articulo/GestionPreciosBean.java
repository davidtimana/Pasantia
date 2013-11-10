/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pasantia.articulos.articulo;

import com.pasantia.entidades.Producto;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "gestionPreciosBean")
@SessionScoped
public class GestionPreciosBean implements Serializable{
    
    private static final long serialVersionUID = -922301025045117329L;
    private static final Logger log = Logger.getLogger(GestionPreciosBean.class.getName());
    
    @Inject
    BuscarProductoReporteBean reporteBean;
    
    private Producto producto;
    private Boolean mostrar;        
    
    public void cargarObjeto(Producto p){
        producto=p;        
    }
    
    public void abrirBuscadorProductos(){
        reporteBean.abrirBuscador();
    }

    @PostConstruct
    public void Init(){
        mostrar=false;
    }
    
    public GestionPreciosBean() {
        producto=new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }
    
    
    
    
    
}
