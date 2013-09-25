/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import com.pasantia.excepciones.DatosPersonalesPersonaException;
import com.pasantia.excepciones.FechaNacimientoMenorException;
import com.pasantia.excepciones.FechaNacimientoPersonaMayorActualException;
import com.pasantia.excepciones.ImagenNoSelecionadaException;
import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import com.pasantia.utilidades.Utilidad;
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

    /**
     * Creates a new instance of ValidarUsuarioBean
     */
    private List<String> estilosError;
    private String fechaActual;
    
    @Inject
    CrudDAO<Persona> crudDAO;
    @Inject
    GuardarSinFotoBean guardarSinFotoBean;

    public void validarDatosPersonalesUsuario(Persona p, Sexo s, TipoIdentificacion ti,Boolean imagenCargada) 
            throws DatosPersonalesPersonaException, 
            PersonaIdentificacionDuplicadoException, 
            FechaNacimientoMenorException, FechaNacimientoPersonaMayorActualException, 
            ImagenNoSelecionadaException {       

        validarPrimerNombre(p.getPnombre(), "txtpnombre", "lblpnombre", 0);
        validarPrimerApellido(p.getPapellido(), "txtpapellido", "lblpapellido", 1);
        validarSexo(s.getNombreSexo(), "lblsexper", "cmbsexper", 2);
        validarTipoIdentificacion(ti.getNombreTipoIdentificacion(), "cmbTipIdenti", "lblTipIdenti", 3);
        validarNroIdentificacion(p.getCedula(), "lblTipnroIdenti", "txtnroidentificacion", 4);
        validarFechaNacimiento(p.getFechaNacimiento(), "lblfechanacimiento", "txtfechanacimiento", 5,ti);        
        validarFechaMenosaActual(p.getFechaNacimiento(), "lblfechanacimiento", "txtfechanacimiento", 5);
        validarImagenCargada(imagenCargada);
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
    
    public Boolean validarNroIdentificacion(String nroidentificacion, String idTxt, String idLbl, int posEstilo) 
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
            if (p != null) {
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
