/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.bean.usuariossistema.ValidarUsuarioBean;
import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "validarCasinoBean")
@SessionScoped
public class ValidarCasinoBean implements Serializable {
    
    private static final long serialVersionUID = -6607254935408086220L;
    private static final Logger log = Logger.getLogger(ValidarCasinoBean.class.getName());
    
    private List<String> estilosError;
    
    @Inject
    ValidarUsuarioBean validarUsuarioBean;
    @Inject
    CrudDAO<Casino> crudDAO;

    
    
    
    public void validarCasino(Casino c) throws CadenaVaciaException{
        
        limpiarEstilos();
        validarCadenasVacias(c.getNombre(), "lblnomcasino", "txtnomcasino", 0, "Nombre del casino requerido.");
        validarCadenasVacias(c.getNit(), "lblnit", "txtnit", 1, "Nit del casino requerido.");
        validarNit(c.getNit(), "lblnit", "txtnit", 1, "Nit del casino duplicado.");
        validarCadenasVacias(c.getTelefono1(), "lbltel1", "txttel1", 2, "Telefono Movil del casino requerido.");
        validarCadenasVacias(c.getTelefono2(), "lbltel2", "txttel2", 3, "Telefono del casino requerido.");
        validarObjeto(c.getPersona(),  "btnbuscarcomcasi", 4, "Seleccion comandante Casino requerido.");
        validarObjeto(c.getBatallon(), "btnbuscarbat", 5, "Selección batallón requerido.");
        
    }
    
    public void limpiarEstilos(){
        estilosError.set(0,"");
        estilosError.set(1,"");
        estilosError.set(2,"");
        estilosError.set(3,"");
        estilosError.set(4,"");
        estilosError.set(5,"");
        Utilidad.actualizarElemento("accordioncasinos");
    }
    
    public void cargarEstilosError(){
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
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
    
    
    public void validarObjeto(Object objeto, String idLbl, Integer posEstilo, String mensaje) throws CadenaVaciaException {
        if (objeto==null) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);            
            throw new CadenaVaciaException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);            
        }
    }
    
    public void validarNit(String cadena, String idLbl, String idtxt, 
            Integer posEstilo, String mensaje) throws CadenaVaciaException {
        Casino casino=new Casino();
        casino=crudDAO.buscarxAlgunCampoString(Casino.class, "nit", cadena);
         if (casino!=null) {
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
    
    @PostConstruct
    public void Init(){
       cargarEstilosError();
    }
    
    public ValidarCasinoBean() {
        estilosError=new ArrayList<String>();
    }

    public List<String> getEstilosError() {
        return estilosError;
    }

    public void setEstilosError(List<String> estilosError) {
        this.estilosError = estilosError;
    }
    
    
}
