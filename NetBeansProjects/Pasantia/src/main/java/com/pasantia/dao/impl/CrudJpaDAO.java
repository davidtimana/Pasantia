/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CrudDAO;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author David Orlando Timaná
 */
public class CrudJpaDAO<T> implements CrudDAO<T>{
    
    
    private Session session;
   

    public CrudJpaDAO() {
        session = ConexionHibernate.getSessionFactory().openSession();  
    }

    @Override
    public void commitTransaccion() {
        session.flush();
    }

    @Override
    public Boolean crear(T entity) {
        Boolean result=false;
        try{   
            session.beginTransaction();
            session.save(entity);
            session.flush();
            session.beginTransaction().commit();
            result = true;            
        }catch(Exception e){
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
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    
    
}
