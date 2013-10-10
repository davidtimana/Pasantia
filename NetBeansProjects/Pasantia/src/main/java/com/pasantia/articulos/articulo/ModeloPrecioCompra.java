/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.entidades.PrecioCompra;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloPrecioCompra extends ListDataModel<PrecioCompra> implements SelectableDataModel<PrecioCompra>, Serializable{
    
    private static final long serialVersionUID = -347249552744223183L;

    @Override
    public Object getRowKey(PrecioCompra t) {
        return t.getIdPrecioCompra();
    }

    @Override
    public PrecioCompra getRowData(String rowkey) {
        if (Utilidad.cadenaVacia(rowkey)) {
            Integer llave = Integer.parseInt(rowkey);
            List<PrecioCompra> preciosCompra = (List<PrecioCompra>) getWrappedData();

            for (PrecioCompra precioCompra : preciosCompra) {
                if (precioCompra.getIdPrecioCompra() == llave) {
                    return precioCompra;
                }
            }
        }

        return null;
    }

    public ModeloPrecioCompra(List<PrecioCompra> list) {
        super(list);
    }
    
    
    
}
