/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import java.io.Serializable;

/**
 *
 * @author David Timana
 */
public class DepartamentoTemp implements Serializable{
    
    private static final long serialVersionUID = -7233253807367934610L;
    
    private Integer idDepartamento;
    private String nombreDepartamento;
    private Boolean Seleccionado;

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Boolean getSeleccionado() {
        return Seleccionado;
    }

    public void setSeleccionado(Boolean Seleccionado) {
        this.Seleccionado = Seleccionado;
    }

    public DepartamentoTemp() {
    }

        
           
            
        }        
