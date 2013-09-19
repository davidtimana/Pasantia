/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.excepciones;

import com.pasantia.entidades.Persona;
import com.pasantia.utilidades.Utilidad;

/**
 *
 * @author David Timana
 */
public class ExcepcionPersona extends Exception {
    
    private static Persona persona;

    public ExcepcionPersona(String mensajeError) {
        super(mensajeError);
    }

    static void primerNombreVacio() throws ExcepcionPersona {
        
        if(!Utilidad.cadenaVacia(persona.getPnombre())){
            throw new ExcepcionPersona("Primer Nombre de la persona vacio");
        }
        
    }

    public static Persona getPersona() {
        return persona;
    }

    public static void setPersona(Persona persona) {
        ExcepcionPersona.persona = persona;
    }

    
    
    
}
