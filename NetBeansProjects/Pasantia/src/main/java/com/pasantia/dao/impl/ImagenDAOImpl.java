/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.ImagenDAO;
import com.pasantia.entidades.Imagen;
import com.pasantia.entidades.Ubicacion;
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
public class ImagenDAOImpl implements ImagenDAO{

    @Override
    public List<Imagen> buscarImagenesxPrincipal() {
        Session session = ConexionHibernate.getSessionFactory().openSession();        
        List<Imagen> imagenes = new ArrayList<Imagen>();
        try {
            String jpql =
                    " SELECT i "
                    + " FROM Imagen i "
                    + " WHERE i.principal=1";
                    
            System.out.println("El jpql es el siguiente-->" + jpql);
            Query q = session.createQuery(jpql);            
            imagenes = (List<Imagen>) q.list();

        } catch (Exception e) {
            imagenes = null;
            System.out.println("Error en buscar buscarImagenesxPrincipal " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            System.out.println("cerrando la sesion en buscarImagenesxPrincipal retorna imagenes");
            session.flush();
            session.close();
        }
        return imagenes;
    }
    
}
