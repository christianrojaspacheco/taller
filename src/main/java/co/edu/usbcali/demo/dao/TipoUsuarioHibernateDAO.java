package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;


/**
 * The Class TipoUsuarioHibernateDAO.
 */
@Repository
@Scope("singleton")
public class TipoUsuarioHibernateDAO implements ITiposUsuariosDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITiposUsuariosDAO#grabar(co.edu.usbcali.demo.modelo.TiposUsuarios)
	 */
	@Override
	public void grabar(TiposUsuarios tipoUsuarios) {

		sessionFactory.getCurrentSession().save(tipoUsuarios);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITiposUsuariosDAO#modificar(co.edu.usbcali.demo.modelo.TiposUsuarios)
	 */
	@Override
	public void modificar(TiposUsuarios tipoUsuarios) {
		

		sessionFactory.getCurrentSession().update(tipoUsuarios);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITiposUsuariosDAO#borrar(co.edu.usbcali.demo.modelo.TiposUsuarios)
	 */
	@Override
	public void borrar(TiposUsuarios tipoUsuarios) {
		
		sessionFactory.getCurrentSession().delete(tipoUsuarios);

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITiposUsuariosDAO#consultarTipoUsuariosPorId(long)
	 */
	@Override
	public TiposUsuarios consultarTipoUsuariosPorId(long id) {
		
		return sessionFactory.getCurrentSession().get(TiposUsuarios.class, id);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ITiposUsuariosDAO#consultarTodos()
	 */
	@Override
	public List<TiposUsuarios> consultarTodos() {
		
	    return sessionFactory.getCurrentSession().createCriteria(TiposUsuarios.class).list();

	}



}
