package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Retiros;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRetiroLogica.
 */
public interface IRetiroLogica {

	/**
	 * Grabar.
	 *
	 * @param retiro the retiro
	 * @throws Exception the exception
	 */
	public void grabar(Retiros retiro) throws Exception;

	/**
	 * Modificar.
	 *
	 * @param retiro the retiro
	 * @throws Exception the exception
	 */
	public void modificar(Retiros retiro) throws Exception;

	/**
	 * Borrar.
	 *
	 * @param retiro the retiro
	 * @throws Exception the exception
	 */
	public void borrar(Retiros retiro) throws Exception;

	/**
	 * Consultar retiro por id.
	 *
	 * @param id the id
	 * @return the retiros
	 * @throws Exception the exception
	 */
	public Retiros consultarRetiroPorId(long id) throws Exception;

	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Retiros> consultarTodos() throws Exception;
	
	/**
	 * Obtener consecutivo id.
	 *
	 * @return the long
	 */
	public Long obtenerConsecutivoId();

}
