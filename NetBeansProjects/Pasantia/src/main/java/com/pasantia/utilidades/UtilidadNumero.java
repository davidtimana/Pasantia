package com.hds.vpm.comun.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para trabajar con numeros.
 * 
 * @author Fredy Dorado
 * 
 * @version 25/06/2013
 * 
 */
public final class UtilidadNumero {

	/**
	 * Constructor privado para evitar instanciar objetos de esta clase
	 */
	private UtilidadNumero() {
	}

	/**
	 * Genera un listado de numeros consecutivos desde un rango, incremento y
	 * orden dado
	 * 
	 * @author Fredy Dorado
	 * 
	 * @param numeroInicio
	 *            n&uacute;mero inicio de la secuencia
	 * @param numeroFin
	 *            n&uacute;mero fin de la secuencia
	 * @param incremento
	 *            incremento de la secuencia
	 * @param ordenAscendente
	 *            orden en que se retorna la secuencia, true en orden ascendente
	 *            y false en orden descendente
	 * @return
	 */
	public static List<Integer> generarNumerosConsecutivos(int numeroInicio,
			int numeroFin, int incremento, boolean ordenAscendente) {
		List<Integer> listaNumeros = new ArrayList<Integer>();

		if (numeroInicio > numeroFin || incremento <= 0) {
			throw new IllegalArgumentException("Parametros invalidos");
		}

		for (int i = numeroInicio, j = numeroFin; i <= numeroFin; i += incremento, j -= incremento) {
			if (ordenAscendente) {
				listaNumeros.add(i);
			} else {
				listaNumeros.add(j);
			}
		}

		return listaNumeros;
	}

}
