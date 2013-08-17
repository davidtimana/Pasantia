package com.pasantia.entidades;
// Generated 17/08/2013 10:58:34 AM by Hibernate Tools 3.2.1.GA


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
 * Persona generated by hbm2java
 */
@Entity
@Table(name="Persona"
    ,catalog="CasinoPasantia"
)
public class Persona  implements java.io.Serializable {


     private Integer idTblpersona;
     private CatalogoVenta catalogoVenta;
     private Cargo cargo;
     private Ciudad ciudad;
     private TipoIdentificacion tipoIdentificacion;
     private Sexo sexo;
     private TipoPersona tipoPersona;
     private String pnombre;
     private String snombre;
     private String papellido;
     private String sapellido;
     private String cedula;
     private Date fechaNacimiento;
     private String email;
     private String telefono;
     private String movil;
     private String foto;
     private String direccion;
     private String barrio;
     private Set batallons = new HashSet(0);
     private Set usuarios = new HashSet(0);
     private Set proveedors = new HashSet(0);
     private Set ventas = new HashSet(0);
     private Set casinos = new HashSet(0);

    public Persona() {
    }

	
    public Persona(Ciudad ciudad, TipoIdentificacion tipoIdentificacion, Sexo sexo, TipoPersona tipoPersona, String pnombre, String papellido, String cedula, Date fechaNacimiento, String direccion, String barrio) {
        this.ciudad = ciudad;
        this.tipoIdentificacion = tipoIdentificacion;
        this.sexo = sexo;
        this.tipoPersona = tipoPersona;
        this.pnombre = pnombre;
        this.papellido = papellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.barrio = barrio;
    }
    public Persona(CatalogoVenta catalogoVenta, Cargo cargo, Ciudad ciudad, TipoIdentificacion tipoIdentificacion, Sexo sexo, TipoPersona tipoPersona, String pnombre, String snombre, String papellido, String sapellido, String cedula, Date fechaNacimiento, String email, String telefono, String movil, String foto, String direccion, String barrio, Set batallons, Set usuarios, Set proveedors, Set ventas, Set casinos) {
       this.catalogoVenta = catalogoVenta;
       this.cargo = cargo;
       this.ciudad = ciudad;
       this.tipoIdentificacion = tipoIdentificacion;
       this.sexo = sexo;
       this.tipoPersona = tipoPersona;
       this.pnombre = pnombre;
       this.snombre = snombre;
       this.papellido = papellido;
       this.sapellido = sapellido;
       this.cedula = cedula;
       this.fechaNacimiento = fechaNacimiento;
       this.email = email;
       this.telefono = telefono;
       this.movil = movil;
       this.foto = foto;
       this.direccion = direccion;
       this.barrio = barrio;
       this.batallons = batallons;
       this.usuarios = usuarios;
       this.proveedors = proveedors;
       this.ventas = ventas;
       this.casinos = casinos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="idTBLPERSONA", unique=true, nullable=false)
    public Integer getIdTblpersona() {
        return this.idTblpersona;
    }
    
    public void setIdTblpersona(Integer idTblpersona) {
        this.idTblpersona = idTblpersona;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCATALOGO_VENTA")
    public CatalogoVenta getCatalogoVenta() {
        return this.catalogoVenta;
    }
    
    public void setCatalogoVenta(CatalogoVenta catalogoVenta) {
        this.catalogoVenta = catalogoVenta;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCARGO")
    public Cargo getCargo() {
        return this.cargo;
    }
    
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECCIUDAD", nullable=false)
    public Ciudad getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECTIPO_IDENTIFICACION", nullable=false)
    public TipoIdentificacion getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }
    
    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECSEXO", nullable=false)
    public Sexo getSexo() {
        return this.sexo;
    }
    
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SECTIPO_PERSONA", nullable=false)
    public TipoPersona getTipoPersona() {
        return this.tipoPersona;
    }
    
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
    @Column(name="PNOMBRE", nullable=false, length=45)
    public String getPnombre() {
        return this.pnombre;
    }
    
    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }
    
    @Column(name="SNOMBRE", length=45)
    public String getSnombre() {
        return this.snombre;
    }
    
    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }
    
    @Column(name="PAPELLIDO", nullable=false, length=45)
    public String getPapellido() {
        return this.papellido;
    }
    
    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }
    
    @Column(name="SAPELLIDO", length=45)
    public String getSapellido() {
        return this.sapellido;
    }
    
    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }
    
    @Column(name="CEDULA", nullable=false, length=45)
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FECHA_NACIMIENTO", nullable=false, length=10)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    @Column(name="EMAIL", length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="TELEFONO", length=45)
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Column(name="MOVIL", length=45)
    public String getMovil() {
        return this.movil;
    }
    
    public void setMovil(String movil) {
        this.movil = movil;
    }
    
    @Column(name="FOTO")
    public String getFoto() {
        return this.foto;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    @Column(name="DIRECCION", nullable=false, length=45)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Column(name="BARRIO", nullable=false, length=45)
    public String getBarrio() {
        return this.barrio;
    }
    
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
    public Set getBatallons() {
        return this.batallons;
    }
    
    public void setBatallons(Set batallons) {
        this.batallons = batallons;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
    public Set getProveedors() {
        return this.proveedors;
    }
    
    public void setProveedors(Set proveedors) {
        this.proveedors = proveedors;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="persona")
    public Set getCasinos() {
        return this.casinos;
    }
    
    public void setCasinos(Set casinos) {
        this.casinos = casinos;
    }




}


