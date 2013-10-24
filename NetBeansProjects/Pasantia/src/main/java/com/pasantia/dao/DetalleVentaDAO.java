package com.pasantia.dao;

import com.pasantia.entidades.TbldetalleVenta;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DetalleVentaDAO {

    /**
     * Metodo que se encarga de guardar el detalle de una venta
     *
     * @param listaDetalleVenta
     */
    public void guardar(List<TbldetalleVenta> listaDetalleVenta);
}
