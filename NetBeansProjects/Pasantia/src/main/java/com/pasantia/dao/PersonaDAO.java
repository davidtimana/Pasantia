/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Timana
 */
@Local
public interface PersonaDAO {

    /**
     * Metodo que se encarga de la insercion de un nuev persona
     *
     * @param persona
     */
    public Boolean insertarPersona(Persona persona);

    /**
     * Metodo que se encarga de la actualizacion de un nueva persona
     *
     * @param persona
     */
    public void actualizarPersona(Persona persona);

    /**
     * Metodo que se encarga de eliminar una persona
     *
     * @param persona
     */
    public boolean eliminarPersona(Persona persona);

    /**
     * Metodo que se encarga de buscar una persona por su id
     *
     * @param id
     * @return persona
     */
    public Persona buscarPersonaporId(Integer id);

    /**
     * Metodo que se encarga de listar las personas
     *
     * @return lista de personas
     */
    public List<Persona> buscar();

    /**
     * Metodo que se encarga de listar las personas
     *
     * @return Persona Objeto persona encontrado
     * @author David Timana
     */
    public Persona buscarUltimoIngresado();

    /**
     * Metodo que se encarga de listar los comandantes que no han sido asignados
     * a ningun batallon.
     *
     * @return List<Persona>
     * lista de Objetos personas encontrados
     * @author David Timana
     */
    public List<Persona> buscarComandanteSinAsignar();

    /**
     * Metodo que se encarga de listar los comandantes que no han sido asignados
     * a ningun batallon.
     *
     * @return List<Persona>
     * lista de Objetos personas encontrados
     * @author David Timana
     */
    public List<Persona> buscarComandanteCasinoSinAsignar();

    /**
     * Función que se encarga de buscar una persona por cedula.
     *
     * @param cedula
     * @return Persona Objeto persona encontrado
     */
    public Persona buscarPersonaPorCedula(String cedula);

}
