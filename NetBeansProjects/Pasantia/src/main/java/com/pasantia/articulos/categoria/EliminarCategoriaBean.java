/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.categoria;

import com.pasantia.dao.CategoriaDAO;
import com.pasantia.entidades.Categoria;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "eliminarCategoriaBean")
@SessionScoped
public class EliminarCategoriaBean implements Serializable {
    
    private static final long serialVersionUID = -3686112827798692519L;

    private Categoria categoria;
    private Boolean abrirEliminarcategoria;
    
    @Inject
    CategoriaDAO categoriaDAO;
    @Inject
    CategoriaBean categoriaBean;
    
    
    public void cargarEliminadoCategoria(Categoria c){
        categoria=c;
        abrirEliminarcategoria = true;
        Utilidad.actualizarElemento("dlgeliminarcategoria");
    }
    
    public void cancelarEliminarCategoria(){
        abrirEliminarcategoria = false;
        Utilidad.actualizarElemento("dlgeliminarcategoria");
    }
    
    public void eliminarCategoria(){
        if(categoriaDAO.eliminarCategoria(categoria)){
            Utilidad.mensajeInfo("SICOVI", "Categoria Eliminada Exitosamente");
            categoriaBean.cargarCategorias();
            Utilidad.actualizarElemento("tblcategorias");
        }else{
            Utilidad.mensajeFatal("SICOVI", "Error al eliminar la Categoria seleccionada");
        }     
        cancelarEliminarCategoria();
        
    }
    
    public EliminarCategoriaBean() {
        abrirEliminarcategoria = false;
        categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getAbrirEliminarcategoria() {
        return abrirEliminarcategoria;
    }

    public void setAbrirEliminarcategoria(Boolean abrirEliminarcategoria) {
        this.abrirEliminarcategoria = abrirEliminarcategoria;
    }
    
    
}
