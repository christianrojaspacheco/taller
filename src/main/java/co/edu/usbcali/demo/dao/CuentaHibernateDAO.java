package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Cuentas;

/**
 * The Class CuentaHibernateDAO.
 */
@Repository
@Scope("singleton")
public class CuentaHibernateDAO implements ICuentasDAO {


	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ICuentasDAO#grabar(co.edu.usbcali.demo.modelo.Cuentas)
	 */
	@Override
	public void grabar(Cuentas cuenta) {

		sessionFactory.getCurrentSession().save(cuenta);	

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ICuentasDAO#modificar(co.edu.usbcali.demo.modelo.Cuentas)
	 */
	@Override
	public void modificar(Cuentas cuenta) {

		sessionFactory.getCurrentSession().update(cuenta);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ICuentasDAO#borrar(co.edu.usbcali.demo.modelo.Cuentas)
	 */
	@Override
	public void borrar(Cuentas cuenta) {

		sessionFactory.getCurrentSession().delete(cuenta);

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ICuentasDAO#consultarCuentaPorId(long)
	 */
	@Override
	public Cuentas consultarCuentaPorNumero(String numero) {

		return sessionFactory.getCurrentSession().get(Cuentas.class, numero);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.ICuentasDAO#consultarTodos()
	 */
	@Override
	public List<Cuentas> consultarTodos() {
		
       return sessionFactory.getCurrentSession().createCriteria(Cuentas.class).list();
	}

}
