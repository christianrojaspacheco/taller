package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;

/**
 * The Interface IClienteDAO.
 */
public interface IClienteDAO {
	
	/**
	 * Grabar.
	 *
	 * @param clientes the clientes
	 */
	public void grabar(Clientes clientes);
	
	/**
	 * Modificar.
	 *
	 * @param clientes the clientes
	 */
	public void modificar(Clientes clientes);
	
	/**
	 * Borrar.
	 *
	 * @param clientes the clientes
	 */
	public void borrar(Clientes clientes);
	
	/**
	 * Consultar cliente por id.
	 *
	 * @param cliId the cli id
	 * @return the clientes
	 */
	public Clientes consultarClientePorId(long cliId);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<Clientes> consultarTodos();
	

}
