/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.dao.PreciosCompraDAO;
import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.PrecioCompra;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David timana
 */

@Stateless
public class PreciosCompraDAOImpl implements PreciosCompraDAO{

    @Override
    public List<PrecioCompra> buscarPreciosporProducto(Integer secproducto) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<PrecioCompra> precioscompra = new ArrayList<PrecioCompra>();
        String jpql = "";

        try {
            jpql = " FROM PrecioCompra d "
                    + "WHERE d.producto.idProducto= :prod ";                    
            Query q = session.createQuery(jpql);
            q.setInteger("prod", secproducto);
            precioscompra = (List<PrecioCompra>) q.list();

        } catch (Exception e) {
            precioscompra = null;
            System.out.println("Error en buscarPreciosporProducto " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return precioscompra;
    }
    
        
}
