package co.edu.usbcali.demo.delegado;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ICuentaLogica;
import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.ITransaccionLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio
{
    @Autowired
    private IClienteLogica clienteLogica;

    @Autowired
    private ITipoDocumentoLogica tipoDocumentoLogica;

    @Autowired
    private IUsuarioLogica usuarioLogica;

    @Autowired
    private ITiposUsuariosLogica tipoUsuarioLogica;

    @Autowired
    private ICuentaLogica cuentaLogica;

    @Autowired
    private ITransaccionLogica transaccionLogica;

    @Override
    public void grabarCliente(Clientes clientes)
    throws Exception
    {
        clienteLogica.grabar(clientes);
    }

    @Override
    public void modificarCliente(Clientes clientes)
    throws Exception
    {
        clienteLogica.modificar(clientes);
    }

    @Override
    public void borrarCliente(Clientes clientes)
    throws Exception
    {
        clienteLogica.borrar(clientes);
    }

    @Override
    public Clientes consultarClienteClientePorId(long cliId)
    throws Exception
    {
        return clienteLogica.consultarClientePorId(cliId);
    }

    @Override
    public List<Clientes> consultarTodosCliente()
    throws Exception
    {
        return clienteLogica.consultarTodos();
    }

    @Override
    public void grabarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception
    {
        tipoDocumentoLogica.grabar(tiposDocumentos);
    }

    @Override
    public void modificarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception
    {
        tipoDocumentoLogica.modificar(tiposDocumentos);
    }

    @Override
    public void borrarTiposDocumentos(TiposDocumentos tiposDocumentos)
    throws Exception
    {
        tipoDocumentoLogica.borrar(tiposDocumentos);
    }

    @Override
    public TiposDocumentos consultarTipoDocumentoPorIdTiposDocumentos(long cliId)
    throws Exception
    {
        return tipoDocumentoLogica.consultarTipoDocumentoPorId(cliId);
    }

    @Override
    public List<TiposDocumentos> consultarTodosTiposDocumentos()
    throws Exception
    {
        return tipoDocumentoLogica.consultarTodos();
    }

    @Override
    public void grabarUsuario(Usuarios usuario)
    throws Exception
    {
        usuarioLogica.grabar(usuario);
    }

    @Override
    public void modificarUsuario(Usuarios usuario)
    throws Exception
    {
        usuarioLogica.modificar(usuario);
    }

    @Override
    public void borrarUsuario(Usuarios usuario)
    throws Exception
    {
        usuarioLogica.borrar(usuario);
    }

    @Override
    public Usuarios consultarUsuarioPorIdUsuario(long id)
    throws Exception
    {
        return usuarioLogica.consultarUsuarioPorId(id);
    }

    @Override
    public List<Usuarios> consultarTodosUsuario()
    throws Exception
    {
        return usuarioLogica.consultarTodos();
    }

    @Override
    public void grabarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception
    {
        tipoUsuarioLogica.grabar(tipoUsuarios);
    }

    @Override
    public void modificarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception
    {
        tipoUsuarioLogica.modificar(tipoUsuarios);
    }

    @Override
    public void borrarTiposUsuarios(TiposUsuarios tipoUsuarios)
    throws Exception
    {
        tipoUsuarioLogica.borrar(tipoUsuarios);
    }

    @Override
    public TiposUsuarios consultarTipoUsuariosPorIdTiposUsuarios(long id)
    throws Exception
    {
        return tipoUsuarioLogica.consultarTipoUsuariosPorId(id);
    }

    @Override
    public List<TiposUsuarios> consultarTodosTiposUsuarios()
    throws Exception
    {
        return tipoUsuarioLogica.consultarTodos();
    }

    @Override
    public void grabarCuenta(Cuentas cuenta) throws Exception
    {
        cuentaLogica.grabar(cuenta);
    }

    @Override
    public void modificarCuenta(Cuentas cuenta) throws Exception
    {
        cuentaLogica.modificar(cuenta);
    }

    @Override
    public void borrarCuenta(Cuentas cuenta) throws Exception
    {
        cuentaLogica.borrar(cuenta);
    }

    @Override
    public Cuentas consultarCuentaPorNumeroCuenta(String numero) throws Exception
    {
        return cuentaLogica.consultarCuentaPorNumero(numero);
    }

    @Override
    public List<Cuentas> consultarTodosCuenta() throws Exception
    {
        return cuentaLogica.consultarTodos();
    }

    @Override
    public void retirar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor) throws Exception
    {
        transaccionLogica.retirar(numeroCuenta, cedulaUsuario, valor);
    }

    @Override
    public void consigar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor) throws Exception
    {
        transaccionLogica.consigar(numeroCuenta, cedulaUsuario, valor);
    }
}
