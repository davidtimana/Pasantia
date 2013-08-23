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
    
    
    public void buscarBatallonesxNombre(String nombre){
        System.out.println("empezando a buscar por buscarBatallonesxNombre en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxNombre(nombre);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxDireccion(String direccion){
        System.out.println("empezando a buscar por buscarBatallonesxDireccion en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxDireccion(direccion);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxBarrio(String barrio){
        System.out.println("empezando a buscar por buscarBatallonesxBarrio en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxBarrio(barrio);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxTelefono1(String telefono1){
        System.out.println("empezando a buscar por buscarBatallonesxTelefono1 en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxTelefono1(telefono1);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxTelefono2(String telefono2){
        System.out.println("empezando a buscar por buscarBatallonesxTelefono2 en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxTelefono2(telefono2);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxPais(Integer pais){
        System.out.println("empezando a buscar por buscarBatallonesxPais en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxPais(pais);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxDepartamento(Integer departamento){
        System.out.println("empezando a buscar por buscarBatallonesxDepartamento en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxDepartamento(departamento);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
    public void buscarBatallonesxCiudad(Integer ciudad){
        System.out.println("empezando a buscar por buscarBatallonesxCiudad en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxCiudad(ciudad);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
    
     public void buscarBatallonesxDivision(Integer division){
        System.out.println("empezando a buscar por buscarBatallonesxDivision en batallonesbean.");
        batallones=batallonDAO.buscarBatallonesxDivision(division);                
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
    }
     
     public void buscarBatallones(){
         cargarBatallones();
         RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
     }
    
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