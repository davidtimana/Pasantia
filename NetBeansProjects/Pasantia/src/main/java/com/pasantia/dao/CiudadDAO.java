/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Ciudad;
import java.util.List;

/**
 *
 * @author root
 */
public interface CiudadDAO {
    
    public List<Ciudad> cargarCoordenadas();
    public void actualizarCiudad(Ciudad ciudad);
    public Ciudad buscarxid(Integer id);
    public List<Ciudad> buscarxidDpto(Integer id);
    
    
}
