/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.categoria;

import com.pasantia.dao.CategoriaDAO;
import com.pasantia.dao.UbicacionDAO;
import com.pasantia.dao.impl.CategoriaDAOImpl;
import com.pasantia.dao.impl.UbicacionDAOImpl;
import com.pasantia.entidades.Categoria;
import com.pasantia.utilidades.Utilidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author root
 */
@Named(value = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable{

    private Categoria categoria;
    private List<Categoria> categorias;
    
    @Inject
    CategoriaDAO categoriaDAO;
    
    public void buscarCategorias(Integer idCategoria,String nombreCategoria){
        if(idCategoria==null && nombreCategoria.equals("")){
            categorias=categoriaDAO.buscartodasCategorias();
        }else{
            categorias=categoriaDAO.buscartodasCategoriasxNombrexId(idCategoria, nombreCategoria);
        }
        if (categorias.isEmpty()) {
            if(idCategoria==null){
                idCategoria=0;
            }
            Utilidad.mensajePeligro("SICOVI.", "La busqueda de Categorias con codigo: " + idCategoria + " y Nombre: " + nombreCategoria + ". No tuvo Resultados");

        } else {
            Utilidad.mensajeInfo("SICOVI.", "La busqueda devolvio: " + categorias.size() + " Resultados.");
        }
        Utilidad.actualizarElemento("tblcategorias");
        
    }
    
   public void cargarCategorias(){
       categorias=categoriaDAO.buscartodasCategorias();
   }
   
   public Integer totalCategorias(){
       return categorias.size();
   }   
        
    public CategoriaBean() {
        categoria = new Categoria();
        categorias= new ArrayList<Categoria>();        
        
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {        
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    
    

    

    
    
    
    
   
    
    
        
        
    
    
    
    
    
    
    
    
}
