/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.entidades.Cargo;
import com.pasantia.entidades.CatalogoVenta;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import com.pasantia.entidades.TipoPersona;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author David Timana
 */
@Named(value = "gestionarUsuarioBean")
@SessionScoped
public class GestionarUsuarioBean extends CombosComunes implements Serializable{


    private Integer paisSeleccionado,tipoIdentificacionSeleccionada,sexoSeleccionado,departamentoSeleccionado,ciudadSeleccionado,zoom;
    private Sexo sexo;
    private TipoPersona tipoPersona;
    private TipoIdentificacion tipoIdentificacion;
    private Ciudad ciudad;
    private Cargo cargo;
    private CatalogoVenta catalogoVenta;
    private Persona persona;
    private Boolean deshabilitarDepartamento,deshabilitarCiudad,skip;
    private String estErrDepartamento,estErrCiudad,estErrNombre,estErrapellido,estErrsexo,estErrtipoidenti,estErrfecha,estErrtelefono,
            estErrmovil,estErremail,estErrPais,stErrbarrio,stErrdireccion;
    private Double latitud,longitud;
    
    private static Logger logger = Logger.getLogger(GestionarUsuarioBean.class.getName());  
    
    
    
    @PostConstruct
    public void Init(){
        System.out.println("***********Inicianilizando"); 
        System.out.println("*****************Cargando Combo Paises");
        cargarComboPais();
        System.out.println("*****************Fin Cargando Combo Paises");
        System.out.println("*****************Cargando Combo TipoIdentificacion");
        cargarComboTipoIdentificacion();        
        System.out.println("*****************Fin Cargando Combo TipoIdentificacion");
        System.out.println("*****************Cargando Combo Sexo");
        cargarComboSexo();        
        System.out.println("*****************Fin Cargando Combo Sexo");
        
    }
    
     public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "prueba";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  
    
    public void cargardDepartamentoxPais(){
        System.out.println("El pais seleccionado es el siguente..> "+paisSeleccionado);
        if(paisSeleccionado!=null){            
            System.out.println("1");
            cargarComboDepartamento(paisSeleccionado);
            deshabilitarDepartamento=false;
            System.out.println("8");
        }else{
            deshabilitarDepartamento=true;
            deshabilitarCiudad=true;
        }
        Utilidad.actualizarElemento("cmbdepartamentobper");
        Utilidad.actualizarElemento("cmbciudadbper");
        System.out.println("9");
        
    }
    
    public void cargarCiudadesxDepartamento(){
        if(departamentoSeleccionado!=null){
            cargarComboCiudad(departamentoSeleccionado);
            deshabilitarCiudad=false;
        }else{
            deshabilitarCiudad=true;
        }
        Utilidad.actualizarElemento("cmbciudadbper");
    }

    public GestionarUsuarioBean() {
        sexo = new Sexo();
        tipoPersona = new TipoPersona();
        tipoIdentificacion = new TipoIdentificacion();
        ciudad = new Ciudad();
        cargo = new Cargo();
        catalogoVenta = new CatalogoVenta();
        persona = new Persona();
        deshabilitarCiudad=false;
        deshabilitarDepartamento=false;
        estErrDepartamento = "";
        estErrCiudad = "";
        estErrNombre = "";
        estErrapellido = "";
        estErrsexo = "";
        estErrtipoidenti = "";
        estErrfecha = "";
        estErrtelefono = "";
        estErrmovil = "";
        estErremail = "";
        estErrPais = "";
        stErrbarrio = "";
        stErrdireccion = "";
        zoom = 6;
        latitud = 4.599047;
        longitud = -74.080917;
    }
    
   

    public Integer getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(Integer paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    public Integer getTipoIdentificacionSeleccionada() {
        return tipoIdentificacionSeleccionada;
    }

    public void setTipoIdentificacionSeleccionada(Integer tipoIdentificacionSeleccionada) {
        this.tipoIdentificacionSeleccionada = tipoIdentificacionSeleccionada;
    }

    public Integer getSexoSeleccionado() {
        return sexoSeleccionado;
    }

    public void setSexoSeleccionado(Integer sexoSeleccionado) {
        this.sexoSeleccionado = sexoSeleccionado;
    }

    public Integer getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Integer departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Integer getCiudadSeleccionado() {
        return ciudadSeleccionado;
    }

    public void setCiudadSeleccionado(Integer ciudadSeleccionado) {
        this.ciudadSeleccionado = ciudadSeleccionado;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public CatalogoVenta getCatalogoVenta() {
        return catalogoVenta;
    }

    public void setCatalogoVenta(CatalogoVenta catalogoVenta) {
        this.catalogoVenta = catalogoVenta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Boolean getDeshabilitarDepartamento() {
        return deshabilitarDepartamento;
    }

    public void setDeshabilitarDepartamento(Boolean deshabilitarDepartamento) {
        this.deshabilitarDepartamento = deshabilitarDepartamento;
    }

    public Boolean getDeshabilitarCiudad() {
        return deshabilitarCiudad;
    }

    public void setDeshabilitarCiudad(Boolean deshabilitarCiudad) {
        this.deshabilitarCiudad = deshabilitarCiudad;
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

    public String getEstErrNombre() {
        return estErrNombre;
    }

    public void setEstErrNombre(String estErrNombre) {
        this.estErrNombre = estErrNombre;
    }

    public String getEstErrapellido() {
        return estErrapellido;
    }

    public void setEstErrapellido(String estErrapellido) {
        this.estErrapellido = estErrapellido;
    }

    public String getEstErrsexo() {
        return estErrsexo;
    }

    public void setEstErrsexo(String estErrsexo) {
        this.estErrsexo = estErrsexo;
    }

    public String getEstErrtipoidenti() {
        return estErrtipoidenti;
    }

    public void setEstErrtipoidenti(String estErrtipoidenti) {
        this.estErrtipoidenti = estErrtipoidenti;
    }

    public String getEstErrfecha() {
        return estErrfecha;
    }

    public void setEstErrfecha(String estErrfecha) {
        this.estErrfecha = estErrfecha;
    }

    public String getEstErrtelefono() {
        return estErrtelefono;
    }

    public void setEstErrtelefono(String estErrtelefono) {
        this.estErrtelefono = estErrtelefono;
    }

    public String getEstErrmovil() {
        return estErrmovil;
    }

    public void setEstErrmovil(String estErrmovil) {
        this.estErrmovil = estErrmovil;
    }

    public String getEstErremail() {
        return estErremail;
    }

    public void setEstErremail(String estErremail) {
        this.estErremail = estErremail;
    }

    public String getEstErrPais() {
        return estErrPais;
    }

    public void setEstErrPais(String estErrPais) {
        this.estErrPais = estErrPais;
    }

    public String getStErrbarrio() {
        return stErrbarrio;
    }

    public void setStErrbarrio(String stErrbarrio) {
        this.stErrbarrio = stErrbarrio;
    }

    public String getStErrdireccion() {
        return stErrdireccion;
    }

    public void setStErrdireccion(String stErrdireccion) {
        this.stErrdireccion = stErrdireccion;
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

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
}
