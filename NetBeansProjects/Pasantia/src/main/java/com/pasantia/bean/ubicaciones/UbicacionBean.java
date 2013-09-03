/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.ubicaciones;

import com.pasantia.dao.UbicacionDAO;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "ubicacionBean")
@SessionScoped
public class UbicacionBean implements Serializable{

    private Ubicacion ubicacion;
    private List<Ubicacion> ubicaciones; 
    private Boolean abrirNuevoUbicacion,abrirEdicionUbicacion,abrirEliminarUbicacion;
    
    @Inject
    UbicacionDAO ubicacionDAO;
    
   
    //Metodos para gestionar nuevas ubicaciones
    public void prepararGuardadoDelaUbicacion(){
        System.out.println("llegando a preparar guardado de ubicación");
        abrirNuevoUbicacion=true;
        Utilidad.actualizarElemento("dlgNuevaUbicacion");        
    }    
    public void guardarNuevaUbicacion() {
        System.out.println("LLegue a guardar -->>>" + ubicacion.getDescripcion());

        if (ubicacion.getDescripcion().equals("")) {
            Utilidad.mensajeFatal("Guardar Ubicación", "Error Al Guardar La Ubicacion Del Articulo descripcion requerida");
        } else {
            if (ubicacionDAO.insertarUbicacion(ubicacion)) {
                Utilidad.mensajeInfo("Guardar Ubicación", "Ubicacion Guardada Exitosamente");
                Utilidad.actualizarElemento("datatableUbicaciones");
                abrirNuevoUbicacion = false;
                Utilidad.actualizarElemento("dlgNuevaUbicacion");
            } else {
                Utilidad.mensajeFatal("Guardar Ubicación", "Error Al Guardar La Ubicacion");
            }
        }


    }
    public void cancalarNuevaUbicacion(){
        ubicacion=new Ubicacion();
        abrirNuevoUbicacion=false;
        Utilidad.actualizarElemento("dlgNuevaUbicacion");        
    }    
   
     //Metodos para gestionar actualizacion de  ubicaciones
    public void prepararActualizadoDeUbicacion(Ubicacion u){
        System.out.println("Preparando para actualizar una ubicacion con id:"+u.getIdUbicacion());
        ubicacion=u;
        System.out.println("Preparando para actualizar una ubicacion con id:"+ubicacion.getIdUbicacion());      
        abrirEdicionUbicacion=true;
        Utilidad.actualizarElemento("dlgEditarUbicacion");
       
        
        
    }
    public void actualizarUbicacion() {
        System.out.println("Actualizando ubicacion con descripcion: " + ubicacion.getDescripcion() + ubicacion.getIdUbicacion());

        if (ubicacion.getDescripcion().equals("")) {
            Utilidad.mensajeFatal("Actualizar Ubicación", "Error Al Actualizar La Ubicacion Del Articulo descripcion requerida");

        } else {
            if (ubicacionDAO.actualizarUbicacion(ubicacion)) {
                Utilidad.mensajeInfo("Actualizar Ubicación", "Ubicacion Actualizada Exitosamente");
                Utilidad.actualizarElemento("datatableUbicaciones");
                abrirEdicionUbicacion = false;
                Utilidad.actualizarElemento("dlgEditarUbicacion");
            } else {
                Utilidad.mensajeFatal("Actualizar Ubicación", "Error Al Actualizar La Ubicacion");
            }
            abrirEdicionUbicacion = false;
            Utilidad.actualizarElemento("dlgEditarUbicacion");
        }
        ubicacion = new Ubicacion();
    }    
    public void cancalarEdicionUbicacion(){
        ubicacion=new Ubicacion();
        abrirEdicionUbicacion=false;
        Utilidad.actualizarElemento("dlgEditarUbicacion");        
    }
    
    //Metodos para gestionar la eliminacion de ubicaciones
    public void prepararEliminacionUbicacion(Ubicacion u){
        System.out.println("Preparando para eliminar una ubicacion con id:"+u.getIdUbicacion());
        ubicacion=u;
        System.out.println("Preparando para eliminar una ubicacion con id:"+ubicacion.getIdUbicacion());      
        abrirEliminarUbicacion=true;
        Utilidad.actualizarElemento("dlgeliminarUbicacion");
    }
    public void cancalarEliminarUbicacion(){
        ubicacion=new Ubicacion();
        abrirEliminarUbicacion=false;
        Utilidad.actualizarElemento("dlgeliminarUbicacion");        
    }  
    public void eliminarUbicacion(){      
        if(ubicacionDAO.eliminarUbicacion(ubicacion)){
            Utilidad.mensajeInfo("Eliminar Ubicaciones", "Ubicacion Eliminada Exitosamente");
            Utilidad.actualizarElemento("datatableUbicaciones");
        }else{
            Utilidad.mensajeFatal("Eliminar Ubicaciones", "Error al eliminar la ubicación seleccionada");
        }        
        cancalarEliminarUbicacion();
    }

    //Metodo para cargar las ubicaciones
    public void cargarUbicaciones(){        
        ubicaciones=ubicacionDAO.buscartodasUbicaciones();
    }
    
    //Metodo para totalizar las ubicaciones
    public int totalUbicaciones(){
        int total=ubicaciones.size();
        return total;
    }
    
     public UbicacionBean() {       
       abrirNuevoUbicacion=false;
       abrirEdicionUbicacion=false;
       abrirEliminarUbicacion=false;
       ubicacion=new Ubicacion();
       
    }
    
     

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Ubicacion> getUbicaciones() {
        cargarUbicaciones();
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }    

    public Boolean getAbrirNuevoUbicacion() {
        return abrirNuevoUbicacion;
    }

    public void setAbrirNuevoUbicacion(Boolean abrirNuevoUbicacion) {
        this.abrirNuevoUbicacion = abrirNuevoUbicacion;
    }

    public Boolean getAbrirEdicionUbicacion() {
        return abrirEdicionUbicacion;
    }

    public void setAbrirEdicionUbicacion(Boolean abrirEdicionUbicacion) {
        this.abrirEdicionUbicacion = abrirEdicionUbicacion;
    }

    public Boolean getAbrirEliminarUbicacion() {
        return abrirEliminarUbicacion;
    }

    public void setAbrirEliminarUbicacion(Boolean abrirEliminarUbicacion) {
        this.abrirEliminarUbicacion = abrirEliminarUbicacion;
    }
}
