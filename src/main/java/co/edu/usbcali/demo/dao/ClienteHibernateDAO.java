package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Clientes;

/**
 * The Class ClienteHibernateDAO.
 */
@Repository
@Scope("singleton")
public class ClienteHibernateDAO implements IClienteDAO {
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IClienteDAO#grabar(co.edu.usbcali.demo.modelo.Clientes)
	 */
	@Override
	public void grabar(Clientes clientes) {
		sessionFactory.getCurrentSession().save(clientes);		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IClienteDAO#modificar(co.edu.usbcali.demo.modelo.Clientes)
	 */
	@Override
	public void modificar(Clientes clientes) {
		sessionFactory.getCurrentSession().update(clientes);		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IClienteDAO#borrar(co.edu.usbcali.demo.modelo.Clientes)
	 */
	@Override
	public void borrar(Clientes clientes) {
		sessionFactory.getCurrentSession().delete(clientes);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IClienteDAO#consultarClientePorId(long)
	 */
	@Override
	public Clientes consultarClientePorId(long cliId) {
		return sessionFactory.getCurrentSession().get(Clientes.class, cliId);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IClienteDAO#consultarTodos()
	 */
	@Override
	public List<Clientes> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Clientes.class).list();
	}

}
