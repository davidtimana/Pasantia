/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CiudadDAO;
import com.pasantia.entidades.Ciudad;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class CiudadDAOImpl implements CiudadDAO{

    @Override
    public List<Ciudad> cargarCoordenadas() {
        Session session = ConexionHibernate.getSessionFactory().openSession();  
        List list= new ArrayList();
        try{
        list = session.createQuery("from Ciudad").list();
        
        }catch(Exception e){
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion");
            session.close();
        }
        
        return list;
        
    }

    @Override
    public void actualizarCiudad(Ciudad ciudad) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        
        try{
            
            session.beginTransaction();            
            session.saveOrUpdate(ciudad);
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
    public Ciudad buscarxid(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        
        Query q=session.createQuery("from Ciudad as d where d.idCiudad= :id");
        q.setInteger("id", id);
        return (Ciudad)q.uniqueResult();
    }

    @Override
    public List<Ciudad> buscarxidDpto(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();  
        List<Ciudad> list=new ArrayList<Ciudad>();
        try{
            
        Query q=session.createQuery("select distinct c from Ciudad as c where c.departamento.idDepartamento= :id");
        q.setInteger("id", id);
        list=(List<Ciudad>)q.list();
        
        
        }catch(Exception e){
            list=null;
            System.out.println("Error en buscar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            System.out.println("cerrando la sesion en buscarxidDpto");
            session.close();
        }
        
        return list;
    }

    
    
}
