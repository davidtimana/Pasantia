/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
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
    public static void actualizarElemento(String idElemento){
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete(idElemento).getClientId(FacesContext.getCurrentInstance()));
    }
    
    public static UIComponent buscarHtmlComponete(String idComponete) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (null != context) {
            return buscarHtmlComponete(context.getViewRoot(), idComponete);
        }
        return null;
    } 

    public static UIComponent buscarHtmlComponete(UIComponent parent,
            String idComponete) {
        if (idComponete.equals(parent.getId())) {
            return parent;
        }
        Iterator<UIComponent> kids = parent.getFacetsAndChildren();
        while (kids.hasNext()) {
            UIComponent kid = kids.next();
            UIComponent found = buscarHtmlComponete(kid, idComponete);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
    
    
    
}
