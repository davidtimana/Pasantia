/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.CategoriaDAO;
import com.pasantia.entidades.Categoria;
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
public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public Boolean insertarCategoria(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion = "";
        Boolean result = false;;

        try {
            session.beginTransaction();
            descripcion = categoria.getDescripcion();
            descripcion = descripcion.toUpperCase();
            descripcion = descripcion.trim();
            categoria.setDescripcion(descripcion);
            session.save(categoria);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.out.println("Error en insertarUbicacion " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Boolean actualizarCategoria(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String descripcion = "";
        Boolean result = false;;
        try {
            session.beginTransaction();
            descripcion = categoria.getDescripcion();
            descripcion = descripcion.toUpperCase();
            descripcion = descripcion.trim();
            categoria.setDescripcion(descripcion);
            session.update(categoria);
            session.beginTransaction().commit();
            result = true;
        } catch (Exception e) {
            System.out.println("Error en actualizar " + e.getMessage());
            session.beginTransaction().rollback();
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean eliminarCategoria(Categoria categoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(categoria);
            session.beginTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
            session.beginTransaction().rollback();
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public Categoria buscarCategoriaporId(Integer id) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        Categoria categoria = null;
        try {
            categoria = (Categoria) session.load(Categoria.class, id);
        } catch (Exception e) {
            System.out.println("Error al buscar el id: " + id + " :" + e.getMessage());
        } finally {
            session.close();
        }
        return categoria;
    }

    @Override
    public List<Categoria> buscartodasCategorias() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Categoria> categorias = new ArrayList<Categoria>();
        String jpql = "";

        try {
            jpql = " FROM Categoria c "
                    + "ORDER BY c.descripcion";
            Query q = session.createQuery(jpql);
            categorias = (List<Categoria>) q.list();

        } catch (Exception e) {
            categorias = null;
            System.err.println("Error al buscartodasCategorias: " + e.getMessage());
            session.beginTransaction().rollback();

        } finally {
            session.close();
        }

        return categorias;

    }

    @Override
    public List<Categoria> buscartodasCategoriasxNombrexId(Integer idCategori, String nombreCategoria) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List list = new ArrayList();
        List<Categoria> categorias = new ArrayList<Categoria>();
        String condicion="";
        System.out.println("idCategoria es-->"+idCategori);
        if(idCategori!=null){
            condicion="AND b.idCategoria = "+idCategori;
        }
        
        try {
            String jpql =
                    " SELECT b "
                    + " FROM Categoria b "
                    + " WHERE UPPER(b.descripcion) LIKE '%" + nombreCategoria + "%' "                    
                    + condicion
                    + " ORDER BY b.descripcion";
            System.out.println("El jpql es el siguiente-->" + jpql);
            Query q = session.createQuery(jpql);
            session.flush();

            list = q.list();
            categorias = (List<Categoria>) list;

        } catch (Exception e) {
            categorias = null;
            System.out.println("Error en buscar buscartodasCategoriasxNombrexId " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            System.out.println("cerrando la sesion en buscartodasCategoriasxNombrexId retorna categorias");            
            session.close();
        }
        return categorias;
    }
    
    
}
