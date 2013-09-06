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
    private List<Departamento> departamentos,departamentosSeleccionados;
    private DualListModel<Departamento> modeloDepartamentos;
    private Divisiones divisionSeleccionada;
    
    
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    DivisionesUbicacionDAO divisionesUbicacionDAO;
    
    public void cargarAsignaciones(Divisiones d){
        divisionSeleccionada=d;
        abrirAsignaciones=true;        
        List<DivisionesUbicacion> listaComprobacion;
        if((divisionesUbicacionDAO.buscarubicacionesxiddivision(d.getIdDivisiones())).isEmpty()){
            //No tiene divisiones asociadas
            cargarDepartamentosNoAsociados();
            Utilidad.actualizarElemento("pickAsignacionesDepartamentos");
        }else{
            //tiene divisiones asociadas
            
        }
        
        
        
        
        Utilidad.actualizarElemento("dlgasignarubicaciones");
        
    }
    
    public void cerrarAsignaciones(){
        abrirAsignaciones=false;
        Utilidad.actualizarElemento("dlgasignarubicaciones");
    }
    
    @PostConstruct
    public void cargarDepartamentos(){        
        departamentos = departamentoDAO.buscartodosDepartamentos();
        modeloDepartamentos = new DualListModel<Departamento>(departamentos, departamentosSeleccionados);
        
    }
    
    public void cargarDepartamentosNoAsociados(){
        departamentos = departamentoDAO.buscartodosDepartamentos();
        eliminarDepartamentosSeleccionados();
        modeloDepartamentos = new DualListModel<Departamento>(departamentos, departamentosSeleccionados);
        
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
        departamentosSeleccionados = new ArrayList<Departamento>();
        
        
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

    public List<Departamento> getDepartamentosSeleccionados() {
        return departamentosSeleccionados;
    }

    public void setDepartamentosSeleccionados(List<Departamento> departamentosSeleccionados) {
        this.departamentosSeleccionados = departamentosSeleccionados;
    }

    public DualListModel<Departamento> getModeloDepartamentos() {
        return modeloDepartamentos;
    }

    public void setModeloDepartamentos(DualListModel<Departamento> modeloDepartamentos) {
        this.modeloDepartamentos = modeloDepartamentos;
    }

    public Divisiones getDivisionSeleccionada() {
        return divisionSeleccionada;
    }

    public void setDivisionSeleccionada(Divisiones divisionSeleccionada) {
        this.divisionSeleccionada = divisionSeleccionada;
    }
    
    
    
    

    
    
    
    
    

    
    
    

   
    
    
    
    
    
}
