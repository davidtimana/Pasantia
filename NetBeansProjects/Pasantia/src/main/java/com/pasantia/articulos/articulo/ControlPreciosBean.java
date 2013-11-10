/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.dao.CrudDAO;
import com.pasantia.dao.PreciosCompraDAO;
import com.pasantia.entidades.PrecioCompra;
import com.pasantia.entidades.Producto;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author David Timana
 */
@Named(value = "controlPreciosBean")
@SessionScoped
public class ControlPreciosBean implements Serializable {
    
    private static final long serialVersionUID = -353555958411021048L;
    private static final Logger log = Logger.getLogger(ControlPreciosBean.class.getName());
    
    
    private List<PrecioCompra> precios;    
    private PrecioCompra precioSeleccionado;
    private PrecioCompra precioCompra;
    private Boolean desHabiAdd;
    
    @Inject
    GestionArticulosBean articulosBean;
    @Inject
    PreciosCompraDAO  preciosCompraDAO;
    
    public void cargarPrecios(Producto p){        
        
        if(p!=null){
            precios=preciosCompraDAO.buscarPreciosporProducto(p.getIdProducto());
            if(!Utilidad.listaEstaVacia(precios)){
                for (PrecioCompra precioCompra1 : precios) {
                    if(precioCompra1.getActivo()){
                        precioCompra=precioCompra1;
                    }
                }
            }
	}
            
        
    }
    
    public void prueba(SelectEvent event){
        PrecioCompra p=(PrecioCompra)event.getObject();
        log.log(Level.INFO, "probando*********************************{0}", p.getPrecio());
    }
    
    public Integer totalPrecios() {
        return precios.size();
    }
    
    public void editar(CellEditEvent event) {
        
            BigDecimal anteriorValor = (BigDecimal) event.getOldValue();
            BigDecimal nuevoValor = (BigDecimal) event.getNewValue();
            log.log(Level.INFO, "El anterior valor es el siguiente-->{0}", anteriorValor);
            log.log(Level.INFO, "El nuevo valor es el siguiente-->{0}", nuevoValor);
            
                
                if (nuevoValor != null) {                    
                    if (articulosBean.getProducto().getPrecioVenta1() != null
                            && articulosBean.getProducto().getPrecioVenta1().intValue() > 0
                            && articulosBean.getProducto().getPrecioVenta1().intValue() > nuevoValor.intValue()) {                            
                        
                        int cont=0;
                        for (PrecioCompra pr : precios) {
                            
                            if (pr.getPrecio().compareTo(nuevoValor) == 0) {                            
                                pr.setPrecio(nuevoValor);
                                Date fechaActual = new Date();
                                pr.setFecha(fechaActual);
                               // pr.setValor_ganancia(calcularGanancia(nuevoValor, articulosBean.getProducto().getPrecioVenta1()));
                                //log.log(Level.INFO, "***El valor de la ganancia para este precio de venta es -->{0}", precioCompra.getValor_ganancia());
                                //pr.setPorcentaje_ganacia(calcularPorcentaje(nuevoValor, articulosBean.getProducto().getPrecioVenta1()));
                                //log.log(Level.INFO, "el porcentaje de venta para este precio de venta es el siguiente-->{0}", precioCompra.getPorcentaje_ganacia());
                                precios.set(cont, pr);
                                break;
                            }
                            cont++;
                        }                        
                        Utilidad.actualizarElemento("tblprecios");

                    }
                }

           
        
    }
    
    public void eliminar(PrecioCompra p) {
        log.log(Level.INFO, "Inicializaremos a eliminar de la lista temporal el siguiente elemento-->{0}", p.getPrecio());
        int cont = 0;
        for (PrecioCompra precioCompra1 : precios) {
            log.log(Level.INFO, "---------------Buscamos el elemento a elimnar {0}", precioCompra1.getPrecio());
            log.log(Level.INFO, "el resultado de la comparacion es-->{0}", precioCompra1.getPrecio().compareTo(p.getPrecio()));
            if (precioCompra1.getPrecio().compareTo(p.getPrecio()) == 0) {
                precios.remove(cont);
                desHabiAdd=false;
                    Utilidad.actualizarElemento("btnpreccompra");
                log.info("Objeto eliminado correctamente");
                Utilidad.actualizarElemento("tblprecios");
                Utilidad.mensajeInfo("SICOVI", "Precio de compra eliminado correctamente.");
                break;
            }
            cont++;
        }

    }
    
    public void agregar() {
        try {
            buscarPreciosRepetidos();
            if (precioCompra.getPrecio() != null && precioCompra.getPrecio().intValue() > 0) {
                if (articulosBean.getProducto().getPrecioVenta1() != null
                        && articulosBean.getProducto().getPrecioVenta1().intValue() > 0
                        && articulosBean.getProducto().getPrecioVenta1().intValue() > precioCompra.getPrecio().intValue()) {
                    desHabiAdd=true;
                    Utilidad.actualizarElemento("btnpreccompra");
                    Date fechaActual = new Date(); 
                    precioCompra.setActivo(true);
                    precioCompra.setFecha(fechaActual);
                    //precioCompra.setValor_ganancia(calcularGanancia(precioCompra.getPrecio(), articulosBean.getProducto().getPrecioVenta1()));
                    //log.log(Level.INFO, "***El valor de la ganancia para este precio de venta es -->{0}", precioCompra.getValor_ganancia());
                    //precioCompra.setPorcentaje_ganacia(calcularPorcentaje(precioCompra.getPrecio(), articulosBean.getProducto().getPrecioVenta1()));
                    //log.log(Level.INFO, "el porcenteja de venta para este precio de venta es el siguiente-->{0}", precioCompra.getPorcentaje_ganacia());
                    precios.add(precioCompra);                    
                    Utilidad.actualizarElemento("tblprecios");
                    precioCompra = new PrecioCompra();
                    Utilidad.actualizarElemento("txtprec2");
                    Utilidad.actualizarElemento("txtpreccompra");
                } else {
                    Utilidad.mensajeError("SICOVI", "El precio de venta principal es requerido, t"
                            + "iene que ser mayor que cero (0) y mayor que el precio de compra.");
                }
            } else {
                Utilidad.mensajeError("SICOVI", "El precio de compra es requerido y tiene que ser mayor que cero (0).");
            }
        } catch (CadenaVaciaException ex) {
            log.info(ex.getMessage());
            Utilidad.mensajeInfo("SICOVI", ex.getMessage());
            precioCompra = new PrecioCompra();
            Utilidad.actualizarElemento("txtprec2");
            Utilidad.actualizarElemento("txtpreccompra");
            Utilidad.actualizarElemento("tblprecios");
        }


    }
    
    public void buscarPreciosRepetidos() throws CadenaVaciaException{
        for (PrecioCompra p : precios) {
            if(p.getPrecio().compareTo(precioCompra.getPrecio())==0){
                throw  new CadenaVaciaException("El precio ingresado ya existe.");
            }
        }
    }
    
    public void buscarPreciosValor(BigDecimal valor) throws CadenaVaciaException{
        for (PrecioCompra p : precios) {
            log.log(Level.INFO, "los valores son los siguiente-->{0}", p.getPrecio());
            if(p.getPrecio().compareTo(valor)==0){
                log.info("Encontrado en editar***********************");
                throw  new CadenaVaciaException("El precio ingresado ya existe.");
            }else{
                log.info("no fue encontrado----------------------------------");
            }
        }
    }
    
    public void buscarPreciosValorValidator(BigDecimal valor){
        for (PrecioCompra p : precios) {
            if(p.getPrecio().compareTo(valor)==0){
                FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SICOVI", "El precio ingresado ya existe.");
                throw new ValidatorException(mess);
            }
        }
    }
    
    public BigDecimal calcularPorcentaje(BigDecimal precioCompra, BigDecimal precioVenta) {
        BigDecimal ganancia = calcularGanancia(precioCompra, precioVenta);
        log.log(Level.INFO, "La ganacia en el calculo del porcentaje quedo-->{0}", ganancia);
        BigDecimal cien = BigDecimal.valueOf(100);
        log.log(Level.INFO, "El porcentaje total quedo-->{0}", cien);
        BigDecimal multi = cien.multiply(ganancia);
        log.log(Level.INFO, "El multiplicacion al aplicar regla de tres para obtener porcentaje quedo-->{0}", multi);
        return multi.divide(precioCompra,3, RoundingMode.CEILING);
    }
    
    public BigDecimal calcularGanancia(BigDecimal precioCompra, BigDecimal precioVenta) {
        log.log(Level.INFO, "El precio de compra es el siguiente-->{0} y el precio de venta es-->{1}", new Object[]{precioCompra, precioVenta});
        return precioVenta.subtract(precioCompra);
    }
    
    @PostConstruct
    public void Init() {
        totalPrecios();
        
    }
    
    public ControlPreciosBean() {
        precios = new ArrayList<PrecioCompra>();        
        precioSeleccionado = new PrecioCompra();
        precioCompra = new PrecioCompra();
        desHabiAdd=false;
        
    }
    
    public List<PrecioCompra> getPrecios() {
        return precios;
    }
    
    public void setPrecios(List<PrecioCompra> precios) {
        this.precios = precios;
    }
    
       
    public PrecioCompra getPrecioSeleccionado() {
        return precioSeleccionado;
    }
    
    public void setPrecioSeleccionado(PrecioCompra precioSeleccionado) {
        this.precioSeleccionado = precioSeleccionado;
    }
    
    public PrecioCompra getPrecioCompra() {
        return precioCompra;
    }
    
    public void setPrecioCompra(PrecioCompra precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Boolean getDesHabiAdd() {
        return desHabiAdd;
    }

    public void setDesHabiAdd(Boolean desHabiAdd) {
        this.desHabiAdd = desHabiAdd;
    }
    
    
}
