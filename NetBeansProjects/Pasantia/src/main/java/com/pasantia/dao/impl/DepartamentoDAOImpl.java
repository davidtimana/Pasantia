/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.entidades.Departamento;
import java.util.ArrayList;
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

    private EntityManager entityManager;
    
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
    
}
