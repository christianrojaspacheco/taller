package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

/**
 * The Interface ITipoDocumentoDAO.
 */
public interface ITipoDocumentoDAO {

	/**
	 * Grabar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 */
	public void grabar(TiposDocumentos tiposDocumentos);
	
	/**
	 * Modificar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 */
	public void modificar(TiposDocumentos tiposDocumentos);
	
	/**
	 * Borrar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 */
	public void borrar(TiposDocumentos tiposDocumentos);
	
	/**
	 * Consultar tipo documento por id.
	 *
	 * @param cliId the cli id
	 * @return the tipos documentos
	 */
	public TiposDocumentos consultarTipoDocumentoPorId(long cliId);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<TiposDocumentos> consultarTodos();
	
}
