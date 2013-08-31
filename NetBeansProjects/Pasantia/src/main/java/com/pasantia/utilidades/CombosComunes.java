/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.dao.SexoDAO;
import com.pasantia.dao.TipoIdentificacionDAO;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Pais;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
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
    
    //Atributos combo Pais
    private List<Pais> paises;
    private List<SelectItem> comboPaises;
    private Integer paisSeleccinado;
    
    //Atributos combo Departamento
    private List<Departamento> departamentos;
    private List<SelectItem> comboDepartamentos;
    private Integer departamentoSeleccionado;
    
    //Atributos combo Ciudades
    private List<Ciudad> ciudades;
    private List<SelectItem> comboCiudades;
    
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

    

    //Metodos para cargar el combo Tipo IDentificacion
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
    
    //Metodos para cargar el combo sexo
    public List<SelectItem> cargarComboSexo(){
        cargarSexos();        
        for (int i = 0; i < sexos.size(); i++) {
            comboSexo.add(new SelectItem(sexos.get(i).getIdSexo(), sexos.get(i).getNombreSexo()));
        }
        return comboSexo;
    }
    private void cargarSexos(){
        sexos = sexoDAO.buscarTodosSexo();
    }
    
    //Metodos para cargar el combo Pais
    public List<SelectItem> cargarComboPais(){
         cargarPaises();
        for (int i = 0; i < paises.size(); i++) {
            comboPaises.add(new SelectItem(paises.get(i).getIdPais(),paises.get(i).getNombrePais()));
        }
        return comboPaises;
    }
    private void cargarPaises(){
        paises = paisDAO.buscartodasPaises();
    }
    
    //Metodos para cargar el combo Departamento
    public void cargarComboDepartamentoxPais(){
        
        System.out.println("*******************Llegando con el pais seleccionado-->"+paisSeleccinado);        
        if (paisSeleccinado!=null) {
            
            comboDepartamentos=null;
            cargarDepartamentos(paisSeleccinado);
            for (int i = 0; i < departamentos.size(); i++) {
                comboDepartamentos.add(new SelectItem(departamentos.get(i).getIdDepartamento(), departamentos.get(i).getNombreDepartamento()));
            }
        
        }else{
            Utilidad.mensajePeligro("Carga De Departamentos.", "Por favor Seleccione un Pais.");
        }
         
        
    }
    private void cargarDepartamentos(Integer idPais){
        departamentos = departamentoDAO.buscarDepartamentoporIdPais(idPais);
    }

    
    
    

    
    //Constructor por Defecto
    public CombosComunes() {
        sexos = new ArrayList<Sexo>();
        tipoIdentificaciones = new ArrayList<TipoIdentificacion>();
        paises = new ArrayList<Pais>();
        departamentos = new ArrayList<Departamento>();
        ciudades = new ArrayList<Ciudad>();
        
        
        comboSexo= new ArrayList<SelectItem>();
        comboTipoIdentificacion = new ArrayList<SelectItem>();
        comboPaises = new ArrayList<SelectItem>();
        comboDepartamentos = new ArrayList<SelectItem>();
        comboCiudades = new ArrayList<SelectItem>();
        
        
        
    }    
    
    //Getter's y Setter's 
    public List<TipoIdentificacion> getTipoIdentificaciones() {
        return tipoIdentificaciones;
    }

    public void setTipoIdentificaciones(List<TipoIdentificacion> tipoIdentificaciones) {
        this.tipoIdentificaciones = tipoIdentificaciones;
    }

    public List<SelectItem> getComboTipoIdentificacion() {
        comboTipoIdentificacion=this.cargarComboTipoIdentificacion();
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
        comboSexo=this.cargarComboSexo();
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

    public List<SelectItem> getComboPaises() {
        comboPaises = cargarComboPais();
        return comboPaises;
    }

    public void setComboPaises(List<SelectItem> comboPaises) {
        this.comboPaises = comboPaises;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<SelectItem> getComboDepartamentos() {        
        return comboDepartamentos;
    }

    public void setComboDepartamentos(List<SelectItem> comboDepartamentos) {
        this.comboDepartamentos = comboDepartamentos;
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

    public Integer getPaisSeleccinado() {
        return paisSeleccinado;
    }

    public void setPaisSeleccinado(Integer paisSeleccinado) {
        this.paisSeleccinado = paisSeleccinado;
    }

    public Integer getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Integer departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }
    
    
    
    
    
    
    
    
}
