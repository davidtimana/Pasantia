package com.pasantia.entidades;
// Generated 15/08/2013 10:50:14 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ubicacion generated by hbm2java
 */
@Entity
@Table(name="Ubicacion"
    ,catalog="CasinoPasantia"
)
public class Ubicacion  implements java.io.Serializable {


     private Integer idUbicacion;
     private String descripcion;
     private Set productos = new HashSet(0);

    public Ubicacion() {
    }

	
    public Ubicacion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Ubicacion(String descripcion, Set productos) {
       this.descripcion = descripcion;
       this.productos = productos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idUbicacion", unique=true, nullable=false)
    public Integer getIdUbicacion() {
        return this.idUbicacion;
    }
    
    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
    
    @Column(name="descripcion", nullable=false, length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ubicacion")
    public Set getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set productos) {
        this.productos = productos;
    }




}


