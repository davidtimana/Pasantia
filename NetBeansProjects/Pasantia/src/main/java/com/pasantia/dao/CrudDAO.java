/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

/**
 *
 * @author David Orlando Timaná
 */

public interface CrudDAO<T> extends ICrud<T>{
    
    	/**
	 * Confirma la transacci&oacute;n en la base de datos
	 */
	public void commitTransaccion();
    
}
