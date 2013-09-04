/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.UbicacionDAO;
import com.pasantia.entidades.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author david
 */
@Stateless
public class UbicacionDAOImpl implements UbicacionDAO {

    @Override
    public Boolean insertarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion = "";
        Boolean result = false;
        try {
            session.beginTransaction();
            descripcion = ubicacion.getDescripcion();
            descripcion = descripcion.toUpperCase();
            descripcion = descripcion.trim();
            ubicacion.setDescripcion(descripcion);
            session.save(ubicacion);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            result = false;
            System.out.println("Error en insertar " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Boolean actualizarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion = "";
        Boolean result;
        try {
            session.beginTransaction();
            descripcion = ubicacion.getDescripcion();
            descripcion = descripcion.toUpperCase();
            descripcion = descripcion.trim();
            ubicacion.setDescripcion(descripcion);
            session.update(ubicacion);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            result = false;
            System.out.println("Error en actualizar " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean eliminarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Boolean result = false;
        try {
            session.beginTransaction();
            session.delete(ubicacion);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.err.println("Error al eliminar " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Ubicacion buscarUbicacionporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Ubicacion ubicacion = null;
        try {
            ubicacion = (Ubicacion) session.load(Ubicacion.class, id);
        } catch (Exception e) {
            System.out.println("Error al buscar el id: " + id + " :" + e.getMessage());
        } finally {
            session.close();
        }
        return ubicacion;
    }

    @Override
    public List<Ubicacion> buscartodasUbicaciones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
        try {
            Query q = session.createQuery("from Ubicacion");
            ubicaciones = q.list();
        } catch (Exception e) {
            ubicaciones = null;
            System.err.println("Error al buscartodasUbicaciones: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return ubicaciones;
    }

    @Override
    public List<Ubicacion> buscartodasUbicacionesxNombre(String nombre) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
        try {
            String jpql =
                    " SELECT b "
                    + " FROM Ubicacion b "
                    + " WHERE UPPER(b.descripcion) LIKE '%" + nombre + "%'"
                    + " ORDER BY b.descripcion";
            System.out.println("El jpql es el siguiente-->" + jpql);
            Query q = session.createQuery(jpql);

            list = q.list();
            ubicaciones = (List<Ubicacion>) list;

        } catch (Exception e) {
            ubicaciones = null;
            System.out.println("Error en buscar buscartodasUbicacionesxNombre " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            System.out.println("cerrando la sesion en buscartodasUbicacionesxNombre retorna batallones");
            session.flush();
            session.close();
        }
        return ubicaciones;
    }

    @Override
    public List<Ubicacion> buscartodasUbicacionesxNombrexId(Integer idUbicacion, String nombreUbicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
        String condicion="";
        System.out.println("idubicacion es-->"+idUbicacion);
        if(idUbicacion!=null){
            condicion="AND b.idUbicacion = "+idUbicacion;
        }
        
        try {
            String jpql =
                    " SELECT b "
                    + " FROM Ubicacion b "
                    + " WHERE UPPER(b.descripcion) LIKE '%" + nombreUbicacion + "%' "                    
                    + condicion
                    + " ORDER BY b.descripcion";
            System.out.println("El jpql es el siguiente-->" + jpql);
            Query q = session.createQuery(jpql);
            session.flush();

            list = q.list();
            ubicaciones = (List<Ubicacion>) list;

        } catch (Exception e) {
            ubicaciones = null;
            System.out.println("Error en buscar buscartodasUbicacionesxNombrexId " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            System.out.println("cerrando la sesion en buscartodasUbicacionesxNombrexId retorna batallones");            
            session.close();
        }
        return ubicaciones;
    }
}
