/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author david
 */
@Local
public interface RolDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de un nuevo Rol
     * @param rol
     */
    public Boolean insertarRol(Rol rol);
    /**
     *Metodo que se encarga de la
     * actualizacion de un un Rol
     * @param rol
     */
    public Boolean actualizarRol(Rol rol);
    /**
     *Metodo que se encarga de la
     * eliminacion de un Rol
     * @param rol
     */
    public Boolean eliminarRol(Rol rol);
    /**
     *Metodo que se encarga de buscar
     * un rol por su id
     * @param id
     * @return Rol
     */
    public Rol buscarRolPorId(Integer id);
    /**
     *Metodo que se encarga de
     * listar todos los roles
     * @return
     */
    public List<Rol> buscartodosRoles();
    
    /**
     *Metodo que se encarga de
     * listar roles x nombre
     * o por id
     * @return lista de ubicaciones
     */
    public List<Rol> buscartodasRolesxNombrexId(Integer idRol,String nombreRol);
    
}
