package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.Retiros;


/**
 * The Class RetiroHibernateDAO.
 */
@Repository
@Scope("singleton")
public class RetiroHibernateDAO implements IRetirosDAO {


	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IRetirosDAO#grabar(co.edu.usbcali.demo.modelo.Retiros)
	 */
	@Override
	public void grabar(Retiros retiro) {

		sessionFactory.getCurrentSession().save(retiro);	
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IRetirosDAO#modificar(co.edu.usbcali.demo.modelo.Retiros)
	 */
	@Override
	public void modificar(Retiros retiro) {

		sessionFactory.getCurrentSession().update(retiro);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IRetirosDAO#borrar(co.edu.usbcali.demo.modelo.Retiros)
	 */
	@Override
	public void borrar(Retiros retiro) {

		sessionFactory.getCurrentSession().delete(retiro);
		
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IRetirosDAO#consultarRetiroPorId(long)
	 */
	@Override
	public Retiros consultarRetiroPorId(long id) {

		return sessionFactory.getCurrentSession().get(Retiros.class, id);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IRetirosDAO#consultarTodos()
	 */
	@Override
	public List<Retiros> consultarTodos() {
		   return sessionFactory.getCurrentSession().createCriteria(Retiros.class).list();
			
	}

	@Override
	public Long obtenerConsecutivoId() {
		Criteria criteria =
            sessionFactory.getCurrentSession().createCriteria(
                Retiros.class).setProjection(Projections.max("retCodigo"));
        return ((Long) criteria.uniqueResult() + 1L);
	}

}
