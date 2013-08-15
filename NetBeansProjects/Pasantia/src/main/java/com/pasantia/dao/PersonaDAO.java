/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Persona;
import java.util.List;

/**
 *
 * @author david
 */
public interface PersonaDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de un nuev persona
     * @param persona 
     */
    public void insertarPersona(Persona persona);
    /**
     *Metodo que se encarga de la
     * actualizacion de un nueva persona
     * @param persona 
     */
    public void actualizarPersona(Persona persona);
    /**
     *Metodo que se encarga de 
     * eliminar una persona
     * @param persona
     */
    public boolean eliminarPersona(Persona persona);
    /**
     *Metodo que se encarga de buscar
     * una persona por su id
     * @param id
     * @return persona
     */
    public Persona buscarPersonaporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar las personas
     * @return lista de usuarios
     */
    public List<Persona> buscartodosPersona();
    
}
