package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Usuarios;


/**
 * The Class UsuarioHibernateDAO.
 */
@Repository
@Scope("singleton")
public class UsuarioHibernateDAO implements IUsuarioDAO {


	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IUsuarioDAO#grabar(co.edu.usbcali.demo.modelo.Usuarios)
	 */
	@Override
	public void grabar(Usuarios usuario) {
		sessionFactory.getCurrentSession().save(usuario);	


	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IUsuarioDAO#modificar(co.edu.usbcali.demo.modelo.Usuarios)
	 */
	@Override
	public void modificar(Usuarios usuario) {

		sessionFactory.getCurrentSession().update(usuario);

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IUsuarioDAO#borrar(co.edu.usbcali.demo.modelo.Usuarios)
	 */
	@Override
	public void borrar(Usuarios usuario) {

		sessionFactory.getCurrentSession().delete(usuario);

	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IUsuarioDAO#consultarUsuarioPorId(long)
	 */
	@Override
	public Usuarios consultarUsuarioPorId(long id) {

		return sessionFactory.getCurrentSession().get(Usuarios.class, id);
	}

	/* (non-Javadoc)
	 * @see co.edu.usbcali.demo.dao.IUsuarioDAO#consultarTodos()
	 */
	@Override
	public List<Usuarios> consultarTodos() {

	       return sessionFactory.getCurrentSession().createCriteria(Usuarios.class).list();
	}

}
