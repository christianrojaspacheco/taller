package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Consignaciones;

/**
 * The Class ConsignacioneHibernateDAO.
 */
@Repository
@Scope("singleton")
public class ConsignacioneHibernateDAO implements IConsignacionesDAO {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IConsignacionesDAO#grabar(co.edu.usbcali.demo.modelo.Consignaciones)
	 */
	@Override
	public void grabar(Consignaciones consignaciones) {

		sessionFactory.getCurrentSession().save(consignaciones);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IConsignacionesDAO#modificar(co.edu.usbcali.demo.modelo.Consignaciones)
	 */
	@Override
	public void modificar(Consignaciones consignaciones) {

		sessionFactory.getCurrentSession().update(consignaciones);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IConsignacionesDAO#borrar(co.edu.usbcali.demo.modelo.Consignaciones)
	 */
	@Override
	public void borrar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().delete(consignaciones);

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IConsignacionesDAO#consultarConsignacionesPorId(long)
	 */
	@Override
	public Consignaciones consultarConsignacionesPorId(long id) {
		return sessionFactory.getCurrentSession().get(Consignaciones.class, id);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IConsignacionesDAO#consultarTodos()
	 */
	@Override
	public List<Consignaciones> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Consignaciones.class).list();
	}
	

    @Override
    public Long obtenerConsecutivoId()
    {
        Criteria criteria =
            sessionFactory.getCurrentSession().createCriteria(
                Consignaciones.class).setProjection(Projections.max("conCodigo"));
        return ((Long) criteria.uniqueResult() + 1L);
	}
}
