/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CargoDAO;
import com.pasantia.dao.CatalogoVentaDAO;
import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.dao.SexoDAO;
import com.pasantia.dao.TipoIdentificacionDAO;
import com.pasantia.dao.TipoPersonaDAO;
import com.pasantia.entidades.Cargo;
import com.pasantia.entidades.CatalogoVenta;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import com.pasantia.entidades.TipoPersona;
import com.pasantia.excepciones.ExcepcionPersona;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author David Timana
 */
@Named(value = "gestionarUsuarioBean")
@SessionScoped
public class GestionarUsuarioBean extends CombosComunes implements Serializable {

    private Integer paisSeleccionado, tipoIdentificacionSeleccionada, sexoSeleccionado, departamentoSeleccionado, ciudadSeleccionado, zoom, tipoPersonaSeleccionado,
            cargoSeleccionado, catalogoSeleccionado;
    private Sexo sexo;
    private TipoPersona tipoPersona;
    private TipoIdentificacion tipoIdentificacion;
    private Ciudad ciudad;
    private Cargo cargo;
    private CatalogoVenta catalogoVenta;
    private Persona persona;
    private Boolean deshabilitarDepartamento, deshabilitarCiudad, abrirSubir,fotoSubida;
    private String estErrDepartamento, estErrCiudad, estErrNombre, estErrapellido, estErrsexo, estErrtipoidenti, estErrfecha, estErrtelefono,
            estErrmovil, estErremail, estErrPais, stErrbarrio, stErrdireccion, estErrMapa,estErrTipPerson,estErrCargo,estErrCatalogo;    
    private Double latitud, longitud;
    private LatLng coordenadas;
    private Integer contadorMapa;
    private InputStream fotoSeleccionada;
    private String nombre_foto;
    private String botonCargar, lblCargar, mensajeCarga;
    private String rutaFotoCargar;
    private String ocultarCargo;
    private String ocultarCatalogo;
    private String urlTemporal;
    private MapModel modMapa;    
    
    private static Logger logger = Logger.getLogger(GestionarUsuarioBean.class.getName());
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    CiudadDAO ciudadDAO;
    @Inject
    SexoDAO sexoDAO;
    @Inject
    TipoIdentificacionDAO tipoIdentificacionDAO;
    @Inject
    TipoPersonaDAO tipoPersonaDAO;
    @Inject
    PersonaDAO personaDAO;
    @Inject
    CargoDAO cargoDAO;
    @Inject
    CatalogoVentaDAO catalogoVentaDAO;

    @PostConstruct
    public void Init() {
        logger.info("***********Iniciando");
        logger.info("*****************Cargando Combo Paises");
        cargarComboPais();
        logger.info("*****************Fin Cargando Combo Paises");
        logger.info("*****************Cargando Combo TipoIdentificacion");
        cargarComboTipoIdentificacion();
        logger.info("*****************Fin Cargando Combo TipoIdentificacion");
        logger.info("*****************Cargando Combo Sexo");
        cargarComboSexo();
        logger.info("*****************Fin Cargando Combo Sexo");
        logger.info("*****************Cargando Combo Tipo Persona");
        cargarComboTiposPersonas();
        logger.info("*****************Fin Cargando Combo Tipo Persona");
        logger.info("*****************Cargando Combo Cargos");
        cargarComboCargo();
        logger.info("*****************Fin Cargando Combo Cargos");
        logger.info("*****************Cargando Combo Catalogos Venta");
        cargarComboCatalogosVenta();
        logger.info("*****************Fin Cargando Combo Catalogos Venta");
    }
    
    public String navegarWizard(FlowEvent event){
        Utilidad.actualizarElemento("paso1");
        Utilidad.actualizarElemento("paso2");
        if(contadorMapa!=0){
            zoom=zoom+3;
        }
       return event.getNewStep();
    }
    
    public void guardar() {
        if (validarDatos()) {
            logger.info("Validacion correcta");
        } else {
            logger.info("Validacion incorrecta");
        }
    }

    public Boolean validarDatos() {    

              Boolean result=false;
        

            //Valido el primer nombre de la persona
            if (Utilidad.cadenaVacia(persona.getPnombre())) {
                estErrNombre = Utilidad.estilosErrorInput();
                Utilidad.actualizarElemento("txtpnombre");
                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Nombre requerido.");
                logger.info("****************Error...Primer Nombre vacio");
                result=false;

            } else {
                estErrNombre = "";
                Utilidad.actualizarElemento("txtpnombre");

                //Valido el primer apellido de la persona
                if (Utilidad.cadenaVacia(persona.getPapellido())) {
                    estErrapellido = Utilidad.estilosErrorInput();
                    Utilidad.actualizarElemento("txtpapellido");
                    Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Apellido requerido.");
                    logger.info("****************Error...Primer Apellido vacio");
                    result=false;
                } else {
                    estErrapellido = "";
                    Utilidad.actualizarElemento("txtpapellido");

                    //Valido que se haiga selecionado algun sexo
                    if (sexo==null) {
                        estErrsexo = Utilidad.estilosErrorInput();
                        Utilidad.actualizarElemento("cmbsexper");
                        Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Sexo requerido.");
                        logger.info("****************Error...Sexo no seleccionado");
                        result=false;
                    } else {                        
                        estErrsexo = "";
                        Utilidad.actualizarElemento("cmbsexper");

                        //Valido que se haiga seleccionado algun tipo de identificacion
                        if (tipoIdentificacion == null) {
                            estErrtipoidenti = Utilidad.estilosErrorInput();
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Tipo Identificación requerido.");
                            logger.info("****************Error...Tipo Identificació no seleccionado");
                            result=false;
                        } else {                            
                            estErrtipoidenti = "";

                            //Valido que se haiga seleccionado alguna fecha de nacimiento
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            if (persona.getFechaNacimiento() == null) {
                                estErrfecha = Utilidad.estilosErrorInput();
                                Utilidad.actualizarElemento("txtfechanacimiento");
                                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Fecha Nacimiento requerida.");
                                logger.info("****************Error...Fecha Nacimiento no seleccionada");
                                result=false;
                            } else {


                                estErrfecha = "";
                                Utilidad.actualizarElemento("txtfechanacimiento");

                                //Valido el telefono
                                if (Utilidad.cadenaVacia(persona.getTelefono())) {
                                    estErrtelefono = Utilidad.estilosErrorInput();
                                    Utilidad.actualizarElemento("txttelefonoper");
                                    Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono requerido.");
                                    logger.info("****************Error...Telefono vacio");
                                    result=false;
                                } else {
                                    estErrtelefono = "";
                                    Utilidad.actualizarElemento("txttelefonoper");

                                    //Valido el telefono movil
                                    if (Utilidad.cadenaVacia(persona.getMovil())) {
                                        estErrmovil = Utilidad.estilosErrorInput();
                                        Utilidad.actualizarElemento("txtmovilper");
                                        Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono Movil requerido.");
                                        logger.info("****************Error...Telefono Movil vacio");
                                        result=false;
                                    } else {
                                        estErrmovil = "";
                                        Utilidad.actualizarElemento("txtmovilper");

                                        //Valido el email
                                        if (Utilidad.cadenaVacia(persona.getEmail())) {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");
                                            Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: E-mail requerido.");
                                            logger.info("****************Error...E-maill vacio");
                                            result=false;

                                        } else {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");

                                            //Valido el pais seleccionado
                                            if (paisSeleccionado == null) {
                                                estErrPais = Utilidad.estilosErrorInput();
                                                Utilidad.actualizarElemento("cmbPaisPersona");
                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección País requerido.");
                                                logger.info("****************Error...seleccion pais vacio");
                                                result=false;
                                            } else {
                                                estErrPais = "";
                                                Utilidad.actualizarElemento("cmbPaisPersona");

                                                //Valido el departamento seleccionado
                                                if (departamentoSeleccionado == null) {
                                                    estErrDepartamento = Utilidad.estilosErrorInput();
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");
                                                    Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Departamento requerido.");
                                                    logger.info("****************Error...seleccion departamento vacio");
                                                    result=false;
                                                } else {
                                                    estErrDepartamento = "";
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");

                                                    //Valido la ciudad seleccionada
                                                    if (ciudad == null) {
                                                        estErrCiudad = Utilidad.estilosErrorInput();
                                                        Utilidad.actualizarElemento("cmbciudadbper");
                                                        Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Ciudad requerida.");
                                                        logger.info("****************Error...seleccion ciudad vacio");
                                                        result=false;
                                                    } else {
                                                        
                                                        estErrCiudad = "";
                                                        Utilidad.actualizarElemento("cmbciudadbper");

                                                        //Valido el barrio 
                                                        if (Utilidad.cadenaVacia(persona.getBarrio())) {
                                                            stErrbarrio = Utilidad.estilosErrorInput();
                                                            Utilidad.actualizarElemento("txtbarrioper");
                                                            Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Barrio requerida.");
                                                            logger.info("****************Error...barrio vacio");
                                                            result=false;
                                                        } else {
                                                            stErrbarrio = "";
                                                            Utilidad.actualizarElemento("txtbarrioper");

                                                            //Valido la direccion
                                                            if (Utilidad.cadenaVacia(persona.getDireccion())) {
                                                                stErrdireccion = Utilidad.estilosErrorInput();
                                                                Utilidad.actualizarElemento("txtdireccionper");
                                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Dirección requerida.");
                                                                logger.info("****************Error...Dirección vacio");
                                                                result=false;
                                                            } else {
                                                                stErrdireccion = "";
                                                                Utilidad.actualizarElemento("txtdireccionper");

                                                                //Valido que se haiga seleccionado el punto de la direccion en el mapa
                                                                if (contadorMapa == 0) {
                                                                    estErrMapa = Utilidad.estilosErrorInput();
                                                                    Utilidad.actualizarElemento("lblmapPersonas");
                                                                    Utilidad.mensajeError("SICOVI", "Sección Geolocalización Usuario: Seleccion de la direccion en el mapa requerida.");
                                                                    logger.info("****************Error...mapa vacio");
                                                                    result=false;
                                                                } else {
                                                                    estErrMapa = "";
                                                                    Utilidad.actualizarElemento("lblmapPersonas");                             
                                                                    logger.info("****************Aqui validamos el mapa");
                                                                    if (tipoPersona == null) {
                                                                        estErrTipPerson = Utilidad.estilosErrorInput();
                                                                        Utilidad.actualizarElemento("cmbTipoPersona");
                                                                        Utilidad.mensajeError("SICOVI", "Configuración Usuario - Paso 2: Seleccion del tipo de persona requerido.");
                                                                        logger.info("****************Error...tipo persona vacio");
                                                                        result = false;
                                                                    } else {
                                                                        estErrTipPerson = "";
                                                                        Utilidad.actualizarElemento("cmbTipoPersona");
                                                                        result = true;
                                                                    }
                                                                    
                
                                                                }


                                                            }
                                                        }

                                                    }
                                                }

                                            }

                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            }

        

        
        
        return result; 



    }

    public void puntoSeleccionadoMapa(PointSelectEvent event) {
        coordenadas = event.getLatLng();
        contadorMapa++;
        modMapa.addOverlay(new Marker(coordenadas));
        latitud=coordenadas.getLat();
        longitud=coordenadas.getLng();
        zoom=zoom+3;
        Utilidad.actualizarElemento("mapPersonas");
        Utilidad.actualizarElemento("lbllan");
        Utilidad.actualizarElemento("lbllon");
        logger.log(Level.INFO, "la longitud y la latitud seleccionada es la siguiente{0} y la longitud es--> {1}", new Object[]{coordenadas.getLat(), coordenadas.getLng()});        

    }

    public void cargardDepartamentoxPais() {
        logger.log(Level.INFO, "El pais seleccionado es el siguente..> {0}", paisSeleccionado);
        if (paisSeleccionado != null) {
            cargarComboDepartamento(paisSeleccionado);
            deshabilitarDepartamento = false;
        } else {
            deshabilitarDepartamento = true;
            deshabilitarCiudad = true;
        }
        Utilidad.actualizarElemento("cmbdepartamentobper");
        Utilidad.actualizarElemento("cmbciudadbper");

    }

    public void cargarCiudadesxDepartamento() {
        Departamento d = new Departamento();
        if (departamentoSeleccionado != null) {
            cargarComboCiudad(departamentoSeleccionado);
            d = departamentoDAO.buscarDepartamentoporIdUno(departamentoSeleccionado);
            if (d.getLatitud() != null && d.getLongitud() != null) {
                logger.info("***************Inicia la asignacion de coordenadas departamento al mapa");
                latitud = d.getLatitud();
                longitud = d.getLongitud();
                zoom = 10;
                Utilidad.actualizarElemento("mapPersonas");
                logger.info("***************Fin la asignacion de coordenadas departamento al mapa");
            } else {
                logger.info("***************Error. Departamento sin coordenadas");
            }

            deshabilitarCiudad = false;
        } else {
            deshabilitarCiudad = true;
        }

        Utilidad.actualizarElemento("cmbciudadbper");
    }

    public void cambiarMapaxCiudad() {
        asignarCiudad();
        Ciudad c = new Ciudad();
        if (ciudadSeleccionado != null) {
            c = ciudadDAO.buscarxid(ciudadSeleccionado);
            if (c.getLatitud() != null && c.getLongitud() != null) {
                latitud = c.getLatitud();
                longitud = c.getLongitud();
                zoom = 12;
                Utilidad.actualizarElemento("mapPersonas");
                logger.info("***************Fin la asignacion de coordenadas ciudad al mapa");
            } else {
                logger.info("***************Error. Ciudad sin coordenadas");
            }
        } else {
        }
    }

    public void abrirSubirFoto() {
        abrirSubir = true;
        Utilidad.actualizarElemento("dlgsubirFoto");
    }

    public void cancelarSubirFoto() {
        fotoSubida=false;
        abrirSubir = false;
        Utilidad.actualizarElemento("dlgsubirFoto");
        nombre_foto = "";
        fotoSeleccionada = null;
    }

    public void fincargaFoto(FileUploadEvent event) throws IOException, InterruptedException {
        
        logger.info("**************Iniciamos seleccion foto");
        nombre_foto = event.getFile().getFileName();
        fotoSeleccionada = event.getFile().getInputstream();
        logger.log(Level.INFO, "El nombre de la imagen seleccionada es-->{0}", nombre_foto);
        botonCargar = "display:none";
        mensajeCarga = "Foto Cargada: " + nombre_foto;
        Boolean result=false;
        try{
            Utilidad.copiarArchivo(nombre_foto, fotoSeleccionada, urlTemporal);
            result=true;
        }catch(Exception e){
            result=false;
            logger.info("*************Error al copiar el archivo");
        }
        if(result){
            rutaFotoCargar="../../temp/"+nombre_foto;
            logger.log(Level.INFO, "La ruta temporal de la foto es la siguiente--->{0}", rutaFotoCargar);
            
        }
        Utilidad.mensajeInfo("SICOVI", "Foto: " + nombre_foto + ". Cargada Correctamente");
        fotoSubida=true;
        logger.info("**************Fin seleccion foto");
        abrirSubir = false;
        Utilidad.actualizarElemento("dlgsubirFoto");        
        Utilidad.actualizarElemento("imgfotoCargar");
        Utilidad.actualizarElemento("btncargar");
        Utilidad.actualizarElemento("lblmensajefoto");        
        Thread.sleep(4000);
        Utilidad.actualizarElemento("imgfotoCargar");

    }  

    public void cambiarAvatar() {
        asignarSexo();
        if (!fotoSubida) {
            if (sexoSeleccionado == 1) {
                rutaFotoCargar = "../../FotosUsuarios/sinfotoh.jpeg";
            } else {
                rutaFotoCargar = "../../FotosUsuarios/sinfotom.jpeg";
            }
            Utilidad.actualizarElemento("imgfotoCargar");
        }
    }

    public void ocultarCombos() {
        
        asignarTipoPersona();
        logger.log(Level.INFO, "el tipo Persona Seleccionado es el siguiente-->{0}", tipoPersonaSeleccionado);
        ocultarCatalogo = "display:none";
                        ocultarCargo = "display:none";
        if (tipoPersonaSeleccionado != null) {

            TipoPersona t = tipoPersonaDAO.buscarTipoPersonasxId(tipoPersonaSeleccionado);
            
            if (t != null) {
                if (t.getNombreTipoPersona().equals("Cliente Interno") || t.getNombreTipoPersona().equals("Comandante Batallon")
                        || t.getNombreTipoPersona().equals("Comandante Casino") || t.getNombreTipoPersona().equals("Comandante Division")
                        || t.getNombreTipoPersona().equals("Vendedor Casino Interno")) {

                    ocultarCatalogo = "display:none";
                    ocultarCargo = "display:block";

                } else {
                    if (t.getNombreTipoPersona().equals("Cliente Externo") || t.getNombreTipoPersona().equals("Vendedor Casino Externo")) {
                        ocultarCatalogo = "display:none";
                        ocultarCargo = "display:none";
                    } else {
                        if (t.getNombreTipoPersona().equals("Vendedor Proveedor")) {
                            ocultarCatalogo = "display:block";
                            ocultarCargo = "display:none";
                        } else {
                            ocultarCatalogo = "display:none";
                            ocultarCargo = "display:none";
                        }
                    }

                }

            }




        } else {
            ocultarCatalogo = "display:none";
            ocultarCargo = "display:none";
        }
        Utilidad.actualizarElemento("lblcatalogo");
        Utilidad.actualizarElemento("lblcargo");
        Utilidad.actualizarElemento("cmbCargo");
        Utilidad.actualizarElemento("cmbCatalogos");
    }
    
    public void asignarSexo(){
        if(sexoSeleccionado!=null){
            sexo = sexoDAO.buscarSexoxId(sexoSeleccionado);
        }        
    }
    
    public void asignarTipoIdentificacion(){
        if(tipoIdentificacionSeleccionada!=null){
            tipoIdentificacion=tipoIdentificacionDAO.buscarTipoIdentificacionxId(tipoIdentificacionSeleccionada);
        }
    }
    
    public void asignarCiudad(){
        if(ciudadSeleccionado!=null){
            ciudad=ciudadDAO.buscarxid(ciudadSeleccionado);            
        }
    }
    
   public void asignarTipoPersona(){
       if(tipoPersonaSeleccionado!=null){
           tipoPersona=tipoPersonaDAO.buscarTipoPersonasxId(tipoPersonaSeleccionado);
       }
   }
   
   
   public void asignarCargo(){
       if(cargoSeleccionado!=null){
           cargo=cargoDAO.buscarCargoporId(cargoSeleccionado);
       }
   }
   
   public void asignarCatalogoVenta(){
       if(catalogoSeleccionado!=null){
           catalogoVenta=catalogoVentaDAO.buscarCatalogoxId(catalogoSeleccionado);
       }
   }
    
    public GestionarUsuarioBean() {
        sexo = new Sexo();
        tipoPersona = new TipoPersona();
        tipoIdentificacion = new TipoIdentificacion();
        ciudad = new Ciudad();
        cargo = new Cargo();
        catalogoVenta = new CatalogoVenta();
        persona = new Persona();
        deshabilitarCiudad = false;
        deshabilitarDepartamento = false;
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
        estErrMapa = "";
        estErrTipPerson="";
        estErrCargo="";
        estErrCatalogo="";
        zoom = 6;
        latitud = 4.599047;
        longitud = -74.080917;
        coordenadas = new LatLng(latitud, longitud);
        contadorMapa = 0;
        abrirSubir = false;
        botonCargar = "display:block";
        lblCargar = "display:block";
        mensajeCarga = "Sin Selección de Foto.";
        rutaFotoCargar = "../../FotosUsuarios/sinfotoh.jpeg";
        ocultarCargo = "display:none";
        ocultarCatalogo = "display:none";
        urlTemporal="/home/jbuitron/NetBeansProjects/Pasantia/NetBeansProjects/Pasantia/src/main/webapp/temp/";
        fotoSubida=false;
        modMapa=new DefaultMapModel();

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

    public String getEstErrMapa() {
        return estErrMapa;
    }

    public void setEstErrMapa(String estErrMapa) {
        this.estErrMapa = estErrMapa;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getContadorMapa() {
        return contadorMapa;
    }

    public void setContadorMapa(Integer contadorMapa) {
        this.contadorMapa = contadorMapa;
    }

    public Integer getTipoPersonaSeleccionado() {
        return tipoPersonaSeleccionado;
    }

    public void setTipoPersonaSeleccionado(Integer tipoPersonaSeleccionado) {
        this.tipoPersonaSeleccionado = tipoPersonaSeleccionado;
    }

    public Boolean getAbrirSubir() {
        return abrirSubir;
    }

    public void setAbrirSubir(Boolean abrirSubir) {
        this.abrirSubir = abrirSubir;
    }

    public InputStream getFotoSeleccionada() {
        return fotoSeleccionada;
    }

    public void setFotoSeleccionada(InputStream fotoSeleccionada) {
        this.fotoSeleccionada = fotoSeleccionada;
    }

    public String getNombre_foto() {
        return nombre_foto;
    }

    public void setNombre_foto(String nombre_foto) {
        this.nombre_foto = nombre_foto;
    }

    public String getBotonCargar() {
        return botonCargar;
    }

    public void setBotonCargar(String botonCargar) {
        this.botonCargar = botonCargar;
    }

    public String getLblCargar() {
        return lblCargar;
    }

    public void setLblCargar(String lblCargar) {
        this.lblCargar = lblCargar;
    }

    public String getMensajeCarga() {
        return mensajeCarga;
    }

    public void setMensajeCarga(String mensajeCarga) {
        this.mensajeCarga = mensajeCarga;
    }

    public String getRutaFotoCargar() {
        return rutaFotoCargar;
    }

    public void setRutaFotoCargar(String rutaFotoCargar) {
        this.rutaFotoCargar = rutaFotoCargar;
    }

    public Integer getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(Integer cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public Integer getCatalogoSeleccionado() {
        return catalogoSeleccionado;
    }

    public void setCatalogoSeleccionado(Integer catalogoSeleccionado) {
        this.catalogoSeleccionado = catalogoSeleccionado;
    }

    public String getOcultarCargo() {
        return ocultarCargo;
    }

    public void setOcultarCargo(String ocultarCargo) {
        this.ocultarCargo = ocultarCargo;
    }

    public String getOcultarCatalogo() {
        return ocultarCatalogo;
    }

    public void setOcultarCatalogo(String ocultarCatalogo) {
        this.ocultarCatalogo = ocultarCatalogo;
    }

    public String getUrlTemporal() {
        return urlTemporal;
    }

    public void setUrlTemporal(String urlTemporal) {
        this.urlTemporal = urlTemporal;
    }

    public Boolean getFotoSubida() {
        return fotoSubida;
    }

    public void setFotoSubida(Boolean fotoSubida) {
        this.fotoSubida = fotoSubida;
    }

    public MapModel getModMapa() {
        return modMapa;
    }

    public void setModMapa(MapModel modMapa) {
        this.modMapa = modMapa;
    }

    public String getEstErrTipPerson() {
        return estErrTipPerson;
    }

    public void setEstErrTipPerson(String estErrTipPerson) {
        this.estErrTipPerson = estErrTipPerson;
    }

    public String getEstErrCargo() {
        return estErrCargo;
    }

    public void setEstErrCargo(String estErrCargo) {
        this.estErrCargo = estErrCargo;
    }

    public String getEstErrCatalogo() {
        return estErrCatalogo;
    }

    public void setEstErrCatalogo(String estErrCatalogo) {
        this.estErrCatalogo = estErrCatalogo;
    }
    
    
    
    
    
    
    
    
}
