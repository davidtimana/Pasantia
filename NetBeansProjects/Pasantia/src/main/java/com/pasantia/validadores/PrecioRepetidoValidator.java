/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.validadores;

import com.pasantia.articulos.articulo.ControlPreciosBean;
import com.pasantia.utilidades.Utilidad;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author David Timana
 */

@FacesValidator("PrecioRepetidoValidator")
public class PrecioRepetidoValidator implements Validator{
    private static final Logger log = Logger.getLogger(PrecioRepetidoValidator.class.getName());

    @Inject
    ControlPreciosBean preciosBean;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.log(Level.INFO, "el valor es el siguiente en validacion-->{0}", value.toString());
        BigDecimal valor = (BigDecimal) value;
        if (valor.intValue() <= 0) {
            FacesMessage mess = new FacesMessage(FacesMessage.SEVERITY_ERROR, "SICOVI", "El precio ingresado debe ser mayor que cero (0).");
            throw new ValidatorException(mess);
        }

    }

    
    
   
    
    
    
}
