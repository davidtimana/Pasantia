/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.TipoIdentificacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Timana
 */

@Local
public interface TipoIdentificacionDAO {
    
    public List<TipoIdentificacion> buscarTipoIdentificaciones();
        
    
    
}
