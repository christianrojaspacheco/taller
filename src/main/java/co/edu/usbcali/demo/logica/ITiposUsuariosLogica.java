package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

/**
 * The Interface ITiposUsuariosDAO.
 */
public interface ITiposUsuariosLogica {

	/**
	 * Grabar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 * @throws Exception the exception
	 */
	public void grabar(TiposUsuarios tipoUsuarios) throws Exception;
	
	/**
	 * Modificar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 * @throws Exception the exception
	 */
	public void modificar(TiposUsuarios tipoUsuarios) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 * @throws Exception the exception
	 */
	public void borrar(TiposUsuarios tipoUsuarios) throws Exception;
	
	/**
	 * Consultar tipo usuarios por id.
	 *
	 * @param id the id
	 * @return the tipos usuarios
	 * @throws Exception the exception
	 */
	public TiposUsuarios consultarTipoUsuariosPorId(long id) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TiposUsuarios> consultarTodos() throws Exception;
	
	
}
