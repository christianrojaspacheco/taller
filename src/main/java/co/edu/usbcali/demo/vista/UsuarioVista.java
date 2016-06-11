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
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ViewScoped
@ManagedBean
public class UsuarioVista
{
    private final static Logger LOG = LoggerFactory.getLogger(UsuarioVista.class);

    @ManagedProperty(value="#{delegadoDeNegocio}")
    private IDelegadoDeNegocio delegadoDeNegocio;

    private List<Usuarios> losUsuarios;
    private List<SelectItem>losTiposUsuarioItem;
    private InputText txtCedula;
    private InputText txtNombre;
    private InputText txtLogin;
    private InputText txtClave;
    private SelectOneMenu secTipoUsuario;
    private CommandButton btnCrear;
    private CommandButton btnModificar;
    private CommandButton btnBorrar;
    private CommandButton btnLimpiar;

    public String crearAction()
    {
        LOG.info("Ingreso a crear");
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
        usuario.setUsuClave(txtClave.getValue().toString().trim());
        usuario.setUsuLogin(txtLogin.getValue().toString().trim());
        usuario.setUsuNombre(txtNombre.getValue().toString().trim());

        TiposUsuarios tiposUsuarios;

        try
        {
            tiposUsuarios =
                delegadoDeNegocio.consultarTipoUsuariosPorIdTiposUsuarios(
                    Long.parseLong(secTipoUsuario.getValue().toString()));

            usuario.setTiposUsuarios(tiposUsuarios);

            delegadoDeNegocio.grabarUsuario(usuario);

            String message = "El usuario se creo correctamente!";
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
        Usuarios usuario = new Usuarios();
        usuario.setUsuCedula(Long.parseLong(txtCedula.getValue().toString().trim()));
        usuario.setUsuClave(txtClave.getValue().toString().trim());
        usuario.setUsuLogin(txtLogin.getValue().toString().trim());
        usuario.setUsuNombre(txtNombre.getValue().toString().trim());

        TiposUsuarios tiposUsuarios;

        try
        {
            tiposUsuarios =
                delegadoDeNegocio.consultarTipoUsuariosPorIdTiposUsuarios(
                    Long.parseLong(secTipoUsuario.getValue().toString()));

            usuario.setTiposUsuarios(tiposUsuarios);

            delegadoDeNegocio.modificarUsuario(usuario);

            String message = "El usuario se modifico correctamente!";
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

    public String borrarAction()
    {
        LOG.info("Ingreso a borrar");

        Usuarios usuario = new Usuarios();

        try
        {
            usuario =
                delegadoDeNegocio.consultarUsuarioPorIdUsuario(
                    Long.parseLong(txtCedula.getValue().toString().trim()));

            delegadoDeNegocio.borrarUsuario(usuario);

            String message = "El usuario se elimino correctamente!";
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

    public String limpiarAction()
    {
        LOG.info("Ingreso a limpiar");
        txtCedula.resetValue();
        txtClave.resetValue();
        txtNombre.resetValue();
        txtLogin.resetValue();
        secTipoUsuario.setValue("-1");

        btnBorrar.setDisabled(true);
        btnCrear.setDisabled(false);
        btnModificar.setDisabled(true);
    	return "";
    }

    public void txtIdentificacionListener()
    {
        Usuarios entity = null;

        try
        {
            Long id = Long.parseLong(txtCedula.getValue().toString().trim());
            entity = delegadoDeNegocio.consultarUsuarioPorIdUsuario(id);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        }

        if (entity == null)
        {
            txtClave.resetValue();
            txtLogin.resetValue();
            txtNombre.resetValue();
            secTipoUsuario.setValue("-1");

            btnBorrar.setDisabled(true);
            btnCrear.setDisabled(false);
            btnModificar.setDisabled(true);
        }
        else
        {
            txtClave.setValue(entity.getUsuClave());
            txtLogin.setValue(entity.getUsuLogin());
            txtNombre.setValue(entity.getUsuNombre());

            secTipoUsuario.setValue(entity.getTiposUsuarios().getTusuCodigo());
            btnBorrar.setDisabled(false);
            btnCrear.setDisabled(true);
            btnModificar.setDisabled(false);
        }
    }

    public InputText getTxtCedula()
    {
        return txtCedula;
    }

    public void setTxtCedula(InputText txtCedula)
    {
        this.txtCedula = txtCedula;
    }

    public InputText getTxtNombre()
    {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre)
    {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtLogin()
    {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin)
    {
        this.txtLogin = txtLogin;
    }

    public InputText getTxtClave()
    {
        return txtClave;
    }

    public void setTxtClave(InputText txtClave)
    {
        this.txtClave = txtClave;
    }

    public SelectOneMenu getSecTipoUsuario()
    {
        return secTipoUsuario;
    }

    public void setSecTipoUsuario(SelectOneMenu secTipoUsuario)
    {
        this.secTipoUsuario = secTipoUsuario;
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

	public IDelegadoDeNegocio getDelegadoDeNegocio()
    {
        return delegadoDeNegocio;
    }

    public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio)
    {
        this.delegadoDeNegocio = delegadoDeNegocio;
    }

    public List<Usuarios> getLosUsuarios()
    {
        if (losUsuarios == null )
        {
            try
            {
                losUsuarios = delegadoDeNegocio.consultarTodosUsuario();
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage(), e);
            }
        }
        return losUsuarios;
    }

    public void setLosUsuarios(List<Usuarios> losUsuarios)
    {
        this.losUsuarios = losUsuarios;
    }

    public List<SelectItem> getLosTiposUsuarioItem()
    {
        try
        {
            if (losTiposUsuarioItem == null)
            {
            	losTiposUsuarioItem = new ArrayList<SelectItem>();
                List<TiposUsuarios> losEntity = delegadoDeNegocio.consultarTodosTiposUsuarios();
                
                for (TiposUsuarios tipoUsuario : losEntity)
                {
                	losTiposUsuarioItem.add(
                        new SelectItem(
                            tipoUsuario.getTusuCodigo(), tipoUsuario.getTusuNombre()));
                }
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(), e);
        }
        return losTiposUsuarioItem;
    }

    public void setLosTiposUsuarioItem(List<SelectItem> losTiposUsuarioItem)
    {
        this.losTiposUsuarioItem = losTiposUsuarioItem;
    }
}
