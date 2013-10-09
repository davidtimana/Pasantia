/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.bean.ParametrosBean;
import com.pasantia.excepciones.FotoNoCopiadaException;
import com.pasantia.utilidades.Utilidad;
import com.pasantia.utilidades.UtilidadCadena;
import com.pasantia.utilidades.UtilidadNumero;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author David Timana
 */
@Named(value = "subirFotoProductoBean")
@SessionScoped
public class SubirFotoProductoBean implements Serializable {

    private static final long serialVersionUID = 719599983664857267L;
    private static final Logger log = Logger.getLogger(SubirFotoProductoBean.class.getName());
    private Boolean abrir;
    private String foto;
    private InputStream fotoSeleccionada;    
    private Map<Boolean, String> fotosSinGuardar;
    
    @Inject
    ParametrosBean parametrosBean;

    public void abrirSubir() {
        abrir = true;
        Utilidad.actualizarElemento("dlgsubirFotoProducto");
    }

    @PreDestroy
    public void finCarga() {
        log.info("*************************Limpiando fotos que se cargaron y no se guardaron");
        for (Map.Entry e : fotosSinGuardar.entrySet()) {            
            Boolean llave=(Boolean)e.getKey();
            String valor=(String)e.getValue();
            if(!llave){
                File imagen = new File(parametrosBean.getRutaTemporal()+valor);
                if(imagen.delete()){
                    log.log(Level.INFO,"La foto con nombre: {0}"+" ha sido eliminada de los temporales del servidor"
                            + "por que no ha sido asociada a ningun articulo", valor);
                }else{
                    log.log(Level.INFO,"La foto con nombre: {0}"+" N O pudo ser eliminada de los temporales del servidor"
                            + "por que no ha sido asociada a ningun articulo", valor);
                }
            }
        }
        log.info("*************************F I N Limpiando fotos que se cargaron y no se guardaron");
    }

    public void subirFoto(FileUploadEvent event) throws InterruptedException, IOException {
        log.info("**************Iniciamos Carga foto de producto");
        Integer num = UtilidadNumero.generarNumerosAleatorios();
        foto = event.getFile().getFileName();
        if (!UtilidadCadena.esUnaExtensionImagen(foto)) {

            foto = num + foto;
            fotoSeleccionada = event.getFile().getInputstream();
            log.log(Level.INFO, "El nombre de la imagen seleccionada es-->{0}", foto);

            try {
                log.log(Level.INFO, "La ubicacion del servidor donde se guardara es-->{0}", parametrosBean.getRutaTemporal());
                Utilidad.copiarArchivo(foto, fotoSeleccionada, parametrosBean.getRutaTemporal());
                Utilidad.mensajeInfo("SICOVI", "Foto: " + foto + ". Cargada Correctamente");
                fotosSinGuardar.put(false, foto);
                foto = "../../temp/" + foto;
                log.log(Level.INFO, "La ruta temporal de la foto es la siguiente--->{0}", foto);

                
                log.info("**************Fin seleccion foto");
                abrir = false;

                Utilidad.actualizarElemento("dlgsubirFotoProducto");
                Thread.sleep(4000);
                Utilidad.actualizarElemento("imgfotousuario");
            } catch (FotoNoCopiadaException e) {

                log.info("*************Error al copiar el archivo");
                Utilidad.mensajeFatal("SICOVI", e.getMessage());
            }

        } else {
            Utilidad.mensajeFatal("SICOVI", "Solo se permite la carga de imagenes con las siguientes extenciones: gif, jpeg, jpeg y png.");
        }


    }

    public void cancelarSubir() {
        abrir = false;
        Utilidad.actualizarElemento("dlgsubirFotoProducto");
    }

    public SubirFotoProductoBean() {
        abrir = false;      
        fotosSinGuardar= new TreeMap<Boolean,String>();
    }

    public Boolean getAbrir() {
        return abrir;
    }

    public void setAbrir(Boolean abrir) {
        this.abrir = abrir;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public InputStream getFotoSeleccionada() {
        return fotoSeleccionada;
    }

    public void setFotoSeleccionada(InputStream fotoSeleccionada) {
        this.fotoSeleccionada = fotoSeleccionada;
    }

    public Map<Boolean, String> getFotosSinGuardar() {
        return fotosSinGuardar;
    }

    public void setFotosSinGuardar(Map<Boolean, String> fotosSinGuardar) {
        this.fotosSinGuardar = fotosSinGuardar;
    }

  

   
    
    
}
