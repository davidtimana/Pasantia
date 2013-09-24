/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.entidades.Batallon;
import com.pasantia.entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David Timana
 */
@Stateless
public class PersonaDAOImpl  implements PersonaDAO{

    @Override
    public Boolean insertarPersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarPersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarPersona(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona buscarPersonaporId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> buscartodosPersona() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona buscarUltimoIngresado() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Persona b=null;
        String jpql="";
        try{
            jpql="SELECT MAX(b) FROM Persona b";
            Query q=session.createQuery(jpql);            
            b=(Persona)q.uniqueResult();
            
        }catch(Exception e){
            System.err.println("Error en buscarUltimoIngresado "+e.getMessage());
            session.beginTransaction().rollback();
            b= null;
        }
        finally{
            System.out.println("cerrando la sesion en buscarUltimoIngresado");
            session.flush();
            session.close();
        }
        return b;
    }
    
}
