package com.pasantia.entidades;
// Generated 10/09/2013 10:40:59 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tblegreso generated by hbm2java
 */
@Entity
@Table(name="tblegreso"
    ,catalog="CasinoPasantia"
)
public class Tblegreso  implements java.io.Serializable {


     private Integer secegreso;
     private Tbltipoegreso tbltipoegreso;
     private Tblcompra tblcompra;
     private Date fecha;
     private Set tblcajas = new HashSet(0);

    public Tblegreso() {
    }

	
    public Tblegreso(Tbltipoegreso tbltipoegreso, Tblcompra tblcompra, Date fecha) {
        this.tbltipoegreso = tbltipoegreso;
        this.tblcompra = tblcompra;
        this.fecha = fecha;
    }
    public Tblegreso(Tbltipoegreso tbltipoegreso, Tblcompra tblcompra, Date fecha, Set tblcajas) {
       this.tbltipoegreso = tbltipoegreso;
       this.tblcompra = tblcompra;
       this.fecha = fecha;
       this.tblcajas = tblcajas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECEGRESO", unique=true, nullable=false)
    public Integer getSecegreso() {
        return this.secegreso;
    }
    
    public void setSecegreso(Integer secegreso) {
        this.secegreso = secegreso;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECTIPOEGRESO", nullable=false)
    public Tbltipoegreso getTbltipoegreso() {
        return this.tbltipoegreso;
    }
    
    public void setTbltipoegreso(Tbltipoegreso tbltipoegreso) {
        this.tbltipoegreso = tbltipoegreso;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCOMPRA", nullable=false)
    public Tblcompra getTblcompra() {
        return this.tblcompra;
    }
    
    public void setTblcompra(Tblcompra tblcompra) {
        this.tblcompra = tblcompra;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", nullable=false, length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblegreso")
    public Set getTblcajas() {
        return this.tblcajas;
    }
    
    public void setTblcajas(Set tblcajas) {
        this.tblcajas = tblcajas;
    }




}


