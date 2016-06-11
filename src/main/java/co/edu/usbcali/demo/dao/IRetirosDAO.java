package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Retiros;

/**
 * The Interface IRetirosDAO.
 */
public interface IRetirosDAO {

	/**
	 * Grabar.
	 *
	 * @param retiro the retiro
	 */
	public void grabar(Retiros retiro);

	/**
	 * Modificar.
	 *
	 * @param retiro the retiro
	 */
	public void modificar(Retiros retiro);

	/**
	 * Borrar.
	 *
	 * @param retiro the retiro
	 */
	public void borrar(Retiros retiro);

	/**
	 * Consultar retiro por id.
	 *
	 * @param id the id
	 * @return the retiros
	 */
	public Retiros consultarRetiroPorId(long id);

	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<Retiros> consultarTodos();
	

	/**
	 * Obtener consecutivo id.
	 *
	 * @return the long
	 */
	public Long obtenerConsecutivoId();

}
