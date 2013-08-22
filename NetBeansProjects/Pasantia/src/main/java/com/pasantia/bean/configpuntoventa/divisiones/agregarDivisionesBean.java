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
import javax.inject.Inject;
import org.primefaces.component.dialog.Dialog;


/**
 *
 * @author root
 */
@Named(value = "agregarDivisionesBean")
@RequestScoped
public class agregarDivisionesBean implements Serializable{
    
    private String descripcion;
    private Divisiones divisiones;
    private DivisionesDAO divisionesDAO;
    private Dialog dlgNuevo;
    
    
    
    
    

    public void prepararGuardadoDelasDivisiones(){  
        dlgNuevo.setVisible(true);
    }  
    
    
    
   
    public void guardarNuevaDivision(){        
        
        if(descripcion.equals("") || descripcion.isEmpty() || descripcion==null){                       
            Utilidad.mensajeFatal("Error al guardar la division.", "Nombre de la division requerida.");            
        }
        else{              
            divisiones.setNombreDivision(descripcion);               
                if(divisionesDAO.insertarDivisiones(divisiones)){
                    Utilidad.mensajeInfo("Guardado Exitoso.", "Division guardada Exitosamente.");          
                   
                }                
                else{
                    Utilidad.mensajeFatal("Guardado Incorrecto.", "...ERROR... al guardar.");          
                }
                descripcion="";            
           }
    }
    
    public void cancelarNuevaDivision(){
        dlgNuevo.setVisible(false);
        descripcion="";
    }
    
     public agregarDivisionesBean() {
        divisionesDAO = new DivisionesDAOImpl();
        dlgNuevo = new Dialog();
        divisiones = new Divisiones();
        
        //Valores por defecto
        dlgNuevo.setVisible(false);
    }
    

    public Dialog getDlgNuevo() {
        return dlgNuevo;
    }

    public void setDlgNuevo(Dialog dlgNuevo) {
        this.dlgNuevo = dlgNuevo;
    }   

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Divisiones getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }
    
    
    
   
    
         

    
    
   
   
    
   
    
   
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    
    
    
    
    
}
