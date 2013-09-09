/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Divisiones;
import com.pasantia.entidades.DivisionesUbicacion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 *
 * @author david
 */
@Stateless
public class DivisionesUbicacionDAOImpl implements DivisionesUbicacionDAO{

    @Override
    public boolean insertarDivisionesUbicacion(DivisionesUbicacion divisionesUbicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.save(divisionesUbicacion);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.err.println("Error en insertar " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            System.out.println("cerranndo sesion en insertarDivisionesUbicacion");
            session.close();
        }
        return result;
    }

    @Override
    public boolean eliminarDivisionesUbicacion(DivisionesUbicacion divisionesUbicacion) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(divisionesUbicacion);
            session.beginTransaction().commit();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar "+e.getMessage());
            session.beginTransaction().rollback();
            return false;
        }
        finally{
            System.out.println("cerranndo sesion en eliminarDivisionesUbicacion");
            session.close();
        }
    }

    @Override
    public List<DivisionesUbicacion> buscarubicaciones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from DivisionesUbicacion").list();
    }

    @Override    
    public List<DivisionesUbicacion> buscarubicacionesxiddivision(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <DivisionesUbicacion> ubicaciones = new ArrayList<DivisionesUbicacion>();
        try{
        
        Query q=session.createQuery("from DivisionesUbicacion as d where d.divisiones.idDivisiones=:id");
        q.setInteger("id", id);
        list=q.list();
        ubicaciones=(List<DivisionesUbicacion>)list;
        
       }catch(Exception e){
           ubicaciones=null;
            System.out.println("Error en buscar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarubicacionesxiddivision");
            session.flush();
            session.close();
        }
      
        return ubicaciones;
        
    }

    @Override
    public List<DivisionesUbicacion> buscarubicacionesxidDepartamento(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();        
        Query q=session.createQuery("from DivisionesUbicacion as d where d.departamento.idDepartamento=:id");
        q.setInteger("id", id);
        return q.list();
    }

    @Override
    public DivisionesUbicacion buscarubicacionesxid(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        DivisionesUbicacion divisionUbicacion=null;
        try{
            Query q=session.createQuery("from DivisionesUbicacion as d where d.idDivisionesUbicacion=:id");
            q.setInteger("id", id);
            divisionUbicacion=(DivisionesUbicacion)q.uniqueResult();
            
        }catch(Exception e){
            System.out.println("Error en buscar x id Ubicacion "+e.getMessage());
            session.beginTransaction().rollback();
            divisionUbicacion= null;
        }
        finally{
            System.out.println("cerrando la sesion en buscarubicacionesxid");
            session.flush();
            session.close();
        }
        return divisionUbicacion;
        
    }

    @Override
    public List<Departamento> buscarUbicacionesxIdDivision(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Departamento> departamentos = new ArrayList<Departamento>();
        try{
        String jpql =
        " SELECT d "
        + " FROM DivisionesUbicacion as du JOIN du.departamento d"
        + " WHERE du.divisiones.idDivisiones=:id";
        Query q=session.createQuery(jpql);
        
        
        q.setInteger("id", id);
        list=q.list();
        departamentos=(List<Departamento>)list;
        
       }catch(Exception e){
           departamentos=null;
            System.out.println("Error en buscar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarubicacionesxiddivision retorna departamentos");
            session.flush();
            session.close();
        }       
      
        return departamentos;
    }

    @Override
    public DivisionesUbicacion buscarUbicacionesxIdDivisionYIdDepartamento(Integer idDivision, Integer idDepartamento) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        DivisionesUbicacion divisionUbicacion = null;
        String jpql = "";
        try {
            jpql = " SELECT "
                    + " d "
                    + " FROM "
                    + " DivisionesUbicacion d "
                    + " WHERE "
                    + " d.departamento.idDepartamento=:idDepartamento "
                    + " AND "
                    + " d.divisiones.idDivisiones=:idDivision ";            
            Query q = session.createQuery(jpql);
            q.setInteger("idDepartamento", idDepartamento);
            q.setInteger("idDivision", idDivision);
            divisionUbicacion = (DivisionesUbicacion) q.uniqueResult();

        } catch (Exception e) {
            System.out.println("Error en buscar x id Ubicacion " + e.getMessage());
            session.beginTransaction().rollback();
            divisionUbicacion = null;
        } finally {
            System.out.println("cerrando la sesion en buscarubicacionesxiddivisionyidDepartamento");
            session.flush();
            session.close();
        }

        return divisionUbicacion;
    }

    @Override
    public boolean eliminarDivisionesUbicacionxIdDivision(Integer idDivision) {

        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean result = false;
        String jpql = "";
        try {
            jpql = " DELETE "
                    + " d "
                    + " FROM "
                    + " DivisionesUbicacion d "
                    + " WHERE "
                    + " d.divisiones.idDivisiones=:idDivision ";
            Query q = session.createQuery(jpql);
            q.setInteger("idDivision", idDivision);
            session.beginTransaction().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("Error en eliminarDivisionesUbicacionxIdDivision x id Division " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            System.out.println("cerrando la sesion en eliminarDivisionesUbicacionxIdDivision");
            session.flush();
            session.close();
        }

        return result;



    }

    @Override
    public boolean eliminarDivisionesUbicacion(Integer idDivisiones) {
        
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean result = false;
        String jpql = "";
        try {
            jpql = " DELETE "                    
                    + " DivisionesUbicacion d "
                    + " WHERE "
                    + " d.divisiones.idDivisiones=:idDivision ";
            Query q = session.createQuery(jpql);
            q.setInteger("idDivision", idDivisiones);
            session.beginTransaction().commit();
            result = true;

        } catch (Exception e) {
            System.out.println("Error en eliminarDivisionesUbicacionxIdDivision x id Division " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            System.out.println("cerrando la sesion en eliminarDivisionesUbicacionxIdDivision");
            session.flush();
            session.close();
        }

        return result;
    }

    @Override
    public List<Divisiones> buscarDivisionesAsociadas() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Divisiones> divisiones = null;
        String jpql = "";
        try {
            jpql = " SELECT "
                    + " DISTINCT d "
                    + " FROM "
                    + " DivisionesUbicacion du "
                    + " JOIN "
                    + " du.divisiones d "
                    + " WHERE "
                    + " d.idDivisiones=du.divisiones.idDivisiones ";                    
            Query q = session.createQuery(jpql);            
            divisiones = (List<Divisiones>)q.list();

        } catch (Exception e) {
            System.out.println("Error en buscar buscarDivisionesAsociadas " + e.getMessage());
            session.beginTransaction().rollback();
            divisiones = null;
        } finally {
            System.out.println("cerrando la sesion en buscarDivisionesAsociadas");
            session.flush();
            session.close();
        }

        return divisiones;
    }

    @Override
    public List<Departamento> buscarubicacionesxiddivisionretornaDepartamentos(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List <Departamento> ubicaciones = new ArrayList<Departamento>();
        try{
        
        Query q=session.createQuery("select d.departamento from DivisionesUbicacion as d where d.divisiones.idDivisiones=:id");
        q.setInteger("id", id);
        list=q.list();
        ubicaciones=(List<Departamento>)list;
        
       }catch(Exception e){
           ubicaciones=null;
            System.out.println("Error en buscar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarubicacionesxiddivisionretornaDepartamentos");
            session.flush();
            session.close();
        }
      
        return ubicaciones;
    }
    
}
