package com.pasantia.entidades;
// Generated 6/10/2013 01:37:53 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Divisiones generated by hbm2java
 */
public class Divisiones  implements java.io.Serializable {
    private static final long serialVersionUID = -954935319292826481L;


     private Integer idDivisiones;
     private String nombreDivision;
     private Set<Batallon> batallons = new HashSet<Batallon>(0);
     private Set<DivisionesUbicacion> divisionesUbicacions = new HashSet<DivisionesUbicacion>(0);

    public Divisiones() {
    }

	
    public Divisiones(String nombreDivision) {
        this.nombreDivision = nombreDivision;
    }
    public Divisiones(String nombreDivision, Set<Batallon> batallons, Set<DivisionesUbicacion> divisionesUbicacions) {
       this.nombreDivision = nombreDivision;
       this.batallons = batallons;
       this.divisionesUbicacions = divisionesUbicacions;
    }
   
    public Integer getIdDivisiones() {
        return this.idDivisiones;
    }
    
    public void setIdDivisiones(Integer idDivisiones) {
        this.idDivisiones = idDivisiones;
    }
    public String getNombreDivision() {
        return this.nombreDivision;
    }
    
    public void setNombreDivision(String nombreDivision) {
        this.nombreDivision = nombreDivision;
    }
    public Set<Batallon> getBatallons() {
        return this.batallons;
    }
    
    public void setBatallons(Set<Batallon> batallons) {
        this.batallons = batallons;
    }
    public Set<DivisionesUbicacion> getDivisionesUbicacions() {
        return this.divisionesUbicacions;
    }
    
    public void setDivisionesUbicacions(Set<DivisionesUbicacion> divisionesUbicacions) {
        this.divisionesUbicacions = divisionesUbicacions;
    }




}


