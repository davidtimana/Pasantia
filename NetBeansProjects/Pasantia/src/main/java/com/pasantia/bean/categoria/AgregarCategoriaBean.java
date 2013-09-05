/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.categoria;

import com.pasantia.dao.CategoriaDAO;
import com.pasantia.entidades.Categoria;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Timana
 */
@Named(value = "agregarCategoriaBean")
@SessionScoped
public class AgregarCategoriaBean implements Serializable {

    private Boolean abrirNuevaCateogira;
    private Categoria categoria;
    private String estiloError;
    
    @Inject
    CategoriaBean categoriaBean;
    
    @Inject
    CategoriaDAO categoriaDAO;
    
    public void cargarGuardadoCategoria(){
        System.out.println("LLegue a cargarGuardadoCategoria");
        abrirNuevaCateogira=true;
        System.out.println("LLegue a cargarGuardadoCategoria la variable queda asi-->"+abrirNuevaCateogira);        
        Utilidad.actualizarElemento("dlgnuevacategoria");
    }
    
    public void cancelarGuardado(){
        categoria=new Categoria();
        abrirNuevaCateogira=false;        
        estiloError="";
        Utilidad.actualizarElemento("dlgnuevacategoria");
    }
    
    public void guardarCategoria(){        
        if (categoria.getDescripcion().equals("")) {
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnombreCat"); 
            Utilidad.mensajeFatal("SICOVI", "Error Al Guardar La Categoria Del Articulo descripcion requerida");
        } else {
            estiloError="";
            if (categoriaDAO.insertarCategoria(categoria)) {
                Utilidad.mensajeInfo("SICOVI", "Categoria Guardada Exitosamente");
                categoriaBean.cargarCategorias();
                Utilidad.actualizarElemento("tblcategorias"); 
                categoria=new Categoria();
                Utilidad.actualizarElemento("txtnombreCat"); 
            } else {
                
                Utilidad.mensajeFatal("SICOVI", "Error Al Guardar La Categoria");
                
            }
        }
    }
    
    public AgregarCategoriaBean() {
        abrirNuevaCateogira=false;
        categoria = new Categoria();
        estiloError="";
    }

    public Boolean getAbrirNuevaCateogira() {
        return abrirNuevaCateogira;
    }

    public void setAbrirNuevaCateogira(Boolean abrirNuevaCateogira) {
        this.abrirNuevaCateogira = abrirNuevaCateogira;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }
    
    
    
    
    
    
    
    
}
