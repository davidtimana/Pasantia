package com.pasantia.bean;

import com.pasantia.dao.DetalleVentaDAO;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.dao.ProductoDAO;
import com.pasantia.dao.VentaDAO;
import com.pasantia.entidades.Casino;
import com.pasantia.entidades.Persona;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.TbldetalleVenta;
import com.pasantia.entidades.TblformaPago;
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
import com.pasantia.utilidades.CombosComunes;
import java.math.MathContext;
import org.primefaces.event.CellEditEvent;

@Named("venta")
@SessionScoped
public class VentaBean extends CombosComunes implements Serializable {

    static final Logger logger = Logger.getLogger(VentaBean.class.getSimpleName());

    //EJBs
    @Inject
    private ProductoDAO productoDAO;
    @Inject
    private VentaDAO ventaDAO;
    @Inject
    private DetalleVentaDAO detalleVentaDAO;
    @Inject
    private PersonaDAO personaDAO;
    //@Inject
    //private LoginBean loginBean;

    //Listas    
    private List<TbldetalleVenta> listaDetalleVenta;
    private List<Producto> listaCarrito;
    private List<Producto> listaProducto;
    private List<Persona> listaPersona;

    //Entidades
    private Producto producto;
    private Persona persona;
    private Tblventa venta;
    private TblformaPago formaPago;

    //Variables
    private BigDecimal total;
    
    @PostConstruct
    public void init() {
        logger.info("init() venta");
        //logger.log(Level.INFO, "vendedor ---------------------------> {0}", loginBean.getUsuario().getPersona().getIdTblpersona());
        listaProducto = productoDAO.buscar();
        listaPersona = personaDAO.buscar();
        cargarComboFormaPagos();
        listaDetalleVenta = new ArrayList<TbldetalleVenta>();
        listaCarrito = new ArrayList<Producto>();
        producto = new Producto();
        persona = new Persona();
        venta = new Tblventa();
        formaPago = new TblformaPago();
        total = BigDecimal.ZERO;
    }

    public <T> boolean comparaNulos(T entidad) {
        logger.info("-----------------------------Entro a comparaNulos -----------------------------");
        if (entidad == null) {
            return false;
        }
        return true;
    }

    public boolean comparaVacios(String cadena) {
        logger.info("-----------------------------Entro a comparaVacios -----------------------------");
        if (cadena.isEmpty()) {
            return false;
        }
        return true;
    }

    public void buscarPersonaPorCedula() {
        logger.info("-----------------------------Entro a buscar persona-----------------------------");
        logger.log(Level.INFO, "cedula -> {0}", persona.getCedula());
        persona = personaDAO.buscarPersonaPorCedula(persona.getCedula());
        if (persona == null) {
            persona = new Persona();
            Utilidad.mensajeError("ERROR", "Persona no encontrada.");
        }
    }

    public void buscarProductoPorCodigoBarras() {
        logger.info("-----------------------------Entro a buscar producto-----------------------------");
        logger.log(Level.INFO, "codigo de barras -> {0}", producto.getCodigoBarras());
        producto = productoDAO.buscarPorCodigoBarras(producto.getCodigoBarras());
        if (null == producto) {
            producto = new Producto();
            Utilidad.mensajeError("ERROR", "Producto no encontrado.");
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
        }
        if (suggestions.isEmpty()) {
            Utilidad.mensajeError("ERROR", "Producto no encontrado.");
        }
        return suggestions;
    }

    public List<Persona> completePersona(String query) {
        List<Persona> suggestions = new ArrayList<Persona>();
        logger.log(Level.INFO, " query  autocomplete : {0}", query);
        logger.log(Level.INFO, " tam lista persona  : {0}", listaPersona.size());
        if (query != null && query.length() > 0) {
            for (Persona p : listaPersona) {
                if (p.getCedula().toUpperCase().startsWith(query.toUpperCase())) {
                    suggestions.add(p);
                }
            }
        }
        if (suggestions.isEmpty()) {
            Utilidad.mensajeError("ERROR", "Persona no encontrada");
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

    public void handleSelectPersona(SelectEvent event) {
        logger.info("--------------------------------- Entro a handleSelect");
        persona = (Persona) event.getObject();
        logger.info("[Fijando la persona seleccionada desde el autocomplete");
        logger.log(Level.INFO, "persona nombre**************{0}", persona.getPnombre());
        logger.log(Level.INFO, "tipo persona para cliente**************{0}", persona.getTipoPersona().getNombreTipoPersona());
    }

    public void agregarACarrito() {
        logger.info("Hola desde agregarCarrito()");
        if (listaCarrito.contains(producto)) {
            Utilidad.mensajeInfo("Información", "El producto ya se encuentra en el carrito!");
            logger.info("El producto ya se encuentra en el carrito!");
        } else {
            listaCarrito.add(producto);
            calcularValorVenta();
        }
        producto = new Producto();
    }

    public void calcularValorVenta() {
           double totalTemp=0.0;
        for (Producto prod : listaCarrito) {
            Double precio=new Double(prod.getPrecioVenta1().doubleValue());
            logger.log(Level.INFO, "cantidad de cada producto--------------->{0}", prod.getPrecioVenta1());            
            totalTemp = totalTemp+precio;            
            logger.log(Level.INFO, "el total va quedando de la siguiente manera:  -->{0}", totalTemp);
            
        }
        total=new BigDecimal(totalTemp);
        logger.log(Level.INFO, "total: {0}", totalTemp);
    }

    public void eliminarDCarrito(Producto item) {
        if (item == null) {
            Utilidad.mensajeError("ERROR", "No se pudo eliminar el producto");
        } else {
            listaCarrito.remove(item);
            calcularValorVenta();
            logger.log(Level.INFO, "total despues de eliminar------------------------------->{0}", total);
        }
    }

    public void cellEdit(CellEditEvent event) {
        logger.info("<-------------------ENTRO A cellEdit----------------->");
        calcularValorVenta();
    }

    public void guardar() {

        logger.log(Level.INFO, "<------------------------CLIENTE------------------------------>{0}", persona.getCedula());

        if (getListaCarrito().isEmpty() || getListaCarrito()==null) {
            Utilidad.mensajeInfo("Información", "Debe agregar almenos un producto.");
        } else {
            if (persona.getCedula() == null) {
                Utilidad.mensajeInfo("Información", "Debe buscar un  cliente.");
            } else {
                Casino casino = new Casino();
                Date fecha = new Date();
                Persona vendedor = new Persona();

                venta.setFecha(fecha);
                venta.setPersonaBySeccliente(persona);
                casino.setIdCasino(2);
                venta.setCasino(casino);
                vendedor.setIdTblpersona(1);
                venta.setPersonaBySecvendedor(vendedor);
                venta.setTotal(total);
                byte totalCantidad = (byte) listaCarrito.size();
                venta.setTotalCantidad(totalCantidad);
                if (formaPago.getSecformaPago() == null) {
                    formaPago.setSecformaPago(1);
                    venta.setTblformaPago(formaPago);                     
                } else {
                    venta.setTblformaPago(formaPago);
                }
                venta = ventaDAO.guardar(venta);
                logger.log(Level.SEVERE, " id venta:--------------------- {0}", venta.getSecventa());

                for (Producto pto : listaCarrito) {
                    TbldetalleVenta dtv = new TbldetalleVenta();
                    dtv.setCantidad(10000);
                    dtv.setSubtotal(null);
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
                init();

                Utilidad.mensajeInfo("Información", "Venta realizada con exito.");
            }
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }   

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Tblventa getVenta() {
        return venta;
    }

    public void setVenta(Tblventa venta) {
        this.venta = venta;
    }

    public TblformaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(TblformaPago formaPago) {
        this.formaPago = formaPago;
    }

}
