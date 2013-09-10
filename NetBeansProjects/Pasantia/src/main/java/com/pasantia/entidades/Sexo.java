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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sexo generated by hbm2java
 */
@Entity
@Table(name="Sexo"
    ,catalog="CasinoPasantia"
)
public class Sexo  implements java.io.Serializable {


     private Integer idSexo;
     private String nombreSexo;
     private Set personas = new HashSet(0);

    public Sexo() {
    }

	
    public Sexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }
    public Sexo(String nombreSexo, Set personas) {
       this.nombreSexo = nombreSexo;
       this.personas = personas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idSexo", unique=true, nullable=false)
    public Integer getIdSexo() {
        return this.idSexo;
    }
    
    public void setIdSexo(Integer idSexo) {
        this.idSexo = idSexo;
    }
    
    @Column(name="nombre_sexo", nullable=false, length=45)
    public String getNombreSexo() {
        return this.nombreSexo;
    }
    
    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="sexo")
    public Set getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set personas) {
        this.personas = personas;
    }




}


