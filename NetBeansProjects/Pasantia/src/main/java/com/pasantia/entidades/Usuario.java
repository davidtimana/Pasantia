package com.pasantia.entidades;
// Generated 23/09/2013 01:53:37 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="Usuario"
    ,catalog="CasinoPasantia"
    , uniqueConstraints = {@UniqueConstraint(columnNames="secpersona"), @UniqueConstraint(columnNames="nomusuario"), @UniqueConstraint(columnNames="clave")} 
)
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private Persona persona;
     private Rol rol;
     private String clave;
     private String nomusuario;
     private boolean activo;
     private boolean sesion;
     private String Thema;

    public Usuario() {
    }

    public Usuario(Persona persona, Rol rol, String clave, String nomusuario, boolean activo, boolean sesion,String Thema) {
       this.persona = persona;
       this.rol = rol;
       this.clave = clave;
       this.nomusuario = nomusuario;
       this.activo = activo;
       this.sesion = sesion;
       this.Thema=Thema;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idUsuario", unique=true, nullable=false)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secpersona", unique=true, nullable=false)
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="secrol", nullable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    @Column(name="clave", unique=true, nullable=false, length=45)
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Column(name="nomusuario", unique=true, nullable=false, length=45)
    public String getNomusuario() {
        return this.nomusuario;
    }
    
    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }
    
    @Column(name="activo", nullable=false)
    public boolean isActivo() {
        return this.activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Column(name="sesion", nullable=false)
    public boolean isSesion() {
        return this.sesion;
    }
    
    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    @Column(name = "Thema", nullable = false)
    public String getThema() {
        return Thema;
    }

    public void setThema(String Thema) {
        this.Thema = Thema;
    }
    
    




}


