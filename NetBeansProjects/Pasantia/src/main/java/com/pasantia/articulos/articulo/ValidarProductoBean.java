/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.entidades.Categoria;
import com.pasantia.entidades.Producto;
import com.pasantia.entidades.Tblunidad;
import com.pasantia.entidades.Ubicacion;
import com.pasantia.excepciones.CadenaVaciaException;
import com.pasantia.utilidades.Utilidad;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

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
    

    public void validarProducto(Producto p, Tblunidad un, Categoria c, Ubicacion u,Boolean estaEditando) 
            throws CadenaVaciaException{
        limpiarEstilos();
        validarCadenasVacias(p.getDescripcion(), "lbldesprodu", "txtdesprodu", 1, "Descripción del producto requerida.");
        validarCadenasVacias(p.getCodigoBarras(), "lblcodbarras", "txtcodbarras", 2, "Codigo de barras del producto requerido.");
        validarObjeto(p.getCantidadMinima(), "lblcantmin", "txtcantmin", 3, "Cantidad Minima del producto requerida.");
        validarObjeto(p.getCantidadActual(), "lblcantact", "txtcantactu", 4, "Cantidad Actual del producto requerida.");
        validarObjeto(un.getSecunidad(), "lblunidades", "cmbunidades", 5, "Seleccion de la unidad de medida para este producto requerida.");
        validarObjeto(c.getIdCategoria(), "lblcategoria", "cmbcategoria", 6, "Seleccion de la categoria del producto requerida.");
        validarObjeto(u.getIdUbicacion(), "lblubicacion", "cmbubicacion", 7, "Seleccion de la ubicación de este producto requerida.");
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
        Utilidad.actualizarElemento("accordioproduc");
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
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
        estilosError.add("");
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
