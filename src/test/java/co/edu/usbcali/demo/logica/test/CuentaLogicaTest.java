package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.IConsignacionLogica;
import co.edu.usbcali.demo.logica.ICuentaLogica;
import co.edu.usbcali.demo.logica.IRetiroLogica;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CuentaLogicaTest {

	private static final Logger log=LoggerFactory.getLogger(CuentaLogicaTest.class);

	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IRetiroLogica retirosLogica;

	@Autowired
	private IConsignacionLogica consignacionesLogica;
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	private String cueNumero = "1232";
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		
		Cuentas cuenta = new Cuentas();

		cuenta.setCueNumero("1232");
		cuenta.setCueActiva("S");
		cuenta.setCueClave("clave123");
		cuenta.setCueSaldo(new BigDecimal(10000));
		
		cuenta.setClientes(clienteLogica.consultarClientePorId(101234L));
		
		Consignaciones consigna = consignacionesLogica.consultarConsignacionesPorId(1L);
		Set<Consignaciones> consignacioneses = new HashSet<Consignaciones>();
		consignacioneses.add(consigna);
		cuenta.setConsignacioneses(consignacioneses);
		
		Retiros retiro = retirosLogica.consultarRetiroPorId(1L);
		Set<Retiros> retiroses = new HashSet<Retiros>();
		retiroses.add(retiro);
		
		cuenta.setRetiroses(retiroses);
		
		cuentaLogica.grabar(cuenta );
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		Cuentas cuenta= cuentaLogica.consultarCuentaPorNumero(cueNumero);
		assertNotNull("La cuenta no existe",cuenta);
		log.info(cuenta.getCueNumero());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		Cuentas cuenta= cuentaLogica.consultarCuentaPorNumero(cueNumero);
		assertNotNull("La cuenta no existe",cuenta);
		cuenta.setCueClave("44452");
		cuentaLogica.modificar(cuenta);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		Cuentas cuenta= cuentaLogica.consultarCuentaPorNumero(cueNumero);
		assertNotNull("La cuenta no existe", cuenta);
		
		cuentaLogica.borrar(cuenta);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<Cuentas> losDatos=cuentaLogica.consultarTodos();
		for (Cuentas dato : losDatos) {
			log.info(dato.getCueNumero());
		}
	}
}
