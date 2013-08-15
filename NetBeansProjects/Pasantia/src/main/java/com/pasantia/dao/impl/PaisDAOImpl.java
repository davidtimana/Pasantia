/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.PaisDAO;
import com.pasantia.entidades.Pais;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public class PaisDAOImpl implements PaisDAO{

    @Override
    public List<Pais> buscartodasPaises() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return session.createQuery("from Pais").list();
    }

    @Override
    public Pais buscarPaisxId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Pais pais=null;
        try{
            pais=(Pais)session.load(Pais.class,id);
        }catch(Exception e){
            System.out.println("Error al buscar el id: "+id+" :"+e.getMessage());
        }
        finally{
            session.close();
        }
        return pais;
    }
    
}
