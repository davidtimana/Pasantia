/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.UsuarioDAO;
import com.pasantia.entidades.Usuario;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author david
 */
public class UsuarioDAOImpl implements UsuarioDAO{

    @Override
    public void insertarUsuario(Usuario usuario) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String nom_usuario="";
        try{
            session.beginTransaction();
            nom_usuario=usuario.getNomusuario();
            nom_usuario=nom_usuario.trim();
            usuario.setNomusuario(nom_usuario);
            session.save(usuario);
            session.beginTransaction().commit();
            }catch(Exception e){
            System.out.println("Error en insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        String nom_usuario="";
        try{
            session.beginTransaction();
            nom_usuario=usuario.getNomusuario();
            nom_usuario=nom_usuario.trim();
            usuario.setNomusuario(nom_usuario);
            usuario.setNomusuario(nom_usuario);
            session.update(usuario);
            session.beginTransaction().commit();            
        }catch(Exception e){
            System.out.println("Error en actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
        finally{
            session.close();
        }
    }

    @Override
    public boolean eliminarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarUsuarioporId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscartodosUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
