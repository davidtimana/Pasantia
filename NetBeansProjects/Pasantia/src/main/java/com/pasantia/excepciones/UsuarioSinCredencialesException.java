/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class UsuarioSinCredencialesException extends Exception{
    
    private static final long serialVersionUID = -7242652623056859177L;    
    

    public UsuarioSinCredencialesException(String message) {
        super(message);
    }
    
    
    
}
