package org.noip2.noskamaru.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.noip2.noskamaru.model.Document;
import org.noip2.noskamaru.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Transactional
	public List<User> listUsers() {
		return 
		(List<User>)sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Transactional
	public void remove(int userid) {		
		sessionFactory.getCurrentSession().delete(get(userid));
	}

	@Transactional
	public List<User> list() {
		return 
		sessionFactory.getCurrentSession().createQuery("from User").list();
		
	}

	@Transactional
	public User get(int id) {
		User us=(User)sessionFactory.getCurrentSession().get(User.class, id);
		return us;
	}
	
	@Transactional
	public void update(User user)	{
		sessionFactory.getCurrentSession().update(user);
	}


}
