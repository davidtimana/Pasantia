/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class LoginRepetidoException extends Exception{
    
    private static final long serialVersionUID = 6993519477185929562L;

    public LoginRepetidoException(String mensaje) {
        super(mensaje);
    }
    
    
}
