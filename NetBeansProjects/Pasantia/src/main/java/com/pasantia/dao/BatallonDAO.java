/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Batallon;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
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
    
    /**
     *Metodo que se encarga de
     * actualizar los batallones
     * @param batallon 
     * @return verdadero o falso
     */
    public Boolean actualizarBatallon(Batallon batallon);
    
    /**
     *Metodo que se encarga de
     * eliminar los batallones
     * @param batallon 
     * @return verdadero o falso
     */
    public Boolean eliminarBatallon(Batallon batallon);
    
     /**
     *Metodo que se encarga de
     * listar los batallones por pais
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxPais(Integer id);
    
     /**
     *Metodo que se encarga de
     * listar los batallones por departamento
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxDepartamento(Integer id);
    
     /**
     *Metodo que se encarga de
     * listar los batallones por Ciudad
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxCiudad(Integer id);
    
     /**
     *Metodo que se encarga de
     * listar los batallones por Division
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxDivision(Integer id);
    
    /**
     *Metodo que se encarga de
     * listar los batallones por nombre
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxNombre(String nombre_batallon);
    
    /**
     *Metodo que se encarga de
     * listar los batallones por direccion
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxDireccion(String direccion);
    
    /**
     *Metodo que se encarga de
     * listar los batallones por barrio
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxBarrio(String barrio);
    
    /**
     *Metodo que se encarga de
     * listar los batallones por telefono1
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxTelefono1(String telefono1);
    
    /**
     *Metodo que se encarga de
     * listar los batallones por telefono2
     * @return lista de divisiones
     */
    public List<Batallon> buscarBatallonesxTelefono2(String telefono2);
    
}
