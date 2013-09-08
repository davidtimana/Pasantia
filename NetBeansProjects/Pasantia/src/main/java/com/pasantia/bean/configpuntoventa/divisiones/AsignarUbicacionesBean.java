/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.BeforeCompletion;
import javax.inject.Inject;
import org.primefaces.model.DualListModel;

/**
 *
 * @author David Timana
 */
@Named(value = "asignarUbicacionesBean")
@SessionScoped
public class AsignarUbicacionesBean implements Serializable {

    private Boolean abrirAsignaciones;
    private List<Departamento> departamentos;    
    private Divisiones divisionSeleccionada;
    private List<DepartamentoTemp> depSeleccionados;
    
    
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    DivisionesUbicacionDAO divisionesUbicacionDAO;
    
    public void cargarAsignaciones(Divisiones d) {
        divisionSeleccionada = d;

        abrirAsignaciones = true;
        List<DivisionesUbicacion> listaComprobacion = divisionesUbicacionDAO.buscarubicacionesxiddivision(d.getIdDivisiones());
        if (listaComprobacion.isEmpty()) {
            //No tiene divisiones asociadas            
            cargarDepartamentosNoAsociados();

        } else {
            //tiene divisiones asociadas
            for (DivisionesUbicacion du : listaComprobacion) {
                DepartamentoTemp temp = new DepartamentoTemp();
                temp.setIdDepartamento(du.getDepartamento().getIdDepartamento());
                temp.setNombreDepartamento(du.getDepartamento().getNombreDepartamento());
                temp.setSeleccionado(false);
                for (DepartamentoTemp dtemp : depSeleccionados) {
                    if (dtemp.getNombreDepartamento().equals(temp.getNombreDepartamento())) {
                        dtemp.setSeleccionado(true);
                    }
                }

            }
        }
        Utilidad.actualizarElemento("tblubicacionesdepasociadas");
        Utilidad.actualizarElemento("dlgasignarubicaciones");

    }
    
    public void cargarDepartamentoYaAsociados(List<DivisionesUbicacion> listaComprobacion) {
        for (DivisionesUbicacion du : listaComprobacion) {
            DepartamentoTemp temp = new DepartamentoTemp();
            temp.setIdDepartamento(du.getDepartamento().getIdDepartamento());
            temp.setNombreDepartamento(du.getDepartamento().getNombreDepartamento());
            temp.setSeleccionado(false);
            for (DepartamentoTemp dtemp : depSeleccionados) {
                if (dtemp.getNombreDepartamento().equals(temp.getNombreDepartamento())) {
                    dtemp.setSeleccionado(true);
                }
            }

        }
    }
    
  
    
    public void guardarDepartamentosAsignados(){
        for (DepartamentoTemp dtemp : depSeleccionados) {
            System.out.println("los departamento modificados--> "+dtemp.getNombreDepartamento()+" estado--> "+dtemp.getSeleccionado());
        }
        
      
    }

    public void cerrarAsignaciones(){
        abrirAsignaciones=false;    
        depSeleccionados = new ArrayList<DepartamentoTemp>();
        Utilidad.actualizarElemento("dlgasignarubicaciones");
    }
    
    public Integer totalDepartamentos(){
        return depSeleccionados.size();
    }
    
    @PostConstruct
    public void cargarDepartamentos(){        
        departamentos = departamentoDAO.buscartodosDepartamentos();
        cargarNuevaLista();
        
    }
    
    public void cargarDepartamentosNoAsociados(){
        departamentos = departamentoDAO.buscartodosDepartamentos();
        eliminarDepartamentosSeleccionados();
        cargarNuevaLista();
        
        
    }
    
    public void cargarNuevaLista(){
        for (Departamento d : departamentos) {
            DepartamentoTemp dt = new DepartamentoTemp();
            dt.setIdDepartamento(d.getIdDepartamento());
            dt.setNombreDepartamento(d.getNombreDepartamento());
            dt.setSeleccionado(false);
            depSeleccionados.add(dt);
        }
    }
    
     public void eliminarDepartamentosSeleccionados(){
        for (int i = 0; i < departamentos.size(); i++) {
            int sec = departamentos.get(i).getIdDepartamento();                        
            if (!(divisionesUbicacionDAO.buscarubicacionesxidDepartamento(sec)).isEmpty()) {                
                departamentos.remove(i);
            }
        }
    }
    
    
    public AsignarUbicacionesBean() {
        System.out.println("inicializando");
        abrirAsignaciones=false;  
        departamentos = new ArrayList<Departamento>();
     
        depSeleccionados = new ArrayList<DepartamentoTemp>();
        
        
        
    }

    public Boolean getAbrirAsignaciones() {
        return abrirAsignaciones;
    }

    public void setAbrirAsignaciones(Boolean abrirAsignaciones) {
        this.abrirAsignaciones = abrirAsignaciones;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

   

    

    public Divisiones getDivisionSeleccionada() {
        return divisionSeleccionada;
    }

    public void setDivisionSeleccionada(Divisiones divisionSeleccionada) {
        this.divisionSeleccionada = divisionSeleccionada;
    }

   

    public List<DepartamentoTemp> getDepSeleccionados() {
        return depSeleccionados;
    }

    public void setDepSeleccionados(List<DepartamentoTemp> depSeleccionados) {
        this.depSeleccionados = depSeleccionados;
    }

   
    
    
    
    
    
    

    
    
    
    
    

    
    
    
    
    

    
    
    

   
    
    
    
    
    
}
