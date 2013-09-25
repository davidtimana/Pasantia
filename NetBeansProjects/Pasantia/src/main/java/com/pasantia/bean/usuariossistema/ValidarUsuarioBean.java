/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author david
 */
@Named(value = "validarUsuarioBean")
@SessionScoped
public class ValidarUsuarioBean implements Serializable {

    /**
     * Creates a new instance of ValidarUsuarioBean
     */
     public void onTabChange(TabChangeEvent event) {  
         System.out.println("la que se activa es la siguiente--> "+event.getTab().getTitle());
    } 
    
    public ValidarUsuarioBean() {
    }
}
