/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author david
 */
@Named(value = "categoriasBean")
@SessionScoped
public class CategoriasBean implements Serializable {

    /**
     * Creates a new instance of CategoriasBean
     */
    public CategoriasBean() {
    }
}
