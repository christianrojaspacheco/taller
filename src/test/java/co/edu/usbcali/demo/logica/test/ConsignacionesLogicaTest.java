package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
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

import co.edu.usbcali.demo.logica.IConsignacionLogica;
import co.edu.usbcali.demo.logica.ICuentaLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ConsignacionesLogicaTest {

	private static final Logger log=LoggerFactory.getLogger(RetiroLogicaTest.class);
	
	@Autowired
	private IConsignacionLogica consignacionLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	

	private Long id=20L;
	

	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		Consignaciones consignacion = new Consignaciones();
		
		consignacion.setConCodigo(id);
		consignacion.setConFecha(new Date());
		consignacion.setConDescripcion("Test");
		consignacion.setConValor(new BigDecimal(10000));
		
		Cuentas cuentas = cuentaLogica.consultarCuentaPorNumero("4008-5305-0015");
		consignacion.setCuentas(cuentas);
		consignacion.setCueNumero("4008-5305-0015");
		
		Usuarios usuarios = usuarioLogica.consultarUsuarioPorId(10L);
		consignacion.setUsuarios(usuarios);
		
		consignacionLogica.grabar(consignacion);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		Consignaciones consignacion =consignacionLogica.consultarConsignacionesPorId(id);
		assertNotNull("La consignacion no exsiste",consignacion);
		log.info(consignacion.getConDescripcion());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		Consignaciones consignacion =consignacionLogica.consultarConsignacionesPorId(id);
		assertNotNull("La consignacion no exsiste",consignacion);
		consignacion.setConDescripcion("modificada");
		consignacionLogica.modificar(consignacion);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		Consignaciones consignacion =consignacionLogica.consultarConsignacionesPorId(id);
		assertNotNull("La consignacion no exsiste",consignacion);
		
		consignacionLogica.borrar(consignacion);
	}

	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<Consignaciones> losDatos =consignacionLogica.consultarTodos();
		for (Consignaciones dato : losDatos) {
			log.info(dato.getConDescripcion());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void ftest()
    throws Exception
    {
        Long id = consignacionLogica.obtenerConsecutivoId();
        log.info("Nuevo identificador consigacion: " + id);
        assertNotNull("No se genero id", id);
        
	}
}
