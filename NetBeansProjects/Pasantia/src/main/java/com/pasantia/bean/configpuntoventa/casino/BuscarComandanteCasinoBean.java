/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author David Timana
 */
@Named(value = "buscarComandanteCasinoBean")
@SessionScoped
public class BuscarComandanteCasinoBean implements Serializable {
    
    private static final long serialVersionUID = 2867410063400349218L;

    /**
     * Creates a new instance of BuscarComandanteCasinoBean
     */
    public BuscarComandanteCasinoBean() {
    }
}
