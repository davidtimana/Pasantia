/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.BatallonDAO;
import com.pasantia.dao.impl.BatallonDAOImpl;
import com.pasantia.entidades.Batallon;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author root
 */
@Named(value = "batallonesBean")
@ApplicationScoped
public class BatallonesBean implements Serializable{

    private List<Batallon> batallones;
    private BatallonDAO batallonDAO;
    
    public BatallonesBean() {
        batallonDAO = new BatallonDAOImpl();
        cargarBatallones();
    }
    
    public Integer totalBatallones(){
        return batallones.size();
    }
    
    public void cargarBatallones(){
        batallones = batallonDAO.buscartodosBatallones();
    }

    public List<Batallon> getBatallones() {
        return batallones;
    }

    public void setBatallones(List<Batallon> batallones) {
        this.batallones = batallones;
    }
    
    
    
    
}
