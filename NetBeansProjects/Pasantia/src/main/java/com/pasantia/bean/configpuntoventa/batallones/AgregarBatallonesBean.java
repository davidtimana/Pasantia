/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.*;
import com.pasantia.dao.impl.*;
import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.Pais;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author David Orlando Timanà
 */
@Named(value = "agregarBatallonesBean")
@SessionScoped
public class AgregarBatallonesBean implements Serializable{   
 
    
    @Inject 
    BatallonDAO batallonDAO;  
    

    
    public void guardarBatallon(Batallon batallon) {

        if (batallonDAO.insertarBatallon(batallon)) {            
            Utilidad.mensajeInfo("SICOVI", "Batallon: " + batallon.getNombreBatallon() + ". Guardado Correctamente.");
        } else {
            Utilidad.mensajeError("SICOVI", "Batallon: " + batallon.getNombreBatallon() + ". No se pudo Guardar.");
        }

    }

    public AgregarBatallonesBean() {
    }
    
    
    
    
    
    
    
    
    
   
   
    

   

    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
