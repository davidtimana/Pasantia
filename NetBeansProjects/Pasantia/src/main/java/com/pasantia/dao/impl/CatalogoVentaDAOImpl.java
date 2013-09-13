/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CatalogoVentaDAO;
import com.pasantia.entidades.CatalogoVenta;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David Timana
 */

@Stateless
public class CatalogoVentaDAOImpl implements CatalogoVentaDAO{

    @Override
    public List<CatalogoVenta> buscarCatalogos() {
         Session session = ConexionHibernate.getSessionFactory().openSession();
        List<CatalogoVenta> catalogos = new ArrayList<CatalogoVenta>();
        try {
            Query q = session.createQuery("from CatalogoVenta s ORDER BY  s.descripcion ASC");
            catalogos = q.list();
        } catch (Exception e) {
            catalogos = null;
            System.err.println("Error al  buscarCatalogos: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return catalogos;
    }
    
}
