package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Usuarios;

/**
 * The Interface IUsuarioLogica.
 */
public interface IUsuarioLogica {

    /**
     * Grabar.
     *
     * @param usuario the usuario
     * @throws Exception the exception
     */
    public void grabar(Usuarios usuario) throws Exception;
	
	/**
	 * Modificar.
	 *
	 * @param usuario the usuario
	 * @throws Exception the exception
	 */
	public void modificar(Usuarios usuario) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param usuario the usuario
	 * @throws Exception the exception
	 */
	public void borrar(Usuarios usuario) throws Exception;
	
	/**
	 * Consultar usuario por id.
	 *
	 * @param id the id
	 * @return the usuarios
	 * @throws Exception the exception
	 */
	public Usuarios consultarUsuarioPorId(long id) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Usuarios> consultarTodos() throws Exception;
	
}
