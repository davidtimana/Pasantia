/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.dao.SexoDAO;
import com.pasantia.dao.TipoIdentificacionDAO;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.Pais;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 * Clase que implementa unos metodos para consultar y cargar combos comunes para
 * toda la aplicacion
 *
 * @author David Timana
 */
public class CombosComunes {

    //Atributos Combo Tipo Identificacion
    private List<TipoIdentificacion> tipoIdentificaciones;
    private List<SelectItem> comboTipoIdentificacion;
    //Atributos combo sexo
    private List<Sexo> sexos;
    private List<SelectItem> comboSexo;
    //Atributos Combo Pais
    private List<Pais> paises;
    private List<SelectItem> comboPais;
    //Atributos Combo Departamento
    private List<Departamento> departamentos;
    private List<SelectItem> comboDepartamento;
    //Atributos Combo Ciudad
    private List<Ciudad> ciudades;
    private List<SelectItem> comboCiudades;
    //Atributos Combo Divisiones asociadas
    private List<Divisiones> divisionesAsociadas;
    private List<SelectItem> comboDivisionesAsociadas;
    //Inyecciones
    @Inject
    TipoIdentificacionDAO tipoIdentificacionDAO;
    @Inject
    SexoDAO sexoDAO;
    @Inject
    PaisDAO paisDAO;
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    CiudadDAO ciudadDAO;
    @Inject
    DivisionesDAO divisionesDAO;

    /**
     * Metodo que se encarga de buscar y cargar un combo con Tipo
     * Identificaciones.
     *
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboTipoIdentificacion() {
        cargarTipoIdentificaciones();
        for (int i = 0; i < tipoIdentificaciones.size(); i++) {
            comboTipoIdentificacion.add(new SelectItem(tipoIdentificaciones.get(i).getIdTipoIdentificacion(), tipoIdentificaciones.get(i).getNombreTipoIdentificacion()));
        }
        return comboTipoIdentificacion;
    }

    private void cargarTipoIdentificaciones() {
        tipoIdentificaciones = tipoIdentificacionDAO.buscarTipoIdentificaciones();
    }

    /**
     * Metodo que se encarga de buscar y cargar un combo con Sexo
     *
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboSexo() {
        cargarSexos();
        for (int i = 0; i < sexos.size(); i++) {
            comboSexo.add(new SelectItem(sexos.get(i).getIdSexo(), sexos.get(i).getNombreSexo()));
        }
        return comboSexo;
    }

    private void cargarSexos() {
        sexos = sexoDAO.buscarTodosSexo();
    }

    /**
     * Metodo que se encarga de buscar y cargar un combo con paises
     *
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboPais() {
        cargarPaises();
        for (int i = 0; i < paises.size(); i++) {
            comboPais.add(new SelectItem(paises.get(i).getIdPais(), paises.get(i).getNombrePais()));
        }
        return comboPais;
    }

    private void cargarPaises() {
        paises = paisDAO.buscartodasPaises();
    }

    /**
     * Metodo que se encarga de buscar y cargar un combo con departamentos por
     * pa&icute;s.
     *
     * @param Integer idPais
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboDepartamento(Integer idPais) {
        cargarDepartamentos(idPais);
        for (int i = 0; i < departamentos.size(); i++) {
            comboDepartamento.add(new SelectItem(departamentos.get(i).getIdDepartamento(), departamentos.get(i).getNombreDepartamento()));
        }
        return comboDepartamento;
    }

    private void cargarDepartamentos(Integer idPais) {
        departamentos = departamentoDAO.buscarDepartamentoporIdPais(idPais);
    }

    /**
     * Metodo que se encarga de buscar y cargar un combo con ciudades por
     * departamento.
     *
     * @param Integer idDepartamento
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboCiudad(Integer idDepartamento) {
        cargaCiudades(idDepartamento);
        for (int i = 0; i < ciudades.size(); i++) {
            comboCiudades.add(new SelectItem(ciudades.get(i).getIdCiudad(), ciudades.get(i).getNombreCiudad()));
        }
        return comboCiudades;
    }

    private void cargaCiudades(Integer idDepartamento) {
        ciudades = ciudadDAO.buscarxidDpto(idDepartamento);
    }

    /**
     * Metodo que se encarga de buscar y cargar un combo con divisiones
     * asociadas.
     *
     * @return List<SelecItem>
     */
    public List<SelectItem> cargarComboDivisionesAsociadas() {
        cargaDivisionesAsociadas();
        for (int i = 0; i < divisionesAsociadas.size(); i++) {
            comboDivisionesAsociadas.add(new SelectItem(divisionesAsociadas.get(i).getIdDivisiones(), divisionesAsociadas.get(i).getNombreDivision()));
        }
        return comboDivisionesAsociadas;
    }

    private void cargaDivisionesAsociadas() {
        divisionesAsociadas = divisionesDAO.buscarDivisionesAsociadas();
    }

    //Constructor por Defecto
    public CombosComunes() {
        sexos = new ArrayList<Sexo>();
        tipoIdentificaciones = new ArrayList<TipoIdentificacion>();
        paises = new ArrayList<Pais>();
        departamentos = new ArrayList<Departamento>();
        ciudades = new ArrayList<Ciudad>();
        divisionesAsociadas = new ArrayList<Divisiones>();

        comboSexo = new ArrayList<SelectItem>();
        comboTipoIdentificacion = new ArrayList<SelectItem>();
        comboPais = new ArrayList<SelectItem>();
        comboDepartamento = new ArrayList<SelectItem>();
        comboCiudades = new ArrayList<SelectItem>();
        comboDivisionesAsociadas = new ArrayList<SelectItem>();

    }

    //Getter's y Setter's 
    public List<TipoIdentificacion> getTipoIdentificaciones() {
        return tipoIdentificaciones;
    }

    public void setTipoIdentificaciones(List<TipoIdentificacion> tipoIdentificaciones) {
        this.tipoIdentificaciones = tipoIdentificaciones;
    }

    public List<SelectItem> getComboTipoIdentificacion() {
        comboTipoIdentificacion = this.cargarComboTipoIdentificacion();
        return comboTipoIdentificacion;
    }

    public void setComboTipoIdentificacion(List<SelectItem> comboTipoIdentificacion) {
        this.comboTipoIdentificacion = comboTipoIdentificacion;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<SelectItem> getComboSexo() {
        comboSexo = this.cargarComboSexo();
        return comboSexo;
    }

    public void setComboSexo(List<SelectItem> comboSexo) {
        this.comboSexo = comboSexo;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<SelectItem> getComboPais() {
        return comboPais;
    }

    public void setComboPais(List<SelectItem> comboPais) {
        this.comboPais = comboPais;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<SelectItem> getComboDepartamento() {
        return comboDepartamento;
    }

    public void setComboDepartamento(List<SelectItem> comboDepartamento) {
        this.comboDepartamento = comboDepartamento;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<SelectItem> getComboCiudades() {
        return comboCiudades;
    }

    public void setComboCiudades(List<SelectItem> comboCiudades) {
        this.comboCiudades = comboCiudades;
    }

    public List<Divisiones> getDivisionesAsociadas() {
        return divisionesAsociadas;
    }

    public void setDivisionesAsociadas(List<Divisiones> divisionesAsociadas) {
        this.divisionesAsociadas = divisionesAsociadas;
    }

    public List<SelectItem> getComboDivisionesAsociadas() {
        return comboDivisionesAsociadas;
    }

    public void setComboDivisionesAsociadas(List<SelectItem> comboDivisionesAsociadas) {
        this.comboDivisionesAsociadas = comboDivisionesAsociadas;
    }
}
