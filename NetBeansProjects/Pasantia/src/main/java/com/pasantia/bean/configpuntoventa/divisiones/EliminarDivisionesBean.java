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
import org.primefaces.component.dialog.Dialog;

/**
 *
 * @author David Timaná
 */
@Named(value = "eliminarDivisionesPriBean")
@ApplicationScoped
public class EliminarDivisionesBean implements Serializable{

    private Dialog dlgeliminarDivisiones;
    private Divisiones division;
    private DivisionesDAO divisionesDAO;
    private List<DivisionesUbicacion> listDivisiones;
    private DivisionesUbicacionDAO divisionesUbicacionDAO;

    public void cargarEliminadoDivision(Divisiones d) {
        listDivisiones = divisionesUbicacionDAO.buscarubicacionesxiddivision(d.getIdDivisiones());
        if (listDivisiones.isEmpty()) {
            division = d;
            dlgeliminarDivisiones.setVisible(true);
        } else {
            Utilidad.mensajeError("Eliminar Divisiones", "Division: " + d.getNombreDivision() + ". "
                    + "Tiene ubicaciones asociades transfiera esas ubicaciones a otra división antes de eliminar esta división");
        }

    }

    public void cancelarEliminar() {
        dlgeliminarDivisiones.setVisible(false);
    }

    public void eliminarDivision() {
        if (division != null) {
            if (divisionesDAO.eliminarDivisiones(division)) {
                Utilidad.mensajeInfo("Eliminar Divisiones.", "Division: " + division.getNombreDivision() + ". Eliminada Correctamente.");
            } else {
                Utilidad.mensajeFatal("Eliminar Divisiones", "Division: " + division.getNombreDivision() + ". NO pudo ser eliminada.");
            }
        } else {
            Utilidad.mensajeFatal("Selección División", "...ERROR...Al seleccionar la divisiòn.");
        }
        division = null;
    }

    public EliminarDivisionesBean() {
        dlgeliminarDivisiones = new Dialog();
        division = new Divisiones();
        divisionesDAO = new DivisionesDAOImpl();
        divisionesUbicacionDAO = new DivisionesUbicacionDAOImpl();
        //Valores por defecto

        dlgeliminarDivisiones.setVisible(false);
    }

    public Dialog getDlgeliminarDivisiones() {
        return dlgeliminarDivisiones;
    }

    public void setDlgeliminarDivisiones(Dialog dlgeliminarDivisiones) {
        this.dlgeliminarDivisiones = dlgeliminarDivisiones;
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
}
