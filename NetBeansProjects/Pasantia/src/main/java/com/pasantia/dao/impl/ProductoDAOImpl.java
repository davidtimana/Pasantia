package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.ProductoDAO;
import com.pasantia.entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

@Stateless
public class ProductoDAOImpl implements ProductoDAO {

    private static final Logger log = Logger.getLogger(ProductoDAOImpl.class.getName());

    @Override
    public Producto buscarPorCodigoBarras(String codigo) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String jpql = "SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras";
        try {
            Query q = session.createQuery(jpql);
            q.setString("codigoBarras", codigo);
            return (Producto) q.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Producto> buscar() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Producto> producto = new ArrayList<Producto>();
        try {
            Query q = session.createQuery("from Producto");
            producto = q.list();
        } catch (Exception e) {
            producto = null;
            log.log(Level.INFO, "Error al buscar los productos: {0}", e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return producto;
    }

}
