/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class PersonaUsuarioRepetidaException extends Exception{
    
    private static final long serialVersionUID = -5331757489687222045L;

    public PersonaUsuarioRepetidaException(String message) {
        super(message);
    }
    
    
    
}
