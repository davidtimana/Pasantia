/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.usuariossistema;

import com.pasantia.entidades.Persona;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloUsuarios extends ListDataModel<Persona> implements SelectableDataModel<Persona>, Serializable{
    
    private static final long serialVersionUID = -6376583208786005379L;

    @Override
    public Object getRowKey(Persona t) {
        return t.getCedula();
    }

    @Override
    public Persona getRowData(String rowKey) {
        List<Persona> personas = (List<Persona>) getWrappedData();  
         for(Persona p : personas) {  
            if(p.getCedula().equals(rowKey))  
                return p;  
        } 
        return null;  
    }

    public ModeloUsuarios() {
    }
    
    public ModeloUsuarios(List<Persona> personas) {
        super(personas);
    }
    
    
    
    
    
}
