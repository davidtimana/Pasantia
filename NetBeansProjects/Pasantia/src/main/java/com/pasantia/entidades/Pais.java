package com.pasantia.entidades;
// Generated 6/10/2013 04:27:25 PM by Hibernate Tools 3.2.1.GA


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
 * Pais generated by hbm2java
 */
@Entity
@Table(name="Pais"
    ,catalog="CasinoPasantia"
)
public class Pais  implements java.io.Serializable {


     private Integer idPais;
     private String nombrePais;
     private Set departamentos = new HashSet(0);

    public Pais() {
    }

	
    public Pais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    public Pais(String nombrePais, Set departamentos) {
       this.nombrePais = nombrePais;
       this.departamentos = departamentos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idPais", unique=true, nullable=false)
    public Integer getIdPais() {
        return this.idPais;
    }
    
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    
    @Column(name="nombre_pais", nullable=false, length=45)
    public String getNombrePais() {
        return this.nombrePais;
    }
    
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="pais")
    public Set getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Set departamentos) {
        this.departamentos = departamentos;
    }




}


