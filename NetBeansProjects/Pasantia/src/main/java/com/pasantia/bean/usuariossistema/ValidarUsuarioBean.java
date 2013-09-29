/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Cargo;
import com.pasantia.entidades.CatalogoVenta;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import com.pasantia.entidades.TipoPersona;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.ComboNoSeleccionadoException;
import com.pasantia.excepciones.CorreoInvalidoException;
import com.pasantia.excepciones.DatosPersonalesPersonaException;
import com.pasantia.excepciones.FechaNacimientoMenorException;
import com.pasantia.excepciones.FechaNacimientoPersonaMayorActualException;
import com.pasantia.excepciones.ImagenNoSelecionadaException;
import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import com.pasantia.excepciones.UbicacionNoSeleccionadaMapaException;
import com.pasantia.utilidades.Utilidad;
import com.pasantia.utilidades.UtilidadCadena;
import com.pasantia.utilidades.UtilidadFecha;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author David Timana
 */
@Named(value = "validarUsuarioBean")
@SessionScoped
public class ValidarUsuarioBean implements Serializable {
    
    private static final long serialVersionUID = 4198300994964280834L;

    /**
     * Creates a new instance of ValidarUsuarioBean
     */
    private List<String> estilosError;
    private String fechaActual;
    
    @Inject
    CrudDAO<Persona> crudDAO;
    @Inject
    GuardarSinFotoBean guardarSinFotoBean;

    public void validarConfiguracionUsuarioPaso2(TipoPersona tipoPersona,
            CatalogoVenta catalogoVenta, Cargo cargo)
            throws ComboNoSeleccionadoException {

        if (Utilidad.cadenaVacia(tipoPersona.getNombreTipoPersona())) {
            estilosError.set(15, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("lbltipper");
            Utilidad.actualizarElemento("cmbTipoPersona");
            throw new ComboNoSeleccionadoException("Selección tipo persona requerido.");
        } else {
            estilosError.set(15, "");
            Utilidad.actualizarElemento("lbltipper");
            Utilidad.actualizarElemento("cmbTipoPersona");
            if (tipoPersona.getNombreTipoPersona().equals("Vendedor Proveedor")) {
                if (Utilidad.cadenaVacia(catalogoVenta.getDescripcion())) {
                    estilosError.set(17, Utilidad.estilosErrorInput());
                    Utilidad.actualizarElemento("lblcatalogo");
                    throw new ComboNoSeleccionadoException("Selección catalogo venta requerido.");
                } else {
                    estilosError.set(17, "");
                    Utilidad.actualizarElemento("lblcatalogo");
                }
            } else {

                if (tipoPersona.getNombreTipoPersona().equals("Vendedor Casino Externo")
                        || tipoPersona.getNombreTipoPersona().equals("Cliente Externo")) {
                } else {

                    if (Utilidad.cadenaVacia(cargo.getDescripcion())) {
                        estilosError.set(16, Utilidad.estilosErrorInput());
                        Utilidad.actualizarElemento("lblcargo");
                        throw new ComboNoSeleccionadoException("Selección cargo requerido.");

                    } else {
                        estilosError.set(16, "");
                        Utilidad.actualizarElemento("lblcargo");
                    }
                }
            }
        }
    }
    
    public void validarDatosPersonalesUsuario(Persona p, Sexo s, TipoIdentificacion ti,Boolean imagenCargada,Boolean estaeditando) 
            throws DatosPersonalesPersonaException, 
            PersonaIdentificacionDuplicadoException, 
            FechaNacimientoMenorException, FechaNacimientoPersonaMayorActualException, 
            ImagenNoSelecionadaException {       

        validarPrimerNombre(p.getPnombre(), "txtpnombre", "lblpnombre", 0);
        validarPrimerApellido(p.getPapellido(), "txtpapellido", "lblpapellido", 1);
        validarSexo(s.getNombreSexo(), "lblsexper", "cmbsexper", 2);
        validarTipoIdentificacion(ti.getNombreTipoIdentificacion(), "cmbTipIdenti", "lblTipIdenti", 3);
        validarNroIdentificacion(p.getCedula(), "lblTipnroIdenti", "txtnroidentificacion", 4,estaeditando);
        validarFechaNacimiento(p.getFechaNacimiento(), "lblfechanacimiento", "txtfechanacimiento", 5,ti);        
        validarFechaMenosaActual(p.getFechaNacimiento(), "lblfechanacimiento", "txtfechanacimiento", 5);
        validarImagenCargada(imagenCargada);
    }
    
    public void validarDatosDeContactoDeUsuario(Persona p) 
            throws CorreoInvalidoException, CadenaVaciaException{
         validarCadenasVacias(p.getTelefono(), "lbltelefonoper", "txttelefonoper",
                 6, "Telefono requerido.");
         validarCadenasVacias(p.getMovil(), "lblmovilper", "txtmovilper",
                 7, "Telefono Movil requerido.");
         validarCadenasVacias(p.getEmail(), "lblemailper", "txtemailper",
                 8, "Email requerido.");
         validarCorreoValido(p.getEmail(), "lblemailper", "txtemailper", 8);
    }
    
   
    public void validarDomicilioUsuario(Integer paisSelect,Integer departamentoSelect,
            Integer ciudadSelect, Persona p) 
            throws ComboNoSeleccionadoException, CadenaVaciaException{
        validarComboNoSeleccionado(paisSelect, "lblPaisPersona", "cmbPaisPersona", 9, 
                "País No Seleccionado");
        validarComboNoSeleccionado(departamentoSelect, "lbldepartamentobper", "cmbdepartamentobper", 10, 
                "Departamento No Seleccionado");
        validarComboNoSeleccionado(ciudadSelect, "lblciudadbper", "cmbciudadbper", 11, 
                "Ciudad No Seleccionado");
        validarCadenasVacias(p.getBarrio(), "lblbarrioper", "txtbarrioper", 12, 
                "Barrio requerido.");
        validarCadenasVacias(p.getDireccion(), "lbldireccionper", "txtdireccionper", 13, 
                "Dirección requerido.");       
        
    }
    
    public void validarGeolocalizacionUsuario(int bandera) throws UbicacionNoSeleccionadaMapaException{
          if (bandera==0) {
            estilosError.set(14, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("geomapa");            
            throw new UbicacionNoSeleccionadaMapaException("No se ha seleccionado su ubicación actual en el mapa.");
        } else {
            estilosError.set(14, "");            
            Utilidad.actualizarElemento("geomapa");
        }
    }
    
    /**
     * Metodo que se encarga de validar cualquier si el
     * combo se selecciono o no.
     * @param select
     *          Value del combo.
     * @param idLbl
     *          Id del label de la informacion del campo
     *          para ser actualizado y cambiar sus estilos
     *          a clase error.
     * @param idtxt
     *          Id del textbox del registro del campo
     *          para ser actualizado y cambiar sus 
     *          estilos a clase error.
     * @param  posEstilo
     *          Pocision del array en donde se encuentra
     *          el estilo correspondiente para este campo.
     * @param  mensaje
     *          Mensaje que arroja la excepcion en caso
     *          de que la cadena este vacia.
     * @exception ComboNoSeleccionadoException
     *          Si la cadena esta vacia
     * @author David Timana
     * @since 1.0
     */   
    public void validarComboNoSeleccionado(Integer select, String idLbl, String idtxt, 
            Integer posEstilo, String mensaje) throws ComboNoSeleccionadoException 
              
             {
        if (select==null) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
            throw new ComboNoSeleccionadoException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
        }
    }
    
    /**
     * Metodo que se encarga de validar cualquier campo string.
     * @param cadena
     *          Campo a validar.
     * @param idLbl
     *          Id del label de la informacion del campo
     *          para ser actualizado y cambiar sus estilos
     *          a clase error.
     * @param idtxt
     *          Id del textbox del registro del campo
     *          para ser actualizado y cambiar sus 
     *          estilos a clase error.
     * @param  posEstilo
     *          Pocision del array en donde se encuentra
     *          el estilo correspondiente para este campo.
     * @param  mensaje
     *          Mensaje que arroja la excepcion en caso
     *          de que la cadena este vacia.
     * @exception CadenaVaciaException
     *          Si la cadena esta vacia
     * @author David Timana
     * @since 1.0
     */    
    public void validarCadenasVacias(String cadena, String idLbl, String idtxt, 
            Integer posEstilo, String mensaje) 
            throws CadenaVaciaException 
             {
        if (Utilidad.cadenaVacia(cadena)) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
            throw new CadenaVaciaException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
        }
    }
    
    public void validarCorreoValido(String correo,String idLbl,String idtxt,Integer posEstilo) throws CorreoInvalidoException{
        if (!UtilidadCadena.esUnCorreoElectronico(correo)) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
            throw new CorreoInvalidoException("El Correo Ingresado no es valido. Ej. alguien@gmail.com");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento("txtpapellido");
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
        }
    }

    public Boolean validarPrimerNombre(String nombre, String idTxt, String idLbl, int posEstilo) 
            throws DatosPersonalesPersonaException {
        Boolean resultado = false;
        if (Utilidad.cadenaVacia(nombre)) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("Primer nombre usuario requerido.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            resultado = true;
        }
        return resultado;

    }

    public Boolean validarPrimerApellido(String apellido, String idTxt, String idLbl, int posEstilo) 
            throws DatosPersonalesPersonaException {
        Boolean resultado = false;
        if (Utilidad.cadenaVacia(apellido)) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("Primer apellido usuario requerido.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            resultado = true;
        }
        return resultado;

    }

    public Boolean validarSexo(String sexo, String idCmb, String idLbl, int posEstilo) 
            throws DatosPersonalesPersonaException {
        Boolean resultado = false;
        if (Utilidad.cadenaVacia(sexo)) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idCmb);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("Selección de sexo requerido.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idCmb);
            Utilidad.actualizarElemento(idLbl);
            resultado = true;
        }
        return resultado;

    }

    public Boolean validarTipoIdentificacion(String tipoIdentificacion, String idCmb, String idLbl, int posEstilo) 
            throws DatosPersonalesPersonaException {
        Boolean resultado = false;
        if (Utilidad.cadenaVacia(tipoIdentificacion)) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idCmb);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("Selección de tipo identificación requerido.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idCmb);
            Utilidad.actualizarElemento(idLbl);
            resultado = true;
        }
        return resultado;

    }
    
    public Boolean validarNroIdentificacion(String nroidentificacion, String idTxt, String idLbl, int posEstilo,Boolean estaEditando) 
            throws DatosPersonalesPersonaException, PersonaIdentificacionDuplicadoException {
        Boolean resultado = false;
        Persona p = new Persona();
        if (Utilidad.cadenaVacia(nroidentificacion)) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("N° identificación usuario requerido.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            p = crudDAO.buscarxAlgunCampoString(Persona.class, "cedula", nroidentificacion);
            if (p != null && !estaEditando) {
                resultado = false;
                estilosError.set(posEstilo, Utilidad.estilosErrorInput());
                Utilidad.actualizarElemento(idTxt);
                Utilidad.actualizarElemento(idLbl);
                throw new PersonaIdentificacionDuplicadoException("N° identificación del usuario duplicado.");
            } else {
                estilosError.set(posEstilo, "");
                Utilidad.actualizarElemento(idTxt);
                Utilidad.actualizarElemento(idLbl);
                resultado = true;
            }
        }
        return resultado;
    }
    
    public Boolean validarFechaNacimiento(Date fecha, String idTxt, String idLbl, int posEstilo, TipoIdentificacion tp) 
            throws DatosPersonalesPersonaException, FechaNacimientoMenorException {
        Boolean resultado = false;
        if (fecha == null) {
            resultado = false;
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            throw new DatosPersonalesPersonaException("Fecha nacimiento usuario requerida.");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            if (!validarFechaConTI(fecha, tp)) {
                
                resultado = false;
            } else {
                estilosError.set(3, "");
                Utilidad.actualizarElemento("lblTipIdenti");
                Utilidad.actualizarElemento("cmbTipIdenti");
                resultado = true;
            }
        }
        return resultado;
    }
    
    public Boolean validarFechaConTI(Date fecha, TipoIdentificacion tp) throws FechaNacimientoMenorException {
        Boolean resultado = false;
        Integer edad = UtilidadFecha.calcularEdad(UtilidadFecha.obtenerFechaEnFormatoTexto(fecha, "dd/MM/yyyy"));

        if (!tp.getNombreTipoIdentificacion().equals("Tarjeta Identidad") && edad < 18) {
            resultado = false;
            estilosError.set(5, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("lblfechanacimiento");
            Utilidad.actualizarElemento("txtfechanacimiento");
            estilosError.set(3, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("lblTipIdenti");
            Utilidad.actualizarElemento("cmbTipIdenti");
            throw new FechaNacimientoMenorException("Tipo De Identificación seleccionado no "
                    + "corresponde a la fecha de nacimiento.");
        } else {
            resultado = true;
        }
        return resultado;
    }
    
    public void validarFechaMenosaActual(Date fechaNacimiento, String idTxt, String idLbl, int posEstilo) 
            throws FechaNacimientoPersonaMayorActualException {
        Date fechaActualDate = new Date();
        if (fechaActualDate.before(fechaNacimiento)) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
            throw new FechaNacimientoPersonaMayorActualException("La fecha ingresada es mayor a la actual;");
        } else {
            estilosError.set(posEstilo, "");
            Utilidad.actualizarElemento(idTxt);
            Utilidad.actualizarElemento(idLbl);
        }
    }
    
    public void validarImagenCargada(Boolean bandera) throws ImagenNoSelecionadaException{
        
        if(!bandera){
            guardarSinFotoBean.abrirComfirmar();
            throw new ImagenNoSelecionadaException("Imagen No seleccionada");
        }
        
    }
    
    public Boolean validarAccordion(List<Boolean> list) {
        Boolean resultado = false;
        Integer total = list.size();
        Integer cont = 0;
        for (Boolean b : list) {            
            if (b == true) {
                cont++;
            }
        }

        if (cont == total) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }


    @PostConstruct
    public void cargarEstilosSinError() {
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        fechaActual=UtilidadFecha.obtenerFechaActualFormatoString("dd/MM/yyyy");
    }

    public ValidarUsuarioBean() {
        estilosError = new ArrayList<String>();
    }

    public List<String> getEstilosError() {
        return estilosError;
    }

    public void setEstilosError(List<String> estilosError) {
        this.estilosError = estilosError;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }
    
    

  
    
    
}
