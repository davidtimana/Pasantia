/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.entidades.PrecioCompra;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Timana
 */

@Local
public interface PreciosCompraDAO {
    
    public List<PrecioCompra> buscarPreciosporProducto(Integer secproducto);     
    
}
