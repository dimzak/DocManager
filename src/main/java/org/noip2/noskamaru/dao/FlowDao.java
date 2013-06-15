package org.noip2.noskamaru.dao;

import java.util.List;

import org.noip2.noskamaru.model.Flow;

public interface FlowDao {
	
	public List<Flow> list();
	public void save(Flow flow);
	public Flow get(int id);
	public void remove(int id);
	public void update(Flow flow);

}
