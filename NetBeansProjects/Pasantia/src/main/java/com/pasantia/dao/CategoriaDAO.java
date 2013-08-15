/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Categoria;
import java.util.List;

/**
 *
 * @author root
 */
public interface CategoriaDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de una nueva categoria
     * @param categoria 
     */
    public void insertarUbicacion(Categoria categoria);
    /**
     *Metodo que se encarga de la
     * actualizacion de una nueva Categoria
     * @param categoria 
     */
    public void actualizarUbicacion(Categoria categoria);
    /**
     *Metodo que se encarga de la
     * eliminacion de una Categoria
     * @param categoria 
     */
    public boolean eliminarUbicacion(Categoria categoria);
    /**
     *Metodo que se encarga de buscar
     * una Categoria por su id
     * @param id
     * @return Categoria
     */
    public Categoria buscarCategoriaporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar todas las Categorias
     * @return lista de categorias
     */
    public List<Categoria> buscartodasCategorias();
    
    
}
