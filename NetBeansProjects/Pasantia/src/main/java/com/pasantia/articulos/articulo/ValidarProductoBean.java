/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 *
 * @author David Timana
 */
@Named(value = "validarProductoBean")
@SessionScoped
public class ValidarProductoBean implements Serializable {
    
    private static final long serialVersionUID = 3870572897373551536L;
    private static final Logger log = Logger.getLogger(ValidarProductoBean.class.getName());
    

    
    /**
     * Creates a new instance of ValidarProductoBean
     */
    public ValidarProductoBean() {
    }
}
