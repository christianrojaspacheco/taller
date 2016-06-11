package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;

/**
 * The Interface ITransaccionLogica.
 */
public interface ITransaccionLogica
{
    
    /**
     * Retirar.
     *
     * @param numeroCuenta the numero cuenta
     * @param cedulaUsuario the cedula usuario
     * @param valor the valor
     * @throws Exception the exception
     */
    public void retirar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor)
    throws Exception;

    /**
     * Consigar.
     *
     * @param numeroCuenta the numero cuenta
     * @param cedulaUsuario the cedula usuario
     * @param valor the valor
     * @throws Exception the exception
     */
    public void consigar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor)
    throws Exception;
}
