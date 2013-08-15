/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Divisiones;
import java.util.List;

/**
 *
 * @author root
 */
public interface DivisionesDAO {
    /**
     *Metodo que se encarga de la
     * insercion de un nueva division
     * @param division 
     */
    public boolean insertarDivisiones(Divisiones divisiones);
    /**
     *Metodo que se encarga de la
     * actualizacion de un nueva division
     * @param division 
     */
    public boolean actualizarDivisiones(Divisiones divisiones);
    /**
     *Metodo que se encarga de 
     * eliminar una division
     * @param division
     */
    public boolean eliminarDivisiones(Divisiones divisiones);
    /**
     *Metodo que se encarga de buscar
     * un Casino por su id
     * @param id
     * @return division
     */
    public Divisiones buscarDivisionesporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar las division
     * @return lista de divisiones
     */
    public List<Divisiones> buscartodasDivisiones();
    
    /**
     *Metodo que se encarga de buscar
     * una division por su nombre
     * @param nombre
     * @return division
     */
    public Divisiones buscarDivisionesporNombre(String nombre);
    
    /**
     *Metodo que se encarga de buscar
     * una ultima division ingresada
     
     * @return division
     */
    public Integer buscarUltimaIngresada();
}
