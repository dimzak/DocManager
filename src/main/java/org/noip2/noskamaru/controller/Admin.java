package org.noip2.noskamaru.controller;

import java.util.Map;

import org.noip2.noskamaru.dao.DocumentDao;
import org.noip2.noskamaru.dao.UserDao;
import org.noip2.noskamaru.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Admin {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DocumentDao docDao;
	
	@RequestMapping("/admin/doc")
	public String index(Map<String, Object> map, Map<String, Object> map2)	{
		try		{
			map.put("document", new Document());
			map.put("documentList", docDao.list());
			map2.put("userList", userDao.list());
			
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return("/admin/documents");//documents.html
	}
	
	

}
