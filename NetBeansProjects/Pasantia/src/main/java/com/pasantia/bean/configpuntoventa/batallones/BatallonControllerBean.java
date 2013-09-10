/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.dao.BatallonDAO;
import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Divisiones;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author David Orlando Timana
 */
@Named(value = "batallonControllerBean")
@SessionScoped
public class BatallonControllerBean extends CombosComunes implements Serializable {

    private Batallon batallon;
    private Divisiones divisiones;
    private Ciudad ciudad;
    private Integer divisionSelec, paisSelec, departamentoSelec, ciudadSelec;
    private Boolean deshabilitarAll, deshabilitarCiudad, deshabilitarDepartamento, estaEditando;
    private String ocultarGuardar, ubicacionEdit, ubicacionLoad, ocultarNuevo, ocultarEdit, ocultarCancelar, estErrNombre, estErrDireccion,
            estErrbarrio, estErrTelefono, estErrDivision, estErrPais, estErrDepartamento, estErrCiudad;
    private Double latitud;
    private Double longitud;
    private Integer zoom;
    private MapModel modMapa;
    
    @Inject
    BatallonDAO batallonDAO;
    @Inject
    AgregarBatallonesBean agregarBatallonesBean;
    @Inject
    EditarBatallonesBean editarbatallonesBean;
    @Inject
    DivisionesDAO divisionesDAO;
    @Inject
    CiudadDAO ciudadDAO;
    

    @PostConstruct
    public void cargarUltimoBatallon() {
        System.out.println("*********************Cargando Combo pais*********************************");
        cargarComboPais();
        System.out.println("*********************Fin Cargando Combo pais*********************************");
        System.out.println("*********************Cargando Combo Divisiones Asociadas*********************************");
        cargarComboDivisionesAsociadas();
        System.out.println("*********************Fin Cargando Combo Divisiones Asociadas*********************************");
        System.out.println("*********************Cargando Ultimo Batallon Ingresado********************");
        batallon = batallonDAO.buscarUltimo();
        divisionSelec = batallon.getDivisiones().getIdDivisiones();
        paisSelec = batallon.getCiudad().getDepartamento().getPais().getIdPais();
        departamentoSelec = batallon.getCiudad().getDepartamento().getIdDepartamento();
        ciudadSelec = batallon.getCiudad().getIdCiudad();
        deshabilitarAll = true;
        deshabilitarCiudad = true;
        deshabilitarDepartamento = true;
        ocultarGuardar = "display:none";
        ciudad = batallon.getCiudad();
        divisiones = batallon.getDivisiones();
        ubicacionEdit = "display:none";
        ubicacionLoad = "display:block";
        ocultarEdit = "display:block";
        ocultarNuevo = "display:block";
        estaEditando = false;
        ocultarCancelar = "display:none";
        if (batallon.getCiudad().getLatitud() != null && batallon.getCiudad().getLongitud() != null) {
            latitud = batallon.getCiudad().getLatitud();
            longitud = batallon.getCiudad().getLongitud();
            System.out.println("La latitud con ciudad quedo--> " + latitud + " la longitud quedo--> " + longitud);
            LatLng coordenadaTemp = new LatLng(batallon.getCiudad().getLatitud(), batallon.getCiudad().getLongitud());
            modMapa.addOverlay(new Marker(coordenadaTemp));
            zoom = 15;
        } else {
            latitud = batallon.getCiudad().getDepartamento().getLatitud();
            longitud = batallon.getCiudad().getLongitud();
            System.out.println("La latitud con Departamento quedo--> " + latitud + " la longitud quedo--> " + longitud);
            LatLng coordenadaTemp = new LatLng(batallon.getCiudad().getLatitud(), batallon.getCiudad().getLongitud());
            modMapa.addOverlay(new Marker(coordenadaTemp));
            zoom = 10;
        }
        System.out.println("*********************Fin Ultimo Batallon Ingresado********************");


    }
    public void nuevoBatallon() {
        batallon = new Batallon();
        deshabilitarAll = false;
        estaEditando = false;
        ocultarEdit = "display:none";
        ocultarNuevo = "display:none";
        ocultarGuardar = "display:block";
        ubicacionEdit = "display:block";
        ubicacionLoad = "display:none";
        ocultarCancelar = "display:block";
        deshabilitarCiudad = true;
        deshabilitarDepartamento = true;
        modMapa = new DefaultMapModel();
        latitud = 4.599047;
        longitud = -74.080917;
        LatLng coordenadaTemp = new LatLng(latitud, longitud);
        modMapa.addOverlay(new Marker(coordenadaTemp));
        zoom = 6;
        Utilidad.actualizarElemento("btncancelarbat");
        Utilidad.actualizarElemento("btnnuevobat");
        Utilidad.actualizarElemento("btneditarbat");
        Utilidad.actualizarElemento("mapbatallones");
        Utilidad.actualizarElemento("accordionBatallones");
        Utilidad.actualizarElemento("btnguardarBatallon");
    }
    public void editarBatallon() {
        deshabilitarAll = false;
        estaEditando = true;
        ocultarEdit = "display:none";
        ocultarNuevo = "display:none";
        deshabilitarCiudad = true;
        deshabilitarDepartamento = true;
        ocultarGuardar = "display:block";
        ubicacionEdit = "display:block";
        ubicacionLoad = "display:none";
        ocultarCancelar = "display:block";
        Utilidad.actualizarElemento("btncancelarbat");
        Utilidad.actualizarElemento("btnnuevobat");
        Utilidad.actualizarElemento("btneditarbat");
        Utilidad.actualizarElemento("accordionBatallones");
        Utilidad.actualizarElemento("btnguardarBatallon");
    }
    public void cancelarBatallon() {
        System.out.println("*********************Inicia cancelacion de edicion o nueva batallon dejando por defecto********************");
        estErrNombre = "";
        estErrDireccion = "";
        estErrbarrio = "";
        estErrTelefono = "";
        estErrDivision = "";
        estErrPais = "";
        estErrDepartamento = "";
        estErrCiudad = "";
        batallon = new Batallon();
        ocultarEdit = "display:block";
        ocultarNuevo = "display:block";
        deshabilitarAll = false;
        ocultarGuardar = "display:none";
        modMapa = new DefaultMapModel();
        cargarUltimoBatallon();
        ubicacionEdit = "display:none";
        ubicacionLoad = "display:block";
        ocultarCancelar = "display:none";
        estaEditando = false;
        Utilidad.actualizarElemento("btncancelarbat");
        Utilidad.actualizarElemento("mapbatallones");
        Utilidad.actualizarElemento("btnnuevobat");
        Utilidad.actualizarElemento("btneditarbat");
        Utilidad.actualizarElemento("btnguardarBatallon");
        Utilidad.actualizarElemento("accordionBatallones");
        System.out.println("*********************Fin cancelacion de edicion o nueva batallon dejando por defecto********************");

    }
    public void limpiarObjetos() {
        batallon = new Batallon();
        divisiones = new Divisiones();
        ciudad = new Ciudad();
    }
    public void guardarBatallon() {
        
        if(validarDatos()){            
            batallon.setCiudad(ciudad);
            batallon.setDivisiones(divisiones);
            System.out.println("El batallon que se manda a guardar es--> "+batallon.getIdBatallon());
            System.out.println("El batallon que se manda a guardar es--> "+batallon.getNombreBatallon());
            System.out.println("El batallon que se manda a guardar es--> "+batallon.getDivisiones().getNombreDivision());
            System.out.println("El batallon que se manda a guardar es--> "+batallon.getCiudad().getNombreCiudad());
            if(estaEditando){
                System.out.println("**********Se esta editando.");
                editarbatallonesBean.editarBatallon(batallon);
            }else{
                System.out.println("**********Se esta Guardando nuevo.");
                agregarBatallonesBean.guardarBatallon(batallon);
            }
            cargarUltimoBatallon();
            
        }else{
            System.err.println("*************Ocurrio algun error al realizar la validacion.");
        }

    }

    public boolean validarDatos() {
        boolean resultado = false;
        Integer validador = 0;

        if (batallon.getNombreBatallon().equals("")) {
            estErrNombre = Utilidad.estilosErrorInput();
            Utilidad.actualizarElemento("txtnombrebatallon");
            Utilidad.mensajeError("SICOVI", "Sección Datos Batallon: Nombre batallon requerido.");
            System.err.println("****************Error...nombre batallon vacio");
            validador++;
        } else {
            estErrNombre = "";
            Utilidad.actualizarElemento("txtnombrebatallon");
            if (batallon.getDireccion().equals("")) {
                estErrDireccion = Utilidad.estilosErrorInput();
                Utilidad.actualizarElemento("txtdireccionbatallon");
                Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto: Dirección batallon requerida.");
                System.err.println("****************Error...direccion batallon vacio");
                validador++;
            } else {
                estErrDireccion = "";
                Utilidad.actualizarElemento("txtdireccionbatallon");
                if (batallon.getBarrio().equals("")) {
                    estErrbarrio = Utilidad.estilosErrorInput();
                    Utilidad.actualizarElemento("txtbarriobatallon");
                    Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto: Barrio batallon requerido.");
                    System.err.println("****************Error...barrio batallon vacio");
                    validador++;
                } else {
                    estErrbarrio = "";
                    Utilidad.actualizarElemento("txtbarriobatallon");
                    if (batallon.getTelefono1().equals("")) {
                        estErrTelefono = Utilidad.estilosErrorInput();
                        Utilidad.actualizarElemento("txttelefono1batallon");
                        Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto: Telefono 1 requerido.");
                        System.err.println("****************Error...telefono batallon vacio");
                        validador++;
                    } else {
                        estErrTelefono = "";
                        Utilidad.actualizarElemento("txttelefono1batallon");
                        if (divisionSelec == null) {
                            estErrDivision = Utilidad.estilosErrorInput();
                            Utilidad.actualizarElemento("cmbdivisionbatallon");
                            Utilidad.mensajeError("SICOVI", "Sección División Perteneciente: Seleccion división requerida.");
                            System.err.println("****************Error...division batallon vacio");
                            validador++;
                        } else {
                            divisiones= divisionesDAO.buscarDivisionesporId(divisionSelec);
                            estErrDivision = Utilidad.estilosErrorInput();
                            Utilidad.actualizarElemento("cmbdivisionbatallon");
                            if (paisSelec == null) {
                                estErrPais = Utilidad.estilosErrorInput();
                                Utilidad.actualizarElemento("cmbpaisbat");
                                Utilidad.mensajeError("SICOVI", "Sección Ubicación batallón: Seleccion pais requerido.");
                                System.err.println("****************Error...pais batallon vacio");
                                validador++;
                            } else {
                                estErrPais = "";
                                Utilidad.actualizarElemento("cmbpaisbat");
                                if (departamentoSelec == null) {
                                    estErrDepartamento = Utilidad.estilosErrorInput();
                                    Utilidad.actualizarElemento("cmbdepartamentobat");
                                    Utilidad.mensajeError("SICOVI", "Sección Ubicación batallón: Seleccion departamento requerido.");
                                    System.err.println("****************Error...departamento batallon vacio");
                                    validador++;
                                } else {
                                    estErrDepartamento = "";
                                    Utilidad.actualizarElemento("cmbdepartamentobat");
                                    if (ciudadSelec == null) {
                                        estErrCiudad = Utilidad.estilosErrorInput();
                                        Utilidad.actualizarElemento("cmbciudadbat");
                                        Utilidad.mensajeError("SICOVI", "Sección Ubicación batallón: Seleccion Ciudad requerida.");
                                        System.err.println("****************Error...ciudad batallon vacio");
                                        validador++;
                                    } else {
                                        ciudad=ciudadDAO.buscarxid(ciudadSelec);
                                        estErrCiudad = "";
                                        Utilidad.actualizarElemento("cmbciudadbat");
                                        validador = 0;
                                        System.out.println("**********Formulario batallon validado correctamente.");
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        if (validador == 0) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }
    
    public void cargardDepartamentoxPais(){
        if(paisSelec!=null){            
            cargarComboDepartamento(paisSelec);
            deshabilitarDepartamento=false;
            
        }else{
            deshabilitarDepartamento=true;
            deshabilitarCiudad=true;
        }
        Utilidad.actualizarElemento("cmbdepartamentobat");
        Utilidad.actualizarElemento("cmbciudadbat");
        
    }
    
    public void cargarCiudadesxDepartamento(){
        if(departamentoSelec!=null){
            cargarComboCiudad(departamentoSelec);
            deshabilitarCiudad=false;
        }else{
            deshabilitarCiudad=true;
        }
        Utilidad.actualizarElemento("cmbciudadbat");
    }

    public BatallonControllerBean() {
        batallon = new Batallon();
        divisiones = new Divisiones();
        ciudad = new Ciudad();
        modMapa = new DefaultMapModel();
        deshabilitarCiudad = false;
        deshabilitarDepartamento = false;
        deshabilitarAll = false;
        ocultarGuardar = "display:none";
        zoom = 6;
        latitud = 4.599047;
        longitud = -74.080917;
        ubicacionEdit = "display:block";
        ubicacionLoad = "display:none";
        ocultarEdit = "display:block";
        ocultarNuevo = "display:block";
        ocultarCancelar = "display:none";
        estaEditando = false;
        estErrNombre = "";
        estErrDireccion = "";
        estErrbarrio = "";
        estErrTelefono = "";
        estErrDivision = "";
        estErrPais = "";
        estErrDepartamento = "";
        estErrCiudad = "";
    }

    public Batallon getBatallon() {
        return batallon;
    }

    public void setBatallon(Batallon batallon) {
        this.batallon = batallon;
    }

    public Divisiones getDivisiones() {
        return divisiones;
    }

    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getDivisionSelec() {
        return divisionSelec;
    }

    public void setDivisionSelec(Integer divisionSelec) {
        this.divisionSelec = divisionSelec;
    }

    public Integer getPaisSelec() {
        return paisSelec;
    }

    public void setPaisSelec(Integer paisSelec) {
        this.paisSelec = paisSelec;
    }

    public Integer getDepartamentoSelec() {
        return departamentoSelec;
    }

    public void setDepartamentoSelec(Integer departamentoSelec) {
        this.departamentoSelec = departamentoSelec;
    }

    public Integer getCiudadSelec() {
        return ciudadSelec;
    }

    public void setCiudadSelec(Integer ciudadSelec) {
        this.ciudadSelec = ciudadSelec;
    }

    public Boolean getDeshabilitarAll() {
        return deshabilitarAll;
    }

    public void setDeshabilitarAll(Boolean deshabilitarAll) {
        this.deshabilitarAll = deshabilitarAll;
    }

    public String getOcultarGuardar() {
        return ocultarGuardar;
    }

    public void setOcultarGuardar(String ocultarGuardar) {
        this.ocultarGuardar = ocultarGuardar;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public MapModel getModMapa() {
        return modMapa;
    }

    public void setModMapa(MapModel modMapa) {
        this.modMapa = modMapa;
    }

    public String getUbicacionEdit() {
        return ubicacionEdit;
    }

    public void setUbicacionEdit(String ubicacionEdit) {
        this.ubicacionEdit = ubicacionEdit;
    }

    public String getUbicacionLoad() {
        return ubicacionLoad;
    }

    public void setUbicacionLoad(String ubicacionLoad) {
        this.ubicacionLoad = ubicacionLoad;
    }

    public String getOcultarNuevo() {
        return ocultarNuevo;
    }

    public void setOcultarNuevo(String ocultarNuevo) {
        this.ocultarNuevo = ocultarNuevo;
    }

    public String getOcultarEdit() {
        return ocultarEdit;
    }

    public void setOcultarEdit(String ocultarEdit) {
        this.ocultarEdit = ocultarEdit;
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

    public String getOcultarCancelar() {
        return ocultarCancelar;
    }

    public void setOcultarCancelar(String ocultarCancelar) {
        this.ocultarCancelar = ocultarCancelar;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }

    public String getEstErrNombre() {
        return estErrNombre;
    }

    public void setEstErrNombre(String estErrNombre) {
        this.estErrNombre = estErrNombre;
    }

    public String getEstErrDireccion() {
        return estErrDireccion;
    }

    public void setEstErrDireccion(String estErrDireccion) {
        this.estErrDireccion = estErrDireccion;
    }

    public String getEstErrbarrio() {
        return estErrbarrio;
    }

    public void setEstErrbarrio(String estErrbarrio) {
        this.estErrbarrio = estErrbarrio;
    }

    public String getEstErrTelefono() {
        return estErrTelefono;
    }

    public void setEstErrTelefono(String estErrTelefono) {
        this.estErrTelefono = estErrTelefono;
    }

    public String getEstErrDivision() {
        return estErrDivision;
    }

    public void setEstErrDivision(String estErrDivision) {
        this.estErrDivision = estErrDivision;
    }

    public String getEstErrPais() {
        return estErrPais;
    }

    public void setEstErrPais(String estErrPais) {
        this.estErrPais = estErrPais;
    }

    public String getEstErrDepartamento() {
        return estErrDepartamento;
    }

    public void setEstErrDepartamento(String estErrDepartamento) {
        this.estErrDepartamento = estErrDepartamento;
    }

    public String getEstErrCiudad() {
        return estErrCiudad;
    }

    public void setEstErrCiudad(String estErrCiudad) {
        this.estErrCiudad = estErrCiudad;
    }
}
