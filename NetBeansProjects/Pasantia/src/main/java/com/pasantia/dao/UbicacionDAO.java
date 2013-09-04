/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Ubicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface UbicacionDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de una nueva ubicacion
     * @param ubicacion 
     * @param rol
     */
    public Boolean insertarUbicacion(Ubicacion ubicacion);
    /**
     *Metodo que se encarga de la
     * actualizacion de una nueva ubicacion
     * @param ubicacion 
     * @param rol
     */
    public Boolean actualizarUbicacion(Ubicacion ubicacion);
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
    
     /**
     *Metodo que se encarga de
     * listar ubicaciones x descripcion
     * @return lista de ubicaciones
     */
    public List<Ubicacion> buscartodasUbicacionesxNombre(String nombre);
    
    /**
     *Metodo que se encarga de
     * listar ubicaciones x nombre
     * o por id
     * @return lista de ubicaciones
     */
    public List<Ubicacion> buscartodasUbicacionesxNombrexId(Integer idUbicacion,String nombreUbicacion);
    
}
