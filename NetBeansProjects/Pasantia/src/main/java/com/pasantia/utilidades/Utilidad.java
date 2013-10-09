/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.utilidades;

import com.pasantia.excepciones.FotoNoCopiadaException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Orlando Timaná
 */
public class Utilidad implements Serializable {
    
    

    private static final long serialVersionUID = 4251226551916517595L;
    private static Logger logger = Logger.getLogger(Utilidad.class.getName());

    public static void mensajeError(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
    }

    public static void mensajePeligro(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));
    }

    public static void mensajeInfo(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
    }

    public static void mensajeFatal(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
    }

    public static void abrirDialog(String idDialog) {
        String comando = idDialog + ".show()";
        RequestContext.getCurrentInstance().execute(comando);
    }

    public static void actualizarElemento(String idElemento) {
        RequestContext.getCurrentInstance().update(Utilidad.buscarHtmlComponete(idElemento).getClientId(FacesContext.getCurrentInstance()));
    }

    public static UIComponent buscarHtmlComponete(String idComponete) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (null != context) {
            return buscarHtmlComponete(context.getViewRoot(), idComponete);
        }
        return null;
    }

    public static UIComponent buscarHtmlComponete(UIComponent parent,
            String idComponete) {
        if (idComponete.equals(parent.getId())) {
            return parent;
        }
        Iterator<UIComponent> kids = parent.getFacetsAndChildren();
        while (kids.hasNext()) {
            UIComponent kid = kids.next();
            UIComponent found = buscarHtmlComponete(kid, idComponete);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static String estilosErrorInput() {
        return "border-color:#e9322d;-webkit-box-shadow:0 0 6px #f8b9b7;-moz-box-shadow: 0 0 6px #f8b9b7;box-shadow: 0 0 6px #f8b9b7";
    }

    public static void copiarArchivo(String fileName, InputStream in, String destination) throws FotoNoCopiadaException {

        try {

            logger.log(Level.INFO, "La ruta donde se creara el archivo es la siguiente-->{0}{1}", new Object[]{destination, fileName});
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;

            byte[] bytes = new byte[1024];


            while ((read = in.read(bytes)) != -1) {

                out.write(bytes, 0, read);

            }


            in.close();

            out.flush();

            out.close();



            logger.info("Archivo creado correctamente");

        } catch (IOException e) {

            logger.log(Level.INFO, "Error al copiar el archivo{0}", e.getMessage());
            throw new FotoNoCopiadaException("Error al copiar la foto: " + fileName);

        }

    }

    /**
     * Verifica si una cadena esta vacia o no
     *
     * @param cadena a evaluar
     * @return true si esta vacia y false en caso contrario
     *
     * @author David Timana
     */
    public static boolean cadenaVacia(String cadena) {
        boolean cadenaVacia = false;
        if (cadena == null || cadena.trim().isEmpty()) {
            cadenaVacia = true;
        }
        return cadenaVacia;
    }

    public static String encrypt(String cadena) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword("uniquekey");
        return s.encrypt(cadena);
    }

    public static String decrypt(String cadena) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword("uniquekey");
        String devuelve = "";
        try {
            devuelve = s.decrypt(cadena);
        } catch (Exception e) {
        }
        return devuelve;
    }
}
