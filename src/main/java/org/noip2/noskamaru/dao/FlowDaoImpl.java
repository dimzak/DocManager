package org.noip2.noskamaru.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.noip2.noskamaru.model.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FlowDaoImpl implements FlowDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Flow> list() {
		return 
		sessionFactory.getCurrentSession().createQuery("from Flow").list();
	}

	@Transactional
	public void save(Flow flow) {
		sessionFactory.getCurrentSession().save(flow);

	}
	
	@Transactional
	public Flow get(int id) {
		Flow fl = (Flow)sessionFactory.getCurrentSession().get(Flow.class, id);
		return fl;
	}

	@Transactional
	public void remove(int id) {
		sessionFactory.getCurrentSession().delete(get(id));

	}

	@Transactional
	public void update(Flow flow) {
		sessionFactory.getCurrentSession().update(flow);

	}

}
