/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface CategoriaDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de una nueva categoria
     * @param categoria 
     */
    public Boolean insertarCategoria(Categoria categoria);
    /**
     *Metodo que se encarga de la
     * actualizacion de una nueva Categoria
     * @param categoria 
     */
    public Boolean actualizarCategoria(Categoria categoria);
    /**
     *Metodo que se encarga de la
     * eliminacion de una Categoria
     * @param categoria 
     */
    public boolean eliminarCategoria(Categoria categoria);
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
    
    /**
     *Metodo que se encarga de
     * listar categorias x nombre
     * o por id
     * @return lista de ubicaciones
     */
    public List<Categoria> buscartodasCategoriasxNombrexId(Integer idCategori,String nombreCategoria);
    
    
}
