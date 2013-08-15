/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Casino;
import java.util.List;

/**
 *
 * @author root
 */
public interface CasinoDAO {
    
    /**
     *Metodo que se encarga de la
     * insercion de un nuevo casino
     * @param casino 
     */
    public void insertarCasino(Casino casino);
    /**
     *Metodo que se encarga de la
     * actualizacion de un nuevo casino
     * @param casino 
     */
    public void actualizarCasino(Casino casino);
    /**
     *Metodo que se encarga de 
     * eliminar un casino
     * @param casino
     */
    public boolean eliminarCasino(Casino casino);
    /**
     *Metodo que se encarga de buscar
     * un Casino por su id
     * @param id
     * @return Casino
     */
    public Casino buscarCasinoporId(Integer id);
    /**
     *Metodo que se encarga de
     * listar los casinos
     * @return lista de casinos
     */
    public List<Casino> buscartodosCasinos();
    
}
