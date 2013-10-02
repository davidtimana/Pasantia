/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.accesoyseguridad.acceso;

import com.pasantia.entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author David Timana
 */
public class ModeloAccesos extends ListDataModel<Usuario> implements SelectableDataModel<Usuario>, Serializable{
    
    private static final long serialVersionUID = 7920843444703433349L;

    @Override
    public Object getRowKey(Usuario t) {
        return t.getNomusuario();
    }

    @Override
    public Usuario getRowData(String rowKey) {
      List<Usuario> usuarios = (List<Usuario>) getWrappedData();
        
        for (Usuario usuario : usuarios) {
            if(usuario.getNomusuario().equals(rowKey)){
                return usuario;
            }
        }
        return null;
    }

    public ModeloAccesos(List<Usuario> list) {
        super(list);
    }
    
    
    
}
