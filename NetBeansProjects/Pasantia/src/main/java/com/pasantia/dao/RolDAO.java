/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Rol;
import java.util.List;

/**
 *
 * @author david
 */
public interface RolDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de un nuevo Rol
     * @param rol
     */
    public void insertarRol(Rol rol);
    /**
     *Metodo que se encarga de la
     * actualizacion de un un Rol
     * @param rol
     */
    public void actualizarRol(Rol rol);
    /**
     *Metodo que se encarga de la
     * eliminacion de un Rol
     * @param rol
     */
    public void eliminarRol(Rol rol);
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
    
}
