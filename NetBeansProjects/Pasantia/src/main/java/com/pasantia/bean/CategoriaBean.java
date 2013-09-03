/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.CategoriaDAO;
import com.pasantia.dao.UbicacionDAO;
import com.pasantia.dao.impl.CategoriaDAOImpl;
import com.pasantia.dao.impl.UbicacionDAOImpl;
import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.Ubicacion;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author root
 */
@Named(value = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable{

    private Categoria categoria;
    private List<Categoria> categorias;
    private Integer id;
    private String descripcion;
    private Dialog dlgNuevaCategoria,dlgeditarcategoria,dlgeliminarcomfirmar;
    private InputText txtdescripcioning,txtdescripcioneditar,txtideditar,txtideliminar;
   
    
    
        
    public CategoriaBean() {
        categoria = new Categoria();
        dlgNuevaCategoria = new Dialog();
        txtdescripcioning = new InputText();
        dlgeditarcategoria = new Dialog(); 
        txtdescripcioneditar = new InputText();
        txtideditar = new InputText();
        dlgeliminarcomfirmar = new Dialog();
        txtideliminar = new InputText();
        
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        categorias=categoriaDAO.buscartodasCategorias();
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dialog getDlgNuevaCategoria() {
        return dlgNuevaCategoria;
    }

    public void setDlgNuevaCategoria(Dialog dlgNuevaCategoria) {
        this.dlgNuevaCategoria = dlgNuevaCategoria;
    }

    public InputText getTxtdescripcioning() {
        return txtdescripcioning;
    }

    public void setTxtdescripcioning(InputText txtdescripcioning) {
        this.txtdescripcioning = txtdescripcioning;
    }

    public Dialog getDlgeditarcategoria() {
        return dlgeditarcategoria;
    }

    public void setDlgeditarcategoria(Dialog dlgeditarcategoria) {
        this.dlgeditarcategoria = dlgeditarcategoria;
    }

    public InputText getTxtdescripcioneditar() {
        return txtdescripcioneditar;
    }

    public void setTxtdescripcioneditar(InputText txtdescripcioneditar) {
        this.txtdescripcioneditar = txtdescripcioneditar;
    }

    public InputText getTxtideditar() {
        return txtideditar;
    }

    public void setTxtideditar(InputText txtideditar) {
        this.txtideditar = txtideditar;
    }

    public Dialog getDlgeliminarcomfirmar() {
        return dlgeliminarcomfirmar;
    }

    public void setDlgeliminarcomfirmar(Dialog dlgeliminarcomfirmar) {
        this.dlgeliminarcomfirmar = dlgeliminarcomfirmar;
    }

    public InputText getTxtideliminar() {
        return txtideliminar;
    }

    public void setTxtideliminar(InputText txtideliminar) {
        this.txtideliminar = txtideliminar;
    }

    

    
    
    
    
    public void prepararGuardadoDelaCategoria(){
        categoria=new Categoria();
        dlgNuevaCategoria.setVisible(Boolean.TRUE);
        
    }
    
    public void guardarNuevaCategoria(){
        System.out.println("Guardando la categoria con descripcion  -->>>"+this.descripcion);
        Categoria c= new Categoria();
        c.setDescripcion(this.descripcion);        
         
          if (c.getDescripcion().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Al Guardar La Categoria Del Articulo"
                    , "Descripcion De La Categoria Requerida"));
            dlgNuevaCategoria.setVisible(Boolean.TRUE);
            
            
          }else{
              System.out.println("--------------------------- "+c.getDescripcion());
              
              CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
              categoriaDAO.insertarUbicacion(c);
              FacesContext context = FacesContext.getCurrentInstance();
              context.addMessage(null, new FacesMessage("Categoria Guardada Exitosamente"));
              dlgNuevaCategoria.setVisible(Boolean.TRUE);
              this.descripcion=null;
          }        
        categoria=new Categoria();
    }
    
    public void prepararActualizadoDeCategoria(Integer Id,String descripcion){
        dlgeditarcategoria.setVisible(Boolean.TRUE);
        System.out.println("Preparando para actualizar una categoria con id:"+Id);
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        categoria=categoriaDAO.buscarCategoriaporId(Id);
        
        txtideditar.setValue(Id);
        txtideditar.setReadonly(true);
        this.setDescripcion(descripcion);
        this.setId(Id);
        txtdescripcioneditar.setValue(descripcion);
    }
    
    public void actualizarCategoria(){
        System.out.println("Actualizando ubicacion con descripcion: "+this.descripcion);
        Categoria c = new Categoria();
        c.setDescripcion(this.descripcion);
        c.setIdCategoria(this.id);
        if (c.getDescripcion().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error En La Actualizacion De La Categoria Del Articulo"
                    , "Descripcion De La Categoria Requerida"));
            dlgeditarcategoria.setVisible(Boolean.FALSE);
          }else{
            CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
            System.out.println("---------------------------"+c.getDescripcion());
            System.out.println("---------------------------"+c.getIdCategoria());
            categoriaDAO.actualizarUbicacion(c);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Categoria Actualizada Exitosamente"));
            dlgeditarcategoria.setVisible(Boolean.TRUE);
        }
        categoria=new Categoria();
    }
    
    public void prepararEliminarCategoria(Integer Id,String descripcion){
        System.out.println("Preparando para eliminar una categoria con id:"+Id);
        dlgeliminarcomfirmar.setVisible(true);
        dlgeliminarcomfirmar.setHeader("¿Desea Eliminar la ubicacion con Descripcion: " +descripcion +"?");
        txtideliminar.setValue(Id);
        txtideliminar.setReadonly(Boolean.TRUE);
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        categoria=categoriaDAO.buscarCategoriaporId(Id);
       this.setId(Id);
       this.setDescripcion(descripcion);
    }
    public void eliminarCategoria(){
        Categoria c = new Categoria();
        CategoriaDAO categoriaDAO=new CategoriaDAOImpl();
        System.out.println("Eliminando ubicacion con id"+txtideliminar.getValue());
        c=categoriaDAO.buscarCategoriaporId((Integer)(txtideliminar.getValue()));
        
        
        boolean resultado=categoriaDAO.eliminarUbicacion(c);
        if(resultado){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Categoria Eliminada Exitosamente"));
            dlgeliminarcomfirmar.setVisible(Boolean.FALSE);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar"
                    , "Categoria: "+c.getDescripcion()+". no eliminada."));
            dlgeliminarcomfirmar.setVisible(Boolean.FALSE);
        }
        
        
        categoria=new Categoria();
    }
    public int totalCategorias(){
        int total=categorias.size();
        return total;
    }
    
    
    
    
    
    
}
