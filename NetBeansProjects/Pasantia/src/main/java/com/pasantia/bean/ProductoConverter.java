package com.pasantia.bean;

import com.pasantia.dao.ProductoDAO;
import com.pasantia.entidades.Producto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ProductoConverter extends ConverterGenerico<Producto> implements Serializable {

    @Inject
    private ProductoDAO productoDAO;

    @Override
    List<Producto> getLista() {
        return productoDAO.buscar();
    }

    @Override
    Integer getId(Producto t) {
        return t.getIdProducto();
    }

}
