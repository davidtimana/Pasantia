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
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.excepciones.PreciosArticuloException;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */
@Named(value = "validarProductoBean")
@SessionScoped
public class ValidarProductoBean implements Serializable {
    
    private static final long serialVersionUID = 3870572897373551536L;
    private static final Logger log = Logger.getLogger(ValidarProductoBean.class.getName());
    
    private List<String> estilosError;
    
    @Inject
    CrudDAO<Producto> crudDAO;
    @Inject
    ControlPreciosBean preciosBean;
    

    public void validarProducto(Producto p, Tblunidad un, Categoria c, Ubicacion u,Boolean estaEditando) 
            throws CadenaVaciaException, PreciosArticuloException{
        limpiarEstilos();
        validarCadenasVacias(p.getDescripcion(), "lbldesprodu", "txtdesprodu", 1, "Descripción del producto requerida.");
        validarCadenasVacias(p.getCodigoBarras(), "lblcodbarras", "txtcodbarras", 2, "Codigo de barras del producto requerido.");
        validarCodigoBarrasSinRepetir(p.getCodigoBarras(),estaEditando);
        validarObjeto(p.getCantidadMinima(), "lblcantmin", "txtcantmin", 3, "Cantidad Minima del producto requerida.");
        validarObjeto(p.getCantidadActual(), "lblcantact", "txtcantactu", 4, "Cantidad Actual del producto requerida.");
        validarCantidades(p.getCantidadMinima(), p.getCantidadActual());
        validarObjeto(un.getSecunidad(), "lblunidades", "cmbunidades", 5, "Seleccion de la unidad de medida para este producto requerida.");
        validarObjeto(c.getIdCategoria(), "lblcategoria", "cmbcategoria", 6, "Seleccion de la categoria del producto requerida.");
        validarObjeto(u.getIdUbicacion(), "lblubicacion", "cmbubicacion", 7, "Seleccion de la ubicación de este producto requerida.");
        validarObjetoPrecio(p.getPrecioVenta1(), "lblprec1", "txtprec1", 8, "Tarifa de venta principal para este producto requerido.");
        validarPreciosCompra(preciosBean.getPrecios());
    }
    
    public void limpiarEstilos(){
        estilosError.set(0,"");
        estilosError.set(1,"");
        estilosError.set(2,"");
        estilosError.set(3,"");
        estilosError.set(4,"");
        estilosError.set(5,"");
        estilosError.set(6,"");
        estilosError.set(7,"");
        estilosError.set(8,"");
        estilosError.set(9,"");
        estilosError.set(10,"");
        Utilidad.actualizarElemento("accordioproduc");
    }
    
    public void validarPreciosCompra(List<PrecioCompra> precios) throws PreciosArticuloException {
        if(precios.isEmpty()){
          throw new PreciosArticuloException("La gestion de los precios de compra es obligatoria.");
        }
    }
    
    public void validarCantidades(Integer cantidadMinima,Integer cantidadActual) throws CadenaVaciaException{
        if(cantidadMinima>cantidadActual){
            estilosError.set(3, Utilidad.estilosErrorInput());
            estilosError.set(4, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("lblcantmin");
            Utilidad.actualizarElemento("txtcantmin");
            Utilidad.actualizarElemento("lblcantact");
            Utilidad.actualizarElemento("txtcantactu");
            throw new CadenaVaciaException("La cantidad minima no puede ser mayor a la cantidad actual.");
        }else{
            estilosError.set(3, "");
            estilosError.set(4, "");
            Utilidad.actualizarElemento("lblcantmin");
            Utilidad.actualizarElemento("txtcantmin");
            Utilidad.actualizarElemento("lblcantact");
            Utilidad.actualizarElemento("txtcantactu");
        }
    }
    
    public void validarCodigoBarrasSinRepetir(String codigoBarras,Boolean estaEditando) throws CadenaVaciaException{
        Producto p=new Producto();
        p=crudDAO.buscarxAlgunCampoString(Producto.class, "codigoBarras", codigoBarras);
        if(p!=null && !estaEditando){
            estilosError.set(2, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento("lblcodbarras");
            Utilidad.actualizarElemento("txtcodbarras");
            throw new CadenaVaciaException("El codigo de barras ingresado ya existe.");
        } else {
            estilosError.set(2, "");            
            Utilidad.actualizarElemento("lblcodbarras");
            Utilidad.actualizarElemento("txtcodbarras");
        }
        
        
    }
    
    public void validarObjeto(Object objeto, String idLbl,String idtxt, Integer posEstilo, String mensaje) throws CadenaVaciaException {
        if (objeto==null) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);            
            Utilidad.actualizarElemento(idtxt);            
            throw new CadenaVaciaException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);            
            Utilidad.actualizarElemento(idtxt);            
        }
    }
    
      public void validarObjetoPrecio(Object objeto, String idLbl,String idtxt, Integer posEstilo, String mensaje) throws PreciosArticuloException  {
        if (objeto==null) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);            
            Utilidad.actualizarElemento(idtxt);            
            throw new PreciosArticuloException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);            
            Utilidad.actualizarElemento(idtxt);            
        }
    }
    
       /**
     * Metodo que se encarga de validar cualquier campo string.
     * @param cadena
     *          Campo a validar.
     * @param idLbl
     *          Id del label de la informacion del campo
     *          para ser actualizado y cambiar sus estilos
     *          a clase error.
     * @param idtxt
     *          Id del textbox del registro del campo
     *          para ser actualizado y cambiar sus 
     *          estilos a clase error.
     * @param  posEstilo
     *          Pocision del array en donde se encuentra
     *          el estilo correspondiente para este campo.
     * @param  mensaje
     *          Mensaje que arroja la excepcion en caso
     *          de que la cadena este vacia.
     * @exception CadenaVaciaException
     *          Si la cadena esta vacia
     * @author David Timana
     * @since 1.0
     */    
    public void validarCadenasVacias(String cadena, String idLbl, String idtxt, 
            Integer posEstilo, String mensaje) 
            throws CadenaVaciaException 
             {
        if (Utilidad.cadenaVacia(cadena)) {
            estilosError.set(posEstilo, Utilidad.estilosErrorInput());
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
            throw new CadenaVaciaException(mensaje);
        } else {
            estilosError.set(posEstilo, "");            
            Utilidad.actualizarElemento(idLbl);
            Utilidad.actualizarElemento(idtxt);
        }
    }
    
    public void cargarEstilosError(){
        estilosError.add("");//0
        estilosError.add("");//1
        estilosError.add("");//2
        estilosError.add("");//3
        estilosError.add("");//4
        estilosError.add("");//5
        estilosError.add("");//6
        estilosError.add("");//7
        estilosError.add("");//8
        estilosError.add("");//9
        estilosError.add("");//10
    }
    
    @PostConstruct
    public void Init(){
        cargarEstilosError();
    }
    
    public ValidarProductoBean() {
        estilosError=new ArrayList<String>();
    }

    public List<String> getEstilosError() {
        return estilosError;
    }

    public void setEstilosError(List<String> estilosError) {
        this.estilosError = estilosError;
    }
    
    
}
