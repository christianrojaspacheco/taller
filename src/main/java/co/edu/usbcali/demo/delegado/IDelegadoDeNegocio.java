package co.edu.usbcali.demo.delegado;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

/**
 * The Interface IDelegadoDeNegocio.
 */
public interface IDelegadoDeNegocio {

    /**
     * Grabar cliente.
     *
     * @param clientes the clientes
     * @throws Exception the exception
     */
    public void grabarCliente(Clientes clientes)
    throws Exception;

    /**
     * Modificar cliente.
     *
     * @param clientes the clientes
     * @throws Exception the exception
     */
    public void modificarCliente(Clientes clientes)
    throws Exception;

    /**
     * Borrar cliente.
     *
     * @param clientes the clientes
     * @throws Exception the exception
     */
    public void borrarCliente(Clientes clientes)
    throws Exception;

    /**
     * Consultar cliente cliente por id.
     *
     * @param cliId the cli id
     * @return the clientes
     * @throws Exception the exception
     */
    public Clientes consultarClienteClientePorId(long cliId)
    throws Exception;

    /**
     * Consultar todos cliente.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Clientes> consultarTodosCliente()
    throws Exception;

    /**
     * Grabar tipos documentos.
     *
     * @param tiposDocumentos the tipos documentos
     * @throws Exception the exception
     */
    public void grabarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception;

    /**
     * Modificar tipos documentos.
     *
     * @param tiposDocumentos the tipos documentos
     * @throws Exception the exception
     */
    public void modificarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception;

    /**
     * Borrar tipos documentos.
     *
     * @param tiposDocumentos the tipos documentos
     * @throws Exception the exception
     */
    public void borrarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception;

    /**
     * Consultar tipo documento por id tipos documentos.
     *
     * @param cliId the cli id
     * @return the tipos documentos
     * @throws Exception the exception
     */
    public TiposDocumentos consultarTipoDocumentoPorIdTiposDocumentos(long cliId)
    throws Exception;

    /**
     * Consultar todos tipos documentos.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<TiposDocumentos> consultarTodosTiposDocumentos()
    throws Exception;
    
    /**
     * Grabar usuario.
     *
     * @param usuario the usuario
     * @throws Exception the exception
     */
    public void grabarUsuario(Usuarios usuario)
    throws Exception;

    /**
     * Modificar usuario.
     *
     * @param usuario the usuario
     * @throws Exception the exception
     */
    public void modificarUsuario(Usuarios usuario)
    throws Exception;

    /**
     * Borrar usuario.
     *
     * @param usuario the usuario
     * @throws Exception the exception
     */
    public void borrarUsuario(Usuarios usuario)
    throws Exception;

    /**
     * Consultar usuario por id usuario.
     *
     * @param id the id
     * @return the usuarios
     * @throws Exception the exception
     */
    public Usuarios consultarUsuarioPorIdUsuario(long id)
    throws Exception;

    /**
     * Consultar todos usuario.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Usuarios> consultarTodosUsuario()
    throws Exception;

    /**
     * Grabar tipos usuarios.
     *
     * @param tipoUsuarios the tipo usuarios
     * @throws Exception the exception
     */
    public void grabarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception;

    /**
     * Modificar tipos usuarios.
     *
     * @param tipoUsuarios the tipo usuarios
     * @throws Exception the exception
     */
    public void modificarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception;

    /**
     * Borrar tipos usuarios.
     *
     * @param tipoUsuarios the tipo usuarios
     * @throws Exception the exception
     */
    public void borrarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception;

    /**
     * Consultar tipo usuarios por id tipos usuarios.
     *
     * @param id the id
     * @return the tipos usuarios
     * @throws Exception the exception
     */
    public TiposUsuarios consultarTipoUsuariosPorIdTiposUsuarios(long id)
    throws Exception;

    /**
     * Consultar todos tipos usuarios.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<TiposUsuarios> consultarTodosTiposUsuarios()
    throws Exception;
    
    /**
     * Grabar.
     *
     * @param cuenta the cuenta
     * @throws Exception the exception
     */
    public void grabarCuenta(Cuentas cuenta)
    throws Exception;
    
     /**
     * Modificar.
     *
     * @param cuenta the cuenta
     * @throws Exception the exception
     */
     public void modificarCuenta(Cuentas cuenta)
     throws Exception;
    
    /**
     * Borrar.
     *
     * @param cuenta the cuenta
     * @throws Exception the exception
     */
    public void borrarCuenta(Cuentas cuenta)
    throws Exception;
    
    /**
     * Consultar cuenta por id.
     *
     * @param numero the numero
     * @return the cuentas
     * @throws Exception the exception
     */
    public Cuentas consultarCuentaPorNumeroCuenta(String numero)
    throws Exception;
    
    /**
     * Consultar todos.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<Cuentas> consultarTodosCuenta()
    throws Exception;
    
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
