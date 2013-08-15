/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.impl.DivisionesDAOImpl;
import com.pasantia.entidades.Divisiones;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author root
 */
@Named(value = "editarDivisionesBean")
@ApplicationScoped
public class editarDivisionesBean implements Serializable{
    
    //*****************Inicio Atributos de la clase************************
    private Dialog dlgeditarDivisiones;
    private Divisiones division;
    private String nombreDivision;
    private Integer idDivision;
    private InputText txtnombreDivision;
    private DivisionesDAO divisionesDAO;   
    private CommandButton btneditar,btnguardar;    
    
    //******************Fin Atributos de la clase**************************

    //****************Inicio Metodos de la clase***************************    
    public void cargarEdicion(Integer idDivision){        
        dlgeditarDivisiones.setVisible(true);
        division = divisionesDAO.buscarDivisionesporId(idDivision);                               
        nombreDivision=division.getNombreDivision();        
    }
    
    public void habilitarEdicion(){
        txtnombreDivision.setReadonly(false);  
        btneditar.setStyle("display:none");
        btnguardar.setStyle("display:block");         
    }
    
    public void cancelarEdicion(){        
        btneditar.setStyle("display:block");
        btnguardar.setStyle("display:none");
        txtnombreDivision.setReadonly(true);    
        dlgeditarDivisiones.setVisible(false);
    }
    
    public void editarDivision(){        
        division.setNombreDivision(nombreDivision);
        if (division != null) {
            System.out.println("inicamos actualizar con " + division.getNombreDivision());
            if (divisionesDAO.actualizarDivisiones(division)) {
                Utilidad.mensajeInfo("Actualizacion Division", "Division: " + division.getNombreDivision() + ". Actualizada correctamente.");
            } else {
                Utilidad.mensajeFatal("Actualizacion Division", "Division: " + division.getNombreDivision() + ". Error No actualizada.");
            }
        }
        else{
            Utilidad.mensajeFatal("Buscar Division", "Division: " + division.getNombreDivision() + ". Error No Encontrada.");
        }
        
        dlgeditarDivisiones.setVisible(false);
        
    }
    //*****************Fin Metodos de la clase***************************    
    
    //**********************Getters and setters****************************

    public Integer getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(Integer idDivision) {
        this.idDivision = idDivision;
    }

    public String getNombreDivision() {
        return nombreDivision;
    }

    public void setNombreDivision(String nombreDivision) {
        this.nombreDivision = nombreDivision;
    }
    
    
    public InputText getTxtnombreDivision() {
        return txtnombreDivision;
    }

    public void setTxtnombreDivision(InputText txtnombreDivision) {
        this.txtnombreDivision = txtnombreDivision;
    }
    
    
    public Divisiones getDivision() {
        return division;
    }

    public void setDivision(Divisiones division) {
        this.division = division;
    }
    
    
    public Dialog getDlgeditarDivisiones() {
        return dlgeditarDivisiones;
    }

    public void setDlgeditarDivisiones(Dialog dlgeditarDivisiones) {
        this.dlgeditarDivisiones = dlgeditarDivisiones;
    }

    public CommandButton getBtneditar() {
        return btneditar;
    }

    public void setBtneditar(CommandButton btneditar) {
        this.btneditar = btneditar;
    }

    public CommandButton getBtnguardar() {
        return btnguardar;
    }

    public void setBtnguardar(CommandButton btnguardar) {
        this.btnguardar = btnguardar;
    }
    
    
    
    

    
    //*********************Constructor de la clase************************
    public editarDivisionesBean() {
       
        dlgeditarDivisiones = new Dialog();
        division = new Divisiones();
        txtnombreDivision = new InputText();
        txtnombreDivision.setReadonly(true);
        divisionesDAO = new DivisionesDAOImpl();        
        btneditar = new CommandButton();
        btnguardar = new CommandButton();
        dlgeditarDivisiones.setVisible(false);
        
        
        btneditar.setStyle("display:block");
        btnguardar.setStyle("display:none");
    }
    
    
}
