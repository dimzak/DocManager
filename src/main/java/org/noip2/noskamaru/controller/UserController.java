package org.noip2.noskamaru.controller;

import java.util.Map;

import org.noip2.noskamaru.dao.UserDao;
import org.noip2.noskamaru.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/user")
    public String userIndex(Map<String, Object> map)	{
    	try	{
    	map.put("user",new User());
    	map.put("userList", userDao.list());
    	}
    	catch(Exception e)	{
    		e.printStackTrace();
    	}
    	return("users");//users.html
    }
    
    @RequestMapping(value="/user.do", method=RequestMethod.POST)
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
		return "users";//users.html
	}


}
