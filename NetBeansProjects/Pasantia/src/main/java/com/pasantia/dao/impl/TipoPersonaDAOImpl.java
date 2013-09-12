/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.TipoPersonaDAO;
import com.pasantia.entidades.TipoPersona;
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
public class TipoPersonaDAOImpl implements TipoPersonaDAO{

    @Override
    public List<TipoPersona> buscarTiposPersona() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<TipoPersona> tiposPersona = new ArrayList<TipoPersona>();
        try {
            Query q = session.createQuery("from TipoPersona t ORDER BY  t.nombreTipoPersona ASC");
            tiposPersona = q.list();
        } catch (Exception e) {
            tiposPersona = null;
            System.err.println("Error al  buscarTiposPersona: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return tiposPersona;
    }
    
}
