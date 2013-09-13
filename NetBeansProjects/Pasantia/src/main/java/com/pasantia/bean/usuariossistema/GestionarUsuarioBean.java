/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
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
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.LatLng;

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
    private Boolean deshabilitarDepartamento, deshabilitarCiudad, abrirSubir;
    private String estErrDepartamento, estErrCiudad, estErrNombre, estErrapellido, estErrsexo, estErrtipoidenti, estErrfecha, estErrtelefono,
            estErrmovil, estErremail, estErrPais, stErrbarrio, stErrdireccion, estErrMapa;
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

    public String validarDatos(FlowEvent event) {
        String pestañaActual = event.getOldStep();
        String pestañaSiguiente = event.getNewStep();
        logger.log(Level.INFO, "pestaña donde estoy:{0}", pestañaActual);
        logger.log(Level.INFO, "Siguiente pestaña:{0}", pestañaSiguiente);
        logger.info("Validando datos");
        String pestañaRetornar = "";

        persona.setPnombre(persona.getPnombre().trim());
        persona.setPapellido(persona.getPapellido().trim());
        persona.setTelefono(persona.getTelefono().trim());
        persona.setMovil(persona.getMovil().trim());
        persona.setBarrio(persona.getBarrio().trim());
        persona.setDireccion(persona.getDireccion().trim());


        //Valido que este en la primera pestaña del wizard
        if (pestañaActual.equals("gestionusuarios")) {

            //Valido el primer nombre de la persona
            if (persona.getPnombre().equals("")) {
                estErrNombre = Utilidad.estilosErrorInput();
                Utilidad.actualizarElemento("txtpnombre");
                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Nombre requerido.");
                logger.info("****************Error...Primer Nombre vacio");
                pestañaRetornar = "gestionusuarios";

            } else {
                estErrNombre = "";
                Utilidad.actualizarElemento("txtpnombre");

                //Valido el primer apellido de la persona
                if (persona.getPapellido().equals("")) {
                    estErrapellido = Utilidad.estilosErrorInput();
                    Utilidad.actualizarElemento("txtpapellido");
                    Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Apellido requerido.");
                    logger.info("****************Error...Primer Apellido vacio");
                    pestañaRetornar = "gestionusuarios";
                } else {
                    estErrapellido = "";
                    Utilidad.actualizarElemento("txtpapellido");

                    //Valido que se haiga selecionado algun sexo
                    if (sexoSeleccionado == null) {
                        estErrsexo = Utilidad.estilosErrorInput();
                        Utilidad.actualizarElemento("cmbsexper");
                        Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Sexo requerido.");
                        logger.info("****************Error...Sexo no seleccionado");
                        pestañaRetornar = "gestionusuarios";
                    } else {
                        sexo = sexoDAO.buscarSexoxId(sexoSeleccionado);
                        estErrsexo = "";
                        Utilidad.actualizarElemento("cmbsexper");

                        //Valido que se haiga seleccionado algun tipo de identificacion
                        if (tipoIdentificacionSeleccionada == null) {
                            estErrtipoidenti = Utilidad.estilosErrorInput();
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Tipo Identificación requerido.");
                            logger.info("****************Error...Tipo Identificació no seleccionado");
                            pestañaRetornar = "gestionusuarios";
                        } else {
                            tipoIdentificacion = tipoIdentificacionDAO.buscarTipoIdentificacionxId(tipoIdentificacionSeleccionada);
                            estErrtipoidenti = "";

                            //Valido que se haiga seleccionado alguna fecha de nacimiento
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            if (persona.getFechaNacimiento() == null) {
                                estErrfecha = Utilidad.estilosErrorInput();
                                Utilidad.actualizarElemento("txtfechanacimiento");
                                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Fecha Nacimiento requerida.");
                                logger.info("****************Error...Fecha Nacimiento no seleccionada");
                                pestañaRetornar = "gestionusuarios";
                            } else {


                                estErrfecha = "";
                                Utilidad.actualizarElemento("txtfechanacimiento");

                                //Valido el telefono
                                if (persona.getTelefono().equals("")) {
                                    estErrtelefono = Utilidad.estilosErrorInput();
                                    Utilidad.actualizarElemento("txttelefonoper");
                                    Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono requerido.");
                                    logger.info("****************Error...Telefono vacio");
                                    pestañaRetornar = "gestionusuarios";
                                } else {
                                    estErrtelefono = "";
                                    Utilidad.actualizarElemento("txttelefonoper");

                                    //Valido el telefono movil
                                    if (persona.getMovil().equals("")) {
                                        estErrmovil = Utilidad.estilosErrorInput();
                                        Utilidad.actualizarElemento("txtmovilper");
                                        Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono Movil requerido.");
                                        logger.info("****************Error...Telefono Movil vacio");
                                        pestañaRetornar = "gestionusuarios";
                                    } else {
                                        estErrmovil = "";
                                        Utilidad.actualizarElemento("txtmovilper");

                                        //Valido el email
                                        if (persona.getEmail().equals("")) {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");
                                            Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: E-mail requerido.");
                                            logger.info("****************Error...E-maill vacio");
                                            pestañaRetornar = "gestionusuarios";

                                        } else {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");

                                            //Valido el pais seleccionado
                                            if (paisSeleccionado == null) {
                                                estErrPais = Utilidad.estilosErrorInput();
                                                Utilidad.actualizarElemento("cmbPaisPersona");
                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección País requerido.");
                                                logger.info("****************Error...seleccion pais vacio");
                                                pestañaRetornar = "gestionusuarios";
                                            } else {
                                                estErrPais = "";
                                                Utilidad.actualizarElemento("cmbPaisPersona");

                                                //Valido el departamento seleccionado
                                                if (departamentoSeleccionado == null) {
                                                    estErrDepartamento = Utilidad.estilosErrorInput();
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");
                                                    Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Departamento requerido.");
                                                    logger.info("****************Error...seleccion departamento vacio");
                                                    pestañaRetornar = "gestionusuarios";
                                                } else {
                                                    estErrDepartamento = "";
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");

                                                    //Valido la ciudad seleccionada
                                                    if (ciudadSeleccionado == null) {
                                                        estErrCiudad = Utilidad.estilosErrorInput();
                                                        Utilidad.actualizarElemento("cmbciudadbper");
                                                        Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Ciudad requerida.");
                                                        logger.info("****************Error...seleccion ciudad vacio");
                                                        pestañaRetornar = "gestionusuarios";
                                                    } else {
                                                        ciudad = ciudadDAO.buscarxid(ciudadSeleccionado);
                                                        estErrCiudad = "";
                                                        Utilidad.actualizarElemento("cmbciudadbper");

                                                        //Valido el barrio 
                                                        if (persona.getBarrio().equals("")) {
                                                            stErrbarrio = Utilidad.estilosErrorInput();
                                                            Utilidad.actualizarElemento("txtbarrioper");
                                                            Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Barrio requerida.");
                                                            logger.info("****************Error...barrio vacio");
                                                            pestañaRetornar = "gestionusuarios";
                                                        } else {
                                                            stErrbarrio = "";
                                                            Utilidad.actualizarElemento("txtbarrioper");

                                                            //Valido la direccion
                                                            if (persona.getDireccion().equals("")) {
                                                                stErrdireccion = Utilidad.estilosErrorInput();
                                                                Utilidad.actualizarElemento("txtdireccionper");
                                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Dirección requerida.");
                                                                logger.info("****************Error...Dirección vacio");
                                                                pestañaRetornar = "gestionusuarios";
                                                            } else {
                                                                stErrdireccion = "";
                                                                Utilidad.actualizarElemento("txtdireccionper");

                                                                //Valido que se haiga seleccionado el punto de la direccion en el mapa
                                                                if (contadorMapa == 0) {
                                                                    estErrMapa = Utilidad.estilosErrorInput();
                                                                    Utilidad.actualizarElemento("lblmapPersonas");
                                                                    Utilidad.mensajeError("SICOVI", "Sección Geolocalización Usuario: Seleccion de la direccion en el mapa requerida.");
                                                                    logger.info("****************Error...mapa vacio");
                                                                    pestañaRetornar = "gestionusuarios";
                                                                } else {
                                                                    estErrMapa = "";
                                                                    Utilidad.actualizarElemento("lblmapPersonas");
                                                                    logger.info("****************Aqui validamos el mapa");
                                                                    pestañaRetornar = "confiusuario";
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

        } else {
            if (pestañaActual.equals("confiusuario")) {
                logger.info("Estoy en la pestaña confiusuario");
                pestañaRetornar = "comfirmar";
            } else {
                if(pestañaActual.equals("comfirmar")){
                    pestañaRetornar = "confiusuario";
                }
            }
        }

        logger.log(Level.INFO, "Voyr a retornar a la siguiente pesta\u00f1a--> {0}", pestañaRetornar);
        
        return pestañaRetornar; 



    }

    public void puntoSeleccionadoMapa(PointSelectEvent event) {
        coordenadas = event.getLatLng();
        contadorMapa++;
        logger.log(Level.INFO, "la longitud y la latitud seleccionada es la siguiente{0} y la longitud es--> {1}", new Object[]{coordenadas.getLat(), coordenadas.getLng()});
        botonCargar = "display:block";
        Utilidad.actualizarElemento("btncargar");

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
        abrirSubir = false;
        Utilidad.actualizarElemento("dlgsubirFoto");
        nombre_foto = "";
        fotoSeleccionada = null;
    }

    public void fincargaFoto(FileUploadEvent event) throws IOException {

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
        
        logger.info("**************Fin seleccion foto");
        abrirSubir = false;
        Utilidad.actualizarElemento("dlgsubirFoto");        
        Utilidad.actualizarElemento("imgfotoCargar");
        Utilidad.actualizarElemento("btncargar");
        Utilidad.actualizarElemento("lblmensajefoto");

    }

    public void cambiarAvatar() {
        if (sexoSeleccionado == 1) {
            rutaFotoCargar = "../../FotosUsuarios/sinfotoh.jpeg";
        } else {
            rutaFotoCargar = "../../FotosUsuarios/sinfotom.jpeg";
        }
        Utilidad.actualizarElemento("imgfotoCargar");
    }

    public void ocultarCombos() {
        if (tipoPersonaSeleccionado != null) {
            if (tipoPersonaSeleccionado == 2 || tipoPersonaSeleccionado == 4 || tipoPersonaSeleccionado == 5 || tipoPersonaSeleccionado == 6) {
                ocultarCatalogo = "diplay:none";
                ocultarCargo = "diplay:block";

            } else {
                if (tipoPersonaSeleccionado == 3) {
                    ocultarCatalogo = "diplay:block";
                    ocultarCargo = "diplay:none";
                }else{
                    ocultarCatalogo = "diplay:none";
                    ocultarCargo = "diplay:none";
                }
            }
            Utilidad.actualizarElemento("lblcatalogo");
            Utilidad.actualizarElemento("lblcargo");
            Utilidad.actualizarElemento("cmbCargo");
            Utilidad.actualizarElemento("cmbCatalogos");

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
        zoom = 6;
        latitud = 4.599047;
        longitud = -74.080917;
        coordenadas = new LatLng(latitud, longitud);
        contadorMapa = 0;
        abrirSubir = false;
        botonCargar = "display:none";
        lblCargar = "display:block";
        mensajeCarga = "Sin Selección de Foto.";
        rutaFotoCargar = "../../FotosUsuarios/sinfotoh.jpeg";
        ocultarCargo = "display:none";
        ocultarCatalogo = "display:none";
        urlTemporal="/home/jbuitron/NetBeansProjects/Pasantia/NetBeansProjects/Pasantia/src/main/webapp/temp/";

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
    
    
}
