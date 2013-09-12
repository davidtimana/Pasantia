/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Pais;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author root
 */
@Local
public interface PaisDAO {
    
    /**
     *Metodo que se encarga de
     * listar los paises
     * @return lista de paises
     */
    public List<Pais> buscartodasPaises();
    
    /**
     *Metodo que se encarga de
     * buscar un pais por su id
     * @return lista de paises
     */
    public Pais buscarPaisxId(Integer id);
    
        /**
     *Metodo que se encarga de
     * listar los paises en combo
     * @return lista de paises
     */
    public List<SelectItem> buscartodasPaisesCombo();
    
    
}
