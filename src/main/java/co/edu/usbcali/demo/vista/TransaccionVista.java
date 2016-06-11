package co.edu.usbcali.demo.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class TransaccionVista
{
    private final static Logger LOG = LoggerFactory.getLogger(TransaccionVista.class);

    @ManagedProperty(value="#{delegadoDeNegocio}")
    private IDelegadoDeNegocio delegadoDeNegocio;
 
    private List<SelectItem> lostUsuariosItem;
    private InputText txtNumeroCuenta;
    private InputText txtCliente;
    private InputText txtValor;
    private SelectOneMenu secUsuario;
    private CommandButton btnConsignar;
    private CommandButton btnRetirar;
    private CommandButton btnLimpiar;

    public String consignarAction()
    {
        LOG.info("Ingreso a consignar");
        
        if (validarFormatoValor())
        {
            try
            {
                String numeroCuenta = txtNumeroCuenta.getValue().toString().trim();
                long cedulaUsuario = Long.parseLong(secUsuario.getValue().toString().trim());

                delegadoDeNegocio.consigar(
                    numeroCuenta,
                    cedulaUsuario,
                    new BigDecimal(txtValor.getValue().toString().trim()));

                Cuentas cuenta = delegadoDeNegocio.consultarCuentaPorNumeroCuenta(numeroCuenta);

                btnConsignar.setDisabled(true);
                btnRetirar.setDisabled(true);
                
                String message =
                    "Se realiza la consignación correctamente!. Su saldo actual es $" + cuenta.getCueSaldo();
                
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));

            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            }
        }
        
        return "";
    }

    public String retirarAction()
    {
        LOG.info("Ingreso a retirar");
        
        if (validarFormatoValor())
        {
            try
            {
                String numeroCuenta = txtNumeroCuenta.getValue().toString().trim();
                long cedulaUsuario = Long.parseLong(secUsuario.getValue().toString().trim());

                delegadoDeNegocio.retirar(
                    numeroCuenta,
                    cedulaUsuario,
                    new BigDecimal(txtValor.getValue().toString().trim()));

                Cuentas cuenta = delegadoDeNegocio.consultarCuentaPorNumeroCuenta(numeroCuenta);

                btnConsignar.setDisabled(true);
                btnRetirar.setDisabled(true);
                
                String message =
                    "Se realiza el retiro correctamente! Su saldo actual es $" + cuenta.getCueSaldo();
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));

            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            }
        }
        
        return "";
    }

    public String limpiarAction()
    {
        LOG.info("Ingreso a limpiar");

        txtNumeroCuenta.resetValue();
        txtCliente.resetValue();
        txtValor.resetValue();
        secUsuario.setValue("-1");
        
        txtNumeroCuenta.setDisabled(false);
        btnConsignar.setDisabled(true);
        btnRetirar.setDisabled(true);
        btnLimpiar.setDisabled(true);
        return "";
    }

    public void txtNumeroCuentaListener()
    {
        if (!txtNumeroCuenta.getValue().toString().trim().isEmpty())
        {
            Cuentas cuenta = null;
            
            try
            {
                String numeroCuenta = txtNumeroCuenta.getValue().toString().trim();
                cuenta = delegadoDeNegocio.consultarCuentaPorNumeroCuenta(numeroCuenta);
            
                if (cuenta == null)
                {
                    txtCliente.resetValue();
                    txtValor.resetValue();
                    secUsuario.setValue("-1");
                    
                    btnConsignar.setDisabled(true);
                    btnRetirar.setDisabled(true);
                    btnLimpiar.setDisabled(true);
    
                    String mensaje = "El número de cuenta no existe: " + numeroCuenta;
                    LOG.error(mensaje);
    
                    FacesContext.getCurrentInstance().addMessage(
                        "",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
                }
                else
                {
                    Clientes cliente = delegadoDeNegocio.consultarClienteClientePorId(cuenta.getClientes().getCliId());
                    txtCliente.setValue(cliente.getCliNombre());
    
                    txtNumeroCuenta.setDisabled(true);
                    btnConsignar.setDisabled(false);
                    btnRetirar.setDisabled(false);
                    btnLimpiar.setDisabled(false);
                }
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage(), e);
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            }
        }
    }
    
    public boolean validarFormatoValor()
    {
        boolean resultado = true;
        String valor = txtValor.getValue().toString().trim();
        
        try
        {
            Double monto = Double.parseDouble(valor);
            if (monto <= 0)
            {
                resultado = false;
                String mensaje = "El valor debe ser mayor a cero.";
                LOG.error(mensaje);
                FacesContext.getCurrentInstance().addMessage(
                    "",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
            }
        }
        catch (NumberFormatException nfex)
        {
            resultado = false;
            String mensaje = "El valor ingresado no es un valor numerico";
            LOG.error(mensaje, nfex);
            FacesContext.getCurrentInstance().addMessage(
                "",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, ""));
        }
        
        return resultado;
    }

    public IDelegadoDeNegocio getDelegadoDeNegocio()
    {
        return delegadoDeNegocio;
    }

    public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio)
    {
        this.delegadoDeNegocio = delegadoDeNegocio;
    }

    public List<SelectItem> getLostUsuariosItem()
    {
        try 
        {
            if (lostUsuariosItem == null)
            {
                lostUsuariosItem = new ArrayList<SelectItem>();
                List<Usuarios> losEntity = delegadoDeNegocio.consultarTodosUsuario();
                
                for (Usuarios usuario : losEntity)
                {
                    lostUsuariosItem.add(
                    new SelectItem(
                        usuario.getUsuCedula(), usuario.getUsuNombre()));
                }
           }
        }
        catch (Exception e)
        {
        	LOG.error(e.getMessage(), e);
        }
        return lostUsuariosItem;
    }

    public void setLostUsuariosItem(List<SelectItem> lostUsuariosItem)
    {
        this.lostUsuariosItem = lostUsuariosItem;
    }

    public InputText getTxtNumeroCuenta()
    {
        return txtNumeroCuenta;
    }

    public void setTxtNumeroCuenta(InputText txtNumeroCuenta)
    {
        this.txtNumeroCuenta = txtNumeroCuenta;
    }

    public InputText getTxtCliente()
    {
        return txtCliente;
    }

    public void setTxtCliente(InputText txtCliente)
    {
        this.txtCliente = txtCliente;
    }

    public InputText getTxtValor()
    {
        return txtValor;
    }

    public void setTxtValor(InputText txtValor)
    {
        this.txtValor = txtValor;
    }

    public SelectOneMenu getSecUsuario()
    {
        return secUsuario;
    }

    public void setSecUsuario(SelectOneMenu secUsuario)
    {
        this.secUsuario = secUsuario;
    }

    public CommandButton getBtnConsignar()
    {
        return btnConsignar;
    }

    public void setBtnConsignar(CommandButton btnConsignar)
    {
        this.btnConsignar = btnConsignar;
    }

    public CommandButton getBtnRetirar()
    {
        return btnRetirar;
    }

    public void setBtnRetirar(CommandButton btnRetirar)
    {
        this.btnRetirar = btnRetirar;
    }

    public CommandButton getBtnLimpiar()
    {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar)
    {
        this.btnLimpiar = btnLimpiar;
    }


}
