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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    

    /**
     * Creates a new instance of GuardarArticuloBean
     */
    
    @Inject
    CrudDAO<Producto> crudDAOProduc;
    @Inject
    CrudDAO<PrecioCompra> crudDAOPrCom;
    
    public void guardar(Producto p, Tblunidad u, Categoria c, Ubicacion ub, 
            List<PrecioCompra> listaPreciosCompra,Boolean estaEditando){
        log.log(Level.INFO, "el producto que se va a guardar es el siguiente--->{0}", p.getDescripcion());
        log.log(Level.INFO, "la unidad que se va a guardar es la siguiente--->{0}", u.getUnidades());
        log.log(Level.INFO, "la categoria que se va a guardar es la siguiente--->{0}", c.getDescripcion());
        log.log(Level.INFO, "la ubicacion que se va a guardar es la siguiente--->{0}", ub.getDescripcion());
        
        for (PrecioCompra precioCompra : listaPreciosCompra) {
            log.log(Level.INFO, "los precios de compra son los siguientes-->{0}", precioCompra.getPrecio());
        }
        
        if(estaEditando){
            //Aqui editamos
        }else{
            //Aqui guardamos
            
            if(crudDAOProduc.crear(p)){
                Producto ultimo=crudDAOProduc.buscarUltimo(Producto.class);
                if(ultimo!=null){
                    log.info("el ultimo producto es el siguente-->"+ultimo.getDescripcion());
                    for (PrecioCompra pre : listaPreciosCompra) {
                        if(pre.getActivo()){
                            if(crudDAOPrCom.crear(pre)){
                                Utilidad.mensajeInfo("SICOVI", "Producto: "+p.getDescripcion()+". Creado con exito.");
                            }
                        }
                    }
                }
            }else{
                Utilidad.mensajeFatal("SICOVI", "Ocurrio un error al ejecutar la operación.");
            }
        }
        
    }
    
    public GuardarArticuloBean() {
    }
}
