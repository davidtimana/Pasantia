/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "guardarCasino")
@SessionScoped
public class GuardarCasinoBean implements Serializable {
    
    private static final long serialVersionUID = 52003532345729327L;
    private static final Logger log = Logger.getLogger(GuardarCasinoBean.class.getName());
    
    @Inject
    CrudDAO<Casino> crudDAO;

    /**
     * Creates a new instance of GuardarCasino
     */
    
    public void guardar(Casino c,Boolean estaEditando){
        if(estaEditando){
            if(crudDAO.editar(c)){
                Utilidad.mensajeInfo("SICOVI", "Casino: "+c.getNombre()+". Actualizado correctamente.");
            }else{
                Utilidad.mensajeError("SICOVI", "Casino: "+c.getNombre()+". No pudo ser actualizado.");
            }
        }else{
            if(crudDAO.crear(c)){
                Utilidad.mensajeInfo("SICOVI", "Casino: "+c.getNombre()+". Guardado correctamente.");
            }else{
                Utilidad.mensajeError("SICOVI", "Casino: "+c.getNombre()+". No pudo ser guardado.");
            }
            
        }
    }
    
    public GuardarCasinoBean() {
    }
}
