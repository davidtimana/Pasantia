/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CargoDAO;
import com.pasantia.entidades.Cargo;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class CargoDAOImpl implements CargoDAO{

    @Override
    public void insertarCargo(Cargo cargo) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=cargo.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            cargo.setDescripcion(descripcion);
            session.save(cargo);
            session.beginTransaction().commit();
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void actualizarCargo(Cargo cargo) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        try{
            session.beginTransaction();
            descripcion=cargo.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            cargo.setDescripcion(descripcion);
            session.update(cargo);
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
    public boolean eliminarCargo(Cargo cargo) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(cargo);
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
    public Cargo buscarCargoporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Cargo categoria=null;
        try{
            categoria=(Cargo)session.load(Cargo.class,id);
        }catch(Exception e){
            System.out.println("Error al buscar el id: "+id+" :"+e.getMessage());
        }
        finally{
            session.close();
        }
        return categoria;
    }

    @Override
    public List<Cargo> buscartodosCargos() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from Cargo").list();
    }
    
}
