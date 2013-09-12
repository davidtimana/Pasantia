/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.entidades.Departamento;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
@Stateless
public class DepartamentoDAOImpl implements DepartamentoDAO{

    
    
    @Override
    public List<Departamento> buscartodosDepartamentos() {
        Session session = ConexionHibernate.getSessionFactory().openSession();        
        return session.createQuery("from Departamento d order by d.nombreDepartamento").list();
    }

    @Override
    public List<Departamento> buscarDepartamentoporIdPais(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Departamento> departamentos = new ArrayList<Departamento>();
        String jpql = "";

        try {
            jpql = " FROM Departamento d "
                    + "WHERE d.pais.idPais= :pais "
                    + "ORDER BY d.nombreDepartamento ASC";
            Query q = session.createQuery(jpql);
            q.setInteger("pais", id);
            departamentos = (List<Departamento>) q.list();

        } catch (Exception e) {
            departamentos = null;
            System.out.println("Error en buscarDepartamentoporIdPais " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return departamentos;
    }

    @Override
    public Departamento buscarDepartamentoporIdUno(Integer id) {
        
        Session session = ConexionHibernate.getSessionFactory().openSession();
        
        Query q=session.createQuery("from Departamento as d where d.idDepartamento= :id");
        q.setInteger("id", id);
        return (Departamento)(q.uniqueResult());
    }

    @Override
    public void actualizarDepartamento(Departamento departamento) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        
        try{
            
            session.beginTransaction();            
            session.update(departamento);
            session.beginTransaction().commit();            
            
        }catch(Exception e){
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion");
            session.close();
        }
    }

    @Override
    public List<Departamento> buscarDepartamentoNoAsociados() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Departamento> departamentos = new ArrayList<Departamento>();
        List<Object> listaNativa = new ArrayList<Object>();
        
        
        String sql = "";

        try {
            sql = " SELECT d.idDepartamento,d.nombre_departamento,d.latitud,d.longitud,d.secpais FROM Departamento d "
                    + " LEFT JOIN DivisionesUbicacion du on (d.idDepartamento = du.secdepartamento) "
                    + " LEFT JOIN Divisiones divi on(du.secdivision=divi.idDivisiones) "
                    + " WHERE d.secpais=51 and du.secdepartamento is null and du.secdivision is null";
            Query q = session.createSQLQuery(sql);
            
            listaNativa= q.list();
            Iterator iterator = listaNativa.iterator();
            while (iterator.hasNext()) {
                Departamento departamento = new Departamento();
                Object[] obj = (Object[]) iterator.next();                
                departamento=buscarDepartamentoporIdUno((Integer)obj[0]);
                departamentos.add(departamento);
            }            
            

        } catch (Exception e) {
            departamentos = null;
            System.out.println("Error en buscarDepartamentoNoAsociados " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return departamentos;
    }
    
}
