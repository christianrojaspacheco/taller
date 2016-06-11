package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Consignaciones;

/**
 * The Interface IConsignacionesDAO.
 */
public interface IConsignacionesDAO {

	/**
	 * Grabar.
	 *
	 * @param consignaciones the consignaciones
	 */
	public void grabar(Consignaciones consignaciones);
	
	/**
	 * Modificar.
	 *
	 * @param consignaciones the consignaciones
	 */
	public void modificar(Consignaciones consignaciones);
	
	/**
	 * Borrar.
	 *
	 * @param consignaciones the consignaciones
	 */
	public void borrar(Consignaciones consignaciones);
	
	/**
	 * Consultar consignaciones por id.
	 *
	 * @param id the id
	 * @return the consignaciones
	 */
	public Consignaciones consultarConsignacionesPorId(long id);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<Consignaciones> consultarTodos();
	
	/**
	 * Obtener consecutivo id.
	 *
	 * @return the long
	 */
	public Long obtenerConsecutivoId();
}
