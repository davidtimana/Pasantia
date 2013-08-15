/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.UbicacionDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.entidades.Ubicacion;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author david
 */
public class UbicacionDAOImpl implements UbicacionDAO{

    @Override
    public void insertarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=ubicacion.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            ubicacion.setDescripcion(descripcion);
            session.save(ubicacion);
            session.beginTransaction().commit();
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void actualizarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=ubicacion.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            ubicacion.setDescripcion(descripcion);
            session.update(ubicacion);
            session.beginTransaction().commit();            
        }catch(Exception e){
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            session.close();
        }
    }

    @Override
    public boolean eliminarUbicacion(Ubicacion ubicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(ubicacion);
            session.beginTransaction().commit();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar "+e.getMessage());
            session.beginTransaction().rollback();
            return true;
        }
        finally{
            session.close();
        }
    }

    @Override
    public Ubicacion buscarUbicacionporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Ubicacion ubicacion=null;
        try{
            ubicacion=(Ubicacion)session.load(Ubicacion.class,id);
        }catch(Exception e){
            System.out.println("Error al buscar el id: "+id+" :"+e.getMessage());
        }
        finally{
            session.close();
        }
        return ubicacion;
    }

    @Override
    public List<Ubicacion> buscartodasUbicaciones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from Ubicacion").list();
    }
    
}
