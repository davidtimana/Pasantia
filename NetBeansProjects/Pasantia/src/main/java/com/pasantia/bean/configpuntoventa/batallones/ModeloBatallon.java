/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.batallones;

import com.pasantia.entidades.Batallon;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
@Named(value = "modeloBatallon")
@SessionScoped
public class ModeloBatallon extends ListDataModel<Batallon> implements SelectableDataModel<Batallon>, Serializable{
    
    private static final long serialVersionUID = 5217781371200078755L;

    /**
     * Creates a new instance of ModeloBatallon
     */
    public ModeloBatallon() {
    }

    @Override
    public Object getRowKey(Batallon t) {
        return t.getNombreBatallon();
    }

    @Override
    public Batallon getRowData(String rowKey) {
        List<Batallon> batallones = (List<Batallon>) getWrappedData();
        
        for (Batallon batallon : batallones) {
            if(batallon.getNombreBatallon().equals(rowKey)){
                return batallon;
            }
        }
        return null;
    }
    

    public ModeloBatallon(List<Batallon> list) {
        super(list);
    }
    
    
}
