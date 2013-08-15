/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CategoriaDAO;
import com.pasantia.entidades.Categoria;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class CategoriaDAOImpl implements CategoriaDAO{

    @Override
    public void insertarUbicacion(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=categoria.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            categoria.setDescripcion(descripcion);
            session.save(categoria);
            session.beginTransaction().commit();
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void actualizarUbicacion(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=categoria.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            categoria.setDescripcion(descripcion);
            session.update(categoria);
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
    public boolean eliminarUbicacion(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(categoria);
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
    public Categoria buscarCategoriaporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Categoria categoria=null;
        try{
            categoria=(Categoria)session.load(Categoria.class,id);
        }catch(Exception e){
            System.out.println("Error al buscar el id: "+id+" :"+e.getMessage());
        }
        finally{
            session.close();
        }
        return categoria;
    }

   
    @Override
    public List<Categoria> buscartodasCategorias() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from Categoria").list();
    }
    
}
