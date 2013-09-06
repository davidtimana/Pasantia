/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.dao.impl.*;
import com.pasantia.entidades.*;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.ToggleEvent;


/**
 *
 * @author David Timana
 */
@Named(value = "divisionesBean")
@SessionScoped
public class DivisionesBean implements Serializable{
                         //******************************Inicio divisionBean*********************************************   
    
    
    //*******************Inicio Declaracion de Atributos***********************************    
    private List<Divisiones> divisioneslista;
    private List<Departamento> departamentosAsociados;        
    
    @Inject
    DivisionesDAO divisionesDAO;
    @Inject
    DivisionesUbicacionDAO divisionesUbicacion;
    @Inject
    DepartamentoDAO departamentoDAO;
    //*******************FIn Declaracion de Atributos***********************************
    
    
    //*************************Inicio Declaracion De Metodos de divisionBean*********************************************  
    public int totalDivisiones(){
        int total=divisioneslista.size();
        return total;
    } 
    public int totalUbicaciones(){
        int total=departamentosAsociados.size();
        return total;
    }
    
    public void desplegarFila(Divisiones d){        
        departamentosAsociados = divisionesUbicacion.buscarUbicacionesxIdDivision(d.getIdDivisiones());       
    }
    
    public void mostrarAyuda(Integer num){
        System.out.println("oprimi boton--> "+num);
    }
    
    public void cargarDivisiones(){
        divisioneslista = divisionesDAO.buscartodasDivisiones();              
    }
    public void cargarDepartamentos(){
        departamentosAsociados = departamentoDAO.buscartodosDepartamentos();
    }
    //*************************Fin Declaracion De Metodos de divisionBean********************************************* 
    
    
    //********************************************Constructor por Defecto**************************************************************
    
    public DivisionesBean() {                      
       divisioneslista = new ArrayList<Divisiones>();
       departamentosAsociados = new ArrayList<Departamento>();
       
    }    
    
    //***********************Getters and Setters************************************************* 

    public List<Divisiones> getDivisioneslista() {       
        return divisioneslista;
    }

    public void setDivisioneslista(List<Divisiones> divisioneslista) {
        this.divisioneslista = divisioneslista;
    }

    public List<Departamento> getDepartamentosAsociados() {
        return departamentosAsociados;
    }

    public void setDepartamentosAsociados(List<Departamento> departamentosAsociados) {
        this.departamentosAsociados = departamentosAsociados;
    }
    
    

  }     
//******************************Fin divisionBean*********************************************
