/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.DepartamentoDAO;
import com.pasantia.entidades.Departamento;
import com.pasantia.entidades.Pais;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
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
        
        Query q=session.createQuery("from Departamento as d where d.pais.idPais= :pais");
        q.setInteger("pais", id);
        return q.list();
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
