/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Usuario;
import java.util.List;

/**
 *
 * @author david
 */
public interface UsuarioDAO {
    
      /**
     *Metodo que se encarga de la
     * insercion de un nuevo usuario
     * @param usuario 
     */
    public void insertarUsuario(Usuario usuario);
    /**
     *Metodo que se encarga de la
     * actualizacion de un nuevo usuario
     * @param usuario 
     */
    public void actualizarUsuario(Usuario usuario);
    /**
     *Metodo que se encarga de 
     * eliminar un usuario
     * @param usuario
     */
    public boolean eliminarUsuario(Usuario usuario);
    /**
     *Metodo que se encarga de buscar
     * un Usuario por su id
     * @param id
     * @return Usuario
     */
    public Usuario buscarUsuarioporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar los usuarios
     * @return lista de usuarios
     */
    public List<Usuario> buscartodosUsuario();
}
