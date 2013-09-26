package com.pasantia.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para trabajar con fechas.
 *
 * @author Fredy Dorado
 */
public final class UtilidadFecha {

    /**
     * Definici&oacute;n tipo de tiempo
     *
     * @author Fredy Dorado
     *
     */
    public enum PeriodoTiempoFecha {
        // constantes

        DIA(Calendar.DAY_OF_MONTH), MES(Calendar.MONTH), ANIO(Calendar.YEAR), HORA(
        Calendar.HOUR), MINUTO(Calendar.MINUTE), SEGUNDO(
        Calendar.SECOND);
        // atributos
        private int periodo;

        // metodos
        private PeriodoTiempoFecha(int periodo) {
            this.periodo = periodo;
        }

        // getters y setters
        public int getPeriodo() {
            return periodo;
        }

        public void setPeriodo(int periodo) {
            this.periodo = periodo;
        }
    };

    /**
     * Constructor privado para evitar instanciar objetos de esta clase
     */
    private UtilidadFecha() {
    }

    /**
     * Permite convertir una cadena(String) a una fecha (Date).
     *
     * @param fecha (String) la fecha a la cual deseo convertir
     * @param formato define el formato en el cual esta definida la fecha en la
     * cadena, ejemplo "dd/MM/yyyy"
     * @return Date con la fecha
     *
     * @author Fredy Dorado
     * @throws ParseException
     */
    public static Date convertirCadenaAFecha(String fecha, String formato)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.parse(fecha);
    }

    /**
     * Suma un periodo de tiempo a la fecha dada
     *
     * @param fecha a la cual se sumara el periodo de tiempo
     * @param numeroPeriodoTiempo valor sumar
     * @param periodoTiempo periodo de tiempo que se sumara a la fecha
     *
     * @return fecha despues de la suma del periodo de tiempo
     *
     * @author Fredy Dorado
     */
    public static Date sumarPeriodoTiempoAFecha(Date fecha,
            int numeroPeriodoTiempo, PeriodoTiempoFecha periodoTiempo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(periodoTiempo.getPeriodo(), numeroPeriodoTiempo);
        return calendar.getTime();
    }

    /**
     * Retorna un valor numero correspondiente al periodo de la fecha que se
     * desea consultar, ejemplo 0 para enero
     *
     * @param fecha a la cual se sumara el periodo de tiempo
     * @param numeroPeriodoTiempo valor sumar
     * @param periodoTiempo periodo de tiempo que se sumara a la fecha
     *
     * @return fecha despues de la suma del periodo de tiempo
     *
     * @author Fredy Dorado
     */
    public static int valorPeriodoTiempoEnFecha(Date fecha,
            PeriodoTiempoFecha periodoTiempo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(periodoTiempo.getPeriodo());
    }

    /**
     * Retorna la fecha del sistema en formato establecido en la cadena de
     * entrada
     *
     * @param formato el formato en el cual se quiere obtener la fecha
     * @return la fecha actual del sistema en el formato establecido en el
     * parametro de entrada
     *
     */
    public static String obtenerFechaActualFormatoTexto(String formato) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        Date d = new Date();
        return formatoFecha.format(d);
    }

    /**
     * Retorna la fecha del sistema en formato establecido en la cadena de
     * entrada
     *
     * @param formato el formato en el cual se quiere obtener la fecha
     * @return la fecha actual del sistema en el formato establecido en el
     * parametro de entrada
     * @author Fredy Dorado
     */
    public static String obtenerFechaEnFormatoTexto(Date fecha, String formato) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        return formatoFecha.format(fecha);
    }

    public static int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            date = (Date) formatter.parse(date.toString());
            cal = Calendar.getInstance();
            cal.setTime(date);
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
        }
        return cal;

    }

    /**
     * Metodo que se encarga de calcular la edad de una persona apartir de la
     * fecha de nacimiento
     *
     * @param fecha_nacimiento Fecha nacimiento de la persona en formato
     * dd/MM/yyyy
     * @return anos 
     * Edad de la persona
     * @author David Timana
     */
    public static Integer calcularEdad(String fecha_nacimiento) {

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha_nacimiento.split("/");
        String[] dat2 = hoy.split("/");
        int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if (mes < 0) {
            anos = anos - 1;
        } else if (mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if (dia > 0) {
                anos = anos - 1;
            }
        }
        return anos;
    }
    
    public static String obtenerFechaActualFormatoString(String format) {
        Date fechaActualDate = new Date();
        SimpleDateFormat formato = new SimpleDateFormat(format);
        String fechaActual = formato.format(fechaActualDate);
        return fechaActual;
    }
}
