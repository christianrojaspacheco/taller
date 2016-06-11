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

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;


@Service
@Scope("singleton")
public class TipoDocumentoLogica implements ITipoDocumentoLogica {

	@Autowired 
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Autowired 
	private Validator validator;
	
	static final Logger log=LoggerFactory.getLogger(TipoDocumentoLogica.class);

	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void grabar(TiposDocumentos tiposDocumentos) throws Exception {
		hacerValidaciones(tiposDocumentos);
		tipoDocumentoDAO.grabar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception {
		hacerValidaciones(tiposDocumentos);
		tipoDocumentoDAO.modificar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception {
		hacerValidaciones(tiposDocumentos);
		tipoDocumentoDAO.borrar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=true)
	public TiposDocumentos consultarTipoDocumentoPorId(long id) throws Exception {
		if(id <= 0){
			throw new Exception("El id no existe");
		}
		return tipoDocumentoDAO.consultarTipoDocumentoPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposDocumentos> consultarTodos() throws Exception {
		
		return tipoDocumentoDAO.consultarTodos();
	}

	private void hacerValidaciones(TiposDocumentos tiposDocumentos) throws Exception {
		Set<ConstraintViolation<TiposDocumentos>> constraintViolations = validator.validate(tiposDocumentos);
		 StringBuilder sb = new StringBuilder();
		 
		 if (constraintViolations.size() > 0 ) {
			 for (ConstraintViolation<TiposDocumentos> oViolation : constraintViolations ) { 
				 
				 log.info(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
				 sb.append(oViolation.getPropertyPath().toString());
				 log.info(oViolation.getMessage());
			 }	 
			 
			 throw new Exception(sb.toString());
		 }
	}
	
}
