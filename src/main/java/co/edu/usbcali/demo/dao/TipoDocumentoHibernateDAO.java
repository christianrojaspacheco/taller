package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

/**
 * The Class TipoDocumentoHibernateDAO.
 */
@Repository
@Scope("singleton")
public class TipoDocumentoHibernateDAO implements ITipoDocumentoDAO {
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;


	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITipoDocumentoDAO#grabar(co.edu.usbcali.demo.modelo.TiposDocumentos)
	 */
	@Override
	public void grabar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().save(tiposDocumentos);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITipoDocumentoDAO#modificar(co.edu.usbcali.demo.modelo.TiposDocumentos)
	 */
	@Override
	public void modificar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().update(tiposDocumentos);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITipoDocumentoDAO#borrar(co.edu.usbcali.demo.modelo.TiposDocumentos)
	 */
	@Override
	public void borrar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().delete(tiposDocumentos);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITipoDocumentoDAO#consultarTipoDocumentoPorId(long)
	 */
	@Override
	public TiposDocumentos consultarTipoDocumentoPorId(long cliId) {
		return sessionFactory.getCurrentSession().get(TiposDocumentos.class, cliId);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITipoDocumentoDAO#consultarTodos()
	 */
	@Override
	public List<TiposDocumentos> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(TiposDocumentos.class).list();
	}

}
