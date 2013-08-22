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
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Timana
 */
@Named(value = "editarBatallonesBean")
@ApplicationScoped
public class EditarBatallonesBean implements Serializable{

    private List<Batallon> batallones,batallonesEncontrados;
    private BatallonDAO batallonDAO;
    private Batallon batallon;
    private Boolean abrirComfirmar;
    
    
    public void editarBatallon(Batallon batallon){
        System.out.println("Inicializando Edicion Batallones");
        if(batallonDAO.actualizarBatallon(batallon)){
            Utilidad.mensajeInfo("Actualizacion Batallones", "Batallon: "+batallon.getNombreBatallon()+". Actualizado Correctamente");
            cargarBatallones();
        }else{
            Utilidad.mensajeFatal("Actualizacion Batallones", "Batallon: "+batallon.getNombreBatallon()+". No pudo ser actualizado");
        }
    }
    
    public void cargarEliminarBatallon(Batallon b){        
        batallon=b;
        abrirComfirmar=true;

    }
    public void cancelarEliminado(){
        System.out.println("cancelando la eliminacion");
        abrirComfirmar=false;
        batallon = new Batallon();
    }
    public void eliminarBatallon(){        
        abrirComfirmar=false;
        if(batallonDAO.eliminarBatallon(batallon)){
            Utilidad.mensajeInfo("Eliminar Batallones", "Batallon: "+batallon.getNombreBatallon()+ ". Eliminado correctamente.");
            cargarBatallones();            
            RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
        }else{
            Utilidad.mensajeFatal("Eliminar Batallones", "Batallon: "+batallon.getNombreBatallon()+ ". No pudo ser eliminado.");
        }
        batallon = new Batallon();
    }
    
    public void cargarBatallones(){
        batallones = batallonDAO.buscartodosBatallones();
    }
    
    public Integer totalBatallones(){
        return batallones.size();
    }
    
    public EditarBatallonesBean() {
        batallonDAO = new BatallonDAOImpl();
        batallon = new Batallon();
        abrirComfirmar = false;
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

    public Batallon getBatallon() {
        return batallon;
    }

    public void setBatallon(Batallon batallon) {
        this.batallon = batallon;
    }

    public Boolean getAbrirComfirmar() {
        return abrirComfirmar;
    }

    public void setAbrirComfirmar(Boolean abrirComfirmar) {
        this.abrirComfirmar = abrirComfirmar;
    }
    
    
    
    
    
    
    
    
    
    
}