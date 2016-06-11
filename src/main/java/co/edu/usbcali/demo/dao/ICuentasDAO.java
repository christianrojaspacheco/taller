package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Cuentas;

/**
 * The Interface ICuentasDAO.
 */
public interface ICuentasDAO { 
    
	/**
	 * Grabar.
	 *
	 * @param cuenta the cuenta
	 */
	public void grabar(Cuentas cuenta);
 	
	 /**
	  * Modificar.
	  *
	  * @param cuenta the cuenta
	  */
	 public void modificar(Cuentas cuenta);
	
	/**
	 * Borrar.
	 *
	 * @param cuenta the cuenta
	 */
	public void borrar(Cuentas cuenta);
	
	/**
	 * Consultar cuenta por id.
	 *
	 * @param id the id
	 * @return the cuentas
	 */
	public Cuentas consultarCuentaPorNumero(String numero);
	
	/**
	 * Consultar todos.
	 *
	 * @return the list
	 */
	public List<Cuentas> consultarTodos();
	
}
