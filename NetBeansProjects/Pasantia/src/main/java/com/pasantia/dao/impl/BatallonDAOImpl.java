/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.BatallonDAO;
import com.pasantia.entidades.Batallon;
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
public class BatallonDAOImpl implements BatallonDAO {

    @Override
    public List<Batallon> buscartodosBatallones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Batallon> batallones = new ArrayList<Batallon>();
        try {
            Query q = session.createQuery("from Batallon");
            batallones = q.list();
        } catch (Exception e) {
            batallones = null;
            System.err.println("Error al buscar todos los batallones: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return batallones;
    }

    @Override
    public Boolean insertarBatallon(Batallon batallon) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean resultado = false;
        String nombre_batallon = "";
        String barrio="";
        try {
            session.beginTransaction();
            barrio=batallon.getNombreBatallon();
            barrio=barrio.toUpperCase();
            barrio=barrio.trim();
            nombre_batallon = batallon.getNombreBatallon();
            nombre_batallon = nombre_batallon.toUpperCase();
            nombre_batallon = nombre_batallon.trim();
            batallon.setNombreBatallon(nombre_batallon);
            batallon.setBarrio(barrio);
            session.save(batallon);
            session.flush();
            session.beginTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.err.println("Error en actualizar Batallon " + e.getMessage());
            session.beginTransaction().rollback();
            resultado = false;
        } finally {
            session.close();
        }
        return resultado;
    }

    @Override
    public Boolean actualizarBatallon(Batallon batallon) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean resultado = false;
        String nombre_batallon = "";
        try {
            session.beginTransaction();
            nombre_batallon = batallon.getNombreBatallon();
            nombre_batallon = nombre_batallon.toUpperCase();
            nombre_batallon = nombre_batallon.trim();
            batallon.setNombreBatallon(nombre_batallon);
            session.update(batallon);
            session.flush();
            session.beginTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.err.println("Error en actualizarBatallon " + e.getMessage());
            session.beginTransaction().rollback();
            resultado = false;
        } finally {
            session.close();
        }
        return resultado;
    }

    @Override
    public Boolean eliminarBatallon(Batallon batallon) {
         Session session = ConexionHibernate.getSessionFactory().openSession();
         Boolean result=false;
        try{
            session.beginTransaction();
            session.delete(batallon);
            session.flush();
            session.beginTransaction().commit();
            result=true;
        }catch(Exception e){
            System.out.println("Error al eliminarBatallon "+e.getMessage());
            session.beginTransaction().rollback();
            result=false;
        }
        finally{
            session.close();
        }
        return result;
    }

    @Override
    public List<Batallon> buscarBatallonesxPais(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "
               + " JOIN b.ciudad c "
                + "JOIN c.departamento d "
                + "JOIN d.pais p"
        + " WHERE p.idPais=:id ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setInteger("id", id);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxPais "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxPais retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxDepartamento(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "
               + " JOIN b.ciudad c "
                + "JOIN c.departamento d "                
        + " WHERE d.idDepartamento=:id ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setInteger("id", id);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxDepartamento "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxDepartamento retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxCiudad(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "
               + " JOIN b.ciudad c "                
        + " WHERE c.idCiudad=:id ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setInteger("id", id);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxCiudad "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxCiudad retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxDivision(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "
               + " JOIN b.divisiones d "                
        + " WHERE d.idDivisiones=:id ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setInteger("id", id);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxDivision "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxDivision retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxNombre(String nombre_batallon) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "               
        + " WHERE UPPER(b.nombreBatallon) like UPPER('%:id%') "
                + "ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setString("id", nombre_batallon);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxNombre "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxNombre retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxDireccion(String direccion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "               
        + " WHERE UPPER(b.direccion) like UPPER('%:id%') "
                + "ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setString("id", direccion);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxDireccion "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxDireccion retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxBarrio(String barrio) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "               
        + " WHERE UPPER(b.barrio) like UPPER('%:id%') "
                + "ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setString("id", barrio);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxBarrio "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxBarrio retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxTelefono1(String telefono1) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "               
        + " WHERE UPPER(b.telefono1) like UPPER('%:id%') "
                + "ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setString("id", telefono1);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxTelefono1 "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxTelefono1 retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }

    @Override
    public List<Batallon> buscarBatallonesxTelefono2(String telefono2) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Batallon> batallones = new ArrayList<Batallon>();
        try{
        String jpql =
        " SELECT b "
        + " FROM Batallon b "               
        + " WHERE UPPER(b.telefono2) like UPPER('%:id%') "
                + "ORDER BY b.nombreBatallon";
        Query q=session.createQuery(jpql);        
        
        q.setString("id", telefono2);
        list=q.list();
        batallones=(List<Batallon>)list;
        
       }catch(Exception e){
           batallones=null;
            System.out.println("Error en buscar buscarBatallonesxTelefono2 "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarBatallonesxTelefono2 retorna batallones");
            session.flush();
            session.close();
        } 
        return batallones;
    }
}
