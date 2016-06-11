package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.Usuarios;


@Service
@Scope("singleton")
public class TransaccionLogica implements ITransaccionLogica
{
    @Autowired
    private IRetiroLogica retiroLogica;

    @Autowired
    private IConsignacionLogica consigacionLogica;

    @Autowired
    private IUsuarioLogica usuarioLogica;

    @Autowired
    private ICuentaLogica cuentaLogica;
    
    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void retirar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor)
    throws Exception
    {
        // Cuenta activa ?
        Cuentas cuenta = cuentaLogica.consultarCuentaPorNumero(numeroCuenta);
        if (cuenta == null || cuenta.getCueActiva().equals("S"))
        {
            throw new Exception("La cuenta no existe o se encuentra inactiva.");
        }

        // Validar que tiene dinero en la cuenta suficiente
        int res = cuenta.getCueSaldo().compareTo(valor);
        if (res == -1)
        {
            throw new Exception("No tiene saldo disponible para realizar el valor del retiro.");
        }
   
        Retiros retiro = new Retiros();
        retiro.setRetCodigo(retiroLogica.obtenerConsecutivoId());
        retiro.setRetDescripcion("Nuevo Retiro");
        retiro.setRetFecha(new Date());
        retiro.setRetValor(valor);
        retiro.setCueNumero(numeroCuenta);

        Usuarios usuarios = usuarioLogica.consultarUsuarioPorId(cedulaUsuario);
        retiro.setUsuarios(usuarios);

        retiroLogica.grabar(retiro );
        
        // Afectar la cuenta
        BigDecimal nuevoSaldo = cuenta.getCueSaldo().subtract(valor);
        cuenta.setCueSaldo(nuevoSaldo);
        cuentaLogica.modificar(cuenta);
    }

    @Override
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public void consigar(String numeroCuenta, Long cedulaUsuario, BigDecimal valor)
    throws Exception
    {
        // Cuenta activa ?
        Cuentas cuenta = cuentaLogica.consultarCuentaPorNumero(numeroCuenta);
        if (cuenta == null || cuenta.getCueActiva().equals("S"))
        {
            throw new Exception("La cuenta no existe o se encuentra inactiva.");
        }

        Consignaciones consignacion = new Consignaciones();
        consignacion.setConCodigo(consigacionLogica.obtenerConsecutivoId());
        consignacion.setConDescripcion("Nueva Consignacion");
        consignacion.setConFecha(new Date());
        consignacion.setConValor(valor);
        consignacion.setCueNumero(numeroCuenta);

        Usuarios usuarios = usuarioLogica.consultarUsuarioPorId(cedulaUsuario);
        consignacion.setUsuarios(usuarios);

        consigacionLogica.grabar(consignacion);

        // Afectar la cuenta
        BigDecimal nuevoSaldo = cuenta.getCueSaldo().add(valor);
        cuenta.setCueSaldo(nuevoSaldo);
        cuentaLogica.modificar(cuenta);
    }
}
