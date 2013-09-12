/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.SexoDAO;
import com.pasantia.entidades.Sexo;
import com.pasantia.entidades.TipoIdentificacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author jbuitron
 */

@Stateless
public class SexoDAOImpl implements SexoDAO{

    @Override
    public List<Sexo> buscarTodosSexo() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Sexo> sexos = new ArrayList<Sexo>();
        try {
            Query q = session.createQuery("from Sexo s ORDER BY  s.nombreSexo ASC");
            sexos = q.list();
        } catch (Exception e) {
            sexos = null;
            System.err.println("Error al  buscarTodosSexo: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return sexos;
    }
    
}
