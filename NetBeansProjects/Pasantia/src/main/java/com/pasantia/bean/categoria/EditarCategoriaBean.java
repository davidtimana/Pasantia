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
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "editarCategoriaBean")
@SessionScoped
public class EditarCategoriaBean implements Serializable {

   
    private Categoria categoria;
    private Boolean abrirEditarCategoria;
    private String estiloError;
    
    @Inject
    CategoriaBean categoriaBean;
    @Inject
    CategoriaDAO categoriaDAO;
    
    public void cargarEditarCategorias(Categoria c){
        categoria=c;
        abrirEditarCategoria = true;
        Utilidad.actualizarElemento("dlgEditarCategoria");        
    }
    
    public void cancelarEdicionCategoria(){
        categoria = new Categoria();
        abrirEditarCategoria=false;
        estiloError="";
        Utilidad.actualizarElemento("dlgEditarCategoria");        
    }
    
    public void guardarEdicionCategoria(){
        System.out.println("El id con que actualiza ra es el siguiente-->"+categoria.getIdCategoria());
        if (categoria.getDescripcion().equals("")) {
            Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar La Categoria Del Articulo descripcion requerida");
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtdescat"); 

        } else {
            if (categoriaDAO.actualizarCategoria(categoria)) {
                Utilidad.mensajeInfo("SICOVI", "Categoria Actualizada Exitosamente");
                categoriaBean.cargarCategorias();
                Utilidad.actualizarElemento("tblcategorias");
                abrirEditarCategoria = false;
                Utilidad.actualizarElemento("dlgEditarCategoria");
            } else {
                Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar La Categoria");
            }
            abrirEditarCategoria = false;
            Utilidad.actualizarElemento("dlgEditarCategoria");
        }
        categoria = new Categoria();
        
    }
    
    public EditarCategoriaBean() {
        categoria = new Categoria();
        abrirEditarCategoria = false;
        estiloError="";
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getAbrirEditarCategoria() {
        return abrirEditarCategoria;
    }

    public void setAbrirEditarCategoria(Boolean abrirEditarCategoria) {
        this.abrirEditarCategoria = abrirEditarCategoria;
    }

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }
    
    
    
    
    
    
    
    
}
