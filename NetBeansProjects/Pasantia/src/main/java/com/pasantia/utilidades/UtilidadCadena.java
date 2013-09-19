package com.pasantia.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para trabajar con cadenas.
 * 
 * @author David Timana
 */
public final class UtilidadCadena {

	/**
	 * Constructor privado para evitar instanciar objetos de esta clase
	 */
	private UtilidadCadena() {

	}

	/**
	 * Verifica si la cadena dada es un correo electronico o no
	 * 
	 * @param correo
	 *            cadena a evaluar
	 * @return true si la cadena dada es un correo electronico y false en caso
	 *         contrario
	 * @autor David Timana
	 */
	public static boolean esUnCorreoElectronico(String correo) {
		Pattern pattern = null;
		Matcher matcher = null;
		pattern = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		matcher = pattern.matcher(correo);
		return matcher.matches();
	}
}
