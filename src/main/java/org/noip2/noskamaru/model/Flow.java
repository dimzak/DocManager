package org.noip2.noskamaru.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="flows")
public class Flow {
	
	@Id
	@GeneratedValue
	@Column(name="flow_id")
	private int flow_id;
	
	//line of the user
	@Column(name="line")
	private int line;
	
	@ManyToOne
	@JoinColumn(name="doc_id")
	private Document doc;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Flow()	{
		
	}

	public int getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(int flow_id) {
		this.flow_id = flow_id;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
