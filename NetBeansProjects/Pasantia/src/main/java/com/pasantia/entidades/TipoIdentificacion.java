package com.pasantia.entidades;
// Generated 3/09/2013 11:43:16 AM by Hibernate Tools 3.2.1.GA


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
 * TipoIdentificacion generated by hbm2java
 */
@Entity
@Table(name="Tipo_Identificacion"
    ,catalog="CasinoPasantia"
)
public class TipoIdentificacion  implements java.io.Serializable {


     private Integer idTipoIdentificacion;
     private String nombreTipoIdentificacion;
     private Set personas = new HashSet(0);

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(String nombreTipoIdentificacion, Set personas) {
       this.nombreTipoIdentificacion = nombreTipoIdentificacion;
       this.personas = personas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idTipo_Identificacion", unique=true, nullable=false)
    public Integer getIdTipoIdentificacion() {
        return this.idTipoIdentificacion;
    }
    
    public void setIdTipoIdentificacion(Integer idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }
    
    @Column(name="nombre_tipo_identificacion", length=45)
    public String getNombreTipoIdentificacion() {
        return this.nombreTipoIdentificacion;
    }
    
    public void setNombreTipoIdentificacion(String nombreTipoIdentificacion) {
        this.nombreTipoIdentificacion = nombreTipoIdentificacion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tipoIdentificacion")
    public Set getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set personas) {
        this.personas = personas;
    }




}


