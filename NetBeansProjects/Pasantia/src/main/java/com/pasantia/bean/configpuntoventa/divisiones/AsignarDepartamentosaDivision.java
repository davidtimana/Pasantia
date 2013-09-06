/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;


import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.entidades.Pais;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author David Timana
 */
@Named(value = "asignarDepartamentosaDivision")
@SessionScoped
public class AsignarDepartamentosaDivision implements Serializable{

          
    private Boolean abrirAsignacionesUbicacion;
    private List<Departamento> departamentos,departamentosSeleccionados;    
    private List<DivisionesUbicacion> divisionesUbicacion,divisionesUbicacionlistcomprobar,divisionesUbicacionValidar,divisionesUbicacionCargar;    
    private ModeloDatosDepartamento modeloDatosDepartamento;
    private List<Pais> paises;    
    private List<SelectItem> paisescombo;
    private Pais pais;
    private Divisiones division;
    private DivisionesUbicacion divisionubicacion;
    private DataTable tblDepartamentos;
    private Integer width,height;    
    private Boolean estaEditando;
    
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    PaisDAO paisDAO;
    @Inject
    DivisionesUbicacionDAO divisionesUbicacionDAO;
    
    
    
     public void cargarAsignacion(Divisiones d) {

        abrirAsignacionesUbicacion = true;
        

        System.out.println("la division seleccionada es-->" + d.getNombreDivision());
        //departamentos = departamentoDAO.buscarDepartamentoporIdPais(51);
        //modeloDatosDepartamento = new ModeloDatosDepartamento(departamentos);

        division = d;
        divisionesUbicacionValidar = divisionesUbicacionDAO.buscarubicacionesxiddivision(division.getIdDivisiones());
        if (divisionesUbicacionValidar.isEmpty()) {//Si no tiene departamentos asociados - Agregar Nuevos
            estaEditando = false;
            width = 20;
            height = 20;
            tblDepartamentos.setStyle("display:none");

        } else {//Si tiene departamentos asociados - Editar las Asociaciones
             width = 40;
            height = 100;
            estaEditando = true;
            //cargarPropiedadesConAsociaciones();
        }
        Utilidad.actualizarElemento("dlgasignarubicaciones");


    }

    public void cerrarAsignacionesDivision(){
        abrirAsignacionesUbicacion=false;
        Utilidad.actualizarElemento("dlgasignarubicaciones");
    }
    
    
    public void cargarPaises(){
        paises = paisDAO.buscartodasPaises();
        paisescombo = new ArrayList<SelectItem>();
        for (int i = 0; i < paises.size(); i++) {
            paisescombo.add(new SelectItem(paises.get(i).getIdPais(), paises.get(i).getNombrePais()));
        }
    }
    
    public void cargarDepartamentosxPais(){        
        departamentos = departamentoDAO.buscarDepartamentoporIdPais(pais.getIdPais());
        eliminarDepartamentosSeleccionados();
        modeloDatosDepartamento = new ModeloDatosDepartamento(departamentos); 
        tblDepartamentos.setStyle("display:block");        
        width=700;
        height=590;        
        
        Utilidad.abrirDialog("dlgasignar");
    }    
  
    
     public Integer totalDepartamentos(){        
        return 0;
    }
     
     public void cancelarAsignacion(){
         
         departamentosSeleccionados = null;
         tblDepartamentos.setStyle("display:none");         
         width = 640;
         height = 180;
         departamentosSeleccionados=null;
         cargarPaises();
         departamentos = departamentoDAO.buscarDepartamentoporIdPais(51);
         modeloDatosDepartamento = new ModeloDatosDepartamento(departamentos);
         
     }
     
    public void guardarAsignacion() {

        System.out.println("************* VOY A EDITAR????->  " + estaEditando);
        if (estaEditando) {
            editarAsignaciones();
        } 
            guardarNuevaAsignacion();
        

    }
    
    public void editarAsignaciones() {
        
        
        if(divisionesUbicacionDAO.eliminarDivisionesUbicacion(division.getIdDivisiones())){
            System.out.println("Datos eliminados correctamente");
        }else{
            System.err.println("Datos NO eliminados correctamente");
        }
        
//        Boolean encontrado=false;
//        DivisionesUbicacion divUbicacion = new DivisionesUbicacion();        
//        
//        
//        for (int i = 0; i < departamentostemp.size(); i++) {
//            Departamento p = departamentostemp.get(i);        
//            for (Departamento de : departamentosSeleccionados) {
//                if(p.getIdDepartamento()==de.getIdDepartamento()){                    
//                    encontrado=true;
//                    break;
//                }else{
//                    encontrado=false;                    
//                }              
//            }
//            if(encontrado){
//                System.out.println("********************ELEMENTO ENCONTRADO: "+p.getNombreDepartamento());
//            } else {
//                System.out.println("********************ELEMENTO NOOOOO ENCONTRADO: " + p.getNombreDepartamento());                
//                divUbicacion = divisionesUbicacionDAO.buscarUbicacionesxIdDivisionYIdDepartamento(division.getIdDivisiones(),p.getIdDepartamento());
//                if (divUbicacion != null) {
//                    
//                    if(divisionesUbicacionDAO.eliminarDivisionesUbicacion(divUbicacion)){
//                        System.out.println("divisionUbicacion eliminada correctamente");
//                    }else{
//                        System.err.println("divisionUbicacion no eliminada");
//                    }
//                } else {
//                    System.err.println("no encontro la divisionUbicacion");
//                }
//
//
//            }
//            
//
//
//        }

    }
     
     public void eliminarDepartamentosSeleccionados(){
        for (int i = 0; i < departamentos.size(); i++) {
            int sec = departamentos.get(i).getIdDepartamento();            
            divisionesUbicacionlistcomprobar = divisionesUbicacionDAO.buscarubicacionesxidDepartamento(sec);            
            if (!divisionesUbicacionlistcomprobar.isEmpty()) {                
                departamentos.remove(i);
            }
        }
    }
     
     public void cargarPropiedadesConAsociaciones(){
         
         width = 620;
         height = 600;
         tblDepartamentos.setStyle("display:block");
         
         if (division.getIdDivisiones() != 0 && division.getIdDivisiones() != 0) {
             //divisionesUbicacionCargar = divisionesUbicacionDAO.buscarubicacionesxiddivision(division.getIdDivisiones());
             departamentosSeleccionados = divisionesUbicacionDAO.buscarUbicacionesxIdDivision(division.getIdDivisiones());              
             
         } else {
             Utilidad.mensajeFatal("Carga de Departamentos.", "...ERROR...Al cargar los departamentos seleccionados para la división escojida.");
         }         
         departamentos = departamentoDAO.buscarDepartamentoporIdPais(departamentosSeleccionados.get(0).getPais().getIdPais());
         modeloDatosDepartamento = new ModeloDatosDepartamento(departamentos);
         
         
         
     }
    public void guardarNuevaAsignacion() {
        List<Boolean> results = new ArrayList<Boolean>();
        if (division != null && !departamentosSeleccionados.isEmpty()) {
            for (Departamento departamento : departamentosSeleccionados) {                
                divisionubicacion.setDivisiones(division);
                divisionubicacion.setDepartamento(departamento);
                if (divisionesUbicacionDAO.insertarDivisionesUbicacion(divisionubicacion)) {
                    results.add(Boolean.TRUE);
                } else {
                    results.add(Boolean.FALSE);
                    Utilidad.mensajeFatal("Guardar Asignaciones.", "Ocurrio un error al ejecutar la operación no se pudo asignar ubicaciones a: " + divisionubicacion.getDivisiones().getNombreDivision());
                }

            }
        } else {
            Utilidad.mensajeFatal("Guardar Asignaciones.", "Ocurrio un error al ejecutar la operación.");
        }
        Integer tam = results.size();
        Integer cont = 0;
        for (Boolean boolean1 : results) {
            if (boolean1) {
                cont++;
            }
        }

        if (cont == tam) {
            Utilidad.mensajeInfo("Guardar Asignaciones.", "Asignaciones a la división " + division.getNombreDivision() + ". Guardadas Correctamente.");
//            cargarDepartamentosxPais();
        } else {
            Utilidad.mensajeFatal("Guardar Asignaciones.", "Ocurrio un error al ejecutar la operación no se pudo asignar ubicaciones a: " + divisionubicacion.getDivisiones().getNombreDivision());
        }
        tblDepartamentos.setStyle("display:block");   
    }
      
    public AsignarDepartamentosaDivision() {
        
        
        
        abrirAsignacionesUbicacion=false;
        pais = new Pais();
        division = new Divisiones();
        divisionubicacion =  new DivisionesUbicacion();
        tblDepartamentos = new DataTable();
//        
        departamentosSeleccionados = new ArrayList<Departamento>();
//        //Valores por defecto
//        
//        width=620;
//        height=140;
//        
//        tblDepartamentos.setStyle("display:none");
////        departamentos = departamentoDAO.buscartodosDepartamentos();
//        //eliminarDepartamentosSeleccionados();
//        divisionesUbicacion = divisionesUbicacionDAO.buscarubicaciones();
//        modeloDatosDepartamento = new ModeloDatosDepartamento(departamentos);        
//        //cargarPaises();
        
        
    }   

    

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<DivisionesUbicacion> getDivisionesUbicacion() {
        return divisionesUbicacion;
    }

    public void setDivisionesUbicacion(List<DivisionesUbicacion> divisionesUbicacion) {
        this.divisionesUbicacion = divisionesUbicacion;
    }

    public List<Departamento> getDepartamentosSeleccionados() {
        return departamentosSeleccionados;
    }

    public void setDepartamentosSeleccionados(List<Departamento> departamentosSeleccionados) {
        this.departamentosSeleccionados = departamentosSeleccionados;
    }

    public ModeloDatosDepartamento getModeloDatosDepartamento() {
        return modeloDatosDepartamento;
    }

    public void setModeloDatosDepartamento(ModeloDatosDepartamento modeloDatosDepartamento) {
        this.modeloDatosDepartamento = modeloDatosDepartamento;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<SelectItem> getPaisescombo() {
        return paisescombo;
    }

    public void setPaisescombo(List<SelectItem> paisescombo) {
        this.paisescombo = paisescombo;
    }

    public Divisiones getDivision() {
        return division;
    }

    public void setDivision(Divisiones division) {
        this.division = division;
    }

    public DivisionesUbicacion getDivisionubicacion() {
        return divisionubicacion;
    }

    public void setDivisionubicacion(DivisionesUbicacion divisionubicacion) {
        this.divisionubicacion = divisionubicacion;
    }

    public List<DivisionesUbicacion> getDivisionesUbicacionlistcomprobar() {
        return divisionesUbicacionlistcomprobar;
    }

    public void setDivisionesUbicacionlistcomprobar(List<DivisionesUbicacion> divisionesUbicacionlistcomprobar) {
        this.divisionesUbicacionlistcomprobar = divisionesUbicacionlistcomprobar;
    }

    public DataTable getTblDepartamentos() {
        return tblDepartamentos;
    }

    public void setTblDepartamentos(DataTable tblDepartamentos) {
        this.tblDepartamentos = tblDepartamentos;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public List<DivisionesUbicacion> getDivisionesUbicacionValidar() {
        return divisionesUbicacionValidar;
    }

    public void setDivisionesUbicacionValidar(List<DivisionesUbicacion> divisionesUbicacionValidar) {
        this.divisionesUbicacionValidar = divisionesUbicacionValidar;
    }

    

    public List<DivisionesUbicacion> getDivisionesUbicacionCargar() {
        return divisionesUbicacionCargar;
    }

    public void setDivisionesUbicacionCargar(List<DivisionesUbicacion> divisionesUbicacionCargar) {
        this.divisionesUbicacionCargar = divisionesUbicacionCargar;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }

    public Boolean getAbrirAsignacionesUbicacion() {
        return abrirAsignacionesUbicacion;
    }

    public void setAbrirAsignacionesUbicacion(Boolean abrirAsignacionesUbicacion) {
        this.abrirAsignacionesUbicacion = abrirAsignacionesUbicacion;
    }
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
