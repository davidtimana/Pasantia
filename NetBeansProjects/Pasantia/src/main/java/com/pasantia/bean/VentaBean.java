/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import com.pasantia.dao.DetalleVentaDAO;
import com.pasantia.dao.ProductoDAO;
import com.pasantia.dao.VentaDAO;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.TbldetalleVenta;
import com.pasantia.entidades.Tblventa;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named("venta")
@SessionScoped
public class VentaBean implements Serializable {

    static final Logger logger = Logger.getLogger(VentaBean.class.getSimpleName());

    //EJBs
    @Inject
    private ProductoDAO productoDAO;
    @Inject
    private VentaDAO ventaDAO;
    @Inject
    private DetalleVentaDAO detalleVentaDAO;

    //Listas
    List<Producto> listaProducto;
    List<TbldetalleVenta> listaDetalleVenta;
    List<Producto> listaCarrito;

    //Entidades
    private Producto producto;

    //Variables
    private BigDecimal total;

    @PostConstruct
    public void init() {
        logger.info("init() venta");
        listaProducto = productoDAO.buscar();
        listaDetalleVenta = new ArrayList<TbldetalleVenta>();
        listaCarrito = new ArrayList<Producto>();
        producto = new Producto();       
    }

    public void buscarProductoPorCodigoBarras() {
        logger.log(Level.INFO, "codigo de barras -> {0}", producto.getCodigoBarras());
        producto = productoDAO.buscarPorCodigoBarras(producto.getCodigoBarras());
        if (null == producto) {
            producto = new Producto();
            Utilidad.mensajeError("No encontrado", "Producto no encontrado.");
        }
    }

    public List<Producto> completeProductos(String query) {
        List<Producto> suggestions = new ArrayList<Producto>();
        logger.log(Level.INFO, "query  autocomplete : {0}", query);
        logger.log(Level.INFO, " tam lista  : {0}", listaProducto.size());
        if (query != null && query.length() > 0) {
            for (Producto p : listaProducto) {
                if (p.getDescripcion().toUpperCase().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        } else {
            // productoSeleccionado = null;
        }
        if (suggestions.isEmpty()) {
            Utilidad.mensajeError("No encontrado", "Producto no encontrado");
        }
        for (Producto productos : suggestions) {
            logger.log(Level.INFO, "medicamento {0}", productos.getDescripcion());
        }
        return suggestions;
    }

    public void handleSelect(SelectEvent event) {
        logger.info("--------------------------------- Entro a handleSelect");
        producto = (Producto) event.getObject();
        logger.info("[Fijando el producto seleccionado desde el autocomplete");
        logger.log(Level.INFO, "producto nombre**************{0}", producto.getDescripcion());
        logger.log(Level.INFO, "casino para producto**************{0}", producto.getCasino().getNombre());
    }

    public void agregarACarrito() {
        logger.info("Hola desde agregarCarrito()");
        if (listaCarrito.contains(producto)) {
            Utilidad.mensajeInfo("", "El producto ya se encuentra en el carrito!");
            logger.info("El producto ya se encuentra en el carrito!");
        } else {
            listaCarrito.add(producto);
        }
        producto = new Producto();
    }

    public void calcularValorVenta() {
        for (Producto prod : listaCarrito) {
            total = prod.getPrecioVenta1().add(total);
        }
        logger.log(Level.INFO, "total: {0}", total);
    }

    public void guardar() {
        if (getListaCarrito().isEmpty()) {
            Utilidad.mensajeInfo("Debe Agregar", "Debe agregar almenos un producto.");
        } else {
            Tblventa venta = new Tblventa();
            Date fecha = new Date();
            Persona persona = new Persona();

            venta.setFecha(fecha);
            persona.setIdTblpersona(0);
            venta.setPersonaBySeccliente(persona);

            venta = ventaDAO.guardar(venta);
            logger.log(Level.SEVERE, " id venta:--------------------- {0}", venta.getSecventa());

            for (Producto pto : listaCarrito) {
                TbldetalleVenta dtv = new TbldetalleVenta();
                dtv.setCantidad(5);
                //dtv.setSubtotal(total);
                dtv.setProducto(pto);
                dtv.setTblventa(venta);
                listaDetalleVenta.add(dtv);
            }
            for (TbldetalleVenta dt : listaDetalleVenta) {
                logger.log(Level.WARNING, "======={0}", dt.getProducto().getIdProducto());
                logger.log(Level.WARNING, "======={0}", dt.getTblventa().getSecventa());
                logger.log(Level.WARNING, "======={0}", dt.getSubtotal());
            }
            detalleVentaDAO.guardar(listaDetalleVenta);

            Utilidad.mensajeInfo("Guardo", "Venta realizada con exito.");
        }
    }

    //Getter and Setter
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Producto> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
}
