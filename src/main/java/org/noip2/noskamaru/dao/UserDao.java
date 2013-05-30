package org.noip2.noskamaru.dao;

import java.util.List;

import org.noip2.noskamaru.model.User;

public interface UserDao {
	
	public List<User> list();
	public void save(User user);
	public User get(int id);
	public void remove(int id);
	public void update(User user);
	
}
