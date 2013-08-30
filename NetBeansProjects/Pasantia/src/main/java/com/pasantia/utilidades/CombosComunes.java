/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import com.pasantia.dao.TipoIdentificacionDAO;
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
@Stateless
public class CombosComunes {

    private List<TipoIdentificacion> tipoIdentificaciones;
    private List<SelectItem> comboTipoIdentificacion;
    @Inject
    TipoIdentificacionDAO tipoIdentificacionDAO;

    public List<SelectItem> cargarComboTipoIdentificacion() {
        cargarComboTipoIdentificacion();
        comboTipoIdentificacion = new ArrayList<SelectItem>();
        for (int i = 0; i < tipoIdentificaciones.size(); i++) {
            comboTipoIdentificacion.add(new SelectItem(tipoIdentificaciones.get(i).getIdTipoIdentificacion(), tipoIdentificaciones.get(i).getNombreTipoIdentificacion()));
        }
        return comboTipoIdentificacion;
    }

    private void cargarTipoIdentificaciones() {
        tipoIdentificaciones = tipoIdentificacionDAO.buscarTipoIdentificaciones();
    }

    public List<TipoIdentificacion> getTipoIdentificaciones() {
        return tipoIdentificaciones;
    }

    public void setTipoIdentificaciones(List<TipoIdentificacion> tipoIdentificaciones) {
        this.tipoIdentificaciones = tipoIdentificaciones;
    }

    public List<SelectItem> getComboTipoIdentificacion() {
        return comboTipoIdentificacion;
    }

    public void setComboTipoIdentificacion(List<SelectItem> comboTipoIdentificacion) {
        this.comboTipoIdentificacion = comboTipoIdentificacion;
    }
}
