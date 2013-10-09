/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author David Timana
 */
public class CerrarSesion implements HttpSessionListener {
    
    private static final Logger log = Logger.getLogger(CerrarSesion.class.getName());

    
    
   
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("**********************************************************Inicializando Aplicacion SICOVI");
        
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("**********************************************************Fin Aplicacion SICOVI");
    }
    
}
