/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author David Timana
 */
public class CerrarSesion implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("hola bienvenido");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("hola cerraste navegador");
    }
    
}
