/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.dao.impl.DepartamentoDAOImpl;
import com.pasantia.dao.impl.DivisionesDAOImpl;
import com.pasantia.dao.impl.DivisionesUbicacionDAOImpl;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectonelistbox.SelectOneListbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author root
 */
@Named(value = "controlMapaBean")
@RequestScoped
public class ControlMapaBean implements Serializable{

    private Dialog dlggeolocallizacion;
    private DivisionesDAO divDAO;
    private OutputLabel lblubigeo;
    private List<DivisionesUbicacion> listaGeo,listaCombo;
    private DivisionesUbicacionDAO divisionesubicacionDAO;
    private CommandButton btngeolocalizacion;
    private MapModel modMapa;    
    private DivisionesUbicacion divisionesUbicacionSeleccion;
    private List<SelectItem> comboUbicaciones;
    private Divisiones divu;
    private SelectOneMenu comboUbicMostrar;
    private Integer idDivisionUbicacion;
    private SelectOneListbox listubicaciones;
    private Integer zoom;
    private double latitud;
    private double longitud;
    private DepartamentoDAO departamentoDAO;
    private FacesContext context;

    
    
    
   public void prepararCargaGeolocalizacion(Integer id){     
        mensajeAyuda();
        latitud=4.599047;
        longitud=-74.080917;
        divu = divDAO.buscarDivisionesporId(id);
        String tituloDialog = divu.getNombreDivision();
        dlggeolocallizacion.setHeader("Geolocalizacion para: " + tituloDialog);
        lblubigeo.setValue(tituloDialog);
        listaGeo = divisionesubicacionDAO.buscarubicacionesxiddivision(divu.getIdDivisiones());
        if (listaGeo.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "...ERROR...", "Division sin Ubicaciones"));            
            zoom=10;
            listubicaciones.setStyle("display:none");
        } else {   
            zoom=6;
            cargarUbicacionesDivision();
            cargarCoordenadasMapa();
            listubicaciones.setStyle("display:block");
            
        }
        
    }
   
  
   
   public void cargarCoordenadasMapa()  {
      
      List<LatLng> listaDeCoordenadas = new ArrayList<LatLng>();
                LatLng coordenadaTemp;      
               
                for (DivisionesUbicacion ubic : listaGeo) {
                    System.out.println("la lista geo a cargar en mapa es-->>>"+ubic.getDepartamento().getNombreDepartamento());
                    coordenadaTemp = new LatLng(ubic.getDepartamento().getLatitud(), ubic.getDepartamento().getLongitud());
                    listaDeCoordenadas.add(coordenadaTemp); 
                }
                for (LatLng latLng : listaDeCoordenadas) {
                    modMapa.addOverlay(new Marker(latLng));
                }
  }
   
   public void cargarUbicacionesDivision(){       
       listaCombo=listaGeo;
   }
   
   public void verUbicacion (){
       
       System.out.println("seleccionando ubicaciones" + idDivisionUbicacion);
       zoom=10;
       Departamento departamento=null;
       if(idDivisionUbicacion !=0 || idDivisionUbicacion !=null){
            departamento=departamentoDAO.buscarDepartamentoporIdUno(idDivisionUbicacion);
            System.out.println("la coordenadas para "+departamento.getNombreDepartamento());
            System.out.println("latitud es-->"+departamento.getLatitud());
            System.out.println("longitud es-->"+departamento.getLongitud());
                if(departamento!=null){
                    latitud=departamento.getLatitud();
                    longitud=departamento.getLongitud();                      
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "...ERROR...", "No se encontro las coordenadas de la ubicaciòn"));            
                }
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "...ERROR...", "No se pudo obtener la ubicación."));            
       }
       
       RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete("mapa1").getClientId(FacesContext.getCurrentInstance()));
   }
   
   public void mensajeAyuda(){
      context.addMessage("grwayuda", new FacesMessage("Informacion Mapa", "Seleccione Un Departamento para visualizar su ubicación en el mapa."));      
      
   }
    
    public ControlMapaBean() {
        divDAO = new DivisionesDAOImpl();
        divisionesubicacionDAO = new DivisionesUbicacionDAOImpl();
        modMapa = new DefaultMapModel();
        btngeolocalizacion = new CommandButton();
        dlggeolocallizacion = new Dialog();
        lblubigeo = new OutputLabel();   
        listaCombo = divisionesubicacionDAO.buscarubicaciones();
        listubicaciones = new SelectOneListbox();
        listubicaciones.setStyle("display : none");   
        departamentoDAO = new DepartamentoDAOImpl();
        context = FacesContext.getCurrentInstance();


    }

    public OutputLabel getLblubigeo() {
        return lblubigeo;
    }

    public void setLblubigeo(OutputLabel lblubigeo) {
        this.lblubigeo = lblubigeo;
    }    
    
    public Dialog getDlggeolocallizacion() {
        return dlggeolocallizacion;
    }

    public void setDlggeolocallizacion(Dialog dlggeolocallizacion) {
        this.dlggeolocallizacion = dlggeolocallizacion;
    }

    public List<DivisionesUbicacion> getListaGeo() {
        return listaGeo;
    }

    public void setListaGeo(List<DivisionesUbicacion> listaGeo) {
        this.listaGeo = listaGeo;
    }

    public CommandButton getBtngeolocalizacion() {
        return btngeolocalizacion;
    }

    public void setBtngeolocalizacion(CommandButton btngeolocalizacion) {
        this.btngeolocalizacion = btngeolocalizacion;
    }

    public MapModel getModMapa() {
        return modMapa;
    }

    public void setModMapa(MapModel modMapa) {
        this.modMapa = modMapa;
    }


    public DivisionesUbicacion getDivisionesUbicacionSeleccion() {
        return divisionesUbicacionSeleccion;
    }

    public void setDivisionesUbicacionSeleccion(DivisionesUbicacion divisionesUbicacionSeleccion) {
        this.divisionesUbicacionSeleccion = divisionesUbicacionSeleccion;
    }

    public List<SelectItem> getComboUbicaciones() {
        
        comboUbicaciones = new ArrayList<SelectItem>();
         for (int i = 0; i < listaCombo.size(); i++) {
            comboUbicaciones.add(new SelectItem(listaCombo.get(i).getDepartamento().getIdDepartamento(),listaCombo.get(i).getDepartamento().getNombreDepartamento()));
        }
        
        return comboUbicaciones;
    }

    public void setComboUbicaciones(List<SelectItem> comboUbicaciones) {
        this.comboUbicaciones = comboUbicaciones;
    }

    public List<DivisionesUbicacion> getListaCombo() {
        return listaCombo;
    }

    public void setListaCombo(List<DivisionesUbicacion> listaCombo) {
        this.listaCombo = listaCombo;
    }

    public Divisiones getDivu() {
        return divu;
    }

    public void setDivu(Divisiones divu) {
        this.divu = divu;
    }

    public SelectOneMenu getComboUbicMostrar() {
        return comboUbicMostrar;
    }

    public void setComboUbicMostrar(SelectOneMenu comboUbicMostrar) {
        this.comboUbicMostrar = comboUbicMostrar;
    }

    public Integer getIdDivisionUbicacion() {
        return idDivisionUbicacion;
    }

    public void setIdDivisionUbicacion(Integer idDivisionUbicacion) {
        this.idDivisionUbicacion = idDivisionUbicacion;
    }

    public SelectOneListbox getListubicaciones() {
        return listubicaciones;
    }

    public void setListubicaciones(SelectOneListbox listubicaciones) {
        this.listubicaciones = listubicaciones;
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
    
    

    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
