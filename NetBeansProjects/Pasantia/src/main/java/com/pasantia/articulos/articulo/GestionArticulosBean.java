/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.PrecioCompra;
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
import java.math.BigDecimal;
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
    private Casino casino;
    
    private List<Casino> casinos;
    
    @Inject
    CrudDAO<Tblunidad> unidadDAO;
    @Inject
    CrudDAO<Categoria> categoriaDAO;
    @Inject
    CrudDAO<Ubicacion> ubicacionDAO;
    @Inject
    ValidarProductoBean validarProductoBean;
    @Inject
    ControlPreciosBean controlPreciosBean;
    @Inject
    GuardarArticuloBean guardarArticuloBean;
    @Inject
    CrudDAO<Casino> crudDAO;
    

    public void cargarFoto(String foto) throws InterruptedException {
        producto.setImagen(foto);
        Thread.sleep(4000);
        Utilidad.actualizarElemento("imgfotousuario");
    }
    
    public void guardar(){
        try {
            validarProductoBean.validarProducto(producto, unidad, categoria, ubicacion, estaEditando);
            List<PrecioCompra> precios=new ArrayList<PrecioCompra>();
            precios=controlPreciosBean.getPrecios();
            guardarArticuloBean.guardar(producto, unidad, categoria, ubicacion, precios,estaEditando);
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
        List<PrecioCompra> precios=new ArrayList<PrecioCompra>();
        controlPreciosBean.setPrecios(precios);
        controlPreciosBean.setDesHabiAdd(false);
        Utilidad.actualizarElemento("frmproductos");
    }
    
    public void reiniciarProducto(){
        producto.setCantidadActual(null);
        producto.setCantidadMinima(null);
        producto.setPrecioVenta1(BigDecimal.ZERO);
        producto.setPrecioVenta2(BigDecimal.ZERO);
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
    
    public List<Casino> completeCasinos(String query) {
        List<Casino> suggestions = new ArrayList<Casino>();
        log.log(Level.INFO, "query  autocomplete : {0}", query);
        log.log(Level.INFO, " tam lista  : {0}", casinos.size());
        if (query != null && query.length() > 0) {
            for (Casino c : casinos) {
                if (c.getNombre().toUpperCase().startsWith(query.toUpperCase())) {
                    suggestions.add(c);
                }
            }
        } else {
            // productoSeleccionado = null;
        }
        if (suggestions.isEmpty()) {
            Utilidad.mensajeError("No encontrado", "Producto no encontrado");
        }
        for (Casino casinos : suggestions) {
            log.log(Level.INFO, "medicamento {0}", casinos.getNombre());
        }
        return suggestions;
    }
    
    @PostConstruct
    public void Init(){
        casinos=crudDAO.buscarTodos(Casino.class);
        cargarComboCategorias();
        cargarComboUnidades();
        cargarComboUbicaciones();
        iniciarBotones();
        producto.setImagen("../../FotosUsuarios/Sin_imagen_disponible.jpg");
    }
    
    public GestionArticulosBean() {
        casino=new Casino();
        producto = new Producto();
        unidad=new Tblunidad();
        categoria=new Categoria();
        ubicacion=new Ubicacion();
        listaControlBotones=new ArrayList<Integer>();
        casinos=new ArrayList<Casino>();
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

    public Casino getCasino() {
        return casino;
    }

    public void setCasino(Casino casino) {
        this.casino = casino;
    }
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
}
