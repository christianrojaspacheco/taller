package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

/**
 * The Interface ITiposUsuariosDAO.
 */
public interface ITiposUsuariosDAO {

	/**
	 * Grabar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 */
	public void grabar(TiposUsuarios tipoUsuarios);
	
	/**
	 * Modificar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 */
	public void modificar(TiposUsuarios tipoUsuarios);
	
	/**
	 * Borrar.
	 *
	 * @param tipoUsuarios the tipo usuarios
	 */
	public void borrar(TiposUsuarios tipoUsuarios);
	
	/**
	 * Consultar tipo usuarios por id.
	 *
	 * @param id the id
	 * @return the tipos usuarios
	 */
	public TiposUsuarios consultarTipoUsuariosPorId(long id);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<TiposUsuarios> consultarTodos();
	
	
}
