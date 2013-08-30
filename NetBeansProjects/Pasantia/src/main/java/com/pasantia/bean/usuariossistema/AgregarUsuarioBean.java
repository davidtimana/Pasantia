/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.utilidades.CombosComunes;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author jbuitron
 */
@Named(value = "agregarUsuarioBean")
@ApplicationScoped
public class AgregarUsuarioBean {

    private List<SelectItem> comboTipoIdentificacion;
    private Integer tipoIdentiSelect;
    
    @Inject
    CombosComunes combosComunes;
    
    public void cargarComboTipoIdentificacion(){
        //comboTipoIdentificacion = combosComunes.cargarComboTipoIdentificacion();
    }
    
    public AgregarUsuarioBean() {
         comboTipoIdentificacion=new ArrayList<SelectItem>();
        cargarComboTipoIdentificacion();
    }

    public List<SelectItem> getComboTipoIdentificacion() {
        return comboTipoIdentificacion;
    }

    public void setComboTipoIdentificacion(List<SelectItem> comboTipoIdentificacion) {
        this.comboTipoIdentificacion = comboTipoIdentificacion;
    }

    public Integer getTipoIdentiSelect() {
        return tipoIdentiSelect;
    }

    public void setTipoIdentiSelect(Integer tipoIdentiSelect) {
        this.tipoIdentiSelect = tipoIdentiSelect;
    }
    
}
