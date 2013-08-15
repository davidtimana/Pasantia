/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.dao.RolDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.conexion.ConexionHibernate;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author david
 */
public class RolDAOImpl implements RolDAO{

    
    
    
    @Override
    public void insertarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion=null;
        try{
            session.beginTransaction();
            descripcion=rol.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            rol.setDescripcion(descripcion);
            session.save(rol);
            session.beginTransaction().commit();
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void actualizarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion=null;
        try{
            session.beginTransaction();
            descripcion=rol.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            rol.setDescripcion(descripcion);
            session.merge(rol);
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
    public void eliminarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(rol);
            session.beginTransaction().commit();
        }catch(Exception e){
            System.out.println("Error al eliminar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            session.close();
        }

    }

    @Override
    public Rol buscarRolPorId(Integer id) {
        
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return (Rol)session.load(Rol.class,id);
    }

    @Override
    public List<Rol> buscartodosRoles() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from Rol").list();
    }
    
}
