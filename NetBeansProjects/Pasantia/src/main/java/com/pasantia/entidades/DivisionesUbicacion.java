package com.pasantia.entidades;
// Generated 15/08/2013 10:50:14 AM by Hibernate Tools 3.2.1.GA


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
 * DivisionesUbicacion generated by hbm2java
 */
@Entity
@Table(name="DivisionesUbicacion"
    ,catalog="CasinoPasantia"
)
public class DivisionesUbicacion  implements java.io.Serializable {


     private Integer idDivisionesUbicacion;
     private Departamento departamento;
     private Divisiones divisiones;

    public DivisionesUbicacion() {
    }

    public DivisionesUbicacion(Departamento departamento, Divisiones divisiones) {
       this.departamento = departamento;
       this.divisiones = divisiones;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idDivisionesUbicacion", unique=true, nullable=false)
    public Integer getIdDivisionesUbicacion() {
        return this.idDivisionesUbicacion;
    }
    
    public void setIdDivisionesUbicacion(Integer idDivisionesUbicacion) {
        this.idDivisionesUbicacion = idDivisionesUbicacion;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secdepartamento", nullable=false)
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secdivision", nullable=false)
    public Divisiones getDivisiones() {
        return this.divisiones;
    }
    
    public void setDivisiones(Divisiones divisiones) {
        this.divisiones = divisiones;
    }




}


