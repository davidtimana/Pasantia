package com.pasantia.entidades;
// Generated 6/10/2013 04:27:25 PM by Hibernate Tools 3.2.1.GA


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
 * Tblvisitapedidos generated by hbm2java
 */
@Entity
@Table(name="tblvisitapedidos"
    ,catalog="CasinoPasantia"
)
public class Tblvisitapedidos  implements java.io.Serializable {


     private Integer secvisitapedidos;
     private Tblproveedor tblproveedor;
     private Boolean lunes;
     private Boolean martes;
     private Boolean miercoles;
     private Boolean jueves;
     private Boolean viernes;
     private Boolean sabado;
     private Boolean domingo;

    public Tblvisitapedidos() {
    }

	
    public Tblvisitapedidos(Tblproveedor tblproveedor) {
        this.tblproveedor = tblproveedor;
    }
    public Tblvisitapedidos(Tblproveedor tblproveedor, Boolean lunes, Boolean martes, Boolean miercoles, Boolean jueves, Boolean viernes, Boolean sabado, Boolean domingo) {
       this.tblproveedor = tblproveedor;
       this.lunes = lunes;
       this.martes = martes;
       this.miercoles = miercoles;
       this.jueves = jueves;
       this.viernes = viernes;
       this.sabado = sabado;
       this.domingo = domingo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECVISITAPEDIDOS", unique=true, nullable=false)
    public Integer getSecvisitapedidos() {
        return this.secvisitapedidos;
    }
    
    public void setSecvisitapedidos(Integer secvisitapedidos) {
        this.secvisitapedidos = secvisitapedidos;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECPROVEEDOR", nullable=false)
    public Tblproveedor getTblproveedor() {
        return this.tblproveedor;
    }
    
    public void setTblproveedor(Tblproveedor tblproveedor) {
        this.tblproveedor = tblproveedor;
    }
    
    @Column(name="LUNES")
    public Boolean getLunes() {
        return this.lunes;
    }
    
    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
    }
    
    @Column(name="MARTES")
    public Boolean getMartes() {
        return this.martes;
    }
    
    public void setMartes(Boolean martes) {
        this.martes = martes;
    }
    
    @Column(name="MIERCOLES")
    public Boolean getMiercoles() {
        return this.miercoles;
    }
    
    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
    }
    
    @Column(name="JUEVES")
    public Boolean getJueves() {
        return this.jueves;
    }
    
    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
    }
    
    @Column(name="VIERNES")
    public Boolean getViernes() {
        return this.viernes;
    }
    
    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
    }
    
    @Column(name="SABADO")
    public Boolean getSabado() {
        return this.sabado;
    }
    
    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }
    
    @Column(name="DOMINGO")
    public Boolean getDomingo() {
        return this.domingo;
    }
    
    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }




}


