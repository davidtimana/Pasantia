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
 * Tbltipoegreso generated by hbm2java
 */
@Entity
@Table(name="tbltipoegreso"
    ,catalog="CasinoPasantia"
)
public class Tbltipoegreso  implements java.io.Serializable {


     private Integer sectipoingreso;
     private String conceptoEgreso;
     private Set tblegresos = new HashSet(0);

    public Tbltipoegreso() {
    }

	
    public Tbltipoegreso(String conceptoEgreso) {
        this.conceptoEgreso = conceptoEgreso;
    }
    public Tbltipoegreso(String conceptoEgreso, Set tblegresos) {
       this.conceptoEgreso = conceptoEgreso;
       this.tblegresos = tblegresos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECTIPOINGRESO", unique=true, nullable=false)
    public Integer getSectipoingreso() {
        return this.sectipoingreso;
    }
    
    public void setSectipoingreso(Integer sectipoingreso) {
        this.sectipoingreso = sectipoingreso;
    }
    
    @Column(name="CONCEPTO_EGRESO", nullable=false, length=200)
    public String getConceptoEgreso() {
        return this.conceptoEgreso;
    }
    
    public void setConceptoEgreso(String conceptoEgreso) {
        this.conceptoEgreso = conceptoEgreso;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tbltipoegreso")
    public Set getTblegresos() {
        return this.tblegresos;
    }
    
    public void setTblegresos(Set tblegresos) {
        this.tblegresos = tblegresos;
    }




}


