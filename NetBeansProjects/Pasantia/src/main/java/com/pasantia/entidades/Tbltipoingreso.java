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
 * Tbltipoingreso generated by hbm2java
 */
@Entity
@Table(name="tbltipoingreso"
    ,catalog="CasinoPasantia"
)
public class Tbltipoingreso  implements java.io.Serializable {


     private Integer sectipoingreso;
     private String conceptoIngreso;
     private Set tblingresos = new HashSet(0);

    public Tbltipoingreso() {
    }

	
    public Tbltipoingreso(String conceptoIngreso) {
        this.conceptoIngreso = conceptoIngreso;
    }
    public Tbltipoingreso(String conceptoIngreso, Set tblingresos) {
       this.conceptoIngreso = conceptoIngreso;
       this.tblingresos = tblingresos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECTIPOINGRESO", unique=true, nullable=false)
    public Integer getSectipoingreso() {
        return this.sectipoingreso;
    }
    
    public void setSectipoingreso(Integer sectipoingreso) {
        this.sectipoingreso = sectipoingreso;
    }
    
    @Column(name="CONCEPTO_INGRESO", nullable=false, length=200)
    public String getConceptoIngreso() {
        return this.conceptoIngreso;
    }
    
    public void setConceptoIngreso(String conceptoIngreso) {
        this.conceptoIngreso = conceptoIngreso;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tbltipoingreso")
    public Set getTblingresos() {
        return this.tblingresos;
    }
    
    public void setTblingresos(Set tblingresos) {
        this.tblingresos = tblingresos;
    }




}


