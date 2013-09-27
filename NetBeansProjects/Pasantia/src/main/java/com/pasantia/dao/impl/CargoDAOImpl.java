/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CargoDAO;
import com.pasantia.entidades.Cargo;
import com.pasantia.entidades.CatalogoVenta;
import com.pasantia.entidades.TipoPersona;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
@Stateless
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
        Cargo categoria=new Cargo();
        try{
            Query q = session.createQuery("from Cargo s WHERE s.idCargo=:id");
            q.setInteger("id", id);
            categoria = (Cargo)q.uniqueResult();
        }catch(Exception e){
            categoria=null;
            System.out.println("Error al buscarCargoporId :"+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            session.close();
        }
        return categoria;
    }

    @Override
    public List<Cargo> buscartodosCargos() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Cargo> cargos = new ArrayList<Cargo>();
        try {
            Query q = session.createQuery("from Cargo s ORDER BY  s.descripcion ASC where s.descripcion <> 'NO APLICA'");
            cargos = q.list();
        } catch (Exception e) {
            cargos = null;
            System.err.println("Error al  buscartodosCargos: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return cargos;
    }
    
}
