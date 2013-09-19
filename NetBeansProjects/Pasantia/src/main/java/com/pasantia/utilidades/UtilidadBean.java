package com.pasantia.utilidades;



import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para gestion de archivos.
 * 
 * @author Fredy Dorado
 * 
 * @version 26/04/2013
 * 
 */
public final class UtilidadBean {

	// enumeraciones
	@SuppressWarnings("rawtypes")
	public enum TipoDatoPropiedad {
		INT(Integer.class, "ENTERO"), LONG(Long.class, "ENTERO LARGO"), BIGDECIMAL(
				BigDecimal.class, "DECIMAL"), FLOAT(Float.class,
				"PUNTO FLOTANTE"), DOUBLE(Double.class, "PUNTO FLOTANTE DOBLE"), STRING(
				String.class, "CADENA"), CHAR(Character.class, "CARACTER"), BOOLEAN(
				Boolean.class, "BOOLEANO"), DATE(Date.class, "FECHA"), OBJETO(
				Object.class, "OBJETO");

		// atributos

		private Class tipoClass;
		private String nombre;

		// metodos
		private TipoDatoPropiedad(Class classPropiedad, String nombre) {
			tipoClass = classPropiedad;
			this.nombre = nombre;
		}

		/**
		 * Recupera el tipo de propiedad usando el nombre completo del tipo
		 * 
		 * @author Fredy Dorado
		 * 
		 * @param nombreCompletoTipo
		 * 
		 * @return tipo de propiedad
		 */
		public static TipoDatoPropiedad obtenerTipoPorNombreCompleto(
				String nombreCompletoTipo) {
			TipoDatoPropiedad tipoEncontrado = null;
			for (TipoDatoPropiedad tipo : TipoDatoPropiedad.values()) {
				if (tipo.getNombreCompleto().equals(nombreCompletoTipo)) {
					tipoEncontrado = tipo;
					break;
				}
			}
			return tipoEncontrado;
		}

		/**
		 * Obtiene el tipo de datos de una propiedad a partir de su valor
		 * 
		 * @param valorPropiedad
		 *            valor de la propiedad
		 * 
		 * @return <strong>Object</strong> del tipo de dato dado
		 *         <strong>tipo</strong> con el valor asociado al idVariable, si
		 *         ocurre un error retorna null
		 * 
		 * @author Fredy Dorado
		 */
		public static TipoDatoPropiedad obtenerTipoDePropiedad(
				Object valorPropiedad) {
			TipoDatoPropiedad tipoDatoPropiedad = null;
			// INT, LONG, STRING, BOOLEAN, BIGDECIMAL, FLOAT, DOUBLE, DATE,
			// OBJECTO
			if (valorPropiedad != null) {
				if (valorPropiedad instanceof Integer) {
					tipoDatoPropiedad = TipoDatoPropiedad.INT;
				} else if (valorPropiedad instanceof Long) {
					tipoDatoPropiedad = TipoDatoPropiedad.LONG;
				} else if (valorPropiedad instanceof BigDecimal) {
					tipoDatoPropiedad = TipoDatoPropiedad.BIGDECIMAL;
				} else if (valorPropiedad instanceof Float) {
					tipoDatoPropiedad = TipoDatoPropiedad.FLOAT;
				} else if (valorPropiedad instanceof Double) {
					tipoDatoPropiedad = TipoDatoPropiedad.DOUBLE;
				} else if (valorPropiedad instanceof String) {
					tipoDatoPropiedad = TipoDatoPropiedad.STRING;
				} else if (valorPropiedad instanceof Boolean) {
					tipoDatoPropiedad = TipoDatoPropiedad.BOOLEAN;
				} else if (valorPropiedad instanceof Date) {
					tipoDatoPropiedad = TipoDatoPropiedad.DATE;
				} else {
					tipoDatoPropiedad = TipoDatoPropiedad.OBJETO;
				}
			}
			return tipoDatoPropiedad;
		}

		// getters y setters

		public Class getTipoClass() {
			return tipoClass;
		}

		public void setTipoClass(Class tipoClass) {
			this.tipoClass = tipoClass;
		}

		public String getNombreSimple() {
			return tipoClass.getSimpleName();
		}

		public String getNombreCompleto() {
			return tipoClass.getName();
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

	}

	// metodos
	/**
	 * Constructor privado para evitar instanciar objetos de esta clase
	 */
	private UtilidadBean() {
	}

	@SuppressWarnings("rawtypes")
	public static List<String> obtenerListaPropiedades(Class claseDeObjeto) {

		List<String> listaPropiedades = new ArrayList<String>();
		try {

			BeanInfo bi = Introspector.getBeanInfo(claseDeObjeto);
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();
			String nombrePropiedad = null;
			for (int i = 1; i < pds.length; i++) {
				// Get property name
				nombrePropiedad = pds[i].getName();
				listaPropiedades.add(nombrePropiedad);
			}

			// class, prop1, prop2, PROP3

		} catch (java.beans.IntrospectionException e) {
			listaPropiedades = null;
		}
		return listaPropiedades;
	}

	

	

	

	

	

	/**
	 * Obtiene el tipo de datos de una propiedad
	 * 
	 * @param idVariable
	 *            identificador de la variable en el archivo propiedades
	 * @param tipo
	 *            puede ser INT,LONG o STRING
	 * @return <strong>Object</strong> del tipo de dato dado
	 *         <strong>tipo</strong> con el valor asociado al idVariable, si
	 *         ocurre un error retorna null
	 * @author Fredy Dorado
	 */
	public static boolean laPropiedadEsTipoDato(Object propiedad) {
		TipoDatoPropiedad tipoDatoPropiedad = TipoDatoPropiedad
				.obtenerTipoDePropiedad(propiedad);
		boolean esTipoDato = true;

		if (tipoDatoPropiedad.equals(TipoDatoPropiedad.OBJETO)) {
			esTipoDato = false;
		}

		return esTipoDato;
	}

	

	

	

}
