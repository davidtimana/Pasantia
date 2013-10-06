package com.pasantia.entidades;
// Generated 6/10/2013 01:37:53 PM by Hibernate Tools 3.2.1.GA



/**
 * Tbldetallepedido generated by hbm2java
 */
public class Tbldetallepedido  implements java.io.Serializable {
    private static final long serialVersionUID = 3511391458622772680L;


     private Integer secdetallepedido;
     private Producto producto;
     private Tblpedido tblpedido;
     private Integer cantidad;
     private Long subtotal;

    public Tbldetallepedido() {
    }

	
    public Tbldetallepedido(Producto producto, Tblpedido tblpedido) {
        this.producto = producto;
        this.tblpedido = tblpedido;
    }
    public Tbldetallepedido(Producto producto, Tblpedido tblpedido, Integer cantidad, Long subtotal) {
       this.producto = producto;
       this.tblpedido = tblpedido;
       this.cantidad = cantidad;
       this.subtotal = subtotal;
    }
   
    public Integer getSecdetallepedido() {
        return this.secdetallepedido;
    }
    
    public void setSecdetallepedido(Integer secdetallepedido) {
        this.secdetallepedido = secdetallepedido;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Tblpedido getTblpedido() {
        return this.tblpedido;
    }
    
    public void setTblpedido(Tblpedido tblpedido) {
        this.tblpedido = tblpedido;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Long getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }




}


