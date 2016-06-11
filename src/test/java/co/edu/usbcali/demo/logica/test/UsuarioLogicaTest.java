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

import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UsuarioLogicaTest {

	private static final Logger log=LoggerFactory.getLogger(RetiroLogicaTest.class);
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITiposUsuariosLogica tipoUsuarioLogica;
	
	private Long id=67L;
	
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void atest()throws Exception {
		Usuarios usuario = new Usuarios();
		
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarTipoUsuariosPorId(10L);
		usuario.setTiposUsuarios(tiposUsuarios);
		usuario.setUsuCedula(id);
		usuario.setUsuClave("12334");
		usuario.setUsuLogin("test");
		usuario.setUsuNombre("Usuario test");
		
		usuarioLogica.grabar(usuario);
		
	}
	
	@Test
	@Transactional(readOnly=true)
	public void btest()throws Exception {
		Usuarios usuario = usuarioLogica.consultarUsuarioPorId(id);
		assertNotNull("El usuario no exsiste",usuario);
		log.info(usuario.getUsuNombre());
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void ctest()throws Exception {
		Usuarios usuario = usuarioLogica.consultarUsuarioPorId(id);
		assertNotNull("El usuario no exsiste",usuario);
		usuario.setUsuNombre("SAM");
		usuarioLogica.modificar(usuario);
	}
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Rollback(false)
	public void dtest()throws Exception {
		Usuarios usuario = usuarioLogica.consultarUsuarioPorId(id);
		assertNotNull("El usuario no exsiste",usuario);
		
		usuarioLogica.borrar(usuario);
	}

	@Test
	@Transactional(readOnly=true)
	public void etest()throws Exception {
		List<Usuarios> losDatos =usuarioLogica.consultarTodos();
		for (Usuarios dato : losDatos) {
			log.info(dato.getUsuNombre());
		}
	}

}
