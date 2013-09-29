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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author David Timana
 */
@Named(value = "controlMapaBean")
@SessionScoped
public class ControlMapaBean implements Serializable {
    
    private static final long serialVersionUID = 6327793204738190453L;

    private List<DivisionesUbicacion> listaGeo;
    private MapModel modMapa;    
    private List<SelectItem> comboUbicaciones;
    private Integer idDivisionUbicacion;
    private Integer zoom;
    private double latitud;
    private double longitud;
    private Boolean abrirMapaDivisiones;
    private Departamento departamento;
    private Divisiones divisiones;
    
    @Inject
    DivisionesUbicacionDAO divisionesubicacionDAO;
    @Inject
    DepartamentoDAO departamentoDAO;

    public void prepararCargaGeolocalizacion(Divisiones d) {        
        divisiones=d;
        listaGeo = divisionesubicacionDAO.buscarubicacionesxiddivision(d.getIdDivisiones());
        if (listaGeo.isEmpty()) {
            abrirMapaDivisiones = false;
            Utilidad.mensajePeligro("SICOVI", "Division sin Ubicaciones.");

        } else {
            abrirMapaDivisiones = true;            
            cargarCoordenadasMapa();
            zoom = 6;
            Utilidad.actualizarElemento("mapdivisionesubicacion");
            Utilidad.actualizarElemento("listaubicacionesdivision");
            Utilidad.mensajeInfo("SICOVI", "Seleccione un departamento para ver su ubicación en el mapa.");
        }

        Utilidad.actualizarElemento("dlgUbicacionDivision");

    }

    public void cargarCoordenadasMapa() {

        List<LatLng> listaDeCoordenadas = new ArrayList<LatLng>();
        LatLng coordenadaTemp;

        for (DivisionesUbicacion ubic : listaGeo) {
            //Carga el objeto con las coordenadas a ubicar en el mapa
            coordenadaTemp = new LatLng(ubic.getDepartamento().getLatitud(), ubic.getDepartamento().getLongitud());

            //Carga la lista de departamentos a las cuales pertenece
            comboUbicaciones.add(new SelectItem(ubic.getDepartamento().getIdDepartamento(), ubic.getDepartamento().getNombreDepartamento()));

            //Carga una lista de coordenadas a ubicar en el mapa
            listaDeCoordenadas.add(coordenadaTemp);
        }

        //Inserta los elementos en el mapa
        for (LatLng latLng : listaDeCoordenadas) {
            modMapa.addOverlay(new Marker(latLng));
        }
    }

    public void verUbicacion() {


        departamento = departamentoDAO.buscarDepartamentoporIdUno(idDivisionUbicacion);
        latitud = departamento.getLatitud();
        longitud = departamento.getLongitud();
        zoom = 10;        
        Utilidad.actualizarElemento("mapdivisionesubicacion");

    }

    public void cerrarMapa() {
        comboUbicaciones = new ArrayList<SelectItem>();
        modMapa = new DefaultMapModel();
        latitud = 4.599047;
        longitud = -74.080917;
        zoom = 10;
        abrirMapaDivisiones = false;
        Utilidad.actualizarElemento("mapdivisionesubicacion");
        Utilidad.actualizarElemento("listaubicacionesdivision");
        Utilidad.actualizarElemento("dlgUbicacionDivision");

    }

    public ControlMapaBean() {

        comboUbicaciones = new ArrayList<SelectItem>();
        listaGeo = new ArrayList<DivisionesUbicacion>();;
        modMapa = new DefaultMapModel();
        latitud = 4.599047;
        longitud = -74.080917;
        zoom = 10;
        abrirMapaDivisiones = false;

    }

    public List<DivisionesUbicacion> getListaGeo() {
        return listaGeo;
    }

    public void setListaGeo(List<DivisionesUbicacion> listaGeo) {
        this.listaGeo = listaGeo;
    }

    public MapModel getModMapa() {
        return modMapa;
    }

    public void setModMapa(MapModel modMapa) {
        this.modMapa = modMapa;
    }

    

    public List<SelectItem> getComboUbicaciones() {
        return comboUbicaciones;
    }

    public void setComboUbicaciones(List<SelectItem> comboUbicaciones) {
        this.comboUbicaciones = comboUbicaciones;
    }

    public Integer getIdDivisionUbicacion() {
        return idDivisionUbicacion;
    }

    public void setIdDivisionUbicacion(Integer idDivisionUbicacion) {
        this.idDivisionUbicacion = idDivisionUbicacion;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Boolean getAbrirMapaDivisiones() {
        return abrirMapaDivisiones;
    }

    public void setAbrirMapaDivisiones(Boolean abrirMapaDivisiones) {
        this.abrirMapaDivisiones = abrirMapaDivisiones;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Divisiones getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }
    
    
}
