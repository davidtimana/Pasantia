/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Cargo;
import java.util.List;

/**
 *
 * @author root
 */
public interface CargoDAO {
    /**
     *Metodo que se encarga de la
     * insercion de un nuevo cargo
     * @param cargo 
     */
    public void insertarCargo(Cargo cargo);
    /**
     *Metodo que se encarga de la
     * actualizacion de un nuevo cargo
     * @param cargo 
     */
    public void actualizarCargo(Cargo cargo);
    /**
     *Metodo que se encarga de 
     * eliminar un cargo
     * @param cargo
     */
    public boolean eliminarCargo(Cargo cargo);
    /**
     *Metodo que se encarga de buscar
     * una Categoria por su id
     * @param id
     * @return Cargo
     */
    public Cargo buscarCargoporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar los cargos
     * @return lista de cargos
     */
    public List<Cargo> buscartodosCargos();
    
}
