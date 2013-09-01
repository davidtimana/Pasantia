/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.PaisDAO;
import com.pasantia.utilidades.CombosComunes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author David Orlando Timana
 */
@Named(value = "agregarUsuarioBean")
@SessionScoped
public class AgregarUsuarioBean implements Serializable{

    
    private Integer paisSeleccionado,tipoIdentificacionSeleccionada,sexoSeleccionado;
    private List<SelectItem> comboPaises;
    
    @Inject
    PaisDAO paisDAO;
    
    public void cargarPaises(){
        comboPaises=paisDAO.buscartodasPaisesCombo();
    }
    
    
    public AgregarUsuarioBean() {
        System.out.println("Inicianilizando el constructor por defecto");
        comboPaises=new ArrayList<SelectItem>();
        //cargarPaises();
    }

    
    public List<SelectItem> getComboPaises() {
        comboPaises=paisDAO.buscartodasPaisesCombo();
        return comboPaises;
    }

    public void setComboPaises(List<SelectItem> comboPaises) {
        this.comboPaises = comboPaises;
    }

    public Integer getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Integer paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    public Integer getTipoIdentificacionSeleccionada() {
        return tipoIdentificacionSeleccionada;
    }

    public void setTipoIdentificacionSeleccionada(Integer tipoIdentificacionSeleccionada) {
        this.tipoIdentificacionSeleccionada = tipoIdentificacionSeleccionada;
    }

    public Integer getSexoSeleccionado() {
        return sexoSeleccionado;
    }

    public void setSexoSeleccionado(Integer sexoSeleccionado) {
        this.sexoSeleccionado = sexoSeleccionado;
    }
    
    
    
    
    
    
    

   
    
    
    
    

    
   
    
    

}
