package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Cuentas;

/**
 * The Interface ICuentasLogica.
 */
public interface ICuentaLogica { 
    
	/**
	 * Grabar.
	 *
	 * @param cuenta the cuenta
	 * @throws Exception the exception
	 */
	public void grabar(Cuentas cuenta) throws Exception;
 	
	 /**
 	 * Modificar.
 	 *
 	 * @param cuenta the cuenta
 	 * @throws Exception the exception
 	 */
	 public void modificar(Cuentas cuenta) throws Exception;
	
	/**
	 * Borrar.
	 *
	 * @param cuenta the cuenta
	 * @throws Exception the exception
	 */
	public void borrar(Cuentas cuenta) throws Exception;
	
	/**
	 * Consultar cuenta por id.
	 *
	 * @param id the id
	 * @return the cuentas
	 * @throws Exception the exception
	 */
	public Cuentas consultarCuentaPorNumero(String numero) throws Exception;
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Cuentas> consultarTodos() throws Exception;
	
}
