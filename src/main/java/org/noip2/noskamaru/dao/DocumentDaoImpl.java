package org.noip2.noskamaru.dao;

import java.util.List;
import org.noip2.noskamaru.model.Document;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class DocumentDaoImpl implements DocumentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Document> list()	{
		Session session=sessionFactory.getCurrentSession();
		List<Document> documents=null;
		try		{
			documents=(List<Document>)
					session.createQuery("From Document").list();
		}
		catch(HibernateException he)	{
			he.printStackTrace();
		}
		return documents;
	}
	
	@Transactional
	public void save(Document document)		{
		Session session=sessionFactory.getCurrentSession();
		session.save(document);
	}
	
	@Transactional
	public Document get(int id)	{
		Session session=sessionFactory.getCurrentSession();
		return (Document)session.get(Document.class, id);
	}
	@Transactional
	public void remove(int id)	{
		Session session=sessionFactory.getCurrentSession();
		Document doc=(Document)session.get(Document.class, id);
		session.delete(doc);
	}

	@Transactional
	public void update(Document doc) {
		Session session=sessionFactory.getCurrentSession();
		session.update(doc);
		
	}	

}
