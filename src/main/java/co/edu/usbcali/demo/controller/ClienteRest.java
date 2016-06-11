package co.edu.usbcali.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.delegado.IDelegadoDeNegocio;
import co.edu.usbcali.demo.dto.ClienteDTO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RestController
@RequestMapping("/cliente")
public class ClienteRest
{

	@Autowired
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	@RequestMapping(value="/consultarClienteClientePorId/{cliId}", method=RequestMethod.GET)
	public ClienteDTO consultarClientePorId(@PathVariable("cliId")long cliId)
	throws Exception
	{
		Clientes clientes = delegadoDeNegocio.consultarClienteClientePorId(cliId);
		if (clientes == null)
		{
			throw new Exception("El cliente no existe");
		}
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCliDireccion(clientes.getCliDireccion());
		clienteDTO.setCliId(clientes.getCliId());
		clienteDTO.setCliMail(clientes.getCliMail());
		clienteDTO.setCliNombre(clientes.getCliNombre());
		clienteDTO.setCliTelefono(clientes.getCliTelefono());
		clienteDTO.setTdocCodigo(clientes.getTiposDocumentos().getTdocCodigo());
		
		return clienteDTO;
	}
	

	@RequestMapping(value="/consultarTodosCliente", method=RequestMethod.GET)
	public List<ClienteDTO> consultarTodos()
    throws Exception
	{
		List<Clientes> clientes = delegadoDeNegocio.consultarTodosCliente();
		if (clientes == null)
		{
			throw new Exception("No existe datos de clientes.");
		}
	
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		for(Clientes cliente : clientes)
		{
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCliDireccion(cliente.getCliDireccion());
			clienteDTO.setCliId(cliente.getCliId());
			clienteDTO.setCliMail(cliente.getCliMail());
			clienteDTO.setCliNombre(cliente.getCliNombre());
			clienteDTO.setCliTelefono(cliente.getCliTelefono());
			clienteDTO.setTdocCodigo(cliente.getTiposDocumentos().getTdocCodigo());
			clientesDTO.add(clienteDTO);
		}
		
		return clientesDTO;	
	}
	
	@RequestMapping(value="/grabar", method=RequestMethod.POST)
    public void grabar(@RequestBody ClienteDTO clienteDTO)
	throws Exception 
	{
	    Clientes cliente = new Clientes();
	    cliente.setCliDireccion(clienteDTO.getCliDireccion());
	    cliente.setCliId(clienteDTO.getCliId());
	    cliente.setCliMail(clienteDTO.getCliMail());
	    cliente.setCliNombre(clienteDTO.getCliNombre());
	    cliente.setCliTelefono(clienteDTO.getCliTelefono());

	    TiposDocumentos tiposDocumentos =
	        delegadoDeNegocio.consultarTipoDocumentoPorIdTiposDocumentos(clienteDTO.getTdocCodigo());
	    cliente.setTiposDocumentos(tiposDocumentos);

	    delegadoDeNegocio.grabarCliente(cliente);
	}
	
    @RequestMapping(value="/modificar", method=RequestMethod.PUT)
    public void modificar(@RequestBody ClienteDTO clienteDTO)
    throws Exception
    {
        Clientes cliente = delegadoDeNegocio.consultarClienteClientePorId(clienteDTO.getCliId());

        cliente.setCliDireccion(clienteDTO.getCliDireccion());
        cliente.setCliMail(clienteDTO.getCliMail());
        cliente.setCliNombre(clienteDTO.getCliNombre());
        cliente.setCliTelefono(clienteDTO.getCliTelefono());

        TiposDocumentos tiposDocumentos =
                delegadoDeNegocio.consultarTipoDocumentoPorIdTiposDocumentos(clienteDTO.getTdocCodigo());
            cliente.setTiposDocumentos(tiposDocumentos);

        delegadoDeNegocio.modificarCliente(cliente);
    }

    @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE)
    public void borrar(@PathVariable("id") long cliId)
    throws Exception
    {
        Clientes cliente = delegadoDeNegocio.consultarClienteClientePorId(cliId);

        delegadoDeNegocio.borrarCliente(cliente);
    }
}
