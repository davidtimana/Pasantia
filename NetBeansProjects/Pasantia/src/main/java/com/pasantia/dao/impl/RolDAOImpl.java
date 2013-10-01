/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.dao.RolDAO;
import com.pasantia.entidades.Rol;
import com.pasantia.conexion.ConexionHibernate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author David Timana
 */
@Stateless
public class RolDAOImpl implements RolDAO{

    
    
    
    @Override
    public Boolean insertarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion=null;
        Boolean result=false;
        try{
            session.beginTransaction();
            descripcion=rol.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            rol.setDescripcion(descripcion);
            session.save(rol);
            session.beginTransaction().commit();
            session.flush();
            result=true;
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
            result=false;
        }finally{
            session.close();
        }
        return result;
    }

    @Override
    public Boolean actualizarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion=null;
        Boolean result=false;
        try{
            session.beginTransaction();
            descripcion=rol.getDescripcion();
            descripcion=descripcion.toUpperCase();
            descripcion=descripcion.trim();
            rol.setDescripcion(descripcion);
            session.merge(rol);
            session.beginTransaction().commit(); 
            session.flush();
            session.refresh(rol);
            result=true;
        }catch(Exception e){
            result=false;
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            session.close();
        }
        
        return result;
    }

    @Override
    public Boolean eliminarRol(Rol rol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Boolean result=false;
        try{
            session.beginTransaction();
            session.delete(rol);
            session.beginTransaction().commit();
            session.flush();
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

    @Override
    public Rol buscarRolPorId(Integer id) {
        
        Session session = ConexionHibernate.getSessionFactory().openSession();
        return (Rol)session.load(Rol.class,id);
    }

    @Override
    public List<Rol> buscartodosRoles() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Rol> roles = new ArrayList<Rol>();
        String jpql="";
        try {
            jpql=" FROM Rol r "
                    + "ORDER BY r.descripcion ASC ";
            Query q=session.createQuery(jpql);
            roles=(List<Rol>)q.list();
            session.flush();
            
        } catch (Exception e) {
            System.err.println("Error en buscartodosRoles "+e.getMessage());
            roles=null;
        } finally {
            session.close();
        }
        return roles;
    }

    @Override
    public List<Rol> buscartodasRolesxNombrexId(Integer idRol, String nombreRol) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        nombreRol = nombreRol.toUpperCase();
        List list = new ArrayList();
        List<Rol> roles = new ArrayList<Rol>();
        String condicion="";
        System.out.println("idRol es-->"+idRol);
        if(idRol!=null){
            condicion="AND b.idRol = "+idRol;
        }
        
        try {
            String jpql =
                    " SELECT b "
                    + " FROM Rol b "
                    + " WHERE UPPER(b.descripcion) LIKE '%" + nombreRol + "%' "                    
                    + condicion
                    + " ORDER BY b.descripcion ASC";
            System.out.println("El jpql es el siguiente-->" + jpql);
            Query q = session.createQuery(jpql);
            session.flush();

            list = q.list();
            roles = (List<Rol>) list;

        } catch (Exception e) {
            roles = null;
            System.out.println("Error en buscar buscartodasRolesxNombrexId " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            System.out.println("cerrando la sesion en buscartodasRolesxNombrexId retorna roles");            
            session.close();
        }
        return roles;
    }
    
    
    
}
