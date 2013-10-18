/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.CrudDAO;
import com.pasantia.dao.impl.CrudJpaDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.entidades.Usuario;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.IntentosSuperadosException;
import com.pasantia.utilidades.Utilidad;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David Timana
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 6969617031517585082L;
    private static Logger log = Logger.getLogger(LoginBean.class.getName());
    private Usuario usuario;
    private int intentos;
    @Inject
    ValidarLoginBean validarLoginBean;
    @Inject
    CrudDAO<Usuario> crudDAO;
    @Inject
    Navegacion navegacion;
    @Inject
    ThemaSwitcherBean themaSwitcherBean;
    @Inject
    ImagenesControlBean imagenesControlBean;

    public String iniciarSesion() throws IOException {

        String r="";
        try {
            validarLoginBean.validar(usuario, intentos);
            buscarUsuario();

        } catch (CadenaVaciaException ex) {
            Utilidad.mensajeError("SICOVI", ex.getMessage());
            log.info(ex.getMessage());
            r="error";

        } catch (IntentosSuperadosException ex) {
            Utilidad.mensajeError("SICOVI", ex.getMessage());
            log.info(ex.getMessage());
            r="error";
        }
        log.log(Level.INFO, "esto retornare-->{0}", r);
        return r;
    }

    public String buscarUsuario() throws IOException {
        Usuario u = new Usuario();
        String claveencriptada = "";
        String clavetemp = usuario.getClave();
        String r="";
        u = crudDAO.buscarxAlgunCampoString(Usuario.class, "nomusuario", usuario.getNomusuario());
        if (u != null) {
            claveencriptada = u.getClave();

            u.setClave(Utilidad.decrypt(claveencriptada));

            if (clavetemp.equals(u.getClave()) && u.isActivo()) {
                if (u.isSesion()) {
                    log.info("Usuario esta en session en otro lugar.");
                    Utilidad.mensajeError("SICOVI", "El usuario " + u.getPersona().getPnombre() + " " + u.getPersona().getPapellido() + ". Esta en sesion en otro lugar.");
                } else {
                    log.log(Level.INFO, "*******************Bienvenido {0} {1}", new Object[]{u.getPersona().getPnombre(), u.getPersona().getPapellido()});
                    Utilidad.mensajeError("SICOVI", "Bienvenido " + u.getPersona().getPnombre() + " "
                            + u.getPersona().getPapellido());
                    u.setClave(claveencriptada);
                    u.setSesion(true);
                    crudDAO.editar(u);
                    themaSwitcherBean.setThema(u.getThema());
                    usuario = u;
                    redireccionar(u.getRol());
                }

            } else {
                validarLoginBean.limpiar();
                log.info("*****Credenciales no Validas");
                Utilidad.mensajeError("SICOVI", "El usuario ingresado no existe en el sistema.");
                intentos++;
                log.log(Level.INFO, "Intentos queda en-->{0}", intentos);
                r="error";
            }

        } else {
            validarLoginBean.limpiar();
            log.info("*****Credenciales no Validas");
            Utilidad.mensajeError("SICOVI", "El usuario ingresado no existe en el sistema.");
            intentos++;
            log.log(Level.INFO, "Intentos queda en-->{0}", intentos);
            r="error";
        }
        
        return r;

    }

    public void redireccionar(Rol rol) throws IOException {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        log.log(Level.INFO, "El rol del usuario ingresado es el siguiente-->{0}", rol.getDescripcion());
        String redireccion = "";
        switch (rol.getCodigo()) {
            case 1:                
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("faces/paginas/menu/menuInicial.xhtml;jsessionid="+session.getId());
                break;
            case 2:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("faces/paginas/menu/menuvendedor.xhtml;jsessionid="+session.getId());                
                break;
            case 4:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("faces/paginas/menu/menucliente.xhtml;jsessionid="+session.getId());                
                break;
            case 5:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("faces/paginas/menu/menucomandante.xhtml;jsessionid="+session.getId());                
                break;
            case 6:
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("faces/paginas/menu/menuadmin.xhtml;jsessionid="+session.getId());                
                break;
            default:
                redireccion="error";
                Utilidad.mensajeError("SICOVI", "El usuario no tiene los suficientes "
                        + "permisos para entrar al sistema.");
                break;
        }                
    }
    
    public void cerrarSesion() throws IOException{
        log.info("--------------------------Cerrando sesion-------------------------------");
        usuario.setSesion(false);
        crudDAO.editar(usuario);
        themaSwitcherBean.setThema("pasantia");
        imagenesControlBean.cargarImagenes();      
                       
    }

    @PostConstruct
    public void Init() {
        intentos = 0;

    }

    public LoginBean() {
        usuario = new Usuario();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
}
