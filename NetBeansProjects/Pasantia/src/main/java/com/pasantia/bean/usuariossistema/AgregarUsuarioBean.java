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
import javax.annotation.PostConstruct;
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
public class AgregarUsuarioBean extends CombosComunes implements Serializable{

    
    
    
    
    @PostConstruct
    public void Init(){
        System.out.println("***********Inicianilizando");        
        
    }
    
   
    
    
    public AgregarUsuarioBean() {
        
    }

    
    
        
    

}
