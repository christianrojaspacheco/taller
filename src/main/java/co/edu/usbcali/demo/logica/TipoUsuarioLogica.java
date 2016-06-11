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

import co.edu.usbcali.demo.dao.ITiposUsuariosDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;


@Service
@Scope("singleton")
public class TipoUsuarioLogica implements ITiposUsuariosLogica {

	@Autowired
	private ITiposUsuariosDAO tiposUsuarioDAO;
	
	@Autowired 
	private Validator validator;
	
	static final Logger log=LoggerFactory.getLogger(TipoUsuarioLogica.class);
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(TiposUsuarios tipoUsuarios) throws Exception {
		hacerValidaciones(tipoUsuarios);
		tiposUsuarioDAO.grabar(tipoUsuarios);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(TiposUsuarios tipoUsuarios) throws Exception {
		hacerValidaciones(tipoUsuarios);
		tiposUsuarioDAO.modificar(tipoUsuarios);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(TiposUsuarios tipoUsuarios) throws Exception {
		hacerValidaciones(tipoUsuarios);
		tiposUsuarioDAO.borrar(tipoUsuarios);
	}

	@Override
	@Transactional(readOnly=true)
	public TiposUsuarios consultarTipoUsuariosPorId(long id) throws Exception {
		if(id <= 0){
			throw new Exception("El id no existe");
		}
		return tiposUsuarioDAO.consultarTipoUsuariosPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposUsuarios> consultarTodos() throws Exception {
		
		return tiposUsuarioDAO.consultarTodos();
	}
	

	private void hacerValidaciones(TiposUsuarios tiposUsuario) throws Exception {
		Set<ConstraintViolation<TiposUsuarios>> constraintViolations = validator.validate(tiposUsuario);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<TiposUsuarios> oViolation : constraintViolations ) { 
				 
				 log.info(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
				 sb.append(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
			 }	 
			 
			 throw new Exception(sb.toString());
		 }
	}

}
