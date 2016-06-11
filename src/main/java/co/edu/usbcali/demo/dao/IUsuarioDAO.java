package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Usuarios;

/**
 * The Interface IUsuarioDAO.
 */
public interface IUsuarioDAO {

    /**
     * Grabar.
     *
     * @param usuario the usuario
     */
    public void grabar(Usuarios usuario);
	
	/**
	 * Modificar.
	 *
	 * @param usuario the usuario
	 */
	public void modificar(Usuarios usuario);
	
	/**
	 * Borrar.
	 *
	 * @param usuario the usuario
	 */
	public void borrar(Usuarios usuario);
	
	/**
	 * Consultar usuario por id.
	 *
	 * @param id the id
	 * @return the usuarios
	 */
	public Usuarios consultarUsuarioPorId(long id);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<Usuarios> consultarTodos();
	
}
