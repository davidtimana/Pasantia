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
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.BeforeCompletion;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DualListModel;

/**
 *
 * @author David Timana
 */
@Named(value = "asignarUbicacionesBean")
@SessionScoped
public class AsignarUbicacionesBean implements Serializable {
    
    private static final long serialVersionUID = 7826632365609626117L;

    private Boolean abrirAsignaciones;
    private List<Departamento> departamentos;    
    private Divisiones divisionSeleccionada;
    private List<DepartamentoTemp> depSeleccionados;
    private String txtnombrebuscar;    
    
    
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    DivisionesUbicacionDAO divisionesUbicacionDAO;
    
    public void cargarAsignaciones(Divisiones d) {
        divisionSeleccionada = d;

        abrirAsignaciones = true;
        departamentos = new ArrayList<Departamento>();
        depSeleccionados = new ArrayList<DepartamentoTemp>();
        departamentos = departamentoDAO.buscarDepartamentoNoAsociados();        
        List<Departamento> listaComprobacion = divisionesUbicacionDAO.buscarubicacionesxiddivisionretornaDepartamentos(d.getIdDivisiones());
        departamentos.addAll(listaComprobacion);        
        cargarNuevaLista();
        if (listaComprobacion.isEmpty()) {
            //No tiene divisiones asociadas            
            cargarDepartamentosNoAsociados();

        } else {
            //tiene divisiones asociadas
            for (Departamento du : listaComprobacion) {
                DepartamentoTemp temp = new DepartamentoTemp();
                temp.setIdDepartamento(du.getIdDepartamento());
                temp.setNombreDepartamento(du.getNombreDepartamento());
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
    
  
    
    public void guardarDepartamentosAsignados() {
        System.out.println("********************Se inicializa el guardado de asignacion de ubicaciones***********************");
        int cont = 0,bandera=0;
        List <Boolean> resultados = new ArrayList<Boolean>();
        for (DepartamentoTemp dcomprobar : depSeleccionados) {
            if (!dcomprobar.getSeleccionado()) {
                cont++;
            }
        }
        if (cont == depSeleccionados.size()) {
            Utilidad.mensajeError("SICOVI", "Para guardar necesita asociar algun departamento a la división.");
        } else {
            for (DepartamentoTemp dtemp : depSeleccionados) {
                Departamento d = new Departamento();
                DivisionesUbicacion du = new DivisionesUbicacion();
                DivisionesUbicacion duGuardar = new DivisionesUbicacion();
                d = departamentoDAO.buscarDepartamentoporIdUno(dtemp.getIdDepartamento());
                du = divisionesUbicacionDAO.buscarUbicacionesxIdDivisionYIdDepartamento(divisionSeleccionada.getIdDivisiones(), d.getIdDepartamento());
                if (du == null) {
                    if (dtemp.getSeleccionado()) {
                        duGuardar.setDepartamento(d);
                        duGuardar.setDivisiones(divisionSeleccionada);
                        if (divisionesUbicacionDAO.insertarDivisionesUbicacion(duGuardar)) {
                            System.out.println("Guardado correctamente");
                            resultados.add(true);
                        } else {
                            System.err.println("Nooo se Guardado correctamente");
                            resultados.add(false);
                        }
                    }

                } else {
                    if (!dtemp.getSeleccionado()) {
                        duGuardar = divisionesUbicacionDAO.buscarUbicacionesxIdDivisionYIdDepartamento(divisionSeleccionada.getIdDivisiones(), d.getIdDepartamento());
                        if (divisionesUbicacionDAO.eliminarDivisionesUbicacion(duGuardar)) {
                            System.out.println("Eliminado correctamente");
                            resultados.add(true);
                        } else {
                            System.err.println("Nooo se Elimino correctamente");
                            resultados.add(false);
                        }
                    }
                }
            }

        }
        
        for (Boolean r : resultados) {
            if(!r){
                bandera=1;
            }            
        }
        if (bandera == 1) {
            Utilidad.mensajeFatal("SICOVI", "Ocurrio algun error al ejecutar la operación.");
        } else {
            Utilidad.mensajeInfo("SICOVI", "Tarea Ejecutada Correctamente.");
        }


        Utilidad.actualizarElemento("tblexpandibleDivisiones");
        Utilidad.actualizarElemento("tbldivisiones");

        System.out.println("********************Se Finaliza el guardado de asignacion de ubicaciones***********************");
    }

    public void cerrarAsignaciones(){
        abrirAsignaciones=false; 
        departamentos = new ArrayList<Departamento>();
        depSeleccionados = new ArrayList<DepartamentoTemp>(); 
        
        Utilidad.actualizarElemento("tblubicacionesdepasociadas");
        Utilidad.actualizarElemento("dlgasignarubicaciones");
    }
    
    public Integer totalDepartamentos(){
        return depSeleccionados.size();
    }
    
    @PostConstruct
    public void cargarDepartamentos(){
        
        departamentos = departamentoDAO.buscarDepartamentoNoAsociados();
        cargarNuevaLista();      
    }
    
    public void cargarDepartamentosNoAsociados(){
        departamentos = departamentoDAO.buscarDepartamentoNoAsociados();      
        cargarNuevaLista();       
        
    }
    
    public void cargarNuevaLista(){
        depSeleccionados = new ArrayList<DepartamentoTemp>();
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

    public String getTxtnombrebuscar() {
        return txtnombrebuscar;
    }

    public void setTxtnombrebuscar(String txtnombrebuscar) {
        this.txtnombrebuscar = txtnombrebuscar;
    }

    
    
   
    
    

    
    
    

   
    
    

   
    
    
    
    
    
    

    
    
    
    
    

    
    
    
    
    

    
    
    

   
    
    
    
    
    
}
