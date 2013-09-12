/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.BatallonDAO;
import com.pasantia.dao.impl.BatallonDAOImpl;
import com.pasantia.entidades.Batallon;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Timana
 */
@Named(value = "editarBatallonesBean")
@SessionScoped
public class EditarBatallonesBean implements Serializable{

    
    
    
    @Inject
    BatallonDAO batallonDAO;
    
     
    
    public void editarBatallon(Batallon batallon){   
        
        if(batallonDAO.actualizarBatallon(batallon)){
            Utilidad.mensajeInfo("SICOVI", "Batallon: "+batallon.getNombreBatallon()+". Actualizado Correctamente");
            
        }else{
            Utilidad.mensajeFatal("SICOVI", "Batallon: "+batallon.getNombreBatallon()+". No pudo ser actualizado");
        }
        
    }    
    
    
    public EditarBatallonesBean() {
        
    }

    
    
    
    
    
    
    
    
    
    
    
}