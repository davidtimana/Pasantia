package com.pasantia.entidades;
// Generated 23/09/2013 01:53:37 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ciudad generated by hbm2java
 */
@Entity
@Table(name="Ciudad"
    ,catalog="CasinoPasantia"
)
public class Ciudad  implements java.io.Serializable {


     private Integer idCiudad;
     private Departamento departamento;
     private String nombreCiudad;
     private Double latitud;
     private Double longitud;
     private Set tblproveedors = new HashSet(0);
     private Set personas = new HashSet(0);
     private Set batallons = new HashSet(0);

    public Ciudad() {
    }

	
    public Ciudad(Departamento departamento, String nombreCiudad) {
        this.departamento = departamento;
        this.nombreCiudad = nombreCiudad;
    }
    public Ciudad(Departamento departamento, String nombreCiudad, Double latitud, Double longitud, Set tblproveedors, Set personas, Set batallons) {
       this.departamento = departamento;
       this.nombreCiudad = nombreCiudad;
       this.latitud = latitud;
       this.longitud = longitud;
       this.tblproveedors = tblproveedors;
       this.personas = personas;
       this.batallons = batallons;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idCiudad", unique=true, nullable=false)
    public Integer getIdCiudad() {
        return this.idCiudad;
    }
    
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secdepartamento", nullable=false)
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    @Column(name="nombre_ciudad", nullable=false, length=45)
    public String getNombreCiudad() {
        return this.nombreCiudad;
    }
    
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
    @Column(name="latitud", precision=22, scale=0)
    public Double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    
    @Column(name="longitud", precision=22, scale=0)
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ciudad")
    public Set getTblproveedors() {
        return this.tblproveedors;
    }
    
    public void setTblproveedors(Set tblproveedors) {
        this.tblproveedors = tblproveedors;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ciudad")
    public Set getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set personas) {
        this.personas = personas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ciudad")
    public Set getBatallons() {
        return this.batallons;
    }
    
    public void setBatallons(Set batallons) {
        this.batallons = batallons;
    }




}


