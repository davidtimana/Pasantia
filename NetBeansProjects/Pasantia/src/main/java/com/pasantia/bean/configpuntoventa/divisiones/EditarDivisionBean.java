/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DivisionesDAO;
import com.pasantia.entidades.Divisiones;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "editarDivisionBean")
@SessionScoped
public class EditarDivisionBean implements Serializable {

    private Divisiones division;    
    private Boolean abrirEdicionDivision;
    private String estiloError;
    
    @Inject
    DivisionesDAO divisionesDAO;
    @Inject
    DivisionesBean divisionesBean;
    
    
    //******************Fin Atributos de la clase**************************

    //****************Inicio Metodos de la clase***************************    
    public void cargarEdicion(Divisiones d){
        
        division=d;
        abrirEdicionDivision=true;
        System.out.println("llegando a cargar edicion con divison--> "+d.getNombreDivision()+" y variable--> "+abrirEdicionDivision);
        Utilidad.actualizarElemento("dlgEditardivisiones");
        
    }
    
    public void habilitarEdicion(){
        
    }
    
    public void cancelarEdicion(){
        estiloError="";
        abrirEdicionDivision=false;
        Utilidad.actualizarElemento("txtnomDivEdit"); 
        Utilidad.actualizarElemento("dlgEditardivisiones");
        
        
    }
    
    public void editarDivision(){        
        
        if (division.getNombreDivision().equals("")) {
            Utilidad.mensajeFatal("SICOVI", "Error al actualizar la división nombre requerido.");
            estiloError= Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnomDivEdit"); 

        } else {
            if (divisionesDAO.actualizarDivisiones(division)) {
                Utilidad.mensajeInfo("SICOVI", "División Actualizada Exitosamente");
                divisionesBean.cargarDivisiones();
                Utilidad.actualizarElemento("tbldivisiones");
                abrirEdicionDivision = false;
                Utilidad.actualizarElemento("dlgEditardivisiones");
            } else {
                Utilidad.mensajeFatal("SICOVI", "Error Al Actualizar El Rol");
            }
            abrirEdicionDivision = false;
            Utilidad.actualizarElemento("dlgEditardivisiones");
        }
        division = new Divisiones();
        
        
        
        
    }
    //*****************Fin Metodos de la clase***************************    
    
    //**********************Getters and setters****************************

      
    
    
    public Divisiones getDivision() {
        return division;
    }

    public void setDivision(Divisiones division) {
        this.division = division;
    }

    public Boolean getAbrirEdicionDivision() {
        return abrirEdicionDivision;
    }

    public void setAbrirEdicionDivision(Boolean abrirEdicionDivision) {
        this.abrirEdicionDivision = abrirEdicionDivision;
    }

    public String getEstiloError() {
        return estiloError;
    }

    public void setEstiloError(String estiloError) {
        this.estiloError = estiloError;
    }

    
    
    

    
    //*********************Constructor de la clase************************
    public EditarDivisionBean() {
        abrirEdicionDivision=false;
        division = new Divisiones(); 
        estiloError="";
    }
}
