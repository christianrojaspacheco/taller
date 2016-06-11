package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Consignaciones;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConsignacionesLogica.
 */
public interface IConsignacionLogica {

	/**
	 * Grabar.
	 *
	 * @param consignaciones the consignaciones
	 * @throws Exception the exception
	 */
	public void grabar(Consignaciones consignaciones) throws Exception;
	
	/**
	 * Modificar.
	 *
	 * @param consignaciones the consignaciones
	 * @throws Exception the exception
	 */
	public void modificar(Consignaciones consignaciones) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param consignaciones the consignaciones
	 * @throws Exception the exception
	 */
	public void borrar(Consignaciones consignaciones) throws Exception;
	
	/**
	 * Consultar consignaciones por id.
	 *
	 * @param id the id
	 * @return the consignaciones
	 * @throws Exception the exception
	 */
	public Consignaciones consultarConsignacionesPorId(long id) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Consignaciones> consultarTodos() throws Exception;
	

	/**
	 * Obtener consecutivo id.
	 *
	 * @return the long
	 */
	public Long obtenerConsecutivoId();
	
}
