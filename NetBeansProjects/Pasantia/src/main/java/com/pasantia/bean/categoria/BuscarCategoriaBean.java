/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.categoria;

import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarCategoriaBean")
@SessionScoped
public class BuscarCategoriaBean implements Serializable {

    private Boolean abrirBuscadorCategoria;
    private Integer idCategoria;
    private String nombreCategoria;
    
    @Inject
    CategoriaBean categoriaBean;
    
    public void cargarBuscadorUbicaciones() {
        abrirBuscadorCategoria=true;
        Utilidad.actualizarElemento("dlgbuscarCategorias");
        Utilidad.mensajeInfo("SICOVI", "Buscador de Categorias. Por favor escriba los parametros con los cuales desea buscar.");
    }
    
    public void cancelarBuscadorCategorias(){
        idCategoria=null;
        nombreCategoria="";
        abrirBuscadorCategoria=false;
        Utilidad.actualizarElemento("dlgbuscarCategorias");
    }
    
    public void buscarCategorias(){
        categoriaBean.buscarCategorias(idCategoria, nombreCategoria);
    }
    
    
    public BuscarCategoriaBean() {
        abrirBuscadorCategoria=false;
    }

    public Boolean getAbrirBuscadorCategoria() {
        return abrirBuscadorCategoria;
    }

    public void setAbrirBuscadorCategoria(Boolean abrirBuscadorCategoria) {
        this.abrirBuscadorCategoria = abrirBuscadorCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    
    
    
}
