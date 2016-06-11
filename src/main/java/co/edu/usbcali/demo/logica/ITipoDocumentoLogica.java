package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

/**
 * The Interface ITipoDocumentoLogica.
 */
public interface ITipoDocumentoLogica {

	/**
	 * Grabar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 * @throws Exception the exception
	 */
	public void grabar(TiposDocumentos tiposDocumentos) throws Exception;
	
	/**
	 * Modificar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 * @throws Exception the exception
	 */
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param tiposDocumentos the tipos documentos
	 * @throws Exception the exception
	 */
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception;
	
	/**
	 * Consultar tipo documento por id.
	 *
	 * @param cliId the cli id
	 * @return the tipos documentos
	 * @throws Exception the exception
	 */
	public TiposDocumentos consultarTipoDocumentoPorId(long cliId) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TiposDocumentos> consultarTodos() throws Exception;
	
}
