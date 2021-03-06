package com.pasantia.entidades;
// Generated 6/10/2013 04:27:25 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Departamento generated by hbm2java
 */
@Entity
@Table(name="Departamento"
    ,catalog="CasinoPasantia"
)
public class Departamento  implements java.io.Serializable {


     private int idDepartamento;
     private Pais pais;
     private String nombreDepartamento;
     private Double longitud;
     private Double latitud;
     private Set divisionesUbicacions = new HashSet(0);
     private Set ciudads = new HashSet(0);

    public Departamento() {
    }

	
    public Departamento(int idDepartamento, Pais pais, String nombreDepartamento) {
        this.idDepartamento = idDepartamento;
        this.pais = pais;
        this.nombreDepartamento = nombreDepartamento;
    }
    public Departamento(int idDepartamento, Pais pais, String nombreDepartamento, Double longitud, Double latitud, Set divisionesUbicacions, Set ciudads) {
       this.idDepartamento = idDepartamento;
       this.pais = pais;
       this.nombreDepartamento = nombreDepartamento;
       this.longitud = longitud;
       this.latitud = latitud;
       this.divisionesUbicacions = divisionesUbicacions;
       this.ciudads = ciudads;
    }
   
     @Id 
    
    @Column(name="idDepartamento", unique=true, nullable=false)
    public int getIdDepartamento() {
        return this.idDepartamento;
    }
    
    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secpais", nullable=false)
    public Pais getPais() {
        return this.pais;
    }
    
    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    @Column(name="nombre_departamento", nullable=false, length=45)
    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }
    
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    @Column(name="longitud", precision=22, scale=0)
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    
    @Column(name="latitud", precision=22, scale=0)
    public Double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="departamento")
    public Set getDivisionesUbicacions() {
        return this.divisionesUbicacions;
    }
    
    public void setDivisionesUbicacions(Set divisionesUbicacions) {
        this.divisionesUbicacions = divisionesUbicacions;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="departamento")
    public Set getCiudads() {
        return this.ciudads;
    }
    
    public void setCiudads(Set ciudads) {
        this.ciudads = ciudads;
    }




}


