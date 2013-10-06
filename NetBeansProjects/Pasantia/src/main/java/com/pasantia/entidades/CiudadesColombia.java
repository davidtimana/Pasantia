package com.pasantia.entidades;
// Generated 6/10/2013 01:37:53 PM by Hibernate Tools 3.2.1.GA



/**
 * CiudadesColombia generated by hbm2java
 */
public class CiudadesColombia  implements java.io.Serializable {
    private static final long serialVersionUID = 3646054777583429671L;


     private int idCiudadesColombia;
     private String ciudad;
     private Double latitud;
     private Double longitud;

    public CiudadesColombia() {
    }

	
    public CiudadesColombia(int idCiudadesColombia) {
        this.idCiudadesColombia = idCiudadesColombia;
    }
    public CiudadesColombia(int idCiudadesColombia, String ciudad, Double latitud, Double longitud) {
       this.idCiudadesColombia = idCiudadesColombia;
       this.ciudad = ciudad;
       this.latitud = latitud;
       this.longitud = longitud;
    }
   
    public int getIdCiudadesColombia() {
        return this.idCiudadesColombia;
    }
    
    public void setIdCiudadesColombia(int idCiudadesColombia) {
        this.idCiudadesColombia = idCiudadesColombia;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public Double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }




}


