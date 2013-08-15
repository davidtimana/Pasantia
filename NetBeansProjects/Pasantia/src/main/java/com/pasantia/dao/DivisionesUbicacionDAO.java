/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import java.util.List;

/**
 *
 * @author david
 */
public interface DivisionesUbicacionDAO {

    /**
     * Metodo que se encarga de guardar los departamentos a los cuales pertenece
     *
     * @param divisionesUbicacion
     */
    public boolean insertarDivisionesUbicacion(DivisionesUbicacion divisionesUbicacion);

    /**
     * Metodo que se encarga de eliminar los departamentos a los cuales
     * pertenece
     *
     * @param divisionesUbicacion
     * @return
     */
    public boolean eliminarDivisionesUbicacion(DivisionesUbicacion divisionesUbicacion);

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion para las
     * divisiones
     *
     * @return
     */
    public List<DivisionesUbicacion> buscarubicaciones();

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion para las
     * divisiones
     *
     * @param id
     * @return List<DivisionesUbicacion>
     */
    public List<DivisionesUbicacion> buscarubicacionesxiddivision(Integer id);

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion por
     * departamentos
     *
     * @param id
     */
    public List<DivisionesUbicacion> buscarubicacionesxidDepartamento(Integer id);

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion por el id
     * de la division de la ubicacion
     *
     * @param id
     */
    public DivisionesUbicacion buscarubicacionesxid(Integer id);

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion por el id
     * de la division de la ubicacion
     *
     * @param id
     */
    public List<Departamento> buscarUbicacionesxIdDivision(Integer id);

    /**
     * Metodo que se encarga de buscar las asignaciones de ubicacion por el id
     * de la division de la ubicacion y el departamento
     *
     * @param idDivision
     * @param idDepartamento
     */
    public DivisionesUbicacion buscarUbicacionesxIdDivisionYIdDepartamento(Integer idDivision, Integer idDepartamento);
    
    /**
     * Metodo que se encarga de eliminar las asignaciones de ubicacion por el id
     * de la division de la ubicacion
     *
     * @param idDivision
     
     */
    public boolean eliminarDivisionesUbicacionxIdDivision(Integer idDivision);

    public boolean eliminarDivisionesUbicacion(Integer idDivisiones);
    
    /**
     * Metodo que se encarga de listar
     * las divisiones que estan asociadas
     *     
     */
    public List<Divisiones> buscarDivisionesAsociadas();
    
    
}
