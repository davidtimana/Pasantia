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
 * Tblpedido generated by hbm2java
 */
@Entity
@Table(name="tblpedido"
    ,catalog="CasinoPasantia"
)
public class Tblpedido  implements java.io.Serializable {


     private Integer secpedido;
     private Persona persona;
     private Tblproveedor tblproveedor;
     private long totalpedido;
     private Integer totalCantidad;
     private Date fecha;
     private String observacion;
     private Set tbldetallepedidos = new HashSet(0);

    public Tblpedido() {
    }

	
    public Tblpedido(Persona persona, Tblproveedor tblproveedor, long totalpedido, Date fecha) {
        this.persona = persona;
        this.tblproveedor = tblproveedor;
        this.totalpedido = totalpedido;
        this.fecha = fecha;
    }
    public Tblpedido(Persona persona, Tblproveedor tblproveedor, long totalpedido, Integer totalCantidad, Date fecha, String observacion, Set tbldetallepedidos) {
       this.persona = persona;
       this.tblproveedor = tblproveedor;
       this.totalpedido = totalpedido;
       this.totalCantidad = totalCantidad;
       this.fecha = fecha;
       this.observacion = observacion;
       this.tbldetallepedidos = tbldetallepedidos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECPEDIDO", unique=true, nullable=false)
    public Integer getSecpedido() {
        return this.secpedido;
    }
    
    public void setSecpedido(Integer secpedido) {
        this.secpedido = secpedido;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCLIENTE", nullable=false)
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECPROVEEDOR", nullable=false)
    public Tblproveedor getTblproveedor() {
        return this.tblproveedor;
    }
    
    public void setTblproveedor(Tblproveedor tblproveedor) {
        this.tblproveedor = tblproveedor;
    }
    
    @Column(name="TOTALPEDIDO", nullable=false, precision=10, scale=0)
    public long getTotalpedido() {
        return this.totalpedido;
    }
    
    public void setTotalpedido(long totalpedido) {
        this.totalpedido = totalpedido;
    }
    
    @Column(name="TOTAL_CANTIDAD")
    public Integer getTotalCantidad() {
        return this.totalCantidad;
    }
    
    public void setTotalCantidad(Integer totalCantidad) {
        this.totalCantidad = totalCantidad;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA", nullable=false, length=10)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Column(name="OBSERVACION", length=250)
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblpedido")
    public Set getTbldetallepedidos() {
        return this.tbldetallepedidos;
    }
    
    public void setTbldetallepedidos(Set tbldetallepedidos) {
        this.tbldetallepedidos = tbldetallepedidos;
    }




}


