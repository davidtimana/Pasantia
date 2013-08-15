/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Batallon;
import java.util.List;

/**
 *
 * @author root
 */
public interface BatallonDAO {
    
        /**
     *Metodo que se encarga de
     * listar los batallones
     * @return lista de divisiones
     */
    public List<Batallon> buscartodosBatallones();
    
        /**
     *Metodo que se encarga de
     * guardar los batallones
     * @param batallon 
     * @return verdadero o falso
     */
    public Boolean insertarBatallon(Batallon batallon);
    
}
