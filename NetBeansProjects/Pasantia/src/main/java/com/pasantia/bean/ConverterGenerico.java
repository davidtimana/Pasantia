package com.pasantia.bean;


import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Clase gen&eacute;rica que implementa un converter de primefaces &uacute;til
 * para selectOneMenu
 * 
 * @author Milton Sanchez 
 */

public abstract class ConverterGenerico<T> implements Converter, Serializable {
    
    private static final long serialVersionUID = 1750498704531878734L;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
		final List<T> lista = getLista();
		if (null != lista) {
			try {
				final Integer id = Integer.parseInt(key);
				for (T t : lista) {
					if (id.equals(getId(t))) {
						return t;
					}
				}
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if (null != obj) {
			try {
				final T t = (T) obj;
				return null == getId(t) ? "-1" : getId(t).toString();
			} catch (ClassCastException ex) {
				return "";
			}
		}
		return "";
	}

	/**
	 * Retorna la lista de elementos mostrados en el control de selecci&oacute;n
	 * 
	 * @return Lista de elementos del control de selecci&oacute;n
	 */
	abstract List<T> getLista();

	/**
	 * Retorna el id del objeto t
	 * 
	 * @param t
	 *            objeto el cual retornar&aacute; su id
	 * @return Long del id del objeto
	 */
	abstract Integer getId(T t);
}
