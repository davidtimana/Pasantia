package com.pasantia.utilidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para trabajar con numeros.
 * 
 * @author David Timana
 * 
 * @version 20/09/2013
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
	 * @author David Timana
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
        
        
        /**
	 * Genera un numero aleatorio de 1 a 100000000
	 * 
	 * @author David Timana
	 * 
	 
	 * @return Numero
	 */
        public static Integer generarNumerosAleatorios(){
            return (int)(Math.random()*100000000 + 1);
        }

}
