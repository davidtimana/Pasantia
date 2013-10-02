/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CargoDAO;
import com.pasantia.dao.CatalogoVentaDAO;
import com.pasantia.dao.CiudadDAO;
import com.pasantia.dao.CrudDAO;
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
import com.pasantia.excepciones.CorreoInvalidoException;
import com.pasantia.excepciones.DatosPersonalesPersonaException;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.ComboNoSeleccionadoException;
import com.pasantia.excepciones.FechaNacimientoMenorException;
import com.pasantia.excepciones.FechaNacimientoPersonaMayorActualException;
import com.pasantia.excepciones.FotoNoCopiadaException;
import com.pasantia.excepciones.ImagenNoSelecionadaException;
import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import com.pasantia.excepciones.UbicacionNoSeleccionadaMapaException;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import com.pasantia.utilidades.UtilidadCadena;
import com.pasantia.utilidades.UtilidadFecha;
import com.pasantia.utilidades.UtilidadNumero;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    
    private static final long serialVersionUID = -8605387909514813370L;    
    

    private Integer paisSeleccionado, tipoIdentificacionSeleccionada, sexoSeleccionado, departamentoSeleccionado, ciudadSeleccionado, zoom, tipoPersonaSeleccionado,
            cargoSeleccionado, catalogoSeleccionado;
    private Sexo sexo;
    private TipoPersona tipoPersona;
    private TipoIdentificacion tipoIdentificacion;
    private Ciudad ciudad;
    private Cargo cargo;
    private CatalogoVenta catalogoVenta;
    private Persona persona;
    private Boolean deshabilitarDepartamento, deshabilitarCiudad, abrirSubir, fotoSubida;
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
    private String fechaConvertida;
    private List<Integer> listaControlBotones;
    private List<Boolean> listaControlReadonly;
    private String tabsSeleccionados;
    private List<Boolean> listControlAccordion;
    private Boolean estaEditando;
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
    @Inject
    AgregarUsuarioBean agregarUsuarioBean;
    @Inject
    GuardarSinFotoBean guardarSinFotoBean;
    @Inject
    CrudDAO<Persona> crudDAO;
    @Inject
    ValidarUsuarioBean validarUsuarioBean;
    @Inject
    BuscarUsuarioBean buscarUsuarioBean;

    @PreDestroy
    public void Fin() {
        logger.info("ejecute Fin");
        limpiarObjetos();
        actualizarformulario();
    }

    @PostConstruct
    public void Init() {

        logger.info("***********Iniciando");
        logger.info("*****************Cargando Inicio de Botones");
        iniciarBotones();
        logger.info("*****************Fin Inicio de Botones");
        logger.info("*****************Cargando Inicio de Readonly");
        iniciarReadonly();
        logger.info("*****************Fin Inicio de Readonly");
        logger.info("*****************Cargando Inicio de Accordion");
        iniciarAccordion();
        logger.info("*****************Fin Inicio de Accordion");
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
        logger.info("*****************Cargando Ultimo Usuario Ingresado");
        cargarUltimo();
        logger.info("*****************Fin Ultimo Usuario Ingresado");
        listControlAccordion.set(0,true);
        listControlAccordion.set(1,true);
        listControlAccordion.set(2,true);
        listControlAccordion.set(3,true);
    }

    public void editar() {
        estaEditando = true;
        botonCargar="display:block";
        listaControlReadonly.set(0, false);
        listControlAccordion.set(0,true);
        listControlAccordion.set(1,true);
        listControlAccordion.set(2,true);
        listControlAccordion.set(3,true);
        deshabilitarBotonesEditaroNuevo();
        logger.log(Level.INFO, "******************************Iniciamos Edicion de usuarios.{0}", estaEditando);


    }

    public void cancelar() {
        cargarUltimo();
        listaControlBotones.removeAll(listaControlBotones);
        iniciarBotones();
        botonCargar="display:none";
        tabsSeleccionados = "0";
        listaControlReadonly.set(0, true);
         listControlAccordion.set(0,true);
        listControlAccordion.set(1,true);
        listControlAccordion.set(2,true);
        listControlAccordion.set(3,true);
        Utilidad.actualizarElemento("gestionarusuarios");
        logger.info("******************************Iniciamos Cancelar de usuarios dejando todo como estaba.");
    }

    public void deshabilitarBotonesEditaroNuevo() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(0);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        Utilidad.actualizarElemento("gestionarusuarios");
    }

    public void nuevo() {
        estaEditando = false;
        listaControlReadonly.set(0, false);
        deshabilitarBotonesEditaroNuevo();
        limpiarObjetos();
        limpiarSeleccionados();
        listControlAccordion.set(0,false);
        listControlAccordion.set(1,false);
        listControlAccordion.set(2,false);
        listControlAccordion.set(3,false);
        fotoSubida = false;
        contadorMapa = 0;
        zoom = 6;
        botonCargar="display:block";
        Utilidad.actualizarElemento("gestionarusuarios");
        logger.info("******************************Iniciamos Creacion de usuarios.");
    }

    public void limpiarSeleccionados() {
        sexoSeleccionado = null;
        tipoIdentificacionSeleccionada = null;
        paisSeleccionado = null;
        departamentoSeleccionado = null;
        ciudadSeleccionado = null;
        tipoPersonaSeleccionado = null;
        cargoSeleccionado = null;
        catalogoSeleccionado = null;

    }

    public void cargarObjetoPersona(Persona p) {
        modMapa = new DefaultMapModel();
        ocultarCargo="display:none";
        ocultarCatalogo="display:none";
        persona = p;
        sexoSeleccionado = persona.getSexo().getIdSexo();
        asignarSexo();
        tipoIdentificacionSeleccionada = persona.getTipoIdentificacion().getIdTipoIdentificacion();
        asignarTipoIdentificacion();
        paisSeleccionado = persona.getCiudad().getDepartamento().getPais().getIdPais();
        cargarComboDepartamento(paisSeleccionado);
        departamentoSeleccionado = persona.getCiudad().getDepartamento().getIdDepartamento();
        ciudadSeleccionado = persona.getCiudad().getIdCiudad();
        cargarComboCiudad(departamentoSeleccionado);
        asignarCiudad();
        latitud = persona.getLatitud();
        longitud = persona.getLongitud();
        rutaFotoCargar = persona.getFoto();
        if (!rutaFotoCargar.equals("../../FotosUsuarios/sinfotoh.jpeg") || !rutaFotoCargar.equals("../../FotosUsuarios/sinfotom.jpeg")) {
            int tam = rutaFotoCargar.length();
            nombre_foto = UtilidadCadena.partirCadena(rutaFotoCargar, 11, tam);
            logger.log(Level.INFO, "la foto partida es la siguiente-->{0}", nombre_foto);
        }     
        tipoPersonaSeleccionado = persona.getTipoPersona().getIdTipoPersona();
        asignarTipoPersona();
        catalogoSeleccionado = persona.getCatalogoVenta().getIdCatalogoVenta();
        asignarCatalogoVenta();
        cargoSeleccionado = persona.getCargo().getIdCargo();
        asignarCargo();
        coordenadas = new LatLng(latitud, longitud);
        modMapa.addOverlay(new Marker(coordenadas));
        fechaConvertida = UtilidadFecha.obtenerFechaEnFormatoTexto(persona.getFechaNacimiento(), "dd/MM/yyyy");
        fotoSubida = true;
        contadorMapa++;
        zoom = 13;
        listaControlReadonly.set(0, true);
        estaEditando=true;
        if(!persona.getCargo().getDescripcion().equals("NO APLICA")){
            ocultarCargo="display:block";
        }
        if(!persona.getCatalogoVenta().getDescripcion().equals("No aplica")){
            ocultarCatalogo="display:block";
        }

    }

    public void cargarUltimo() {
        persona = crudDAO.buscarUltimo(Persona.class);
        cargarObjetoPersona(persona);
    }
    
   
    public String navegarWizard(FlowEvent event) {
        String actual = event.getOldStep();
        logger.log(Level.INFO, "estoy en la pesta\u00f1a-->{0}", actual);
        String pestaña = event.getNewStep();
        logger.log(Level.INFO, "estoy en la pesta\u00f1a-->{0}", pestaña);
        Utilidad.actualizarElemento("paso1");
        Utilidad.actualizarElemento("paso2");
        if (contadorMapa != 0) {
            zoom = zoom + 3;
        }
        if (!listControlAccordion.get(3)) {
            try {

                validarUsuarioBean.validarGeolocalizacionUsuario(contadorMapa);
                Utilidad.mensajeInfo("SICOVI", "Gestionar Usuarios - Paso 1. Validado Correctamente "
                        + "puedo continuar con el paso 2.");
                listControlAccordion.set(3, true);

            } catch (UbicacionNoSeleccionadaMapaException e) {
                tabsSeleccionados = "3";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Geolocalización "
                        + "Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            }
        }
        if (!validarUsuarioBean.validarAccordion(listControlAccordion)) {
            Utilidad.mensajePeligro("SICOVI", "Diligencie primero todos los campos requeridos * antes de continuar.");
            pestaña = "gestionusuarios";
        }
        if (actual.equals("confiusuario")) {
            try {
                validarUsuarioBean.validarConfiguracionUsuarioPaso2(tipoPersona, catalogoVenta, cargo);
                Utilidad.mensajeInfo("SICOVI", "Configurar Usuarios - Paso 2. Validado Correctamente "
                        + "puede conmfirmar y guardar ahora.");
            } catch (ComboNoSeleccionadoException ex) {
                pestaña = "confiusuario";
                Utilidad.mensajeError("SICOVI", "Configuración Usuarios - Paso 2: "
                        + ex.getMessage());
            }
        }

        return pestaña;
    }

    public void guardar() throws IOException, InterruptedException {

        agregarUsuarioBean.guardarUsuario(persona, ciudad, sexo, tipoIdentificacion, tipoPersona, cargo, catalogoVenta, nombre_foto, fotoSubida, latitud, longitud, estaEditando);

    }

    public void validarTabAccordion() {
        if (tabsSeleccionados.equals("0,1")
                || tabsSeleccionados.equals("0,2")
                || tabsSeleccionados.equals("0,3")
                || tabsSeleccionados.equals("1")
                || tabsSeleccionados.equals("2")
                || tabsSeleccionados.equals("3")
                && !listControlAccordion.get(0)) {
            try {

                validarUsuarioBean.validarDatosPersonalesUsuario(persona, sexo, tipoIdentificacion, fotoSubida, estaEditando);
                tabsSeleccionados = "1";
                listControlAccordion.set(0, true);
                Utilidad.actualizarElemento("accordionUsur");

            } catch (DatosPersonalesPersonaException e) {
                tabsSeleccionados = "0";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos Personales Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (PersonaIdentificacionDuplicadoException e) {
                tabsSeleccionados = "0";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos Personales Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (FechaNacimientoMenorException e) {
                tabsSeleccionados = "0";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos Personales Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (FechaNacimientoPersonaMayorActualException e) {
                tabsSeleccionados = "0";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos Personales Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (ImagenNoSelecionadaException e) {
                tabsSeleccionados = "0";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos Personales Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            }
        }

        if (tabsSeleccionados.equals("0,1")
                && listControlAccordion.get(0)
                || tabsSeleccionados.equals("1,2")
                || tabsSeleccionados.equals("1,3")
                || tabsSeleccionados.equals("1,0,2")
                || tabsSeleccionados.equals("1,0,3")
                || tabsSeleccionados.equals("1,0,2,3")) {

            try {
                validarUsuarioBean.validarDatosDeContactoDeUsuario(persona);
                tabsSeleccionados = "2";
                listControlAccordion.set(1, true);
                Utilidad.actualizarElemento("accordionUsur");
            } catch (CadenaVaciaException e) {
                tabsSeleccionados = "1";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos "
                        + "Contacto Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (CorreoInvalidoException e) {
                tabsSeleccionados = "1";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Datos "
                        + "Contacto Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            }

        }

        if (tabsSeleccionados.equals("1,2")
                && listControlAccordion.get(1)
                || tabsSeleccionados.equals("2,3")
                || tabsSeleccionados.equals("2,0,3")
                || tabsSeleccionados.equals("2,0,1,3")
                || tabsSeleccionados.equals("2,1,3")) {
            logger.info("entre al if");
            try {
                validarUsuarioBean.validarDomicilioUsuario(paisSeleccionado, departamentoSeleccionado,
                        ciudadSeleccionado, persona);
                tabsSeleccionados = "3";
                Utilidad.actualizarElemento("accordionUsur");
                listControlAccordion.set(2, true);
            } catch (ComboNoSeleccionadoException e) {
                tabsSeleccionados = "2";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Domicilio "
                        + "Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            } catch (CadenaVaciaException e) {
                tabsSeleccionados = "2";
                Utilidad.actualizarElemento("accordionUsur");
                Utilidad.mensajeError("SICOVI", "Gestionar Usuarios - Paso 1-Sección Domicilio "
                        + "Usuario: "
                        + e.getMessage());
                logger.info(e.getMessage());
            }
        }

        if (tabsSeleccionados.equals("2,3")
                && listControlAccordion.get(2)) {
            Utilidad.mensajeInfo("SICOVI", "Por favor busque y haga click en su ubicación actual.");
            Utilidad.actualizarElemento("mapPersonas");

        }

    }

    public void continuarSinFoto() {
        guardarSinFotoBean.cerrarComfirmar();
        fotoSubida = true;
        tabsSeleccionados = "0,1";
        mensajeCarga = "Sin Selección de Foto.";
        Utilidad.actualizarElemento("lblmensajefoto");
        validarTabAccordion();
    }

    public void puntoSeleccionadoMapa(PointSelectEvent event) {
        coordenadas = event.getLatLng();
        contadorMapa++;
        modMapa.addOverlay(new Marker(coordenadas));
        latitud = coordenadas.getLat();
        longitud = coordenadas.getLng();
        zoom = zoom + 3;
        Utilidad.actualizarElemento("mapPersonas");
        Utilidad.actualizarElemento("lbllan");
        Utilidad.actualizarElemento("lbllon");
        Utilidad.mensajeInfo("SICOVI", "La ubicación seleccionada es la siguiente: "
                + "Latitud: " + latitud + " Longitud: " + longitud);
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
        fotoSubida = false;
        abrirSubir = false;
        Utilidad.actualizarElemento("dlgsubirFoto");
        nombre_foto = "";
        fotoSeleccionada = null;
    }

    public void fincargaFoto(FileUploadEvent event) throws InterruptedException, IOException {


        logger.info("**************Iniciamos seleccion foto");
        Integer num = UtilidadNumero.generarNumerosAleatorios();
        nombre_foto = num + event.getFile().getFileName();
        fotoSeleccionada = event.getFile().getInputstream();
        logger.log(Level.INFO, "El nombre de la imagen seleccionada es-->{0}", nombre_foto);
        botonCargar = "display:none";
        mensajeCarga = "Foto Cargada: " + nombre_foto;        
        try {
            Utilidad.copiarArchivo(nombre_foto, fotoSeleccionada, urlTemporal);


            rutaFotoCargar = "../../temp/" + nombre_foto;
            logger.log(Level.INFO, "La ruta temporal de la foto es la siguiente--->{0}", rutaFotoCargar);

            Utilidad.mensajeInfo("SICOVI", "Foto: " + nombre_foto + ". Cargada Correctamente");
            fotoSubida = true;
            logger.info("**************Fin seleccion foto");
            abrirSubir = false;
            Utilidad.actualizarElemento("dlgsubirFoto");
            Utilidad.actualizarElemento("imgfotoCargar");
            Utilidad.actualizarElemento("btncargar");
            Utilidad.actualizarElemento("lblmensajefoto");
            Thread.sleep(4000);
            Utilidad.actualizarElemento("imgfotoCargar");
        } catch (FotoNoCopiadaException e) {

            logger.info("*************Error al copiar el archivo");
            Utilidad.mensajeFatal("SICOVI", e.getMessage());
        }

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

    public void asignarSexo() {
        if (sexoSeleccionado != null) {
            sexo = sexoDAO.buscarSexoxId(sexoSeleccionado);
        } else {
            sexo.setNombreSexo("");
        }
    }

    public void asignarTipoIdentificacion() {
        if (tipoIdentificacionSeleccionada != null) {
            tipoIdentificacion = tipoIdentificacionDAO.buscarTipoIdentificacionxId(tipoIdentificacionSeleccionada);
        } else {
            tipoIdentificacion.setNombreTipoIdentificacion("");
        }
    }

    public void asignarCiudad() {
        if (ciudadSeleccionado != null) {
            ciudad = ciudadDAO.buscarxid(ciudadSeleccionado);
        } else {
            ciudad.setNombreCiudad("");
        }
    }

    public void asignarTipoPersona() {
        if (tipoPersonaSeleccionado != null) {
            tipoPersona = tipoPersonaDAO.buscarTipoPersonasxId(tipoPersonaSeleccionado);
        } else {
            tipoPersona.setNombreTipoPersona("");
        }
    }

    public void asignarCargo() {
        if (cargoSeleccionado != null) {
            cargo = cargoDAO.buscarCargoporId(cargoSeleccionado);
        } else {
            cargo.setDescripcion("");
        }
    }

    public void asignarCatalogoVenta() {
        if (catalogoSeleccionado != null) {
            catalogoVenta = catalogoVentaDAO.buscarCatalogoxId(catalogoSeleccionado);
        } else {
            catalogoVenta.setDescripcion("");
        }
    }
    
  

    public void limpiarObjetos() {

        sexo = new Sexo();
        tipoPersona = new TipoPersona();
        tipoIdentificacion = new TipoIdentificacion();
        ciudad = new Ciudad();
        cargo = new Cargo();
        catalogoVenta = new CatalogoVenta();
        persona = new Persona();
        deshabilitarCiudad = false;
        deshabilitarDepartamento = false;
        fechaConvertida = "";
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
        urlTemporal = "/home/david/NetBeansProjects/Pasantia/NetBeansProjects/Pasantia/src/main/webapp/temp/";
        fotoSubida = false;
        modMapa = new DefaultMapModel();

    }

    public void actualizarformulario() {
    }

    public void iniciarBotones() {
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
    }

    public void iniciarReadonly() {
        listaControlReadonly.add(true);
        listaControlReadonly.add(false);
    }

    public void convertirFecha() {
        fechaConvertida = UtilidadFecha.obtenerFechaEnFormatoTexto(persona.getFechaNacimiento(), "dd/MM/yyyy");
    }

    public void iniciarAccordion() {
        listControlAccordion.add(false);
        listControlAccordion.add(false);
        listControlAccordion.add(false);
        listControlAccordion.add(false);
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
        urlTemporal = "/home/david/NetBeansProjects/Pasantia/NetBeansProjects/Pasantia/src/main/webapp/temp/";
        fotoSubida = false;
        modMapa = new DefaultMapModel();
        listaControlBotones = new ArrayList<Integer>();
        listaControlReadonly = new ArrayList<Boolean>();
        tabsSeleccionados = "0";
        listControlAccordion = new ArrayList<Boolean>();

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

    public String getFechaConvertida() {
        return fechaConvertida;
    }

    public void setFechaConvertida(String fechaConvertida) {
        this.fechaConvertida = fechaConvertida;
    }

    public List<Integer> getListaControlBotones() {
        return listaControlBotones;
    }

    public void setListaControlBotones(List<Integer> listaControlBotones) {
        this.listaControlBotones = listaControlBotones;
    }

    public List<Boolean> getListaControlReadonly() {
        return listaControlReadonly;
    }

    public void setListaControlReadonly(List<Boolean> listaControlReadonly) {
        this.listaControlReadonly = listaControlReadonly;
    }

    public String getTabsSeleccionados() {
        return tabsSeleccionados;
    }

    public void setTabsSeleccionados(String tabsSeleccionados) {
        this.tabsSeleccionados = tabsSeleccionados;
    }

    public List<Boolean> getListControlAccordion() {
        return listControlAccordion;
    }

    public void setListControlAccordion(List<Boolean> listControlAccordion) {
        this.listControlAccordion = listControlAccordion;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }
}
