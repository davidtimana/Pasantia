/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.PrecioCompra;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.Tblunidad;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "guardarArticuloBean")
@SessionScoped
public class GuardarArticuloBean implements Serializable {

    private static final long serialVersionUID = 7897041329912755348L;
    private static final Logger log = Logger.getLogger(GuardarArticuloBean.class.getName());
    private Producto producto;
    private Boolean abrir;
    private List<SelectItem> selPrecios;
    private PrecioCompra precio;
    private String valorSelect;
    @Inject
    CrudDAO<Producto> crudDAOProduc;
    @Inject
    CrudDAO<PrecioCompra> crudDAOPrCom;
    @Inject
    SubirFotoProductoBean subirFotoProductoBean;

    public void guardar(Producto p, Tblunidad u, Categoria c, Ubicacion ub,
            List<PrecioCompra> listaPreciosCompra, Boolean estaEditando) {
        log.log(Level.INFO, "el producto que se va a guardar es el siguiente--->{0}", p.getDescripcion());
        log.log(Level.INFO, "la unidad que se va a guardar es la siguiente--->{0}", u.getUnidades());
        log.log(Level.INFO, "la categoria que se va a guardar es la siguiente--->{0}", c.getDescripcion());
        log.log(Level.INFO, "la ubicacion que se va a guardar es la siguiente--->{0}", ub.getDescripcion());
        log.log(Level.INFO, "la variable de edicion entra de la siguiente manera-->{0}", estaEditando);

        Set precioCompras = new HashSet(0);
        Map<Boolean, String> fotosSinGuardar = new TreeMap<Boolean, String>();
        fotosSinGuardar = subirFotoProductoBean.getFotosSinGuardar();


        for (PrecioCompra precioCompra : listaPreciosCompra) {
            log.log(Level.INFO, "los precios de compra son los siguientes-->{0}", precioCompra.getPrecio());
            precioCompras.add(precioCompra);
        }

        p.setPrecioCompras(precioCompras);

        if (estaEditando) {
            //Aqui editamos

            if (listaPreciosCompra.size() > 1) {
            }

        } else {
            //Aqui guardamos

            if (crudDAOProduc.crear(p)) {
                producto = crudDAOProduc.buscarUltimo(Producto.class);



                if (producto != null) {
                    log.log(Level.INFO, "el ultimo producto es el siguente-->{0}", producto.getDescripcion());
                    for (PrecioCompra pre : listaPreciosCompra) {
                        if (pre.getActivo()) {
                            pre.setProducto(producto);
                            if (crudDAOPrCom.crear(pre)) {
                                for (Map.Entry e : fotosSinGuardar.entrySet()) {
                                    Boolean llave = (Boolean) e.getKey();
                                    String valor = (String) e.getValue();
                                    if (valor.equals(producto.getImagen())) {
                                        llave = true;
                                    }
                                }
                                Utilidad.mensajeInfo("SICOVI", "Producto: " + p.getDescripcion() + ". Creado con exito.");
                            }
                        }
                    }
                }

            } else {
                Utilidad.mensajeFatal("SICOVI", "Ocurrio un error al ejecutar la operación.");
            }
        }

    }

    public void enviaraEditar() {
        log.log(Level.INFO, "El valor seleccionado es el siguiente-->{0}", valorSelect);
        abrir = false;
        Utilidad.actualizarElemento("dlgpreciosprod");
    }

    public void prepararGuardado(Producto p, Tblunidad u, Categoria c, Ubicacion ub,
            List<PrecioCompra> listaPreciosCompra, Boolean estaEditando) {

        if (estaEditando && listaPreciosCompra.size() > 1) {

            for (PrecioCompra precioCompra : listaPreciosCompra) {
                selPrecios.add(new SelectItem(precioCompra.getActivo(), precioCompra.getPrecio().toString()));
            }
            abrir = true;
            Utilidad.actualizarElemento("dlgpreciosprod");

        } else {
            guardar(p, u, c, ub, listaPreciosCompra, estaEditando);
        }

    }

    @PostConstruct
    public void Init() {
        selPrecios = new ArrayList<SelectItem>();
    }

    public GuardarArticuloBean() {
        producto = new Producto();
        precio = new PrecioCompra();
        abrir = false;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Boolean getAbrir() {
        return abrir;
    }

    public void setAbrir(Boolean abrir) {
        this.abrir = abrir;
    }

    public List<SelectItem> getSelPrecios() {
        return selPrecios;
    }

    public void setSelPrecios(List<SelectItem> selPrecios) {
        this.selPrecios = selPrecios;
    }

    public PrecioCompra getPrecio() {
        return precio;
    }

    public void setPrecio(PrecioCompra precio) {
        this.precio = precio;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }
    
    
}
