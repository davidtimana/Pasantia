/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.UbicacionDAO;
import com.pasantia.dao.impl.UbicacionDAOImpl;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import org.hibernate.dialect.IngresDialect;


import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author david
 */
@ManagedBean(name="ubicacionBean")
@RequestScoped
public class UbicacionBean implements Serializable{

    private Ubicacion ubicacion;
    private List<Ubicacion> ubicaciones;
    private Dialog dlgnuevaubicacion;
    private Dialog dlgeditarubicacion;
    private Dialog dlgconfireliminar;
    private String descripcion;
    private Integer id;
    private InputText txtideliminar;

    public InputText getTxtideliminar() {
        return txtideliminar;
    }

    public void setTxtideliminar(InputText txtideliminar) {
        this.txtideliminar = txtideliminar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private InputText txtdescripcioneditar;
    private InputText txtideditar;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Dialog getDlgconfireliminar() {
        return dlgconfireliminar;
    }

    public void setDlgconfireliminar(Dialog dlgconfireliminar) {
        this.dlgconfireliminar = dlgconfireliminar;
    }

    public Dialog getDlgeditarubicacion() {
        return dlgeditarubicacion;
    }

    public void setDlgeditarubicacion(Dialog dlgeditarubicacion) {
        this.dlgeditarubicacion = dlgeditarubicacion;
    }

    public Dialog getDlgnuevaubicacion() {
        return dlgnuevaubicacion;
    }

    public void setDlgnuevaubicacion(Dialog dlgnuevaubicacion) {
        this.dlgnuevaubicacion = dlgnuevaubicacion;
    }
    
    public UbicacionBean() {
       dlgnuevaubicacion=new Dialog();
       dlgeditarubicacion=new Dialog();
       dlgconfireliminar=new Dialog();
       ubicacion=new Ubicacion();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Ubicacion> getUbicaciones() {
        UbicacionDAO ubicacionDAO = new UbicacionDAOImpl();
        ubicaciones=ubicacionDAO.buscartodasUbicaciones();
        return ubicaciones;
    }
    public void prepararGuardadoDelaUbicacion(){
        ubicacion=new Ubicacion();
    }
    
    public void guardarNuevaUbicacion(){
        System.out.println("LLegue a guardar -->>>"+ubicacion.getDescripcion());        
         
          if (ubicacion.getDescripcion().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error Al Guardar La Ubicacion Del Articulo"
                    , "Descripcion De La Ubicacion Requerida"));
            dlgnuevaubicacion.setVisible(Boolean.TRUE);
          }else{
              UbicacionDAO ubicacionDAO = new UbicacionDAOImpl();
              ubicacionDAO.insertarUbicacion(ubicacion);
              FacesContext context = FacesContext.getCurrentInstance();
              context.addMessage(null, new FacesMessage("Ubicacion Guardada Exitosamente"));
              dlgnuevaubicacion.setVisible(Boolean.TRUE);
          }        
        ubicacion=new Ubicacion();
    }
    public void comfirmarEliminado(Integer Id,String descripcion){
        dlgconfireliminar.setVisible(true);
        dlgconfireliminar.setHeader("¿Desea Eliminar la ubicacion con Descripcion: " +descripcion +"?");
        txtideliminar.setValue(Id);
        txtideliminar.setReadonly(true);
    }
    public void abrirEditar(){
        dlgeditarubicacion.setVisible(Boolean.TRUE);
    }
    public void prepararActualizadoDeUbicacion(Integer Id,String descripcion){
        System.out.println("Preparando para actualizar una ubicacion con id:"+Id);
        UbicacionDAO ubicacionDAO=new UbicacionDAOImpl();
        ubicacion=ubicacionDAO.buscarUbicacionporId(Id);
        
        txtideditar.setValue(Id);
        txtideditar.setReadonly(true);
        this.setDescripcion(descripcion);
        this.setId(id);
        txtdescripcioneditar.setValue(descripcion);
    }
    public void actualizarUbicacion(){
        System.out.println("Actualizando ubicacion con descripcion: "+this.descripcion);
        ubicacion.setDescripcion(this.descripcion);
        ubicacion.setIdUbicacion(this.id);
        if (ubicacion.getDescripcion().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error En La Actualizacion La Ubicacion Del Articulo"
                    , "Descripcion De La Ubicacion Requerida"));
            dlgeditarubicacion.setVisible(Boolean.FALSE);
          }else{
            UbicacionDAO ubicacionDAO=new UbicacionDAOImpl();
            System.out.println("---------------------------"+ubicacion.getDescripcion());
            System.out.println("---------------------------"+ubicacion.getIdUbicacion());
            ubicacionDAO.actualizarUbicacion(ubicacion);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ubicacion Actualizada Exitosamente"));
            dlgeditarubicacion.setVisible(Boolean.TRUE);
        }
        ubicacion=new Ubicacion();
    }
    public void eliminarUbicacion(){
        Ubicacion u = new Ubicacion();
        UbicacionDAO ubicacionDAO=new UbicacionDAOImpl();
        System.out.println("Eliminando ubicacion con id"+txtideliminar.getValue());
        u=ubicacionDAO.buscarUbicacionporId((Integer)(txtideliminar.getValue()));
        
        
        boolean resultado=ubicacionDAO.eliminarUbicacion(u);
        if(resultado){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Ubicacion Eliminada Exitosamente"));
            dlgconfireliminar.setVisible(Boolean.FALSE);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al eliminar"
                    , "Ubicacion: "+ubicacion.getDescripcion()+". no eliminada"));
            dlgconfireliminar.setVisible(Boolean.FALSE);
        }
        
        
        ubicacion=new Ubicacion();
    }
    public void prepararEliminarUbicacion(Integer Id,String descripcion){
        System.out.println("Preparando para eliminar una ubicacion con id:"+Id);
        UbicacionDAO ubicacionDAO=new UbicacionDAOImpl();
        ubicacion=ubicacionDAO.buscarUbicacionporId(Id);
       this.setId(Id);
       this.setDescripcion(descripcion);
    }
    public int totalUbicaciones(){
        int total=ubicaciones.size();
        return total;
    }
    
}
