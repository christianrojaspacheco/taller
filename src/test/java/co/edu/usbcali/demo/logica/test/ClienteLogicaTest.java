package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ClienteLogicaTest {
	
	private static final Logger log=LoggerFactory.getLogger(ClienteLogicaTest.class);
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	
	private Long cliId=142021L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		Clientes clientes=new Clientes();
		clientes.setCliId(cliId);
		clientes.setCliDireccion("Avenida Siempre Viva 123");
		clientes.setCliMail("test@gmail.com");
		clientes.setCliNombre("martha");
		clientes.setCliTelefono("5555555");
		
		TiposDocumentos tiposDocumentos=tipoDocumentoDAO.consultarTipoDocumentoPorId(10L);
		
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clienteLogica.grabar(clientes);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		Clientes clientes=clienteLogica.consultarClientePorId(cliId);
		assertNotNull("El cliente no exsiste",clientes);
		log.info(clientes.getCliNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		Clientes clientes=clienteLogica.consultarClientePorId(cliId);
		assertNotNull("El cliente no exsiste",clientes);
		clientes.setCliNombre("Juan Perez");
		
		clienteLogica.modificar(clientes);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		Clientes clientes=clienteLogica.consultarClientePorId(cliId);
		assertNotNull("El cliente no exsiste",clientes);
		
		clienteLogica.borrar(clientes);
	}

	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<Clientes> losClientes=clienteLogica.consultarTodos();
		for (Clientes clientes : losClientes) {
			log.info(clientes.getCliNombre());
		}
	}
	
	

}
