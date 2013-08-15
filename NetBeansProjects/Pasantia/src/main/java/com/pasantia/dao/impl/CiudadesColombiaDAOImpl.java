/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CiudadesColombiaDAO;
import com.pasantia.entidades.CiudadesColombia;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class CiudadesColombiaDAOImpl implements CiudadesColombiaDAO{

    @Override
    public List<CiudadesColombia> buscartodo() {
        Session session = ConexionHibernate.getSessionFactory().openSession();        
        return session.createQuery("Select distinct c from CiudadesColombia c order by c.ciudad asc").list();
    }

    @Override
    public CiudadesColombia buscarporid(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        
        Query q=session.createQuery("from CiudadesColombia as d where d.idCiudadesColombia= :id");
        q.setInteger("id", id);
        return (CiudadesColombia)q.uniqueResult();
    }
    
}
