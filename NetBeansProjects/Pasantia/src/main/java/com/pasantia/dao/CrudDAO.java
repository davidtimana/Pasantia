/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pasantia.dao;

import com.pasantia.excepciones.PersonaIdentificacionDuplicadoException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Orlando Timaná
 */

@Local
public interface CrudDAO<T> {
    
    	/**
	 * Confirma la transacci&oacute;n en la base de datos
	 */
	public void commitTransaccion();
        
        /**
	 * Crea un registro en la base de datos
	 * 
	 * @param entity
	 *            objeto a almacenar
	 */
	public Boolean crear (T entity) throws PersonaIdentificacionDuplicadoException;
        
        
        /*
         * Metodo generico que busca la ultima entidad ingresada
         * 
         *  @param entity
         *          entidad a buscar
         */
        public T buscarUltimo(Class<T> entityClass);

		
	/**
	 * Modifica un registro en la base de datos
	 * 
	 * @param entity
	 *            objeto a modificar
	 */
	public Boolean editar(T entity);

	/**
	 * Eliminar un registro de la base de datos
	 * 
	 * @param entity
	 *            objeto a eliminar
	 */
	public Boolean remover(T entity);

	/**
	 * Eliminar registros de la base de datos en base a una condicion
	 * 
	 * @param entidad
	 *            objeto a eliminar
	 * 
	 * @param alias
	 *            sobre nombre puesta a la entidad en la consulta	 * 	 
	 */
	public long removerEntidades(Class entidad, String alias,
			String condicionWhere);

	/**
	 * Buscar un objeto a partir del id
	 * 
	 * @param id
	 *            llave primaria del registro a buscar
	 * @return objeto buscado
	 */
	public T buscar(Class<T> entityClass, Object id);
        

	/**
	 * Buscar un objeto a partir del id
	 * 
	 * @param entityClass
	 *            tipo de clase {@link Class} perteneciente a una entidad a
	 *            buscar
	 * 
	 * @return objeto buscado
	 * 
	 * @author "David Timana"
	 */
	public List<T> buscarTodos(Class<T> entityClass);



	/**
	 * Permite consultar una lista de entidades adicionando opcionalmente
	 * filtros en Where o campos de orden en el Order By
	 * 
	 * @param entityClass
	 *            tipo de entidad a buscar
	 * 
	 * @param alias
	 *            Coloca un sobre Nombre a la entidad que se consulta por
	 *            ejemplo para la entidad Persona podria ser "per"
	 * 
	 * @param camposWhere
	 *            Campos que sirven como filtro en la consulta. Por ejemplo, si
	 *            la entidad Persona con alias "per" tiene un campo
	 *            identificacion, el campo seria "per.identificacion". Pero si
	 *            la misma entidad tiene una relacion con otra entidad, ejemplo
	 *            telefono, el campo seria "per.telefono.id".
	 * @param camposOrderBy
	 *            Campos usandos para ordenar los resultados.
	 * 
	 * @return lista de entidades <T>	 
	 */
	public List<T> buscarLista(Class<T> entityClass, String alias,
			String camposWhere, String camposOrderBy);

	/**
	 * 
	 * Cuenta todos los registros existente para la entidad dada
	 * 
	 * @return numero de registros existentes
	 * 	 
	 */
	public Long contarTodos(Class<T> entityClass);

	/**
	 * Permite realizar una consulta que retorna un solo valor, un
	 * COUNT,SUM,etc.
	 * 
	 * @param entityClass
	 *            tipo de entidad a buscar
	 * @param alias
	 *            Coloca un sobre Nombre a la entidad que se consulta por
	 *            ejemplo para la entidad Persona podria ser "per"
	 * @param campoSelect
	 *            es el campo que retornara el unico valor, ejemplo
	 *            SUM(per.salario)
	 * @param condicionWhere
	 *            Campos que sirven como filtro en la consulta. Por ejemplo, si
	 *            la entidad Persona con alias "per" tiene un campo
	 *            identificacion, el campo seria "per.identificacion". Pero si
	 *            la misma entidad tiene una relacion con otra entidad, ejemplo
	 *            telefono, el campo seria "per.telefono.id".
	 * @return	 
	 */
	public Long consultaEscalar(Class entityClass, String alias,
			String campoSelect, String condicionWhere);
    
}
