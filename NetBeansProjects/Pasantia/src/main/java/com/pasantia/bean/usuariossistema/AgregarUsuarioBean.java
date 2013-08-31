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
public class AgregarUsuarioBean extends CombosComunes{

    
    private Integer tipoIdentiSelect,sexoSelect;
    
      
    
    
    public AgregarUsuarioBean() {
        
    }

    public Integer getTipoIdentiSelect() {
        return tipoIdentiSelect;
    }

    public void setTipoIdentiSelect(Integer tipoIdentiSelect) {
        this.tipoIdentiSelect = tipoIdentiSelect;
    }

    public Integer getSexoSelect() {
        return sexoSelect;
    }

    public void setSexoSelect(Integer sexoSelect) {
        this.sexoSelect = sexoSelect;
    }

   
    
    
    
    

    
   
    
    

}
