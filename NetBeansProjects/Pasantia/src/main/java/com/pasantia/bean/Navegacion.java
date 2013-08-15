/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author david
 */

@ManagedBean
@RequestScoped
public class Navegacion implements Serializable{

    /**
     * Creates a new instance of Navegacion
     */
    public Navegacion() {
    }
    public String ir_a_Gestion_Articulos(){
        return "gestionararticulos";
    }
    public String ir_a_Listar_Articulos(){
        return "listararticulos";
    }
    public String ir_a_Gestion_Ubicaciones(){
        return "gestionubicaciones";
    }
    public String ir_a_Listar_Ubicaciones(){
        return "listarubicaciones";
    }
    public String ir_a_Gestion_Categorias(){
        return "gestionarcategorias";
    }
    public String ir_a_Listar_Categorias(){
        return "listarcategorias";
    }
    public String ir_a_Gestion_Precios_Compra(){
        return "gestionarprecioscompra";
    }
    public String ir_a_Listar_Variaciones(){
        return "listarvariaciones";
    }
    public String ir_a_Gestionar_Roles(){
        return "gestionarroles";
    }
    public String ir_a_Listar_Roles(){
        return "listarroles";
    }
    public String ir_a_Gestionar_Cargos(){
        return "gestionarcargos";
    }
    public String ir_a_Gestionar_Usuario(){
        return "gestionarusuarios";
    }
    public String ir_a_Listar_Usuario(){
        return "listarusuarios";
    }
    public String ir_a_Gestionar_Divisiones(){
        return "gestionardivisiones";
    }
    public String ir_a_Gestionar_Batallones(){
        return "gestionarbatallones";
    }
    public String ir_a_Gestionar_Casinos(){
        return "gestionarcasinos";
    }
    
    
    
}
