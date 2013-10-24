/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.articulos.articulo;

import com.pasantia.bean.ConverterGenerico;
import com.pasantia.dao.CrudDAO;
import com.pasantia.entidades.Casino;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */

@ManagedBean
@ViewScoped
public abstract class CasinoConverter extends ConverterGenerico<Casino> implements Serializable{
    
    private static final long serialVersionUID = 7768144812663549055L;

    @Inject
    private CrudDAO<Casino> crudDAO;

    
    List<Casino> getLista() {
        List<Casino> casinos=new ArrayList<Casino>();
        return casinos;
    }

    
    Integer getId(Casino t) {
        return t.getIdCasino();
    }
    
    

    

    
    
}
