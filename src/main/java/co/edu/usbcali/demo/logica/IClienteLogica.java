package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;

/**
 * The Interface IClienteLogica.
 */
public interface IClienteLogica {

	/**
	 * Grabar.
	 *
	 * @param clientes the clientes
	 * @throws Exception the exception
	 */
	public void grabar(Clientes clientes) throws Exception;
	
	/**
	 * Modificar.
	 *
	 * @param clientes the clientes
	 * @throws Exception the exception
	 */
	public void modificar(Clientes clientes) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param clientes the clientes
	 * @throws Exception the exception
	 */
	public void borrar(Clientes clientes) throws Exception;
	
	/**
	 * Consultar cliente por id.
	 *
	 * @param cliId the cli id
	 * @return the clientes
	 * @throws Exception the exception
	 */
	public Clientes consultarClientePorId(long cliId) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Clientes> consultarTodos() throws Exception;
	
}
