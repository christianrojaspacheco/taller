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

import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TiposDocumentosLogicaTest {

	private static final Logger log=LoggerFactory.getLogger(RetiroLogicaTest.class);
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	private Long id=50L;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		TiposDocumentos tipo = new TiposDocumentos();
		tipo.setTdocCodigo(id);
		tipo.setTdocNombre("LEGAL");
		tipoDocumentoLogica.grabar(tipo);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		TiposDocumentos tipo  =tipoDocumentoLogica.consultarTipoDocumentoPorId(id);
		assertNotNull("El tipo no exsiste",tipo);
		log.info(tipo.getTdocNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		TiposDocumentos tipo  =tipoDocumentoLogica.consultarTipoDocumentoPorId(id);
		assertNotNull("El tipo no exsiste",tipo);
		tipo.setTdocNombre("modificado");
		tipoDocumentoLogica.modificar(tipo);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		TiposDocumentos tipo  =tipoDocumentoLogica.consultarTipoDocumentoPorId(id);
		assertNotNull("El tipo no exsiste",tipo);
		
		tipoDocumentoLogica.borrar(tipo);
	}

	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<TiposDocumentos> losDatos = tipoDocumentoLogica.consultarTodos();
		for (TiposDocumentos dato : losDatos) {
			log.info(dato.getTdocNombre());
		}
	}
	
}
