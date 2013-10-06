/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.bean.configpuntoventa.casino;

import com.pasantia.entidades.Casino;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloCasino extends ListDataModel<Casino> implements SelectableDataModel<Casino>, Serializable{
    
    private static final long serialVersionUID = 8192132582347944580L;

    @Override
    public Object getRowKey(Casino t) {
        return t.getNit();
    }

    @Override
    public Casino getRowData(String rowKey) {
        List<Casino> casinos = (List<Casino>) getWrappedData();
            for(Casino c : casinos){
                if(c.getNit().equals(rowKey)){
                    return c;                    
                }                
            }
            return null;
    }

      public ModeloCasino(List<Casino> list) {
        super(list);
    }
    
    
    
}
