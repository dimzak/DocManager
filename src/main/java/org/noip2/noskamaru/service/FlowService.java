package org.noip2.noskamaru.service;

import java.util.List;

import org.noip2.noskamaru.model.Document;

public interface FlowService {
	public List<Document> listByUser(int userid);
	public  int getIdFromName(String username);

}
