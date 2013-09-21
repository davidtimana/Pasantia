package com.pasantia.utilidades;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase de utilidad que re&uacute;ne un conjunto de funcionalidades comunes
 * para tareas asociadas a la parte Web.
 * 
 * @author David Timana
 * 
 * @version 20/09/2013
 * 
 */
public final class UtilidadWeb {

	/**
	 * Constructor privado para evitar instanciar objetos de esta clase
	 */
	private UtilidadWeb() {
	}

	/**
	 * Retorna la ruta de la aplicacion siguiendo la estructura
	 * http://IP:PUERTO/contexto. Por ejemplo http://192.168.0.10:8080/vpm
	 * 
	 * @author David Timana
	 * 
	 * @param request objeto de tipo HttpServletRequest
	 * 
	 * @return cadena con la ruta del servidor
	 */
	public static String obtenerURLAplicacion(HttpServletRequest request) {
		String rutaAplicacion = "http://" + request.getLocalAddr() + ":"
				+ request.getLocalPort() + request.getContextPath();
		return rutaAplicacion;
	}

	/**
	 * Retorna la ruta de la aplicacion siguiendo la estructura
	 * http://IP:PUERTO/contexto. Por ejemplo http://192.168.0.10:8080/vpm
	 * 
	 * @author David Timana
	 * 
	 * @return cadena con la ruta del servidor
	 */
	public static String obtenerURLAplicacion() {
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) extContext
				.getRequest();
		String rutaAplicacion = "http://" + request.getLocalAddr() + ":"
				+ request.getLocalPort() + request.getContextPath();
		return rutaAplicacion;
	}
	
	/**
	 * Envia el archivo por el navegador, permitiendo su descarga
	 * 
	 * @param request
	 *            variable del http
	 * @param response
	 *            variable del http
	 * @param tipoMIME
	 *            tipo MIME del archivo a enviar
	 * @param datosArchivoGenerado
	 *            datos del archivo a enviar
	 * @param nombreArchivoDescarga
	 *            nombre del archivo a enviar
	 * @param extension
	 *            extension del archivo a enviar
	 * @return true si todo ocurre bien y false en caso contrario
	 * 
	 * @author David Timana
	 */
	public static boolean descargarArchivo(HttpServletRequest request,
			HttpServletResponse response, String tipoMIME,
			byte[] datosArchivoGenerado, String nombreArchivoDescarga,
			String extension) {
		boolean error = false;
		try {
			OutputStream os = response.getOutputStream();
			response.setContentType(tipoMIME);
			response.setContentLength(datosArchivoGenerado.length);
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ nombreArchivoDescarga + extension + "\"");
			os.write(datosArchivoGenerado);
			os.flush();
			os.close();

		} catch (IOException e) {
			//System.out.println("Error1 en doWork :" + e.getMessage());
			error = true;
		} catch (Exception e) {
			//System.out.println("Error2 en doWork :" + e.getMessage());
			error = true;
		}
		return error;
	}
}
