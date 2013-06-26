package org.noip2.noskamaru.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.noip2.noskamaru.dao.UserDao;
import org.noip2.noskamaru.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlowServiceImpl implements FlowService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public List<Document> listByUser(int userid) {
		 Query q = sessionFactory.getCurrentSession()
		.createSQLQuery("SELECT d.* FROM documents d WHERE" +
				" d.doc_id IN " +
				"(SELECT f.doc_id FROM flows f,documents d " +
				"WHERE f.user_id = :userid  AND f.line = d.status )")
		 		.addEntity(Document.class);		 
				q.setParameter("userid", userid);
		return q.list();
		
	}

	@Transactional
	public int getIdFromName(String username) {
		Query q = (Query) sessionFactory.getCurrentSession().createSQLQuery("SELECT u.user_id FROM users u WHERE u.username LIKE :username");
		q.setParameter("username", username);
		
		int userid =(Integer)q.uniqueResult();
		System.out.println(userid);
		return userid;
	}

}
