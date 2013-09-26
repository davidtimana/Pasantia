/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CrudDAO;
import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import com.pasantia.utilidades.UtilidadCadena;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David Orlando Timaná
 */

@Stateless
public class CrudJpaDAO<T> implements CrudDAO<T>{  
    
    
   

    @Override
    public void commitTransaccion() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        session.flush();
    }

    @Override
    public Boolean crear(T entity){
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Boolean result=false;
        try{   
            session.beginTransaction();
            session.save(entity);
            session.flush();
            session.beginTransaction().commit();
            result = true;            
        }
        catch(Exception e){
            System.err.println("******************* Error en Crear Generico ->" + e.getMessage());            
            session.beginTransaction().rollback();
            result = false;
            
        }finally{
            session.close();
        }


        return result;
    }

    @Override
    public Boolean editar(T entity) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Boolean result=false;
        try{         
            session.beginTransaction();
            session.update(entity);
            session.flush();
            session.beginTransaction().commit();
            result = true;            
        }catch(Exception e){
            System.err.println("******************* Error en Editar Generico -> " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public Boolean remover(T entity) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Boolean result = false;
        try {
            session.beginTransaction();
            session.delete(entity);
            session.flush();
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.err.println("********************Error en Remover Generico " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public long removerEntidades(Class entidad, String alias, String condicionWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T buscar(Class<T> entityClass, Object id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try{            
            return (T) session.get(entityClass, (Integer) id);
        }catch(Exception e){
            return null;
        }
        finally{
            session.close();
        }
        
        
    }

    @Override
    public List<T> buscarTodos(Class<T> entityClass) {

        Session session = ConexionHibernate.getSessionFactory().openSession();
        StringBuilder jpql = new StringBuilder();
        List<T> listaEntidad = null;
        try {
            jpql.append("SELECT miEntidad FROM ");
            jpql.append(entityClass.getSimpleName());
            jpql.append(" miEntidad ");

            Query q = session.createQuery(jpql.toString());
            listaEntidad = (List<T>) q.list();
            session.flush();
        } catch (Exception e) {
            listaEntidad=null;
            System.err.println("Error al buscarTodos Generico: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return listaEntidad;
    }

    @Override
    public List<T> buscarLista(Class<T> entityClass, String alias, String camposWhere, String camposOrderBy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long contarTodos(Class<T> entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long consultaEscalar(Class entityClass, String alias, String campoSelect, String condicionWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T buscarUltimo(Class<T> entityClass) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        StringBuilder jpql = new StringBuilder();
        T retornar=null;
        try {
            jpql.append("SELECT MAX(miEntidad) FROM ");
            jpql.append(entityClass.getSimpleName());
            jpql.append(" miEntidad ");

            Query q = session.createQuery(jpql.toString());
            retornar = (T) q.uniqueResult();
            session.flush();
        } catch (Exception e) {
            retornar=null;
            System.err.println("Error al buscarUltimo Generico: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return retornar;
    }

    @Override
    public T buscarxAlgunCampoString(Class<T> entityClass, Object campo, Object parametro) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        StringBuilder jpql = new StringBuilder();
        T retornar=null;
        try {
            jpql.append("SELECT miEntidad FROM ");
            jpql.append(entityClass.getSimpleName());
            jpql.append(" miEntidad ");
            jpql.append(" WHERE ");
            jpql.append(" miEntidad.");
            jpql.append(campo.toString());
            jpql.append(" = ");
            jpql.append(":string");            
            Query q = session.createQuery(jpql.toString());
            q.setString("string", parametro.toString());
            retornar = (T) q.uniqueResult();
            session.flush();
        } catch (Exception e) {
            retornar=null;
            System.err.println("Error al buscarxAlgunCampoString Generico: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return retornar;
    }
    
    
    
}
