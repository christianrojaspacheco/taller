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

import co.edu.usbcali.demo.dao.IConsignacionesDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;


@Service
@Scope("singleton")
public class ConsignacionLogica implements IConsignacionLogica {

	@Autowired
	private IConsignacionesDAO consignacionDAO;

	
	@Autowired
	private Validator validator;
	
	private static final Logger log=LoggerFactory.getLogger(ConsignacionLogica.class);
	
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(Consignaciones consignaciones) throws Exception {
		hacerValidaciones(consignaciones);
		consignacionDAO.grabar(consignaciones);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Consignaciones consignaciones) throws Exception {
		hacerValidaciones(consignaciones);
		consignacionDAO.modificar(consignaciones);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Consignaciones consignaciones) throws Exception {
		hacerValidaciones(consignaciones);
		consignacionDAO.borrar(consignaciones);
	}

	@Override
	@Transactional(readOnly=true)
	public Consignaciones consultarConsignacionesPorId(long id) throws Exception {
		if(id <= 0){
			throw new Exception("El id no existe");
		}
		return consignacionDAO.consultarConsignacionesPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Consignaciones> consultarTodos() throws Exception {
		return consignacionDAO.consultarTodos();
	}
	
	private void hacerValidaciones(Consignaciones consignacion) throws Exception {
		Set<ConstraintViolation<Consignaciones>> constraintViolations = validator.validate(consignacion);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<Consignaciones> oViolation : constraintViolations ) { 
				 
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
		return consignacionDAO.obtenerConsecutivoId();
	}

}
