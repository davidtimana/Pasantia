/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author David Timana
 */
@Named(value = "parametrosBean")
@SessionScoped
public class ParametrosBean implements Serializable {
    
    private static final long serialVersionUID = -7918647547368475344L;
    private static final Logger log = Logger.getLogger(ParametrosBean.class.getName());
    

   private String rutaTemporal;
   
   
   public void asignarRuta(){
       ExternalContext extContext =FacesContext.getCurrentInstance().getExternalContext();
       log.info("*************************Obteniendo ruta de la carpeta temp de la aplicacion");
       rutaTemporal=extContext.getRealPath("temp")+"/";
       log.log(Level.INFO, "********************La ruta que se a obtenido es la siguiente--> {0}", rutaTemporal);
   }
   
    public ParametrosBean() {
    }

    public String getRutaTemporal() {
        return rutaTemporal;
    }

    public void setRutaTemporal(String rutaTemporal) {
        this.rutaTemporal = rutaTemporal;
    }
    
    
}
