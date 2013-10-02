/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Usuario;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.ComboNoSeleccionadoException;
import com.pasantia.excepciones.PasswordDiferenteException;
import com.pasantia.excepciones.PasswordVacioException;
import com.pasantia.excepciones.PersonaNoSeleccionadaException;
import com.pasantia.excepciones.PersonaUsuarioRepetidaException;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

/**
 *
 * @author David Timana
 */
@Named(value = "validarAccesoBean")
@SessionScoped
public class ValidarAccesoBean implements Serializable {

    private static final long serialVersionUID = 5992625281026997950L;
    private static Logger log = Logger.getLogger(ValidarAccesoBean.class.getName());
    private List<String> estilosError;
    @Inject
    CrudDAO<Usuario> crudDAOUsuario;

    public void validarAcceso(Persona p, Integer rolSelect, String login, String pass1, String pass2) throws PersonaNoSeleccionadaException,
            ComboNoSeleccionadoException,
            CadenaVaciaException,
            LoginException,
            PasswordVacioException,
            PasswordDiferenteException {
        validarPersonaSeleccionada(p);
        validarComboRol(rolSelect);
        log.log(Level.INFO, "llegue a validar acceso llega con-->{0}", login);
        validarLoginVacio(login);
        validarPass(pass1, pass2);
        validarPassDiferente(pass1, pass2);
    }

    public void validarPersonaSeleccionada(Persona p) throws PersonaNoSeleccionadaException {
        if (Utilidad.cadenaVacia(p.getPnombre())) {
            estilosError.set(0, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("btnbuscarper");
            throw new PersonaNoSeleccionadaException("Seleccionar persona en el buscador es obligatorio.");
        } else {
            estilosError.set(0, "");
            Utilidad.actualizarElemento("btnbuscarper");
        }

    }

    public void validarComboRol(Integer rolSelect) throws ComboNoSeleccionadoException {
        if (rolSelect == null) {
            estilosError.set(1, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("cmbrol");
            throw new ComboNoSeleccionadoException("Seleccionar rol es requerido.");
        } else {
            estilosError.set(1, "");
            Utilidad.actualizarElemento("cmbrol");
        }

    }

    public void validarLoginVacio(String login) throws CadenaVaciaException, LoginException {
        log.log(Level.INFO, "llegue a login vacio llega con-->{0}", login);
        if (Utilidad.cadenaVacia(login)) {
            estilosError.set(2, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("txtlogin");
            throw new CadenaVaciaException("Login requerido.");
        } else {
            estilosError.set(2, "");
            Utilidad.actualizarElemento("txtlogin");
            validarLoginRepetido(login);
        }
    }

    public void validarLoginRepetido(String login) throws LoginException {
        Usuario u = new Usuario();
        u = crudDAOUsuario.buscarxAlgunCampoString(Usuario.class, "nomusuario", "login");
        if (u != null) {
            estilosError.set(2, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("txtlogin");
            throw new LoginException("El login ingresado ya existe.");
        } else {
            estilosError.set(2, "");
            Utilidad.actualizarElemento("txtlogin");

        }
    }

    public void validarPass(String pass1, String pass2) throws PasswordVacioException {
        if (Utilidad.cadenaVacia(pass1) || Utilidad.cadenaVacia(pass2)) {
            estilosError.set(3, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("passusur");
            Utilidad.actualizarElemento("passusurvalidacion");
            throw new PasswordVacioException("Password requerido.");
        } else {
            estilosError.set(3, "");
            Utilidad.actualizarElemento("passusur");
            Utilidad.actualizarElemento("passusurvalidacion");
        }
    }

    public void validarPassDiferente(String pass1, String pass2) throws PasswordDiferenteException {
        if (!pass1.equals(pass2)) {
            estilosError.set(3, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("passusur");
            Utilidad.actualizarElemento("passusurvalidacion");
            throw new PasswordDiferenteException("El password ingresado es diferente al inicial.");
        } else {
            estilosError.set(3, "");
            Utilidad.actualizarElemento("passusur");
            Utilidad.actualizarElemento("passusurvalidacion");
        }
    }

    public void limpiarElementos() {
        estilosError.set(0, "");
        estilosError.set(1, "");
        estilosError.set(2, "");
        estilosError.set(3, "");
        Utilidad.actualizarElemento("btnbuscarper");
        Utilidad.actualizarElemento("cmbrol");
        Utilidad.actualizarElemento("txtlogin");
        Utilidad.actualizarElemento("passusur");
        Utilidad.actualizarElemento("passusurvalidacion");
    }
    
    public void validarPesonaRepetida(Persona p) throws PersonaUsuarioRepetidaException{        
        Usuario usur=new Usuario();
        usur=crudDAOUsuario.buscarObjetoConJoin(Usuario.class, "idTblpersona", p.getIdTblpersona(), "persona");
        if(usur!=null){
            throw new PersonaUsuarioRepetidaException("El usuario seleccionado ya tiene asignadas sus credenciales.");
        }
        
        
    }

    @PostConstruct
    public void Init() {
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
    }

    public ValidarAccesoBean() {
        estilosError = new ArrayList<String>();
    }

    public List<String> getEstilosError() {
        return estilosError;
    }

    public void setEstilosError(List<String> estilosError) {
        this.estilosError = estilosError;
    }
}
