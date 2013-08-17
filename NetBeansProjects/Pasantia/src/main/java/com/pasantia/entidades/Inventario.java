package com.pasantia.entidades;
// Generated 17/08/2013 10:58:34 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Inventario generated by hbm2java
 */
@Entity
@Table(name="Inventario"
    ,catalog="CasinoPasantia"
)
public class Inventario  implements java.io.Serializable {


     private Integer idInventario;
     private DetalleVenta detalleVenta;
     private DetalleCompra detalleCompra;
     private int cantidad;

    public Inventario() {
    }

    public Inventario(DetalleVenta detalleVenta, DetalleCompra detalleCompra, int cantidad) {
       this.detalleVenta = detalleVenta;
       this.detalleCompra = detalleCompra;
       this.cantidad = cantidad;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idInventario", unique=true, nullable=false)
    public Integer getIdInventario() {
        return this.idInventario;
    }
    
    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_id_venta_detalle", nullable=false)
    public DetalleVenta getDetalleVenta() {
        return this.detalleVenta;
    }
    
    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_id_compra_detalle", nullable=false)
    public DetalleCompra getDetalleCompra() {
        return this.detalleCompra;
    }
    
    public void setDetalleCompra(DetalleCompra detalleCompra) {
        this.detalleCompra = detalleCompra;
    }
    
    @Column(name="cantidad", nullable=false)
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }




}


