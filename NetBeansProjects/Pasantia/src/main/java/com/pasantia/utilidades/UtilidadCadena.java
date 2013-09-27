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
     * @param correo cadena a evaluar
     * @return true si la cadena dada es un correo electronico y false en caso
     * contrario
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

    /**
     * Parte la cadena en las posiciones especificadas
     *
     * @param cadena cadena a partir
     * @param posIni posicion inicial
     * @param posFin posicion final
     * @return cadena partida segun los parametros
     * @autor David Timana
     */
    public static String partirCadena(String cadena, int posIni, int posFin) {
        String cadenaEnviar = cadena.substring(posIni, posFin);
        return cadenaEnviar;
    }

    /**
     *Metodo que se encarga de convertir solo la primera letra de una cadena
     * a mayusculas.
     * @param cadena
     *          Cadena a convertir.
     * @return cadena
     *          Cadena Convertida
     */
    public static String cambiarPrimeraLetraAMayuscula(String cadena) {
        if (!Utilidad.cadenaVacia(cadena)) {
            String mayuscula = cadena.charAt(0) + "";
            mayuscula = mayuscula.toUpperCase();
            cadena = cadena.replaceFirst(cadena.charAt(0) + "", mayuscula);
        } else {
            cadena = "";
        }

        return cadena;
    }
}
