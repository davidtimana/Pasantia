/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.DepartamentoDAO;
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

    private Integer paisSeleccionado, tipoIdentificacionSeleccionada, sexoSeleccionado, departamentoSeleccionado, ciudadSeleccionado, zoom, tipoPersonaSeleccionado;
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
    
    private static Logger logger = Logger.getLogger(GestionarUsuarioBean.class.getName());
    
    
    @Inject
    DepartamentoDAO departamentoDAO;
    @Inject
    CiudadDAO ciudadDAO;
    

    @PostConstruct
    public void Init() {
        logger.info("***********Inicianilizando");
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
    }

    public String validarDatos(FlowEvent event) {
        String pestañaActual = event.getOldStep();
        String pestañaSiguiente = event.getNewStep();
        logger.log(Level.INFO, "pestaña donde estoy:{0}", pestañaActual);
        logger.log(Level.INFO, "Siguiente pestaña:{0}", pestañaSiguiente);
        logger.info("Validando datos");
        boolean resultado = false;
        Integer validador = 0;
        persona.setPnombre(persona.getPnombre().trim());
        persona.setPapellido(persona.getPapellido().trim());
        persona.setTelefono(persona.getTelefono().trim());
        persona.setMovil(persona.getMovil().trim());
        persona.setBarrio(persona.getBarrio().trim());
        persona.setDireccion(persona.getDireccion().trim());

        if (pestañaActual.equals("gestionusuarios")) {

            if (persona.getPnombre().equals("")) {
                estErrNombre = Utilidad.estilosErrorInput();
                Utilidad.actualizarElemento("txtpnombre");
                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Nombre requerido.");
                logger.info("****************Error...Primer Nombre vacio");
                validador++;
            } else {
                estErrNombre = "";
                Utilidad.actualizarElemento("txtpnombre");
                if (persona.getPapellido().equals("")) {
                    estErrapellido = Utilidad.estilosErrorInput();
                    Utilidad.actualizarElemento("txtpapellido");
                    Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Primer Apellido requerido.");
                    logger.info("****************Error...Primer Apellido vacio");
                    validador++;
                } else {
                    estErrapellido = "";
                    Utilidad.actualizarElemento("txtpapellido");
                    if (sexoSeleccionado == null) {
                        estErrsexo = Utilidad.estilosErrorInput();
                        Utilidad.actualizarElemento("cmbsexper");
                        Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Sexo requerido.");
                        logger.info("****************Error...Sexo no seleccionado");
                        validador++;
                    } else {
                        estErrsexo = "";
                        Utilidad.actualizarElemento("cmbsexper");
                        if (tipoIdentificacionSeleccionada == null) {
                            estErrtipoidenti = Utilidad.estilosErrorInput();
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Tipo Identificación requerido.");
                            logger.info("****************Error...Tipo Identificació no seleccionado");
                            validador++;
                        } else {
                            estErrtipoidenti = "";
                            Utilidad.actualizarElemento("cmbTipIdenti");
                            if (persona.getFechaNacimiento() == null) {
                                estErrfecha = Utilidad.estilosErrorInput();
                                Utilidad.actualizarElemento("txtfechanacimiento");
                                Utilidad.mensajeError("SICOVI", "Sección Datos Personales Usuario: Selección Fecha Nacimiento requerida.");
                                logger.info("****************Error...Fecha Nacimiento no seleccionada");
                                validador++;
                            } else {
                                estErrfecha = "";
                                Utilidad.actualizarElemento("txtfechanacimiento");
                                if (persona.getTelefono().equals("")) {
                                    estErrtelefono = Utilidad.estilosErrorInput();
                                    Utilidad.actualizarElemento("txttelefonoper");
                                    Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono requerido.");
                                    logger.info("****************Error...Telefono vacio");
                                    validador++;
                                } else {
                                    estErrtelefono = "";
                                    Utilidad.actualizarElemento("txttelefonoper");
                                    if (persona.getMovil().equals("")) {
                                        estErrmovil = Utilidad.estilosErrorInput();
                                        Utilidad.actualizarElemento("txtmovilper");
                                        Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: Telefono Movil requerido.");
                                        logger.info("****************Error...Telefono Movil vacio");
                                        validador++;
                                    } else {
                                        estErrmovil = "";
                                        Utilidad.actualizarElemento("txtmovilper");
                                        if (persona.getEmail().equals("")) {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");
                                            Utilidad.mensajeError("SICOVI", "Sección Datos De Contacto Usuario: E-mail requerido.");
                                            logger.info("****************Error...E-maill vacio");
                                            validador++;

                                        } else {
                                            estErremail = Utilidad.estilosErrorInput();
                                            Utilidad.actualizarElemento("txtemailper");
                                            if (paisSeleccionado == null) {
                                                estErrPais = Utilidad.estilosErrorInput();
                                                Utilidad.actualizarElemento("cmbPaisPersona");
                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección País requerido.");
                                                logger.info("****************Error...seleccion pais vacio");
                                                validador++;
                                            } else {
                                                estErrPais = "";
                                                Utilidad.actualizarElemento("cmbPaisPersona");
                                                if (departamentoSeleccionado == null) {
                                                    estErrDepartamento = Utilidad.estilosErrorInput();
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");
                                                    Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Departamento requerido.");
                                                    logger.info("****************Error...seleccion departamento vacio");
                                                    validador++;
                                                } else {
                                                    estErrDepartamento = "";
                                                    Utilidad.actualizarElemento("cmbdepartamentobper");
                                                    if (ciudadSeleccionado == null) {
                                                        estErrCiudad = Utilidad.estilosErrorInput();
                                                        Utilidad.actualizarElemento("cmbciudadbper");
                                                        Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Selección Ciudad requerida.");
                                                        logger.info("****************Error...seleccion ciudad vacio");
                                                        validador++;
                                                    } else {
                                                        estErrCiudad = "";
                                                        Utilidad.actualizarElemento("cmbciudadbper");
                                                        if (persona.getBarrio().equals("")) {
                                                            stErrbarrio = Utilidad.estilosErrorInput();
                                                            Utilidad.actualizarElemento("txtbarrioper");
                                                            Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Barrio requerida.");
                                                            logger.info("****************Error...barrio vacio");
                                                            validador++;
                                                        } else {
                                                            stErrbarrio = "";
                                                            Utilidad.actualizarElemento("txtbarrioper");
                                                            if (persona.getDireccion().equals("")) {
                                                                stErrdireccion = Utilidad.estilosErrorInput();
                                                                Utilidad.actualizarElemento("txtdireccionper");
                                                                Utilidad.mensajeError("SICOVI", "Sección Domicilio Usuario: Dirección requerida.");
                                                                logger.info("****************Error...Dirección vacio");
                                                                validador++;
                                                            } else {
                                                                stErrdireccion = "";
                                                                Utilidad.actualizarElemento("txtdireccionper");
                                                                if (contadorMapa == 0) {
                                                                    estErrMapa = Utilidad.estilosErrorInput();
                                                                    Utilidad.actualizarElemento("lblmapPersonas");
                                                                    Utilidad.mensajeError("SICOVI", "Sección Geolocalización Usuario: Seleccion de la direccion en el mapa requerida.");
                                                                    logger.info("****************Error...mapa vacio");
                                                                    validador++;
                                                                } else {
                                                                    estErrMapa = "";
                                                                    Utilidad.actualizarElemento("lblmapPersonas");
                                                                    logger.info("****************Aqui validamos el mapa");
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

        if (validador > 0) {
            return "gestionusuarios";
        } else {
            return "confiusuario";
        }



    }

    public void puntoSeleccionadoMapa(PointSelectEvent event) {
        coordenadas = event.getLatLng();
        contadorMapa++;
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
    }    
   

    public void fincargaFoto(FileUploadEvent event) throws IOException {                    
        
        logger.info("**************Iniciamos seleccion foto");
        nombre_foto=event.getFile().getFileName();
        fotoSeleccionada=event.getFile().getInputstream();               
        Utilidad.mensajeInfo("SICOVI", "Foto: "+nombre_foto+". Cargada Correctamente");       
        logger.info("**************Fin seleccion foto");

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

 
    
    
    
    

 
    

 
}
