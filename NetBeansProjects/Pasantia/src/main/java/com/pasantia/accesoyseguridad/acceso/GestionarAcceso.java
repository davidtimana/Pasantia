/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.bean.usuariossistema.BuscarUsuarioBean;
import com.pasantia.dao.CrudDAO;
import com.pasantia.dao.RolDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Rol;
import com.pasantia.entidades.Usuario;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.ComboNoSeleccionadoException;
import com.pasantia.excepciones.PasswordDiferenteException;
import com.pasantia.excepciones.PasswordVacioException;
import com.pasantia.excepciones.PersonaNoSeleccionadaException;
import com.pasantia.excepciones.PersonaUsuarioRepetidaException;
import com.pasantia.utilidades.CombosComunes;
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
import javax.security.auth.login.LoginException;

/**
 *
 * @author David Timana
 */
@Named(value = "gestionarAcceso")
@SessionScoped
public class GestionarAcceso extends CombosComunes implements Serializable {

    private static final long serialVersionUID = 4626973270409152048L;
    private static Logger log = Logger.getLogger(GestionarAcceso.class.getName());
    private Rol rol;
    private Usuario usuario;
    private String verificarPass;
    private Persona persona;
    private List<Integer> listaControlBotones;
    private List<Boolean> listaControlReadonly;
    private Boolean estaEditando;
    @Inject
    RolDAO rolDAO;
    @Inject
    BuscarUsuarioBean buscarUsuarioBean;
    @Inject
    ValidarAccesoBean validaraccesoBean;
    @Inject
    GuardarAccesoBean accesoBean;
    @Inject
    BuscarAccesosBean buscarAccesosBean;
    @Inject
    CrudDAO<Usuario> crudDAOUsur;

    public void buscar() {
        listaControlReadonly.set(0, true);
        listaControlReadonly.set(1, false);
        listaControlReadonly.set(2, true);
        validaraccesoBean.limpiarElementos();        
        deshabilitarBotonesCancelar();
        buscarAccesosBean.abrirBuscador();
    }
    
    public void cargarUltimo(){
        usuario=crudDAOUsur.buscarUltimo(Usuario.class);
        cargarObjetoUsuario(usuario);
    }
    
     public void iniciarReadonly() {
        listaControlReadonly.add(true);
        listaControlReadonly.add(false);
        listaControlReadonly.add(true);
    }
    
    public void cancelar() {
        listaControlReadonly.set(0,true);
        listaControlReadonly.set(1,false);
        listaControlReadonly.set(2,true);
        validaraccesoBean.limpiarElementos();
        cargarUltimo();
        Utilidad.actualizarElemento("frmaccesousur");
        deshabilitarBotonesCancelar();
        
    }

    public void cargarObjetoUsuario(Usuario s) {
        usuario = s;
        persona = usuario.getPersona();
        rol = usuario.getRol();
        verificarPass = usuario.getClave();
    }

    public void nuevo() {
        estaEditando = false;
        listaControlReadonly.set(0,false);
        listaControlReadonly.set(1,true);
        listaControlReadonly.set(2,false);
        deshabilitarBotonesEditaroNuevo();
        persona = new Persona();
        usuario = new Usuario();
        rol = new Rol();
        verificarPass = "";
        Utilidad.actualizarElemento("frmaccesousur");
    }

    public void editar() {
        estaEditando = true;
        listaControlReadonly.set(0,false);
        listaControlReadonly.set(1,true);
        listaControlReadonly.set(2,true);
        Utilidad.actualizarElemento("frmaccesousur");
        deshabilitarBotonesEditaroNuevo();
    }

    public void deshabilitarBotonesEditaroNuevo() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(0);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        Utilidad.actualizarElemento("grupobotonesacceso");
    }

    public void deshabilitarBotonesCancelar() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        Utilidad.actualizarElemento("grupobotonesacceso");
    }

    public void iniciarBotones() {
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
    }

    public void guardarAcceso() {
        log.log(Level.INFO, "el thema seleccionado es el siguiente-->{0}", usuario.getThema());
        try {
            validaraccesoBean.validarAcceso(persona, rol.getIdRol(), usuario.getNomusuario(),
                    usuario.getClave(), verificarPass);
            accesoBean.guardar(usuario, rol, persona, estaEditando);
        } catch (PersonaNoSeleccionadaException ex) {
            log.info("Persona no seleccionada.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        } catch (ComboNoSeleccionadoException ex) {
            log.info("Rol no seleccionado.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        } catch (CadenaVaciaException ex) {
            log.info("login requerido.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        } catch (LoginException ex) {
            log.info("login repetido.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        } catch (PasswordVacioException ex) {
            log.info("pass requerido.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        } catch (PasswordDiferenteException ex) {
            log.info("pass diferente.");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        }
    }

    public void cargarPersona(Persona p) {
        log.log(Level.INFO, "la persona a cargar es la siguiente-->{0}", p.getPnombre());
        try {
            validaraccesoBean.validarPesonaRepetida(p);
            persona = p;
            Utilidad.actualizarElemento("perencontrada");
        } catch (PersonaUsuarioRepetidaException ex) {
            log.info("Persona ya tiene login");
            Utilidad.mensajeError("SICOVI", ex.getMessage());
        }

    }

    public void cargarRol() {
        if (rol.getIdRol() != null) {
            rol = rolDAO.buscarRolPorId(rol.getIdRol());
            log.log(Level.INFO, "el rol cargado es-->{0}", rol.getDescripcion());
        }
    }

    @PostConstruct
    public void Init() {
        cargarComboRoles();
        iniciarBotones();
        iniciarReadonly();
        cargarUltimo();
    }

    public GestionarAcceso() {
        rol = new Rol();
        usuario = new Usuario();
        persona = new Persona();
        listaControlBotones = new ArrayList<Integer>();
        listaControlReadonly =new ArrayList<Boolean>();
        estaEditando = false;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getVerificarPass() {
        return verificarPass;
    }

    public void setVerificarPass(String verificarPass) {
        this.verificarPass = verificarPass;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Integer> getListaControlBotones() {
        return listaControlBotones;
    }

    public void setListaControlBotones(List<Integer> listaControlBotones) {
        this.listaControlBotones = listaControlBotones;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }

    public List<Boolean> getListaControlReadonly() {
        return listaControlReadonly;
    }

    public void setListaControlReadonly(List<Boolean> listaControlReadonly) {
        this.listaControlReadonly = listaControlReadonly;
    }
    
    
}
