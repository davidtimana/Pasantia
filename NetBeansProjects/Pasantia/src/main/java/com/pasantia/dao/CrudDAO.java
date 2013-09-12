/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import javax.ejb.Local;

/**
 *
 * @author David Orlando Timaná
 */

@Local
public interface CrudDAO<T> extends ICrud<T>{
    
    	/**
	 * Confirma la transacci&oacute;n en la base de datos
	 */
	public void commitTransaccion();
    
}
