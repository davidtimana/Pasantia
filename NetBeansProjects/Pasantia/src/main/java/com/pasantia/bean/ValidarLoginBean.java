/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.bean.usuariossistema.ValidarUsuarioBean;
import com.pasantia.entidades.Usuario;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.IntentosSuperadosException;
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
@Named(value = "validarLoginBean")
@SessionScoped
public class ValidarLoginBean implements Serializable {

    private static final long serialVersionUID = 3528180050104411782L;
    private static Logger log = Logger.getLogger(ValidarLoginBean.class.getName());
    private List<String> estilosError;
    @Inject
    ValidarUsuarioBean validarUsuarioBean;

    public void validar(Usuario u, Integer intentos) throws CadenaVaciaException,
            IntentosSuperadosException {
        limpiar();
        validarIntentos(intentos);
        log.info("llegue a validar");
        validarCadenasVacias(u.getNomusuario(), "lbllogin", "txtlogin", 0, "Para iniciar sesion es necesario que se digite su login.");
        validarCadenasVacias(u.getClave(), "lblpass", "txtcontrasena", 1, "Para iniciar sesion es necesario que se digite su Contraseña.");
    }

    public void validarIntentos(Integer intentos) throws IntentosSuperadosException {
        log.log(Level.INFO, "Intentos esta llegando en-->{0}", intentos);
        if (intentos >= 3) {
            log.log(Level.INFO, "Intentos es 3-->{0}", intentos);
            limpiar();
            throw new IntentosSuperadosException("Intentos de inicio de sesion superados. "
                    + "Pongase en contacto con el administrador del sistema o si olvido su contraseña "
                    + "recuperela haciendo clic en el enlace Olvido Su Contraseña?");
        }
    }

    public void limpiar() {
        estilosError.set(0, "");
        estilosError.set(1, "");
        Utilidad.actualizarElemento("lbllogin");
        Utilidad.actualizarElemento("txtlogin");
        Utilidad.actualizarElemento("lblpass");
        Utilidad.actualizarElemento("txtcontrasena");
    }

    /**
     * Metodo que se encarga de validar cualquier campo string.
     *
     * @param cadena Campo a validar.
     * @param idLbl Id del label de la informacion del campo para ser
     * actualizado y cambiar sus estilos a clase error.
     * @param idtxt Id del textbox del registro del campo para ser actualizado y
     * cambiar sus estilos a clase error.
     * @param posEstilo Pocision del array en donde se encuentra el estilo
     * correspondiente para este campo.
     * @param mensaje Mensaje que arroja la excepcion en caso de que la cadena
     * este vacia.
     * @exception CadenaVaciaException Si la cadena esta vacia
     * @author David Timana
     * @since 1.0
     */
    public void validarCadenasVacias(String cadena, String idLbl, String idtxt,
            Integer posEstilo, String mensaje)
            throws CadenaVaciaException {
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

    @PostConstruct
    public void Init() {
        estilosError.add("");
        estilosError.add("");
    }

    public ValidarLoginBean() {
        estilosError = new ArrayList<String>();
    }

    public List<String> getEstilosError() {
        return estilosError;
    }

    public void setEstilosError(List<String> estilosError) {
        this.estilosError = estilosError;
    }
}
