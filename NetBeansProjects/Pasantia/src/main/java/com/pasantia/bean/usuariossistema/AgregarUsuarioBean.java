/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.bean.Navegacion;
import com.pasantia.dao.CrudDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.entidades.Cargo;
import com.pasantia.entidades.CatalogoVenta;
import com.pasantia.entidades.Ciudad;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import com.pasantia.entidades.TipoPersona;
import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import com.pasantia.utilidades.UtilidadCadena;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David Orlando Timana
 */
@Named(value = "agregarUsuarioBean")
@SessionScoped
public class AgregarUsuarioBean implements Serializable {

    private static final long serialVersionUID = -8367053396853933675L;
    private static Logger log = Logger.getLogger(AgregarUsuarioBean.class.getName());
    
    @Inject
    PersonaDAO personaDAO;
    @Inject
    GuardarSinFotoBean guardarSinFotoBean;
    @Inject
    CrudDAO<Persona> crudDAO;
    @Inject
    CrudDAO<CatalogoVenta> crudDAO1;
    @Inject
    CrudDAO<Cargo> crudDAO2;
    @Inject
    GestionarUsuarioBean gestionarUsuarioBean;

    @PostConstruct
    public void Init() {
        System.out.println("***********Inicianilizando");

    }

    /**
     * Guarda los usuarios de la aplicacion con sus respectivos roles.
     *
     * @param p Datos comunes de la persona.
     * @param c Ciudad de la persona.
     * @param s Sexo de la persona.
     * @param ti TipoIdentificacion de la persona.
     * @param tp TipoPersona de la persona.
     * @param ca Cargo de la persona.
     * @param cv CatalogoVenta de la persona.
     * @param rutaTemp ruta temporal de donde se encuentra la foto del usuario
     * ubicada.
     * @param fotoSubida bandera que indica si se subio o no la foto.
     * @return Mensaje de guardado correcto o no.
     */
    public void guardarUsuario(Persona p, Ciudad c, Sexo s, TipoIdentificacion ti, TipoPersona tp,
            Cargo ca, CatalogoVenta cv, String nombreFoto, Boolean fotoSubida, Double latitud, Double longitud,
            Boolean estaEditando)
            throws IOException, InterruptedException {
        Boolean r = false;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        log.log(Level.INFO, "el session id es el siguiente-->{0}", session.getId());
        p=formatearNombresYApellidos(p);
        log.log(Level.INFO, "************Iniciando a guardar la persona: {0}", p.getPnombre());
        

        if (fotoSubida) {
            log.info("***********Foto subida");
            p.setSexo(s);
            p.setTipoIdentificacion(ti);
            p.setCiudad(c);
            p.setTipoPersona(tp);
            if (!Utilidad.cadenaVacia(nombreFoto)) {
                p.setFoto("../../temp/" + nombreFoto);
            } else {
                if (p.getSexo().getNombreSexo().equals("Masculino")) {
                    p.setFoto("../../FotosUsuarios/sinfotoh.jpeg");
                } else {
                    p.setFoto("../../FotosUsuarios/sinfotom.jpeg");
                }
            }
            p.setLatitud(latitud);
            p.setLongitud(longitud);

            if (Utilidad.cadenaVacia(cv.getDescripcion())) {
                cv = crudDAO1.buscarxAlgunCampoString(CatalogoVenta.class, "descripcion", "No aplica");
            }
            if (Utilidad.cadenaVacia(ca.getDescripcion())) {
                ca = crudDAO2.buscarxAlgunCampoString(Cargo.class, "descripcion", "NO APLICA");
            }

            p.setCatalogoVenta(cv);
            p.setCargo(ca);

            if (estaEditando) {
                if (crudDAO.editar(p)) {
                    Utilidad.mensajeInfo("SICOVI", "Usuario: " + p.getPnombre() + " " + p.getPapellido() + ". Actualizado Correctamente");
                    r = true;

                } else {
                    r = false;
                    Utilidad.mensajeError("SICOVI", "Usuario: " + p.getPnombre() + " " + p.getPapellido() + ". No se pudo Actualizar.");
                }

            } else {
                if (crudDAO.crear(p)) {
                    Utilidad.mensajeInfo("SICOVI", "Usuario: " + p.getPnombre() + " " + p.getPapellido() + ". Guardado Correctamente");
                    r = true;
                } else {
                    r = false;
                    Utilidad.mensajeError("SICOVI", "Usuario: " + p.getPnombre() + " " + p.getPapellido() + ". No se pudo Guardar.");
                }
            }







        } else {
            log.info("***********Foto Nooooo subida");
            guardarSinFotoBean.abrirComfirmar();
        }


        if (r) {
            gestionarUsuarioBean.cancelar();
            //ec.redirect(ec.getRequestContextPath() + "/faces/paginas/usuariossistema/GestionarUsuarios.xhtml");
        }

    }

    public Persona formatearNombresYApellidos(Persona p) {
        p.setPnombre(UtilidadCadena.cambiarPrimeraLetraAMayuscula(p.getPnombre()));
        p.setSnombre(UtilidadCadena.cambiarPrimeraLetraAMayuscula(p.getSnombre()));
        p.setPapellido(UtilidadCadena.cambiarPrimeraLetraAMayuscula(p.getPapellido()));
        p.setSapellido(UtilidadCadena.cambiarPrimeraLetraAMayuscula(p.getSapellido()));
        return p;
    }

    public AgregarUsuarioBean() {
    }
}
