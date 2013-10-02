/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class PersonaNoSeleccionadaException extends Exception{
    
    private static final long serialVersionUID = 3157362649677370778L;

    public PersonaNoSeleccionadaException(String mensaje) {
        super(mensaje);
    }
    
    
    
}
