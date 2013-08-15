/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Orlando Timaná
 */

   

public class Utilidad {
    
    
    
    
    public static void mensajeError(String titulo, String mensaje) {
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));            
    }

    public static void mensajePeligro(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));            
    }

    public static void mensajeInfo(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));             
    }

    public static void mensajeFatal(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));            
    }
    
    public static void abrirDialog(String idDialog) {
        String comando=idDialog+".show()";
        RequestContext.getCurrentInstance().execute(comando);        
    }

    
    
    
}
