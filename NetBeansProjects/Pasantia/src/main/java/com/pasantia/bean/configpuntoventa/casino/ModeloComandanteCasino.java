/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.entidades.Persona;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloComandanteCasino extends  ListDataModel<Persona> implements SelectableDataModel<Persona>, Serializable{
    
    private static final long serialVersionUID = 8447908889526090693L;

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

    public ModeloComandanteCasino(List<Persona> list) {
        super(list);
    }
    
    
    
    
    
}
