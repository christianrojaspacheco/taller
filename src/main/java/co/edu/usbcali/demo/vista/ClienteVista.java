package co.edu.usbcali.demo.vista;

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
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@ViewScoped
@ManagedBean
public class ClienteVista
{
    private final static Logger LOG = LoggerFactory.getLogger(ClienteVista.class);

    @ManagedProperty(value="#{delegadoDeNegocio}")
    private IDelegadoDeNegocio delegadoDeNegocio;

    private List<Clientes> losClentes;
    private List<SelectItem> losTiposDocumentoItem;
    private InputText txtIdentificacion;
    private InputText txtNombre;
    private InputText txtDireccion;
    private InputText txtTelefono;
    private InputText txtEmail;
    private SelectOneMenu secTipoDocumento;
    private CommandButton btnCrear;
    private CommandButton btnModificar;
    private CommandButton btnBorrar;
    private CommandButton btnLimpiar;

    public String crearAction()
    {
        LOG.info("Ingreso a crear");

        Clientes clientes = new Clientes();
        clientes.setCliDireccion(txtDireccion.getValue().toString().trim());
        clientes.setCliId(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
        clientes.setCliMail(txtEmail.getValue().toString().trim());
        clientes.setCliNombre(txtNombre.getValue().toString().trim());
        clientes.setCliTelefono(txtTelefono.getValue().toString().trim());

        TiposDocumentos tipoDocumento;
        try
        {
            tipoDocumento =
                delegadoDeNegocio.consultarTipoDocumentoPorIdTiposDocumentos(
                    Long.parseLong(secTipoDocumento.getValue().toString()));

            clientes.setTiposDocumentos(tipoDocumento);

            delegadoDeNegocio.grabarCliente(clientes);

            String message = "El cliente se creo correctamente!";
            FacesContext.getCurrentInstance().addMessage(
                "",
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(
                "", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        return "";
	}

    public String modificarAction()
    {
        LOG.info("Ingreso a modificar");

        Clientes clientes = new Clientes();
        clientes.setCliDireccion(txtDireccion.getValue().toString().trim());
        clientes.setCliId(Long.parseLong(txtIdentificacion.getValue().toString().trim()));
        clientes.setCliMail(txtEmail.getValue().toString().trim());
        clientes.setCliNombre(txtNombre.getValue().toString().trim());
        clientes.setCliTelefono(txtTelefono.getValue().toString().trim());

        TiposDocumentos tipoDocumento;
        try
        {
            tipoDocumento =
                delegadoDeNegocio.consultarTipoDocumentoPorIdTiposDocumentos(
                    Long.parseLong(secTipoDocumento.getValue().toString()));

            clientes.setTiposDocumentos(tipoDocumento);

            delegadoDeNegocio.modificarCliente(clientes);

            String message = "El cliente se modifico correctamente!";
            FacesContext.getCurrentInstance().addMessage(
                "",
                new FacesMessage(
                    FacesMessage.SEVERITY_INFO, message, ""));
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(
                "", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }

        return "";
    }

    public String borrarAction()
    {
        LOG.info("Ingreso a borrar");

        Clientes clientes = new Clientes();

        try
        {
            clientes =
            delegadoDeNegocio.consultarClienteClientePorId(
                Long.parseLong(txtIdentificacion.getValue().toString().trim()));

            delegadoDeNegocio.borrarCliente(clientes);

            String message = "El cliente se elimino correctamente!";
            FacesContext.getCurrentInstance().addMessage(
                "", 
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        	FacesContext.getCurrentInstance().addMessage(
                "",
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
        return "";
    }

    public String limpiarAction()
    {
        LOG.info("Ingreso a limpiar");
        txtIdentificacion.resetValue();
        txtDireccion.resetValue();
        txtEmail.resetValue();
        txtNombre.resetValue();
        txtTelefono.resetValue();
        secTipoDocumento.setValue("-1");

        btnBorrar.setDisabled(true);
        btnCrear.setDisabled(false);
        btnModificar.setDisabled(true);

        return "";
    }

    public void txtIdentificacionListener()
    {
        Clientes entity = null;

        try
        {
            Long id = Long.parseLong(txtIdentificacion.getValue().toString().trim());
            entity = delegadoDeNegocio.consultarClienteClientePorId(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (entity == null)
        {
            txtDireccion.resetValue();
            txtEmail.resetValue();
            txtNombre.resetValue();
            txtTelefono.resetValue();
            secTipoDocumento.setValue("-1");

            btnBorrar.setDisabled(true);
            btnCrear.setDisabled(false);
            btnModificar.setDisabled(true);
        }
        else
        {
            txtDireccion.setValue(entity.getCliDireccion());
            txtEmail.setValue(entity.getCliMail());
            txtNombre.setValue(entity.getCliNombre());
            txtTelefono.setValue(entity.getCliTelefono());

            secTipoDocumento.setValue(entity.getTiposDocumentos().getTdocCodigo());
            btnBorrar.setDisabled(false);
            btnCrear.setDisabled(true);
            btnModificar.setDisabled(false);
        }
    }

    public List<Clientes> getLosClentes()
    {
        if (losClentes == null)
        {
            try
            {
                losClentes = delegadoDeNegocio.consultarTodosCliente();
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage(), e);
            }
        }
        return losClentes;
    }

    public void setLosClentes(List<Clientes> losClentes)
    {
        this.losClentes = losClentes;
    }

    public IDelegadoDeNegocio getDelegadoDeNegocio()
    {
        return delegadoDeNegocio;
    }

    public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio)
    {
        this.delegadoDeNegocio = delegadoDeNegocio;
    }

    public List<SelectItem> getLosTiposDocumentoItem()
    {
        try
        {
            if (losTiposDocumentoItem == null)
            {
                losTiposDocumentoItem = new ArrayList<SelectItem>();
                List<TiposDocumentos> losEntity = delegadoDeNegocio.consultarTodosTiposDocumentos();

                for (TiposDocumentos tipoDocumentos : losEntity)
                {
                    losTiposDocumentoItem.add(
                        new SelectItem(
                            tipoDocumentos.getTdocCodigo(), tipoDocumentos.getTdocNombre()));
                }
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        }
        return losTiposDocumentoItem;
    }

    public void setLosTiposDocumentoItem(List<SelectItem> losTiposDocumentoItem)
    {
        this.losTiposDocumentoItem = losTiposDocumentoItem;
    }

    public InputText getTxtIdentificacion()
    {
        return txtIdentificacion;
    }

    public void setTxtIdentificacion(InputText txtIdentificacion)
    {
        this.txtIdentificacion = txtIdentificacion;
    }

    public InputText getTxtNombre()
    {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre)
    {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtDireccion()
    {
        return txtDireccion;
    }

    public void setTxtDireccion(InputText txtDireccion)
    {
        this.txtDireccion = txtDireccion;
    }

    public InputText getTxtTelefono()
    {
        return txtTelefono;
    }

    public void setTxtTelefono(InputText txtTelefono)
    {
        this.txtTelefono = txtTelefono;
    }

    public InputText getTxtEmail()
    {
        return txtEmail;
    }

    public void setTxtEmail(InputText txtEmail)
    {
    this.txtEmail = txtEmail;
    }

    public SelectOneMenu getSecTipoDocumento()
    {
    return secTipoDocumento;
    }

    public void setSecTipoDocumento(SelectOneMenu secTipoDocumento)
    {
        this.secTipoDocumento = secTipoDocumento;
    }

    public CommandButton getBtnCrear()
    {
        return btnCrear;
    }

    public void setBtnCrear(CommandButton btnCrear)
    {
        this.btnCrear = btnCrear;
    }

    public CommandButton getBtnModificar()
    {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar)
    {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnBorrar()
    {
        return btnBorrar;
    }

    public void setBtnBorrar(CommandButton btnBorrar)
    {
        this.btnBorrar = btnBorrar;
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
