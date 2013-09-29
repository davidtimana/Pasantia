/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.dao.impl.DivisionesDAOImpl;
import com.pasantia.dao.impl.DivisionesUbicacionDAOImpl;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.primefaces.component.dialog.Dialog;

/**
 *
 * @author David Timaná
 */
@Named(value = "eliminarDivisionesPriBean")
@SessionScoped
public class EliminarDivisionesBean implements Serializable{
    
    private static final long serialVersionUID = -6335337966813337344L;

    private Divisiones division;    
    private List<DivisionesUbicacion> listDivisiones;
    private Boolean abrirEliminarDivision;
    
    @Inject
    DivisionesUbicacionDAO divisionesUbicacionDAO;
    @Inject
    DivisionesDAO divisionesDAO;
    @Inject
    DivisionesBean divisionesBean;
    
    
    

    public void cargarEliminadoDivision(Divisiones d) {
        listDivisiones = divisionesUbicacionDAO.buscarubicacionesxiddivision(d.getIdDivisiones());
        if (listDivisiones.isEmpty()) {
            division = d;            
            abrirEliminarDivision=true;
        } else {
            abrirEliminarDivision=false;
            Utilidad.mensajeError("Eliminar Divisiones", "Division: " + d.getNombreDivision() + ". "
                    + "Tiene ubicaciones asociades transfiera esas ubicaciones a otra división antes de eliminar esta división");
        }
        Utilidad.actualizarElemento("dlgeliminarDivision");;

    }

    public void cancelarEliminar() {
        abrirEliminarDivision=false;
        division=new Divisiones();
        Utilidad.actualizarElemento("dlgeliminarDivision");
    }

    public void eliminarDivision() {
       if(divisionesDAO.eliminarDivisiones(division)){
            Utilidad.mensajeInfo("SICOVI", "División Eliminada Exitosamente.");
            divisionesBean.cargarDivisiones();
            Utilidad.actualizarElemento("tbldivisiones");
        }else{
            Utilidad.mensajeFatal("SICOVI", "Error al eliminar la división seleccionada.");
        }     
        cancelarEliminar();
    }

    public EliminarDivisionesBean() {
        
        division = new Divisiones();
        abrirEliminarDivision=false;

        //Valores por defecto

        
    }

    

    public Divisiones getDivision() {
        return division;
    }

    public void setDivision(Divisiones division) {
        this.division = division;
    }

    public List<DivisionesUbicacion> getListDivisiones() {
        return listDivisiones;
    }

    public void setListDivisiones(List<DivisionesUbicacion> listDivisiones) {
        this.listDivisiones = listDivisiones;
    }

    public Boolean getAbrirEliminarDivision() {
        return abrirEliminarDivision;
    }

    public void setAbrirEliminarDivision(Boolean abrirEliminarDivision) {
        this.abrirEliminarDivision = abrirEliminarDivision;
    }
    
    
}
