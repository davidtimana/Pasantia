/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.divisiones;

import com.pasantia.entidades.Departamento;
import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel; 

/**
 *
 * @author David Timana
 */
public class ModeloDatosDepartamento extends ListDataModel<Departamento> implements SelectableDataModel<Departamento>, Serializable {
    
    private static final long serialVersionUID = -1128006821432520054L;

    public ModeloDatosDepartamento() {
    }
    
    
    public ModeloDatosDepartamento(List<Departamento> list) {
        super(list);
    }  
    
    @Override
    public Departamento getRowData(String rowKey) {
        List<Departamento> departamentos = (List<Departamento>) getWrappedData();
        
        for (Departamento departamento : departamentos) {
            if(departamento.getNombreDepartamento().equals(rowKey))
                return departamento;
        }
        return null;
    }
    
    
    @Override
    public Object getRowKey(Departamento t) {
        return t.getNombreDepartamento();
    }

    
    
}
