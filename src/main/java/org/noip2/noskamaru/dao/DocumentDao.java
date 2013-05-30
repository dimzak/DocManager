package org.noip2.noskamaru.dao;

import java.util.List;

import org.noip2.noskamaru.model.Document;
import org.noip2.noskamaru.model.User;

public interface DocumentDao {
	
	public List<Document> list();
	public void save(Document document);
	public Document get(int id);
	public void remove(int id);
	public void update(Document doc);

}
