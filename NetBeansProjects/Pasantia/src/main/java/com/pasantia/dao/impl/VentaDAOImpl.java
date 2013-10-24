package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.VentaDAO;
import com.pasantia.entidades.Tblventa;
import javax.ejb.Stateless;
import org.hibernate.Session;

@Stateless
public class VentaDAOImpl implements VentaDAO {

    @Override
    public Tblventa guardar(Tblventa venta) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(venta);
        session.beginTransaction().commit();
        return venta;
    }

}
