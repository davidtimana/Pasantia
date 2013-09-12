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
 * @author jbuitron
 */
@Named(value = "imagenesControlBean")
@SessionScoped
public class ImagenesControlBean implements Serializable {

  
    private List<Imagen> imagenes;
    
    @Inject
    ImagenDAO imagenDAO;
    
    @PostConstruct
    public void cargarImagenes(){
        System.out.println("con postconstructu");
        imagenes=new ArrayList<Imagen>();
        imagenes=imagenDAO.buscarImagenesxPrincipal();
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
