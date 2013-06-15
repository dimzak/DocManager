package org.noip2.noskamaru.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.noip2.noskamaru.dao.DocumentDao;
import org.noip2.noskamaru.dao.UserDao;
import org.noip2.noskamaru.model.Document;
import org.noip2.noskamaru.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HrController {
	
	@Autowired
	private DocumentDao docDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/hr/alldoc*",method = RequestMethod.GET)
	public String allDoc(Map<String, Object> map, Model model, HttpServletRequest request)	{
		try		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String name = auth.getName(); //get logged in username
	        model.addAttribute("username", name);
			map.put("document", new Document());
			map.put("documentList", docDao.list());
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return("/hr/alldoc.html");
	} 
	
	@RequestMapping("/hr/user")
    public String userIndex(Map<String, Object> map,Model model, HttpServletRequest request)	{
    	try	{
    	map.put("user",new User());
    	map.put("userList", userDao.list());
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
   
        model.addAttribute("username", name);
    	}
    	catch(Exception e)	{
    		e.printStackTrace();
    	}
    	return("hr/users.html");//users.html
    }
    
    @RequestMapping(value="/hr/user.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute User user, BindingResult result, @RequestParam String action, Map<String, Object> map){
		User userResult = new User();
		if(action.toLowerCase().equals("add"))	{	//java 6 compliant
			userDao.save(user);
			userResult = user;
		}
		if(action.toLowerCase().equals("edit"))		{
			userDao.update(user);
			userResult = user;
		}
		if(action.toLowerCase().equals("delete"))	{
			userDao.remove(user.getId());
			userResult = new User();
    	}
		if(action.toLowerCase().equals("search"))	{
			User searchedUser = userDao.get(user.getId());
			userResult = searchedUser!=null ? searchedUser : new User();
		}
		map.put("user", userResult);
		map.put("userList", userDao.list());
		return ("hr/users.html");//users.html
	}

}
