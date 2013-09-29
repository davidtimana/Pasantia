/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.dao.DivisionesDAO;
import com.pasantia.dao.DivisionesUbicacionDAO;
import com.pasantia.dao.impl.DivisionesUbicacionDAOImpl;
import com.pasantia.entidades.DivisionesUbicacion;
import com.pasantia.utilidades.Utilidad;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

/**
 *
 * @author David Timana
 */
@Named(value = "eliminarDivisionesBean")
@SessionScoped
public class eliminarDivisionesUbicacionBean implements Serializable{
    
    private static final long serialVersionUID = 6310464022129801144L;

    private DivisionesUbicacionDAO divisionUbicacionDAO;
    private DivisionesUbicacion divisionesUbicacionEliminar;
    private agregarDivisionesBean agregarDivisionesbean;
    private Dialog dlgcomfireliminarubic;
    
    public void prepararEliminadoUbicacion(Integer idDivisionUbicacion){
        
        System.out.println("funciona con objeto es el siguiente-->"+idDivisionUbicacion);
        System.out.println("buscando para eliminar");
        divisionesUbicacionEliminar = divisionUbicacionDAO.buscarubicacionesxid(idDivisionUbicacion);
        eliminarUbicacion();
        //dlgcomfireliminarubic.setVisible(true);
    }
    
    
    public void eliminarUbicacion(){
        System.out.println("entrando a eliminar con -->"+divisionesUbicacionEliminar.getIdDivisionesUbicacion());
        if(divisionUbicacionDAO.eliminarDivisionesUbicacion(divisionesUbicacionEliminar)){
            Utilidad.mensajeInfo("Eliminar Ubicaciones.", "Ubicacion Eliminada correctamente.");
            //agregarDivisionesbean.cargarListaDepartamentoxpais();
        }else{
            Utilidad.mensajeFatal("Eliminar Ubicaciones", "Error al eliminar la ubicacion.");
        }
    }
    
    public eliminarDivisionesUbicacionBean() {
        divisionUbicacionDAO = new DivisionesUbicacionDAOImpl();
        dlgcomfireliminarubic = new Dialog();
        
        dlgcomfireliminarubic.setVisible(false);
    }

    public Dialog getDlgcomfireliminarubic() {
        return dlgcomfireliminarubic;
    }

    public void setDlgcomfireliminarubic(Dialog dlgcomfireliminarubic) {
        this.dlgcomfireliminarubic = dlgcomfireliminarubic;
    }
    
    
}
