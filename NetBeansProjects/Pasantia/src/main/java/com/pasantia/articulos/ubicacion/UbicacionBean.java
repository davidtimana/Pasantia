/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.ubicacion;

import com.pasantia.dao.UbicacionDAO;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

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
    private String estiloError;
    
    @Inject
    UbicacionDAO ubicacionDAO;
    
   
    public void buscarUbicaciones(Integer idUbicacion, String nombreUbicacion) {
        System.out.println("buscaremos en UbicacionesBean con" + idUbicacion + nombreUbicacion);
        if (idUbicacion == null && nombreUbicacion.equals("")) {
            ubicaciones = ubicacionDAO.buscartodasUbicaciones();
        } else {
            ubicaciones = ubicacionDAO.buscartodasUbicacionesxNombrexId(idUbicacion, nombreUbicacion);
        }
        if (ubicaciones.isEmpty()) {
            if(idUbicacion==null){
                idUbicacion=0;
            }
            Utilidad.mensajePeligro("SICOVI.", "La busqueda de Ubicaciones con codigo: " + idUbicacion + " y Nombre: " + nombreUbicacion + ". No tuvo Resultados");

        } else {
            Utilidad.mensajeInfo("SICOVI.", "La busqueda devolvio: " + ubicaciones.size() + " Resultados.");
        }
        Utilidad.actualizarElemento("datatableUbicaciones");


    }
    
    //Metodos para gestionar nuevas ubicaciones
    public void prepararGuardadoDelaUbicacion(){        
        abrirNuevoUbicacion=true;
        Utilidad.actualizarElemento("dlgNuevaUbicacion");        
    }    
    public void guardarNuevaUbicacion() {        

        if (ubicacion.getDescripcion().equals("")) {
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnombreubic"); 
            Utilidad.mensajeFatal("SICOVI", "Error Al Guardar La Ubicacion Del Articulo descripcion requerida");
        } else {
            estiloError="";
            if (ubicacionDAO.insertarUbicacion(ubicacion)) {
                Utilidad.mensajeInfo("SICOVI", "Ubicacion Guardada Exitosamente");
                cargarUbicaciones();
                Utilidad.actualizarElemento("datatableUbicaciones"); 
                ubicacion=new Ubicacion();
                Utilidad.actualizarElemento("txtnombreubic"); 
            } else {
                
                Utilidad.mensajeFatal("SICOVI", "Error Al Guardar La Ubicacion");
                
            }
        }


    }
    public void cancalarNuevaUbicacion(){
        ubicacion=new Ubicacion();
        abrirNuevoUbicacion=false;   
        estiloError="";
        Utilidad.actualizarElemento("dlgNuevaUbicacion");        
    }    
   
     //Metodos para gestionar actualizacion de  ubicaciones
    public void prepararActualizadoDeUbicacion(Ubicacion u){        
        ubicacion=u;        
        abrirEdicionUbicacion=true;
        Utilidad.actualizarElemento("dlgEditarUbicacion");      
    }
    public void actualizarUbicacion() {        

        if (ubicacion.getDescripcion().equals("")) {
            Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar La Ubicacion Del Articulo descripcion requerida");
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtdesubicedit"); 

        } else {
            if (ubicacionDAO.actualizarUbicacion(ubicacion)) {
                Utilidad.mensajeInfo("SICOVI", "Ubicacion Actualizada Exitosamente");
                cargarUbicaciones();
                Utilidad.actualizarElemento("datatableUbicaciones");
                abrirEdicionUbicacion = false;
                Utilidad.actualizarElemento("dlgEditarUbicacion");
            } else {
                Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar La Ubicacion");
            }
            abrirEdicionUbicacion = false;
            Utilidad.actualizarElemento("dlgEditarUbicacion");
        }
        ubicacion = new Ubicacion();
    }    
    public void cancalarEdicionUbicacion(){
        ubicacion=new Ubicacion();
        abrirEdicionUbicacion=false;
        estiloError="";
        Utilidad.actualizarElemento("dlgEditarUbicacion");        
    }
    
    //Metodos para gestionar la eliminacion de ubicaciones
    public void prepararEliminacionUbicacion(Ubicacion u){        
        ubicacion=u;        
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
            Utilidad.mensajeInfo("SICOVI", "Ubicacion Eliminada Exitosamente");
            cargarUbicaciones();
            Utilidad.actualizarElemento("datatableUbicaciones");
        }else{
            Utilidad.mensajeFatal("SICOVI", "Error al eliminar la ubicación seleccionada");
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
    
    public void actualizarEstilo(){
        estiloError="";
        Utilidad.actualizarElemento("txtnombreubic"); 
    }
    
     public UbicacionBean() {       
       abrirNuevoUbicacion=false;
       abrirEdicionUbicacion=false;
       abrirEliminarUbicacion=false;       
       ubicacion=new Ubicacion();
       ubicaciones = new ArrayList<Ubicacion>();   
       estiloError="";
       
    }

   
    
     

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Ubicacion> getUbicaciones() {        
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

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }
    
    
    
    }


