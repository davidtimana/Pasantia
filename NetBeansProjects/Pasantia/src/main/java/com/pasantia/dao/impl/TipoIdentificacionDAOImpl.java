/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.TipoIdentificacionDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.TipoIdentificacion;
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
public class TipoIdentificacionDAOImpl implements TipoIdentificacionDAO{

    @Override
    public List<TipoIdentificacion> buscarTipoIdentificaciones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<TipoIdentificacion> tipoIdentificaciones = new ArrayList<TipoIdentificacion>();
        try {
            Query q = session.createQuery("from TipoIdentificacion t ORDER BY  t.nombreTipoIdentificacion ASC");
            tipoIdentificaciones = q.list();
        } catch (Exception e) {
            tipoIdentificaciones = null;
            System.err.println("Error al  buscarTipoIdentificaciones: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return tipoIdentificaciones;
    }

    @Override
    public TipoIdentificacion buscarTipoIdentificacionxId(Integer idTipoIdentificacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
        try {
            Query q = session.createQuery("from TipoIdentificacion t WHERE t.idTipoIdentificacion=:id");
            q.setInteger("id", idTipoIdentificacion);
            tipoIdentificacion = (TipoIdentificacion)q.uniqueResult();
        } catch (Exception e) {
            tipoIdentificacion = null;
            System.err.println("Error al  buscarTipoIdentificacionxId: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return tipoIdentificacion;
    }
    
}
