/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import com.pasantia.dao.SexoDAO;
import com.pasantia.dao.TipoIdentificacionDAO;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    
    
    //Inyecciones
    @Inject
    TipoIdentificacionDAO tipoIdentificacionDAO;
    @Inject
    SexoDAO sexoDAO;
    

    

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
    
    
    
    
    

    
    //Constructor por Defecto
    public CombosComunes() {
        sexos = new ArrayList<Sexo>();
        tipoIdentificaciones = new ArrayList<TipoIdentificacion>();
        
        
        
        comboSexo= new ArrayList<SelectItem>();
        comboTipoIdentificacion = new ArrayList<SelectItem>();
        
        
        
        
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

    
    
    
    
    
    
    
    
    
    
    
    
    
}
