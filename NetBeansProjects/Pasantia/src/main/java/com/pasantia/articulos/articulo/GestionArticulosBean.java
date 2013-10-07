/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.Tblunidad;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.utilidades.CombosComunes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "gestionArticulosBean")
@SessionScoped
public class GestionArticulosBean extends CombosComunes implements Serializable {
    
    private static final long serialVersionUID = 8930048590380965066L;
    private static final Logger LOG = Logger.getLogger(GestionArticulosBean.class.getName());
    
    
    private Producto producto;
    private Tblunidad unidad;
    private Categoria categoria;
    private Ubicacion ubicacion;
    
    @Inject
    CrudDAO<Tblunidad> unidadDAO;
    @Inject
    CrudDAO<Categoria> categoriaDAO;
    @Inject
    CrudDAO<Ubicacion> ubicacionDAO;
    

    public void cargarUnidad(){
        if(unidad.getSecunidad()!=null){
            unidad=unidadDAO.buscar(Tblunidad.class, unidad.getSecunidad());
        }
    }
    
    public void cargarCategoria(){
        if(categoria.getIdCategoria()!=null){
            categoria=categoriaDAO.buscar(Categoria.class, categoria.getIdCategoria());
        }
    }
    
    public void cargarUbicacion(){
        if(ubicacion.getIdUbicacion()!=null){
            ubicacion=ubicacionDAO.buscar(Ubicacion.class, ubicacion.getIdUbicacion());
        }
    }
    
    @PostConstruct
    public void Init(){
        cargarComboCategorias();
        cargarComboUnidades();
        cargarComboUbicaciones();
    }
    
    public GestionArticulosBean() {
        producto = new Producto();
        unidad=new Tblunidad();
        categoria=new Categoria();
        ubicacion=new Ubicacion();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tblunidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Tblunidad unidad) {
        this.unidad = unidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    

    
    
    
    
    
}
