/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.BatallonDAO;
import com.pasantia.entidades.Batallon;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
@Stateless
public class BatallonDAOImpl implements BatallonDAO {

    @Override
    public List<Batallon> buscartodosBatallones() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Batallon> batallones = new ArrayList<Batallon>();
        try {
            Query q = session.createQuery("from Batallon");
            batallones = q.list();
        } catch (Exception e) {
            batallones = null;
            System.err.println("Error al buscar todos los batallones: " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }

        return batallones;
    }

    @Override
    public Boolean insertarBatallon(Batallon batallon) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean resultado = false;
        String nombre_batallon = "";
        String barrio="";
        try {
            session.beginTransaction();
            barrio=batallon.getNombreBatallon();
            barrio=barrio.toUpperCase();
            barrio=barrio.trim();
            nombre_batallon = batallon.getNombreBatallon();
            nombre_batallon = nombre_batallon.toUpperCase();
            nombre_batallon = nombre_batallon.trim();
            batallon.setNombreBatallon(nombre_batallon);
            batallon.setBarrio(barrio);
            session.save(batallon);
            session.flush();
            session.beginTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.err.println("Error en actualizar Batallon " + e.getMessage());
            session.beginTransaction().rollback();
            resultado = false;
        } finally {
            session.close();
        }
        return resultado;
    }

    @Override
    public Boolean actualizarBatallon(Batallon batallon) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        boolean resultado = false;
        String nombre_batallon = "";
        try {
            session.beginTransaction();
            nombre_batallon = batallon.getNombreBatallon();
            nombre_batallon = nombre_batallon.toUpperCase();
            nombre_batallon = nombre_batallon.trim();
            batallon.setNombreBatallon(nombre_batallon);
            session.update(batallon);
            session.flush();
            session.beginTransaction().commit();
            resultado = true;
        } catch (Exception e) {
            System.err.println("Error en insertar Batallon " + e.getMessage());
            session.beginTransaction().rollback();
            resultado = false;
        } finally {
            session.close();
        }
        return resultado;
    }

    @Override
    public Boolean eliminarBatallon(Batallon batallon) {
         Session session = ConexionHibernate.getSessionFactory().openSession();
         Boolean result=false;
        try{
            session.beginTransaction();
            session.delete(batallon);
            session.flush();
            session.beginTransaction().commit();
            result=true;
        }catch(Exception e){
            System.out.println("Error al eliminar "+e.getMessage());
            session.beginTransaction().rollback();
            result=false;
        }
        finally{
            session.close();
        }
        return result;
    }
}
