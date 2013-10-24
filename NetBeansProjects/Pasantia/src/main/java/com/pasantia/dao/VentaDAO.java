package com.pasantia.dao;

import com.pasantia.entidades.Tblventa;
import javax.ejb.Local;

@Local
public interface VentaDAO {

    /**
     * Metodo que se encarga de guardar una nueva venta
     *
     * @param venta
     * @return venta
     */
    public Tblventa guardar(Tblventa venta);

}
