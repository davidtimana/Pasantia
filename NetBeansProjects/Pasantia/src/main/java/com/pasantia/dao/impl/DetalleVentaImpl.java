package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DetalleVentaDAO;
import com.pasantia.entidades.TbldetalleVenta;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;

@Stateless
public class DetalleVentaImpl implements DetalleVentaDAO {

    @Override
    public void guardar(List<TbldetalleVenta> listaDetalleVenta) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        for (TbldetalleVenta detalleVenta : listaDetalleVenta) {
            session.beginTransaction();
            session.save(detalleVenta);
            session.beginTransaction().commit();
        }
    }
}
