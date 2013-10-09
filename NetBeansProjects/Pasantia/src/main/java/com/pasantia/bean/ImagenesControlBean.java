/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.ImagenDAO;
import com.pasantia.entidades.Imagen;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "imagenesControlBean")
@SessionScoped
public class ImagenesControlBean implements Serializable {
    
    private static final long serialVersionUID = -8550200053499635470L;

  
    private List<Imagen> imagenes;
    
    @Inject
    ImagenDAO imagenDAO;
    @Inject
    ParametrosBean parametrosBean;
    
    @PostConstruct
    public void cargarImagenes(){        
        imagenes=new ArrayList<Imagen>();
        imagenes=imagenDAO.buscarImagenesxPrincipal();
        parametrosBean.asignarRuta();
    }
    
    public ImagenesControlBean() {
        System.out.println("inicializando");
    }

    public List<Imagen> getImagenes() {        
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    
    
}
