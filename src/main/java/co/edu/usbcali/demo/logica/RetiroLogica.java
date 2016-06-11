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

import co.edu.usbcali.demo.dao.IRetirosDAO;
import co.edu.usbcali.demo.modelo.Retiros;


@Service
@Scope("singleton")
public class RetiroLogica implements IRetiroLogica {

	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Autowired 
	private Validator validator;

	static final Logger log=LoggerFactory.getLogger(RetiroLogica.class);
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(Retiros retiro) throws Exception {
		hacerValidaciones(retiro);
		retirosDAO.grabar(retiro);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Retiros retiro) throws Exception {
		hacerValidaciones(retiro);
		retirosDAO.modificar(retiro);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Retiros retiro) throws Exception {
		hacerValidaciones(retiro);
		retirosDAO.borrar(retiro);
	}

	@Override
	@Transactional(readOnly=true)
	public Retiros consultarRetiroPorId(long id) throws Exception {
		if (id <= 0) {
           throw new Exception("El id no existe");
		}
		return retirosDAO.consultarRetiroPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Retiros> consultarTodos() throws Exception {
		return retirosDAO.consultarTodos();
	}

	private void hacerValidaciones(Retiros retiro) throws Exception {
		Set<ConstraintViolation<Retiros>> constraintViolations = validator.validate(retiro);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<Retiros> oViolation : constraintViolations ) { 
				 
				 log.info(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
				 sb.append(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
			 }	 
			 
			 throw new Exception(sb.toString());
		 }
	}

	@Override
	public Long obtenerConsecutivoId() {
		return retirosDAO.obtenerConsecutivoId() ;
	}
}
