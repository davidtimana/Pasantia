/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Ubicacion;
import java.util.List;

/**
 *
 * @author david
 */
public interface UbicacionDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de una nueva ubicacion
     * @param ubicacion 
     * @param rol
     */
    public void insertarUbicacion(Ubicacion ubicacion);
    /**
     *Metodo que se encarga de la
     * actualizacion de una nueva ubicacion
     * @param ubicacion 
     * @param rol
     */
    public void actualizarUbicacion(Ubicacion ubicacion);
    /**
     *Metodo que se encarga de la
     * eliminacion de una ubicacion
     * @param ubicacion 
     * @param rol
     */
    public boolean eliminarUbicacion(Ubicacion ubicacion);
    /**
     *Metodo que se encarga de buscar
     * una ubicacion por su id
     * @param id
     * @return Rol
     */
    public Ubicacion buscarUbicacionporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar todas las ubicaciones
     * @return lista de ubicaciones
     */
    public List<Ubicacion> buscartodasUbicaciones();
    
}
