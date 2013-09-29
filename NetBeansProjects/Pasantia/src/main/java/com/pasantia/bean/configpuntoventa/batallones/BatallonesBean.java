/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.BatallonDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Timana
 */
@Named(value = "batallonesBean")
@SessionScoped
public class BatallonesBean implements Serializable{
    
    private static final long serialVersionUID = 5482033212307691504L;

    private List<Batallon> batallones;    
    private Boolean panel;
    
    @Inject
    EditarBatallonesBean batallonDAO;
    
    public BatallonesBean() {        
        panel=false;
        batallones=new ArrayList<Batallon>();
        //cargarBatallones();
    }
   
    
    
    
    
    
    
    public Integer totalBatallones(){
        return batallones.size();
    }
    
    public void cargarBatallones(){
        System.out.println("empezando a buscar por cargarBatallones en batallonesbean.");
        //batallones = batallonDAO.getBatallonesEncontrados();
    }

    public List<Batallon> getBatallones() {
        return batallones;
    }

    public void setBatallones(List<Batallon> batallones) {
        this.batallones = batallones;
    }

    public Boolean getPanel() {
        return panel;
    }

    public void setPanel(Boolean panel) {
        this.panel = panel;
    }
    
    
    
    
    
    
}
