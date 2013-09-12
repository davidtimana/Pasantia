/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Sexo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Timana
 */

@Local
public interface SexoDAO {
    
    public List<Sexo> buscarTodosSexo();
    
}
