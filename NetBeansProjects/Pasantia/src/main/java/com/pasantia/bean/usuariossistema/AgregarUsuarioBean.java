/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

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
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author David Orlando Timana
 */
@Named(value = "agregarUsuarioBean")
@SessionScoped
public class AgregarUsuarioBean implements Serializable{

    
    private static Logger log = Logger.getLogger(AgregarUsuarioBean.class.getName());
    
    @Inject
    PersonaDAO personaDAO;
    @Inject
    GuardarSinFotoBean guardarSinFotoBean;
    @Inject
    CrudDAO crudDAO;
    
    @PostConstruct
    public void Init(){
        System.out.println("***********Inicianilizando");        
        
    }
    
    
     /** 
     * Guarda los usuarios de la aplicacion con sus respectivos
     * roles.
     * @param p Datos comunes de la persona.
     * @param c Ciudad de la persona.
     * @param s Sexo de la persona.
     * @param ti TipoIdentificacion de la persona.
     * @param tp TipoPersona de la persona.
     * @param ca Cargo de la persona.
     * @param cv CatalogoVenta de la persona.     
     * @param rutaTemp ruta temporal de donde se encuentra la foto del usuario ubicada.     
     * @param fotoSubida bandera que indica si se subio o no la foto.
     * @return Mensaje de guardado correcto o no.
     */
    public void guardarUsuario(Persona p, Ciudad c, Sexo s, TipoIdentificacion ti, TipoPersona tp,
            Cargo ca, CatalogoVenta cv, String nombreFoto, Boolean fotoSubida, Double latitud, Double longitud) {

        log.log(Level.INFO, "************Iniciando a guardar la persona: {0}", p.getPnombre());

        if (fotoSubida) {
            log.info("***********Foto subida");
            p.setSexo(s);
            p.setTipoIdentificacion(ti);
            p.setCiudad(c);
            p.setTipoPersona(tp);
            if (!Utilidad.cadenaVacia(nombreFoto)) {
                p.setFoto("../../" + nombreFoto);
            }

            p.setLatitud(latitud);
            p.setLongitud(longitud);
            if (tp.getNombreTipoPersona().equals("Vendedor Proveedor")) {
                p.setCatalogoVenta(cv);
            } else {
                p.setCargo(ca);
            }

            try {
                if (crudDAO.crear(p)) {
                    Utilidad.mensajeInfo("SICOVI", "Usuario: " + p.getPnombre() + " " + p.getPapellido() + ". Guardado Correctamente");
                }

            } catch (PersonaIdentificacionDuplicadoException e) {
                Utilidad.mensajeError("SICOVI", e.getMessage());
            }

        } else {
            log.info("***********Foto Nooooo subida");
            guardarSinFotoBean.abrirComfirmar();
        }

    }
    
    
    
   
    
    
    public AgregarUsuarioBean() {
        
    }

    
    
        
    

}
