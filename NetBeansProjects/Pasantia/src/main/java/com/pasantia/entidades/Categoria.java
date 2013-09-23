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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name="Categoria"
    ,catalog="CasinoPasantia"
)
public class Categoria  implements java.io.Serializable {


     private Integer idCategoria;
     private String descripcion;
     private Set productos = new HashSet(0);

    public Categoria() {
    }

	
    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }
    public Categoria(String descripcion, Set productos) {
       this.descripcion = descripcion;
       this.productos = productos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idCategoria", unique=true, nullable=false)
    public Integer getIdCategoria() {
        return this.idCategoria;
    }
    
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    @Column(name="descripcion", nullable=false, length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="categoria")
    public Set getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set productos) {
        this.productos = productos;
    }




}


