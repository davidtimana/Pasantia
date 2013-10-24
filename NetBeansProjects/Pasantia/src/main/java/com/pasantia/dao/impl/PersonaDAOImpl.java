/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CrudDAO;
import com.pasantia.dao.PersonaDAO;
import com.pasantia.entidades.Persona;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David Timana
 */
@Stateless
public class PersonaDAOImpl implements PersonaDAO {

    @Inject
    CrudDAO<Persona> crudDAO;

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
    public List<Persona> buscar() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try {
            final Query q = session.createQuery("from Persona");
            return q.list();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Persona buscarUltimoIngresado() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Persona b = null;
        String jpql = "";
        try {
            jpql = "SELECT MAX(b) FROM Persona b";
            Query q = session.createQuery(jpql);
            b = (Persona) q.uniqueResult();

        } catch (Exception e) {
            System.err.println("Error en buscarUltimoIngresado " + e.getMessage());
            session.beginTransaction().rollback();
            b = null;
        } finally {
            System.out.println("cerrando la sesion en buscarUltimoIngresado");
            session.flush();
            session.close();
        }
        return b;
    }

    @Override
    public List<Persona> buscarComandanteSinAsignar() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Persona> personas = new ArrayList<Persona>();
        List<Object> listaNativa = new ArrayList<Object>();

        String sql = "";

        try {
            sql = "SELECT * FROM Persona p "
                    + "INNER JOIN Tipo_Persona tp on (p.SECTIPO_PERSONA=tp.idTipo_Persona) "
                    + "RIGHT JOIN Batallon b on (p.idTBLPERSONA<>b.seccoronel) "
                    + "WHERE tp.nombre_tipo_persona='Comandante Batallon'";
            Query q = session.createSQLQuery(sql);

            listaNativa = q.list();
            Iterator iterator = listaNativa.iterator();
            while (iterator.hasNext()) {
                Persona p = new Persona();
                Object[] obj = (Object[]) iterator.next();
                p = crudDAO.buscar(Persona.class, obj[0]);
                personas.add(p);
            }

        } catch (Exception e) {
            personas = null;
            System.out.println("Error en buscarComandanteSinAsignar " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return personas;
    }

    @Override
    public List<Persona> buscarComandanteCasinoSinAsignar() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Persona> personas = new ArrayList<Persona>();
        List<Object> listaNativa = new ArrayList<Object>();

        String sql = "";

        try {
            sql = "SELECT * FROM Persona p "
                    + "INNER JOIN Tipo_Persona tp on (p.SECTIPO_PERSONA=tp.idTipo_Persona) "
                    + "LEFT JOIN Casino c on (p.idTBLPERSONA=c.fk_id_persona) "
                    + "WHERE (tp.nombre_tipo_persona='Comandante Batallon' "
                    + "OR tp.nombre_tipo_persona='Comandante Casino') "
                    + "AND c.fk_id_persona IS NULL";
            Query q = session.createSQLQuery(sql);

            listaNativa = q.list();
            Iterator iterator = listaNativa.iterator();
            while (iterator.hasNext()) {
                Persona p = new Persona();
                Object[] obj = (Object[]) iterator.next();
                p = crudDAO.buscar(Persona.class, obj[0]);
                personas.add(p);
            }

        } catch (Exception e) {
            personas = null;
            System.out.println("Error en buscarComandanteCasinoSinAsignar " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return personas;
    }

    @Override
    public Persona buscarPersonaPorCedula(String cedula) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String query = "SELECT p FROM Persona p WHERE p.cedula = :cedula";
        try {
            Query q = session.createQuery(query);
            q.setString("cedula", cedula);
            return (Persona) q.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

}
