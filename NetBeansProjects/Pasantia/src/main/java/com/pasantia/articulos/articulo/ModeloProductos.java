/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.entidades.Producto;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloProductos extends ListDataModel<Producto> implements SelectableDataModel<Producto>, Serializable{
    private static final long serialVersionUID = 8847156452435232057L;

    @Override
    public Object getRowKey(Producto t) {
        return t.getCodigoBarras();
    }

    @Override
    public Producto getRowData(String rowKey) {
        List<Producto> productos = (List<Producto>) getWrappedData();
        
        for (Producto producto : productos) {
            if(producto.getCodigoBarras().equals(rowKey))
                return producto;
        }
        return null;
    }

    public ModeloProductos() {
    }

    public ModeloProductos(List<Producto> list) {
        super(list);
    }
    
    
    
}
