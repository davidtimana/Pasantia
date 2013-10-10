/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.Tblunidad;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.PreciosArticuloException;
import com.pasantia.utilidades.CombosComunes;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "gestionArticulosBean")
@SessionScoped
public class GestionArticulosBean extends CombosComunes implements Serializable {
    
    private static final long serialVersionUID = 8930048590380965066L;
    private static final Logger log = Logger.getLogger(GestionArticulosBean.class.getName());
    
    
    private Producto producto;
    private Tblunidad unidad;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private List<Integer> listaControlBotones;
    private Boolean estaEditando;
    private Integer accordion;
    
    @Inject
    CrudDAO<Tblunidad> unidadDAO;
    @Inject
    CrudDAO<Categoria> categoriaDAO;
    @Inject
    CrudDAO<Ubicacion> ubicacionDAO;
    @Inject
    ValidarProductoBean validarProductoBean;
    

    public void cargarFoto(String foto) throws InterruptedException {
        producto.setImagen(foto);
        Thread.sleep(4000);
        Utilidad.actualizarElemento("imgfotousuario");
    }
    
    public void guardar(){
        try {
            validarProductoBean.validarProducto(producto, unidad, categoria, ubicacion, estaEditando);
        } catch (CadenaVaciaException ex) {
            log.info(ex.getMessage());
            Utilidad.mensajeError("SICOVI", "Datos Basicos Articulo: " + ex.getMessage());
            accordion=0;
            Utilidad.actualizarElemento("accordioproduc");
        }catch(PreciosArticuloException ex){
            log.info(ex.getMessage());
            Utilidad.mensajeError("SICOVI", "Precios de compra y venta: " + ex.getMessage());
            accordion=1;
            Utilidad.actualizarElemento("accordioproduc");
        }
    }
    
    public void cancelar(){
        accordion=0;
        validarProductoBean.limpiarEstilos();
        deshabilitarBotonesCancelar();
        reiniciarProducto();
        unidad=new Tblunidad();
        categoria=new Categoria();
        ubicacion=new Ubicacion();
        Utilidad.actualizarElemento("frmproductos");
    }
    
    public void nuevo(){
        estaEditando=false;
        accordion=0;
        reiniciarProducto();
        unidad=new Tblunidad();
        categoria=new Categoria();
        ubicacion=new Ubicacion();
        validarProductoBean.limpiarEstilos();
        deshabilitarBotonesEditaroNuevo();
        Utilidad.actualizarElemento("frmproductos");
    }
    
    public void reiniciarProducto(){
        producto.setCantidadActual(null);
        producto.setCantidadMinima(null);
        producto.setCodigoBarras("");  
        producto.setDescripcion("");
        producto.setImagen("../../FotosUsuarios/Sin_imagen_disponible.jpg");
    }
    
    public void editar(){
        estaEditando=true;
        deshabilitarBotonesEditaroNuevo();
        validarProductoBean.limpiarEstilos();
        
    }
    
    public void deshabilitarBotonesCancelar() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        Utilidad.actualizarElemento("grupobotonesprod");
    }
    
    public void deshabilitarBotonesEditaroNuevo() {
        listaControlBotones.removeAll(listaControlBotones);
        listaControlBotones.add(0);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        Utilidad.actualizarElemento("grupobotonesprod");
    }
    
     public void iniciarBotones() {
        listaControlBotones.add(1);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
        listaControlBotones.add(0);
        listaControlBotones.add(1);
    }
    
    public void cargarUnidad(){
        if(unidad.getSecunidad()!=null){
            unidad=unidadDAO.buscar(Tblunidad.class, unidad.getSecunidad());
        }
    }
    
    public void cargarCategoria(){
        if(categoria.getIdCategoria()!=null){
            categoria=categoriaDAO.buscar(Categoria.class, categoria.getIdCategoria());
        }
    }
    
    public void cargarUbicacion(){
        if(ubicacion.getIdUbicacion()!=null){
            ubicacion=ubicacionDAO.buscar(Ubicacion.class, ubicacion.getIdUbicacion());
        }
    }
    
    @PostConstruct
    public void Init(){
        cargarComboCategorias();
        cargarComboUnidades();
        cargarComboUbicaciones();
        iniciarBotones();
        producto.setImagen("../../FotosUsuarios/Sin_imagen_disponible.jpg");
    }
    
    public GestionArticulosBean() {
        producto = new Producto();
        unidad=new Tblunidad();
        categoria=new Categoria();
        ubicacion=new Ubicacion();
        listaControlBotones=new ArrayList<Integer>();
        estaEditando=false;
        accordion=0;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tblunidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Tblunidad unidad) {
        this.unidad = unidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Integer> getListaControlBotones() {
        return listaControlBotones;
    }

    public void setListaControlBotones(List<Integer> listaControlBotones) {
        this.listaControlBotones = listaControlBotones;
    }

    public Boolean getEstaEditando() {
        return estaEditando;
    }

    public void setEstaEditando(Boolean estaEditando) {
        this.estaEditando = estaEditando;
    }

    public Integer getAccordion() {
        return accordion;
    }

    public void setAccordion(Integer accordion) {
        this.accordion = accordion;
    }
    
    
    
    
    
    
    
    

    
    
    
    
    
}
