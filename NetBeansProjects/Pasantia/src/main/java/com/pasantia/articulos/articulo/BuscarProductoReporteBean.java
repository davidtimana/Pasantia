/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Producto;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "buscarProductoReporteBean")
@SessionScoped
public class BuscarProductoReporteBean implements Serializable{
    
    private static final long serialVersionUID = 1866948902700356353L;
    private static final Logger log = Logger.getLogger(BuscarProductoReporteBean.class.getName());
    

    /**
     * Creates a new instance of BuscarProductoReporteBean
     */
    public BuscarProductoReporteBean() {
        buscador=false;
        productoSeleccionado=new Producto();
    }
    
    @Inject
    CrudDAO<Producto> crudDAO;
    @Inject
    GestionPreciosBean gestionPreciosBean;
    
    
    private Boolean buscador;
    private List<Producto> productos;
    private ModeloProductos modeloProductos;
    private Producto productoSeleccionado;
    
    public void abrirBuscador(){
        buscador=true;
        productoSeleccionado=new Producto();
        Utilidad.actualizarElemento("dlgbuscarproductosReporte");
        cargarBuscador();
        Utilidad.actualizarElemento("tblproductosreporte");
    }
    
    public void cerrarBuscador(){
        buscador=false;
        productoSeleccionado=new Producto();
        Utilidad.actualizarElemento("dlgbuscarproductosReporte");
    }
    
    public void cargarSeleccionado(){
        log.log(Level.INFO, ">>>>>>>>>>>>>>>>> El producto cargado es el siguiente-->{0}", productoSeleccionado.getDescripcion());
        buscador=false;
        Utilidad.actualizarElemento("dlgbuscarproductosReporte");
        gestionPreciosBean.cargarObjeto(productoSeleccionado);
        Utilidad.actualizarElemento("producencontrado");
    }
    
    public void cargarBuscador(){
        productos=crudDAO.buscarTodos(Producto.class);
        modeloProductos=new ModeloProductos(productos);
    }
    
    public Integer totalProductos(){
        return productos.size();
    }
    
    @PostConstruct
    public void Init(){
        cargarBuscador();
        totalProductos();        
    }    
   
    public Boolean getBuscador() {
        return buscador;
    }

    public void setBuscador(Boolean buscador) {
        this.buscador = buscador;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ModeloProductos getModeloProductos() {
        return modeloProductos;
    }

    public void setModeloProductos(ModeloProductos modeloProductos) {
        this.modeloProductos = modeloProductos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }
    
}
