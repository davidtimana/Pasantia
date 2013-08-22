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
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author root
 */
@Named(value = "editarBatallonesBean")
@ApplicationScoped
public class EditarBatallonesBean implements Serializable{

    private List<Batallon> batallones,batallonesEncontrados;
    private BatallonDAO batallonDAO;
    
    public void editarBatallon(Batallon batallon){
        System.out.println("probando-------------------"+batallon.getNombreBatallon());
        //System.out.println("El batallon a editar es el siguiente---> "+batallon.getNombreBatallon());
    }
    
    public void cargarBatallones(){
        batallones = batallonDAO.buscartodosBatallones();
    }
    
    public Integer totalBatallones(){
        return batallones.size();
    }
    
    public EditarBatallonesBean() {
        batallonDAO = new BatallonDAOImpl();
        cargarBatallones();
    }

    public List<Batallon> getBatallones() {
        return batallones;
    }

    public void setBatallones(List<Batallon> batallones) {
        this.batallones = batallones;
    }

    public List<Batallon> getBatallonesEncontrados() {
        return batallonesEncontrados;
    }

    public void setBatallonesEncontrados(List<Batallon> batallonesEncontrados) {
        this.batallonesEncontrados = batallonesEncontrados;
    }
    
    
    
    
}