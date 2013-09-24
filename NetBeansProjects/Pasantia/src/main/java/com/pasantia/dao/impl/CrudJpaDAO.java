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
    public Boolean crear(T entity) throws PersonaIdentificacionDuplicadoException{
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
            System.err.println("******************* Error en Crear Generico ->" + e.getMessage()+"la causa es la siguiente"+e.getCause().getMessage());            
            session.beginTransaction().rollback();
            result = false;
            String causa= UtilidadCadena.partirCadena(e.getCause().getMessage(), 0, 15);            
            if(causa.equals("Duplicate entry")){
                throw new PersonaIdentificacionDuplicadoException("Número De Identificación Usuario Duplicado.");            
            }
            
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
        return (T) session.get(entityClass, (Integer) id);
    }

    @Override
    public List<T> buscarTodos(Class<T> entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public T buscarUltimo(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
