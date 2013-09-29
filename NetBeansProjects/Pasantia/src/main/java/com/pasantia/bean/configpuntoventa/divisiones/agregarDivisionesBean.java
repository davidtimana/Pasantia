/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.impl.DivisionesDAOImpl;
import com.pasantia.entidades.Divisiones;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.dialog.Dialog;


/**
 *
 * @author David Timana
 */
@Named(value = "agregarDivisionesBean")
@SessionScoped
public class agregarDivisionesBean implements Serializable{
    
    private static final long serialVersionUID = -1005502037010274240L;
    
    private Divisiones divisiones;
    private Boolean abrirNuevaDivision;
    private String estiloError;
    
    
    
    @Inject
    DivisionesDAO divisionesDAO;
    @Inject
    DivisionesBean divisionesBean;
    
    
    
    
    
    
    

    public void prepararGuardadoDelasDivisiones(){
        abrirNuevaDivision=true;
        Utilidad.actualizarElemento("dlgnuevadivision");
        
    }  
   
    
    
    
   
    public void guardarNuevaDivision(){        
        
        if (divisiones.getNombreDivision().equals("")) {
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnomdivision"); 
            Utilidad.mensajeFatal("SICOVI", "Error al guardar la división Nombre requerido");
        } else {
            estiloError="";
            if (divisionesDAO.insertarDivisiones(divisiones)) {
                Utilidad.mensajeInfo("SICOVI", "Division Guardada Exitosamente");
                divisionesBean.cargarDivisiones();
                Utilidad.actualizarElemento("tbldivisiones"); 
                divisiones=new Divisiones();
                Utilidad.actualizarElemento("txtnomdivision"); 
            } else {
                
                Utilidad.mensajeFatal("SICOVI", "Error Al Guardar La División");
                
            }
        }
    }
    
    public void cancelarNuevaDivision(){
        divisiones=new Divisiones();
        abrirNuevaDivision=false;
        Utilidad.actualizarElemento("txtnomdivision"); 
        Utilidad.actualizarElemento("dlgnuevadivision");
        
    }
    
     public agregarDivisionesBean() {        
        
        divisiones = new Divisiones();
        abrirNuevaDivision = false;
        
        //Valores por defecto

    }
    

     

   

    public Divisiones getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }

    public Boolean getAbrirNuevaDivision() {
        return abrirNuevaDivision;
    }

    public void setAbrirNuevaDivision(Boolean abrirNuevaDivision) {
        this.abrirNuevaDivision = abrirNuevaDivision;
    }

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }
    
    
    
    
    
    
    
   
    
         

    
    
   
   
    
   
    
   
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
}
