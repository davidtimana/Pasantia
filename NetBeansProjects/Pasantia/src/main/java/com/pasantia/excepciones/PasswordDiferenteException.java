/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class PasswordDiferenteException extends Exception{
    
    private static final long serialVersionUID = -1867574733792359772L;

    public PasswordDiferenteException(String message) {
        super(message);
    }
    
    
    
}
