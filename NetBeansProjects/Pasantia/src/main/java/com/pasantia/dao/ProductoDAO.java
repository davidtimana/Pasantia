package com.pasantia.dao;

import com.pasantia.entidades.Producto;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductoDAO {

    public Producto buscarPorCodigoBarras(String codigo);

    public List<Producto> buscar();

}
