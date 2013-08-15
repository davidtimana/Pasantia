package com.pasantia.entidades;
// Generated 15/08/2013 10:50:14 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CoordenadasCiudad generated by hbm2java
 */
@Entity
@Table(name="CoordenadasCiudad"
    ,catalog="CasinoPasantia"
)
public class CoordenadasCiudad  implements java.io.Serializable {


     private int idCoordenadasCiudad;
     private String nombreCiudad;
     private Double latitud;
     private Double longitud;

    public CoordenadasCiudad() {
    }

	
    public CoordenadasCiudad(int idCoordenadasCiudad) {
        this.idCoordenadasCiudad = idCoordenadasCiudad;
    }
    public CoordenadasCiudad(int idCoordenadasCiudad, String nombreCiudad, Double latitud, Double longitud) {
       this.idCoordenadasCiudad = idCoordenadasCiudad;
       this.nombreCiudad = nombreCiudad;
       this.latitud = latitud;
       this.longitud = longitud;
    }
   
     @Id 
    
    @Column(name="idCoordenadasCiudad", unique=true, nullable=false)
    public int getIdCoordenadasCiudad() {
        return this.idCoordenadasCiudad;
    }
    
    public void setIdCoordenadasCiudad(int idCoordenadasCiudad) {
        this.idCoordenadasCiudad = idCoordenadasCiudad;
    }
    
    @Column(name="nombre_ciudad", length=45)
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




}


