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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author david
 */
@Named(value = "guardarArticuloBean")
@SessionScoped
public class GuardarArticuloBean implements Serializable {
    
    private static final long serialVersionUID = 7897041329912755348L;

    /**
     * Creates a new instance of GuardarArticuloBean
     */
    
    @Inject
    CrudDAO<Producto> crudDAOProduc;
    @Inject
    CrudDAO<PrecioCompra> crudDAOPrCom;
    
    public void guardar(Producto p, Tblunidad u, Categoria c, Ubicacion ub, PrecioCompra pc){
        
    }
    
    public GuardarArticuloBean() {
    }
}
