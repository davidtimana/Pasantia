/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.Pais;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jbuitron
 */
@Named(value = "buscarBatallonesBean")
@ApplicationScoped
public class BuscarBatallonesBean implements Serializable {

    private Boolean abrirBuscador;
    private List<SelectItem> comboOpciones;
    private List<String> opciones;
    private List<Pais> paises;
    private List<Departamento> departamentos;
    private List<Ciudad> ciudades;
    private List<Divisiones> divisiones;
    private List<SelectItem> paisesCombo, departamentosCombo, ciudadesCombo, divisionesCombo;
    private Integer opSeleccionada;
    private Integer paisSeleccionado;
    private Integer departamentoSeleccionado;
    private Integer ciudadSeleccionada;
    private Integer divisionSeleccionada;
    private String oculto;
    private String ocultarPais;
    private String ocultarDepartamento;
    private String ocultarCiudad;
    private String ocultarDivisiones;    
    private String textoBusqueda;
    
    @Inject
    PaisDAO paisDAO;
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    CiudadDAO ciudadDAO;
    @Inject
    DivisionesDAO divisionesDAO;    
    @Inject
    EditarBatallonesBean batallonesBean;

    public void cargarBuscador() {

        //cargarComboOpcionesBuscador();
        abrirBuscador = true;
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("dlgbuscarBatallon").getClientId(FacesContext.getCurrentInstance()));
        Utilidad.mensajeInfo("Buscador De Batallones", "Por favor Seleccione una opcion para empezar a realizar la busqueda.");
        
    }
    
    
    public void cancelarBuscador() {
        oculto = "display:none";
        ocultarPais = "display:none";
        ocultarDepartamento = "display:none";
        ocultarCiudad = "display:none";
        ocultarDivisiones = "display:none";        
        textoBusqueda="";
        abrirBuscador = false;
        opSeleccionada = null;
        paisSeleccionado = null;
        departamentoSeleccionado = null;
        ciudadSeleccionada = null;
        divisionSeleccionada = null;
        
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("dlgbuscarBatallon").getClientId(FacesContext.getCurrentInstance()));
    }

    public void buscarBatallon() {
//        System.out.println("Inicio Busqueda");
//        System.out.println("vamos a buscar " + textoBusqueda);
//        if (oculto.equals("display:block") && textoBusqueda.equals("")) {
//            batallonesBean.buscarBatallones();
//        } else {
//            System.out.println("obtener valor y numero de opcion seleccionada y buscar en la bd->" + textoBusqueda);
//
//            switch (opSeleccionada) {
//                case 0:
//                    batallonesBean.buscarBatallonesxNombre(textoBusqueda);
//                    break;
//                case 1:
//                    batallonesBean.buscarBatallonesxDireccion(textoBusqueda);
//                    break;
//                case 2:
//                    batallonesBean.buscarBatallonesxBarrio(textoBusqueda);
//                    break;
//                case 3:
//                    batallonesBean.buscarBatallonesxTelefono1(textoBusqueda);
//                    break;
//                case 4:
//                    batallonesBean.buscarBatallonesxTelefono2(textoBusqueda);
//                    break;
//                case 5:
//                    if(paisSeleccionado==null){
//                        Utilidad.mensajeError("Buscador Batallones", "Debe seleccionar un pais para realizar la busqueda.");
//                    }else{
//                        batallonesBean.buscarBatallonesxPais(paisSeleccionado);
//                    }
//                    
//                    break;
//                case 6:                    
//                    if(departamentoSeleccionado==null){
//                        Utilidad.mensajeError("Buscador Batallones", "Debe seleccionar un departamento para realizar la busqueda.");
//                    }else{
//                        batallonesBean.buscarBatallonesxDepartamento(departamentoSeleccionado);
//                    }
//                    break;
//                case 7:                    
//                    if(ciudadSeleccionada==null){
//                        Utilidad.mensajeError("Buscador Batallones", "Debe seleccionar una ciudad para realizar la busqueda.");
//                    }else{
//                        batallonesBean.buscarBatallonesxCiudad(ciudadSeleccionada);
//                    }
//                    break;
//                case 8:                    
//                    if(divisionSeleccionada==null){
//                        Utilidad.mensajeError("Buscador Batallones", "Debe seleccionar una divisi{on para realizar la busqueda.");
//                    }else{
//                        batallonesBean.buscarBatallonesxDivision(divisionSeleccionada);
//                    }
//                    break;
//                case 9:
//
//                    break;
//
//
//            }
//        }
    }

    public void actualizarElementosBusqueda() {
        oculto = "display:none";
         ocultarPais = "display:none";
        ocultarDepartamento = "display:none";
        ocultarCiudad = "display:none";
        ocultarDivisiones = "display:none";
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("oppais").getClientId(FacesContext.getCurrentInstance()));
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opdepartamento").getClientId(FacesContext.getCurrentInstance()));
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opciudad").getClientId(FacesContext.getCurrentInstance()));
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opdivisiones").getClientId(FacesContext.getCurrentInstance()));
    }

    public void capturarOpcionSeleccionada() {
        
        
        textoBusqueda="";        
        actualizarElementosBusqueda();
        if (opSeleccionada != null) {            
            
            switch (opSeleccionada) {
                case 0:
                    oculto = "display:block";
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 1:
                    oculto = "display:block";
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 2:
                    oculto = "display:block";
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 3:
                    oculto = "display:block";
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 4:
                    oculto = "display:block";
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 5:
                    ocultarPais = "display:block";
                    cargarPaises();
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("oppais").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 6:
                    ocultarDepartamento = "display:block";
                    cargarDepartamento();
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opdepartamento").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 7:
                    ocultarCiudad = "display:block";
                    cargarCiudades();
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opciudad").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 8:
                    ocultarDivisiones= "display:block";
                    cargarDivisiones();
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("opdivisiones").getClientId(FacesContext.getCurrentInstance()));
                    break;
                case 9:
                    RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("txtnombrebuscar").getClientId(FacesContext.getCurrentInstance()));
                    break;
                default:
                    oculto = "display:none";
                    System.err.println("Error en la seleccion de las opciones");
                    break;
            }
        } else {            
            
            actualizarElementosBusqueda();
            System.err.println("Error en la seleccion de las opciones");
        }
        
    }

    public void cargarOpciones() {
        opciones.add("Nombre");
        opciones.add("Dirección");
        opciones.add("Barrio");
        opciones.add("Telefono 1");
        opciones.add("Telefono 2");
        opciones.add("Pais");
        opciones.add("Departamento");
        opciones.add("Ciudad");
        opciones.add("Division Asignada");
        opciones.add("Coronel a Cargo");
    }

    public void cargarComboOpcionesBuscador() {
        cargarOpciones();
        comboOpciones = new ArrayList<SelectItem>();
        for (int i = 0; i < opciones.size(); i++) {
            comboOpciones.add(new SelectItem(i, opciones.get(i)));
        }
    }

    public void cargarPaises() {
        paises = paisDAO.buscartodasPaises();
        for (int i = 0; i < paises.size(); i++) {
            paisesCombo.add(new SelectItem(paises.get(i).getIdPais(), paises.get(i).getNombrePais()));
        }
    }

    public void cargarDepartamento() {
        departamentos = departamentoDAO.buscartodosDepartamentos();
        for (int i = 0; i < departamentos.size(); i++) {
            departamentosCombo.add(new SelectItem(departamentos.get(i).getIdDepartamento(), departamentos.get(i).getNombreDepartamento()));
        }
    }

    public void cargarCiudades() {
        ciudades = ciudadDAO.cargarCoordenadas();
        for (int i = 0; i < ciudades.size(); i++) {
            ciudadesCombo.add(new SelectItem(ciudades.get(i).getIdCiudad(), ciudades.get(i).getNombreCiudad()));
        }
    }

    public void cargarDivisiones() {
        divisiones = divisionesDAO.buscartodasDivisiones();
        for (int i = 0; i < divisiones.size(); i++) {
            divisionesCombo.add(new SelectItem(divisiones.get(i).getIdDivisiones(), divisiones.get(i).getNombreDivision()));
        }
    }

    public BuscarBatallonesBean() {
        abrirBuscador = false;
        comboOpciones = new ArrayList<SelectItem>();
        opciones = new ArrayList<String>();
        paisesCombo = new ArrayList<SelectItem>();
        departamentosCombo = new ArrayList<SelectItem>();
        ciudadesCombo = new ArrayList<SelectItem>();
        divisionesCombo = new ArrayList<SelectItem>();
        paises = new ArrayList<Pais>();
        departamentos = new ArrayList<Departamento>();
        ciudades = new ArrayList<Ciudad>();
        divisiones = new ArrayList<Divisiones>();
        oculto = "display:none";
        ocultarPais = "display:none";
        ocultarDepartamento = "display:none";
        ocultarCiudad = "display:none";
        ocultarDivisiones = "display:none";        
        cargarComboOpcionesBuscador();
        

    }

    public Boolean getAbrirBuscador() {
        return abrirBuscador;
    }

    public void setAbrirBuscador(Boolean abrirBuscador) {
        this.abrirBuscador = abrirBuscador;
    }

    public List<SelectItem> getComboOpciones() {
        return comboOpciones;
    }

    public void setComboOpciones(List<SelectItem> comboOpciones) {
        this.comboOpciones = comboOpciones;
    }

    public Integer getOpSeleccionada() {
        return opSeleccionada;
    }

    public void setOpSeleccionada(Integer opSeleccionada) {
        this.opSeleccionada = opSeleccionada;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Divisiones> getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(List<Divisiones> divisiones) {
        this.divisiones = divisiones;
    }

    public List<SelectItem> getPaisesCombo() {
        return paisesCombo;
    }

    public void setPaisesCombo(List<SelectItem> paisesCombo) {
        this.paisesCombo = paisesCombo;
    }

    public List<SelectItem> getDepartamentosCombo() {
        return departamentosCombo;
    }

    public void setDepartamentosCombo(List<SelectItem> departamentosCombo) {
        this.departamentosCombo = departamentosCombo;
    }

    public List<SelectItem> getCiudadesCombo() {
        return ciudadesCombo;
    }

    public void setCiudadesCombo(List<SelectItem> ciudadesCombo) {
        this.ciudadesCombo = ciudadesCombo;
    }

    public List<SelectItem> getDivisionesCombo() {
        return divisionesCombo;
    }

    public void setDivisionesCombo(List<SelectItem> divisionesCombo) {
        this.divisionesCombo = divisionesCombo;
    }

    public Integer getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Integer paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    public Integer getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Integer departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Integer getCiudadSeleccionada() {
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Integer ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
    }

    public Integer getDivisionSeleccionada() {
        return divisionSeleccionada;
    }

    public void setDivisionSeleccionada(Integer divisionSeleccionada) {
        this.divisionSeleccionada = divisionSeleccionada;
    }

    public String getOculto() {
        return oculto;
    }

    public void setOculto(String oculto) {
        this.oculto = oculto;
    }

    public String getOcultarPais() {
        return ocultarPais;
    }

    public void setOcultarPais(String ocultarPais) {
        this.ocultarPais = ocultarPais;
    }

    public String getOcultarDepartamento() {
        return ocultarDepartamento;
    }

    public void setOcultarDepartamento(String ocultarDepartamento) {
        this.ocultarDepartamento = ocultarDepartamento;
    }

    public String getOcultarCiudad() {
        return ocultarCiudad;
    }

    public void setOcultarCiudad(String ocultarCiudad) {
        this.ocultarCiudad = ocultarCiudad;
    }

    public String getOcultarDivisiones() {
        return ocultarDivisiones;
    }

    public void setOcultarDivisiones(String ocultarDivisiones) {
        this.ocultarDivisiones = ocultarDivisiones;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
   
    
    
    
    
    
    

   
    
    
}
