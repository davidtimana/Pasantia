/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.PaisDAO;
import com.pasantia.entidades.Divisiones;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class DivisionesDAOImpl implements DivisionesDAO{

    @Override
    public boolean insertarDivisiones(Divisiones divisiones) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean resultado = false;
        String descripcion = "";
        try {
            session.beginTransaction();
            descripcion = divisiones.getNombreDivision();
            descripcion = descripcion.toUpperCase();
            descripcion = descripcion.trim();
            divisiones.setNombreDivision(descripcion);
            session.save(divisiones);
            session.beginTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.err.println("Error en insertar " + e.getMessage());
            session.beginTransaction().rollback();
            resultado = false;
        } finally {
            session.close();
        }
        return resultado;
    }

    @Override
    public boolean actualizarDivisiones(Divisiones divisiones) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion="";
        boolean result=false;
        try{
            session.beginTransaction();
            descripcion=divisiones.getNombreDivision();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            divisiones.setNombreDivision(descripcion);
            session.update(divisiones);
            session.beginTransaction().commit();  
            result=true;
        }catch(Exception e){
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
            result=false;
        }
        finally{
            session.close();
        }
        return result;
    }

    @Override
    public boolean eliminarDivisiones(Divisiones divisiones) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(divisiones);
            session.beginTransaction().commit();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar "+e.getMessage());
            session.beginTransaction().rollback();
            return false;
        }
        finally{
            session.close();
        }
    }

    @Override
    public Divisiones buscarDivisionesporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Divisiones divisiones=null;
        try{
            
            Query q = session.createQuery("from Divisiones d where d.idDivisiones=:id ");
            q.setInteger("id", id);
            divisiones=(Divisiones)q.uniqueResult();            
            
        }catch(Exception e){
            divisiones=null;
            System.err.println("Error al buscar el id: "+id+" :"+e.getMessage());
        }
        finally{
            System.out.println("Cerrando la session hibernate");
            session.close();
        }
        return divisiones; 
    }

    @Override
    public List<Divisiones> buscartodasDivisiones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Divisiones> divisiones= new ArrayList<Divisiones>();        
        try {
            Query q=session.createQuery("from Divisiones");
            divisiones=q.list();
        } catch (Exception e) {
            divisiones=null;
            System.err.println("Error al buscar todas las divisiones: "+e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();            
        }
        
        return divisiones;
        
    }

    @Override
    public Divisiones buscarDivisionesporNombre(String nombre) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Divisiones divisiones = null;
        try {
            Query q = session.createQuery("from Divisiones as d where d.nombreDivision= :nombre");
            q.setString("nombre", nombre);
            divisiones = (Divisiones) (q.uniqueResult());

        } catch (Exception e) {
            divisiones = null;
            System.err.println("Error al buscar el nombre: " + nombre + " :" + e.getMessage());
        } finally {
            System.out.println("Cerrando la session hibernate en buscarDivisionesporNombre");
            session.close();
        }

        return divisiones;
    }

    @Override
    public Integer buscarUltimaIngresada() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String jpql ="select max(d.idDivisiones) from Divisiones as d";
        Query q=session.createQuery(jpql);       
        return (Integer)(q.uniqueResult());
    }
    
}
