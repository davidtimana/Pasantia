/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pasantia.articulos.articulo;

import com.pasantia.dao.PreciosCompraDAO;
import com.pasantia.entidades.PrecioCompra;
import com.pasantia.entidades.Producto;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.ChartSeries;  

/**
 *
 * @author David
 */
@Named(value = "gestionPreciosBean")
@SessionScoped
public class GestionPreciosBean implements Serializable{
    
    private static final long serialVersionUID = -922301025045117329L;
    private static final Logger log = Logger.getLogger(GestionPreciosBean.class.getName());
    
    @Inject
    BuscarProductoReporteBean reporteBean;
    @Inject
    PreciosCompraDAO preciosCompraDAO;
    
    private Producto producto;
    private Boolean mostrar;   
    private List<PrecioCompra> precios;
    private Boolean abrirHisto;
    
    private CartesianChartModel linearModel;  
    
    public void generarGraficaHistorial(){
        abrirHisto=true;
        linearModel = new CartesianChartModel();  
  
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Series 1");  
  
        series1.set(1, 2);  
        series1.set(2, 1);  
        series1.set(3, 3);  
        series1.set(4, 6);  
        series1.set(5, 8);  
         linearModel.addSeries(series1); 
        Utilidad.actualizarElemento("dlghis");
    }
    
    public void cerrarHistorialGrafico(){
        abrirHisto=false;
        Utilidad.actualizarElemento("dlghis");
    }
    
    public void cargarObjeto(Producto p){
        producto=p;
        cargarPreciosPorProducto();
        
    }
    
    public void abrirBuscadorProductos(){
        reporteBean.abrirBuscador();        
    }
    
    public void cargarPreciosPorProducto(){
        precios=preciosCompraDAO.buscarPreciosporProducto(producto.getIdProducto());
        if(precios.isEmpty()){
            Utilidad.mensajePeligro("SICOVI", "Producto sin historial de precios.");
        }else{
            mostrar=true;
        }
    }
    
    public Integer totalPrecios(){
        return precios.size();
    }

    @PostConstruct
    public void Init(){
        mostrar=false;
        totalPrecios();
        linearModel = new CartesianChartModel();  
    }
    
    public GestionPreciosBean() {
        producto=new Producto();
        precios=new ArrayList<PrecioCompra>();
        abrirHisto=false;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    public List<PrecioCompra> getPrecios() {
        return precios;
    }

    public void setPrecios(List<PrecioCompra> precios) {
        this.precios = precios;
    }

    public Boolean isAbrirHisto() {
        return abrirHisto;
    }

    public void setAbrirHisto(Boolean abrirHisto) {
        this.abrirHisto = abrirHisto;
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }
    
    

   
    
    
    

    
    
    
    
    
    
    
    
    
    
}
