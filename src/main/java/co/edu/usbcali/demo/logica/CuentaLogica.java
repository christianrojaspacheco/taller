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

import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.modelo.Cuentas;


@Service
@Scope("singleton")
public class CuentaLogica implements ICuentaLogica {


	@Autowired
	private ICuentasDAO cuentaDAO;

	@Autowired 
	private Validator validator;
	
	static final Logger log=LoggerFactory.getLogger(CuentaLogica.class);
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void grabar(Cuentas cuenta) throws Exception {
		hacerValidaciones(cuenta);
		cuentaDAO.grabar(cuenta);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(Cuentas cuenta) throws Exception {
		hacerValidaciones(cuenta);
	    cuentaDAO.modificar(cuenta);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(Cuentas cuenta) throws Exception {
		hacerValidaciones(cuenta);
	    cuentaDAO.borrar(cuenta);
	}

	@Override
	@Transactional(readOnly=true)
	public Cuentas consultarCuentaPorNumero(String numero) throws Exception{
		if(numero == null){
			throw new Exception("El numero no existe");
		}
		return cuentaDAO.consultarCuentaPorNumero(numero);   
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cuentas> consultarTodos() throws Exception{
		return cuentaDAO.consultarTodos();
	}
	
	
	private void hacerValidaciones(Cuentas cuenta) throws Exception {
		Set<ConstraintViolation<Cuentas>> constraintViolations = validator.validate(cuenta);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<Cuentas> oViolation : constraintViolations ) { 
				 
				 log.info(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
				 sb.append(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
			 }	 
			 
			 throw new Exception(sb.toString());
		 }
	}

}
