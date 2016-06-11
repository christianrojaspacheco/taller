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

import co.edu.usbcali.demo.logica.ICuentaLogica;
import co.edu.usbcali.demo.logica.IRetiroLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.Usuarios;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class RetiroLogicaTest {

	private static final Logger log=LoggerFactory.getLogger(RetiroLogicaTest.class);
	
	@Autowired
	private IRetiroLogica retiroLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	private Long id=142020L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		Retiros retiro = new Retiros();
		retiro.setRetCodigo(id);
		retiro.setRetFecha(new Date());
		retiro.setRetDescripcion("retiro test");
		retiro.setRetValor(new BigDecimal(33222));
		retiro.setCueNumero("4008-5305-0010");
		
		Usuarios usuario = usuarioLogica.consultarUsuarioPorId(10L);
		retiro.setUsuarios(usuario);
		
		Cuentas cuenta = cuentaLogica.consultarCuentaPorNumero("4008-5305-0020");
		retiro.setCuentas(cuenta);
		
		retiroLogica.grabar(retiro);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		Retiros retiros =retiroLogica.consultarRetiroPorId(id);
		assertNotNull("El retiro no exsiste",retiros);
		log.info(retiros.getCueNumero());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		Retiros retiros =retiroLogica.consultarRetiroPorId(id);
		assertNotNull("El retiro no exsiste",retiros);
		retiros.setRetDescripcion("modificado");
		retiroLogica.modificar(retiros);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		Retiros retiros =retiroLogica.consultarRetiroPorId(id);
		assertNotNull("El retiro no exsiste",retiros);
		
		retiroLogica.borrar(retiros);
	}

	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<Retiros> losDatos =retiroLogica.consultarTodos();
		for (Retiros dato : losDatos) {
			log.info(dato.getRetDescripcion());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void ftest()
    throws Exception
    {
        Long id = retiroLogica.obtenerConsecutivoId();
        log.info("Nuevo identificador retiro: " + id);
        assertNotNull("No se genero id", id);
        
	}
	
}
