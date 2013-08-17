package com.pasantia.entidades;
// Generated 17/08/2013 10:58:34 AM by Hibernate Tools 3.2.1.GA


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
 * FormaDePagar generated by hbm2java
 */
@Entity
@Table(name="Forma_De_Pagar"
    ,catalog="CasinoPasantia"
)
public class FormaDePagar  implements java.io.Serializable {


     private Integer idFormaDePagar;
     private String nombrePago;
     private Set detalleVentas = new HashSet(0);

    public FormaDePagar() {
    }

	
    public FormaDePagar(String nombrePago) {
        this.nombrePago = nombrePago;
    }
    public FormaDePagar(String nombrePago, Set detalleVentas) {
       this.nombrePago = nombrePago;
       this.detalleVentas = detalleVentas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idForma_De_Pagar", unique=true, nullable=false)
    public Integer getIdFormaDePagar() {
        return this.idFormaDePagar;
    }
    
    public void setIdFormaDePagar(Integer idFormaDePagar) {
        this.idFormaDePagar = idFormaDePagar;
    }
    
    @Column(name="nombre_pago", nullable=false, length=45)
    public String getNombrePago() {
        return this.nombrePago;
    }
    
    public void setNombrePago(String nombrePago) {
        this.nombrePago = nombrePago;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="formaDePagar")
    public Set getDetalleVentas() {
        return this.detalleVentas;
    }
    
    public void setDetalleVentas(Set detalleVentas) {
        this.detalleVentas = detalleVentas;
    }




}


