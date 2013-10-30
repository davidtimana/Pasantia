/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Producto;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarProductoBean")
@SessionScoped
public class BuscarProductoBean implements Serializable {
    private static final long serialVersionUID = -1329251589059324222L;

    @Inject
    CrudDAO<Producto> crudDAO;
    
    private Boolean buscador;
    private List<Producto> productos;
    private ModeloProductos modeloProductos;
    private Producto productoSeleccionado;
    
    public void abrirBuscador(){
        buscador=true;
        productoSeleccionado=new Producto();
        Utilidad.actualizarElemento("dlgbuscarproductos");
        cargarBuscador();
        Utilidad.actualizarElemento("tblproductos");
    }
    
    public void cerrarBuscador(){
        buscador=false;
        productoSeleccionado=new Producto();
        Utilidad.actualizarElemento("dlgbuscarproductos");
    }
    
    public void cargarSeleccionado(){
        
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
    
    public BuscarProductoBean() {
        buscador=false;
        productoSeleccionado=new Producto();
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
