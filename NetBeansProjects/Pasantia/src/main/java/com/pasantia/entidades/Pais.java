package com.pasantia.entidades;
// Generated 6/10/2013 01:37:53 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Pais generated by hbm2java
 */
public class Pais  implements java.io.Serializable {
    private static final long serialVersionUID = -6177991555124870685L;


     private Integer idPais;
     private String nombrePais;
     private Set<Departamento> departamentos = new HashSet<Departamento>(0);

    public Pais() {
    }

	
    public Pais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    public Pais(String nombrePais, Set<Departamento> departamentos) {
       this.nombrePais = nombrePais;
       this.departamentos = departamentos;
    }
   
    public Integer getIdPais() {
        return this.idPais;
    }
    
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    public String getNombrePais() {
        return this.nombrePais;
    }
    
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    public Set<Departamento> getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }




}


