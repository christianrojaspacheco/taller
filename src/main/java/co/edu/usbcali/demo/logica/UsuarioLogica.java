package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Usuarios;


@Service
@Scope("singleton")
public class UsuarioLogica implements IUsuarioLogica {

	@Autowired 
	private IUsuarioDAO usuarioDAO;
	
	@Autowired 
	private Validator validator;
	
	static final Logger log=LoggerFactory.getLogger(TipoUsuarioLogica.class);
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(Usuarios usuario) throws Exception {
		hacerValidaciones(usuario);
		usuarioDAO.grabar(usuario);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Usuarios usuario) throws Exception {
		hacerValidaciones(usuario);
		usuarioDAO.modificar(usuario);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Usuarios usuario) throws Exception {
		hacerValidaciones(usuario);
		usuarioDAO.borrar(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarUsuarioPorId(long id) throws Exception {
		if(id <= 0){
			throw new Exception("El id no existe");
		}
		return usuarioDAO.consultarUsuarioPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultarTodos() throws Exception {
		return usuarioDAO.consultarTodos();
	}
	
	private void hacerValidaciones(Usuarios usuario) throws Exception {
		Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(usuario);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<Usuarios> oViolation : constraintViolations ) { 
				 
				 log.info(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
				 sb.append(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
			 }	 
			 
			 throw new Exception(sb.toString());
		 }
	}

}
