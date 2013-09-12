/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import java.util.List;
import com.pasantia.entidades.CiudadesColombia;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface CiudadesColombiaDAO {
    public List<CiudadesColombia> buscartodo();
    public CiudadesColombia buscarporid(Integer id);
}
