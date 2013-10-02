/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

/**
 *
 * @author David Timana
 */
public class PasswordVacioException extends Exception{
    
    private static final long serialVersionUID = 7962760193345063437L;

    public PasswordVacioException(String message) {
        super(message);
    }
    
    
    
}
