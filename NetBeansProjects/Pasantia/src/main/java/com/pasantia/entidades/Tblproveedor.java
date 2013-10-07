package com.pasantia.entidades;
// Generated 6/10/2013 04:27:25 PM by Hibernate Tools 3.2.1.GA


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

/**
 * Tblproveedor generated by hbm2java
 */
@Entity
@Table(name="TBLPROVEEDOR"
    ,catalog="CasinoPasantia"
)
public class Tblproveedor  implements java.io.Serializable {


     private Integer secproveedor;
     private Tblentregapedido tblentregapedido;
     private Ciudad ciudad;
     private String nombre;
     private String telefono;
     private String direccion;
     private String nit;
     private String email;
     private Set tblcompras = new HashSet(0);
     private Set tblpedidos = new HashSet(0);
     private Set tblvisitapedidoses = new HashSet(0);

    public Tblproveedor() {
    }

	
    public Tblproveedor(Tblentregapedido tblentregapedido, Ciudad ciudad, String nombre, String telefono, String direccion, String nit) {
        this.tblentregapedido = tblentregapedido;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
    }
    public Tblproveedor(Tblentregapedido tblentregapedido, Ciudad ciudad, String nombre, String telefono, String direccion, String nit, String email, Set tblcompras, Set tblpedidos, Set tblvisitapedidoses) {
       this.tblentregapedido = tblentregapedido;
       this.ciudad = ciudad;
       this.nombre = nombre;
       this.telefono = telefono;
       this.direccion = direccion;
       this.nit = nit;
       this.email = email;
       this.tblcompras = tblcompras;
       this.tblpedidos = tblpedidos;
       this.tblvisitapedidoses = tblvisitapedidoses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="SECPROVEEDOR", unique=true, nullable=false)
    public Integer getSecproveedor() {
        return this.secproveedor;
    }
    
    public void setSecproveedor(Integer secproveedor) {
        this.secproveedor = secproveedor;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECENTREGAPEDIDO", nullable=false)
    public Tblentregapedido getTblentregapedido() {
        return this.tblentregapedido;
    }
    
    public void setTblentregapedido(Tblentregapedido tblentregapedido) {
        this.tblentregapedido = tblentregapedido;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCIUDAD", nullable=false)
    public Ciudad getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    @Column(name="NOMBRE", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name="TELEFONO", nullable=false, length=45)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Column(name="DIRECCION", nullable=false, length=45)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Column(name="NIT", nullable=false, length=45)
    public String getNit() {
        return this.nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    @Column(name="EMAIL", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblproveedor")
    public Set getTblcompras() {
        return this.tblcompras;
    }
    
    public void setTblcompras(Set tblcompras) {
        this.tblcompras = tblcompras;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblproveedor")
    public Set getTblpedidos() {
        return this.tblpedidos;
    }
    
    public void setTblpedidos(Set tblpedidos) {
        this.tblpedidos = tblpedidos;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblproveedor")
    public Set getTblvisitapedidoses() {
        return this.tblvisitapedidoses;
    }
    
    public void setTblvisitapedidoses(Set tblvisitapedidoses) {
        this.tblvisitapedidoses = tblvisitapedidoses;
    }




}


