/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao.impl;

import com.pasantia.conexion.ConexionHibernate;
import com.pasantia.dao.PaisDAO;
import com.pasantia.entidades.Pais;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author root
 */
@Stateless
public class PaisDAOImpl implements PaisDAO{
    
    private List<SelectItem> comboPais;

    @Override
    public List<Pais> buscartodasPaises() {
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Pais> paises = new ArrayList<Pais>();
        String jpql="";
        try{
            jpql="FROM Pais p "
                    + "ORDER BY p.nombrePais ASC";
            Query q = session.createQuery(jpql);
            paises=(List<Pais>)q.list();
            
        }catch(Exception e){
            paises = null;
            System.err.println("Error en buscartodasPaises " + e.getMessage());
            session.beginTransaction().rollback();
        }finally{
            session.close();
            
        }    
        return paises;
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

    @Override
    public List<SelectItem> buscartodasPaisesCombo() {
        System.out.println("entrando a cargar combo");
        Session session = ConexionHibernate.getSessionFactory().openSession();
        List<Pais> paises = new ArrayList<Pais>();
        String jpql = "";
        try {
            jpql = "FROM Pais p "
                    + "ORDER BY p.nombrePais ASC";
            Query q = session.createQuery(jpql);
            paises = (List<Pais>) q.list();
            if (!paises.isEmpty()) {
                for (int i = 0; i < paises.size(); i++) {
                    comboPais.add(new SelectItem(paises.get(i).getIdPais(), paises.get(i).getNombrePais()));
                }
            }


        } catch (Exception e) {
            paises = null;
            comboPais=null;
            System.err.println("Error en buscartodasPaises " + e.getMessage());
            session.beginTransaction().rollback();
        } finally {
            session.close();
        }
        return comboPais;
    }

    public PaisDAOImpl() {
        comboPais= new ArrayList<SelectItem>();
    }
    
    

    public List<SelectItem> getComboPais() {
        return comboPais;
    }

    public void setComboPais(List<SelectItem> comboPais) {
        this.comboPais = comboPais;
    }
    
    
}
