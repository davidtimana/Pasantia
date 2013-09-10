package com.pasantia.entidades;
// Generated 10/09/2013 10:40:59 AM by Hibernate Tools 3.2.1.GA


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
 * Batallon generated by hbm2java
 */
@Entity
@Table(name="Batallon"
    ,catalog="CasinoPasantia"
)
public class Batallon  implements java.io.Serializable {


     private Integer idBatallon;
     private Ciudad ciudad;
     private Divisiones divisiones;
     private Persona persona;
     private String nombreBatallon;
     private String telefono1;
     private String telefono2;
     private String direccion;
     private String barrio;
     private Set casinos = new HashSet(0);

    public Batallon() {
    }

	
    public Batallon(Ciudad ciudad, Divisiones divisiones, String nombreBatallon, String telefono1, String direccion, String barrio) {
        this.ciudad = ciudad;
        this.divisiones = divisiones;
        this.nombreBatallon = nombreBatallon;
        this.telefono1 = telefono1;
        this.direccion = direccion;
        this.barrio = barrio;
    }
    public Batallon(Ciudad ciudad, Divisiones divisiones, Persona persona, String nombreBatallon, String telefono1, String telefono2, String direccion, String barrio, Set casinos) {
       this.ciudad = ciudad;
       this.divisiones = divisiones;
       this.persona = persona;
       this.nombreBatallon = nombreBatallon;
       this.telefono1 = telefono1;
       this.telefono2 = telefono2;
       this.direccion = direccion;
       this.barrio = barrio;
       this.casinos = casinos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idBatallon", unique=true, nullable=false)
    public Integer getIdBatallon() {
        return this.idBatallon;
    }
    
    public void setIdBatallon(Integer idBatallon) {
        this.idBatallon = idBatallon;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secciudad", nullable=false)
    public Ciudad getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secdivision", nullable=false)
    public Divisiones getDivisiones() {
        return this.divisiones;
    }
    
    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seccoronel")
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    @Column(name="nombre_batallon", nullable=false)
    public String getNombreBatallon() {
        return this.nombreBatallon;
    }
    
    public void setNombreBatallon(String nombreBatallon) {
        this.nombreBatallon = nombreBatallon;
    }
    
    @Column(name="telefono 1", nullable=false, length=45)
    public String getTelefono1() {
        return this.telefono1;
    }
    
    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }
    
    @Column(name="telefono 2", length=45)
    public String getTelefono2() {
        return this.telefono2;
    }
    
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
    
    @Column(name="direccion", nullable=false)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Column(name="barrio", nullable=false)
    public String getBarrio() {
        return this.barrio;
    }
    
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="batallon")
    public Set getCasinos() {
        return this.casinos;
    }
    
    public void setCasinos(Set casinos) {
        this.casinos = casinos;
    }




}


