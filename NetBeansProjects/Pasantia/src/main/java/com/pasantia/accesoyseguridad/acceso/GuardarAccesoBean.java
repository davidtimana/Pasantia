/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Rol;
import com.pasantia.entidades.Usuario;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "guardarAccesoBean")
@SessionScoped
public class GuardarAccesoBean implements Serializable {

    private static final long serialVersionUID = 24056530272162290L;
    private static Logger log = Logger.getLogger(GuardarAccesoBean.class.getName());
    @Inject
    CrudDAO<Usuario> crudDAO;

    public void guardar(Usuario u, Rol r, Persona p, Boolean estaEditando) {
        u.setRol(r);
        u.setPersona(p);
        u.setSesion(false);

        if (Utilidad.cadenaVacia(u.getThema())) {
            u.setThema("pasantia");
        }

        String pass = u.getClave();
        pass = Utilidad.encrypt(pass);
        u.setClave(pass);

        if (estaEditando) {
            if (crudDAO.editar(u)) {
                Utilidad.mensajeInfo("SICOVI", "Acceso Actualizado correctamente.");
            } else {
                Utilidad.mensajeFatal("SICOVI", "No se pudo actualizar el acceso.");
            }
        } else {
            if (crudDAO.crear(u)) {
                Utilidad.mensajeInfo("SICOVI", "Acceso creado correctamente");
            } else {
                Utilidad.mensajeFatal("SICOVI", "No se pudo generar el acceso a la aplicación.");
            }
        }
    }

    public GuardarAccesoBean() {
    }
}
