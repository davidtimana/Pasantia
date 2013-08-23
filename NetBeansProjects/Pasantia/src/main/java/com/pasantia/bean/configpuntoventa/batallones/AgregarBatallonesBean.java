/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.*;
import com.pasantia.dao.impl.*;
import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.entidades.Pais;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author David Orlando Timanà
 */
@Named(value = "agregarBatallonesBean")
@ApplicationScoped
public class AgregarBatallonesBean implements Serializable{

    private Dialog dlgnuevobatallon;
    private Batallon batallon;
    private String nombre_batallon;
    private String telefono1;
    private String telefono2;
    private String direccion;
    private String barrio;
    private DivisionesUbicacionDAO divisionesUbicacionDAO;
    private List<SelectItem> comboDivisionesAsociadas,combopaises,combodepartamentos,combociudades;
    private List<Divisiones> divisiones;    
    private List<Pais> paises;
    private List<Departamento> departamentos;
    private List<Ciudad> ciudades;
    private PaisDAO paisDAO;
    private DepartamentoDAO departamentoDAO;
    private CiudadDAO ciudadDAO;
    private Integer paisSeleccionado;
    private Integer departamentoSeleccionado;
    private Integer ciudadSeleccionada;
    private Integer divisionSeleccionada;
    private Boolean deshabilitarDepartamento;
    private Boolean deshabilitarCiudad;
    private DivisionesDAO divisionesDAO;
    private Divisiones division;
    private Ciudad ciudad;
    private BatallonDAO batallonDAO;
    
    @Inject
    EditarBatallonesBean batallonesBean;
    
    

    public void cargarGuardadoBatallon() {
        System.out.println("Cargando el guardar");
        dlgnuevobatallon.setVisible(true);
    }

    public void guardarBatallon() {      
       
        if(validarDatos()){
            
            if(batallonDAO.insertarBatallon(batallon)){
                System.out.println("el batallon es el siguiente--> "+batallon.getTelefono1());
                Utilidad.mensajeInfo("Guardar Batallón.", "Batallon: "+batallon.getNombreBatallon()+". Guardado Correctamente.");                
            }else{
                Utilidad.mensajeInfo("Guardar Batallón.", "Batallon: "+batallon.getNombreBatallon()+". No se pudo Guardar.");  
            }
            limpiarCampos();
        }else{
            System.err.println("...ERROR...EN LA VALIDACIÓN DE DATOS");
        }   
        Utilidad.abrirDialog("dlgNuevo");
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
        batallonesBean.buscarBatallones();
    }
    
    public void limpiarCampos(){
      nombre_batallon=""  ;
      direccion="";
      barrio="";
      telefono1="";
      telefono2="";
      deshabilitarCiudad=true;
      deshabilitarDepartamento=true;
      departamentos=null;
      ciudades=null;
      cargarPaises();
    }
    
    
    
    public void cargarDivisionesAsociadas(){
        divisiones=divisionesUbicacionDAO.buscarDivisionesAsociadas();
    }
    public void cargarComboDivisionesAsociadas(){
        comboDivisionesAsociadas = new ArrayList<SelectItem>();
        for (int i = 0; i < divisiones.size(); i++) {
            comboDivisionesAsociadas.add(new SelectItem(divisiones.get(i).getIdDivisiones(), divisiones.get(i).getNombreDivision()));
        }
    }
    public boolean validarDatos(){
        boolean resultado=false;
        Integer validador=0;
        
        //Validacion nombre del batallon
        if(!nombre_batallon.equals("") && nombre_batallon!=null ){
            batallon.setNombreBatallon(nombre_batallon);
            validador++;
        }else{
          Utilidad.mensajeFatal("Guardar Batallón.", "Sección Datos Batallon - Campo Nombre Batallon Requerido.");  
          validador=validador+10;
        }
        
        //Validacion direccion del batallon        
        if(!direccion.equals("") && direccion!=null ){
            batallon.setDireccion(direccion);
            validador++;
        }else{
          Utilidad.mensajeFatal("Guardar Batallón.", "Sección Datos de Contacto - Campo Dirección Requerido.");  
          validador=validador+10;
        }
        
        //Validacion telefono1 del batallon        
        if(!telefono1.equals("") && telefono1!=null ){
            batallon.setTelefono1(telefono1);
            validador++;
        }else{
          Utilidad.mensajeFatal("Guardar Batallón.", "Sección Datos de Contacto - Campo Telefono 1 Requerido.");  
          validador=validador+10;
        }      
        
        //Validacion barrio del batallon        
        if(!barrio.equals("") && barrio!=null ){
            batallon.setBarrio(barrio);
            validador++;
        }else{
          Utilidad.mensajeFatal("Guardar Batallón", "Sección Datos de Contacto - Campo Barrio Requerido.");  
          validador=validador+10;
        }
        
        //Validacion division del batallon        
        if(divisionSeleccionada!=null){
            division = divisionesDAO.buscarDivisionesporId(divisionSeleccionada);
            if(division!=null){
                batallon.setDivisiones(division);
            }
            validador++;
        }else{
          Utilidad.mensajeFatal("Guardar Batallón", "Sección Comandante a Cargo - Campo Selección division requerido.");  
          validador=validador+10;
        }
        
        //Validacion pais del batallon        
        if (paisSeleccionado != null) {
            validador++;
        } else {
            Utilidad.mensajeFatal("Guardar Batallón", "Sección Ubicación Batallón - Campo Selección País requerido.");
            validador = validador + 10;
        }
        
        //Validacion departamento del batallon        
        if (departamentoSeleccionado != null) {
            validador++;
        } else {
            Utilidad.mensajeFatal("Guardar Batallón", "Sección Ubicación Batallón - Campo Selección Departamento requerido.");
            validador = validador + 10;
        }
        
        //Validacion ciudad del batallon        
        if (ciudadSeleccionada != null) {
            ciudad = ciudadDAO.buscarxid(ciudadSeleccionada);
            if(ciudad!=null){
                batallon.setCiudad(ciudad);
            }
            validador++;
        } else {
            Utilidad.mensajeFatal("Guardar Batallón", "Sección Ubicación Batallón - Campo Selección Ciudad requerido.");
            validador = validador + 10;
        }
        
        batallon.setTelefono2(telefono2);
        
        if(validador==8){
            resultado=true;            
        }else{
            resultado=false;
        }        
        return resultado;
    }
    
    public void cerrarGuardado(){
        dlgnuevobatallon.setVisible(false);
        limpiarCampos();
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("tblbatallones").getClientId(FacesContext.getCurrentInstance()));
        batallonesBean.cargarBatallones();
    }
    
    public void cargarPaises(){
        paises = paisDAO.buscartodasPaises();
        combopaises = new ArrayList<SelectItem>();
        for (int i = 0; i < paises.size(); i++) {
            combopaises.add(new SelectItem(paises.get(i).getIdPais(), paises.get(i).getNombrePais()));
        }
        
    }
    public void cargarDepartamentosxPais(){        
        departamentos = departamentoDAO.buscarDepartamentoporIdPais(paisSeleccionado);
        if(departamentos.isEmpty()){
            Utilidad.mensajePeligro("Consulta Departamentos.", "País sin departamentos asociados.");
            
        }else{
            combodepartamentos = new ArrayList<SelectItem>();
        for (int i = 0; i < departamentos.size(); i++) {
            combodepartamentos.add(new SelectItem(departamentos.get(i).getIdDepartamento(), departamentos.get(i).getNombreDepartamento()));
        }
        }
        
        deshabilitarDepartamento=false;
        deshabilitarCiudad=true;
        
    }
    public void cargarCiudadesxDepartamento(){
        ciudades = ciudadDAO.buscarxidDpto(departamentoSeleccionado);
        if(ciudades.isEmpty()){
            Utilidad.mensajePeligro("Consulta Ciudades.", "Departamento sin ciudades asociadas.");
        }else{
          combociudades = new ArrayList<SelectItem>();
        for (int i = 0; i < ciudades.size(); i++) {
            combociudades.add(new SelectItem(ciudades.get(i).getIdCiudad(), ciudades.get(i).getNombreCiudad()));
        }  
        }
        
        deshabilitarCiudad=false;
        
    }

    public AgregarBatallonesBean() {
        dlgnuevobatallon = new Dialog();
        batallon = new Batallon();
        
        batallonDAO = new BatallonDAOImpl(); 
        division = new Divisiones();
        ciudad = new Ciudad();
        divisionesDAO = new DivisionesDAOImpl();
        dlgnuevobatallon.setVisible(false);
        deshabilitarCiudad=true;
        deshabilitarDepartamento=true;
        divisionesUbicacionDAO = new DivisionesUbicacionDAOImpl();
        paisDAO = new PaisDAOImpl();
        departamentoDAO = new DepartamentoDAOImpl();
        ciudadDAO = new CiudadDAOImpl();
        cargarDivisionesAsociadas();
        cargarComboDivisionesAsociadas();
        cargarPaises();
    }

    public Dialog getDlgnuevobatallon() {
        return dlgnuevobatallon;
    }

    public void setDlgnuevobatallon(Dialog dlgnuevobatallon) {
        this.dlgnuevobatallon = dlgnuevobatallon;
    }

    public Batallon getBatallon() {
        return batallon;
    }

    public void setBatallon(Batallon batallon) {
        this.batallon = batallon;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre_batallon() {
        return nombre_batallon;
    }

    public void setNombre_batallon(String nombre_batallon) {
        this.nombre_batallon = nombre_batallon;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public List<SelectItem> getComboDivisionesAsociadas() {
        return comboDivisionesAsociadas;
    }

    public void setComboDivisionesAsociadas(List<SelectItem> comboDivisionesAsociadas) {
        this.comboDivisionesAsociadas = comboDivisionesAsociadas;
    }

    public List<Divisiones> getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(List<Divisiones> divisiones) {
        this.divisiones = divisiones;
    }
   

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<SelectItem> getCombociudades() {
        return combociudades;
    }

    public void setCombociudades(List<SelectItem> combociudades) {
        this.combociudades = combociudades;
    }

    public List<SelectItem> getCombodepartamentos() {
        return combodepartamentos;
    }

    public void setCombodepartamentos(List<SelectItem> combodepartamentos) {
        this.combodepartamentos = combodepartamentos;
    }

    public List<SelectItem> getCombopaises() {
        return combopaises;
    }

    public void setCombopaises(List<SelectItem> combopaises) {
        this.combopaises = combopaises;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public Integer getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Integer ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    public Integer getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Integer departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Integer getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Integer paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    public Boolean getDeshabilitarCiudad() {
        return deshabilitarCiudad;
    }

    public void setDeshabilitarCiudad(Boolean deshabilitarCiudad) {
        this.deshabilitarCiudad = deshabilitarCiudad;
    }

    public Boolean getDeshabilitarDepartamento() {
        return deshabilitarDepartamento;
    }

    public void setDeshabilitarDepartamento(Boolean deshabilitarDepartamento) {
        this.deshabilitarDepartamento = deshabilitarDepartamento;
    }

    public Integer getDivisionSeleccionada() {
        return divisionSeleccionada;
    }

    public void setDivisionSeleccionada(Integer divisionSeleccionada) {
        this.divisionSeleccionada = divisionSeleccionada;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Divisiones getDivision() {
        return division;
    }

    public void setDivision(Divisiones division) {
        this.division = division;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
