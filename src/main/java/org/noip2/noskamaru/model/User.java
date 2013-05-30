package org.noip2.noskamaru.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	
	//UNIQUE is handled in db
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;	
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="role")
	private String role;
	
	@Column(name="cancreate")
	private boolean cancreate;
	
	@OneToMany(mappedBy="user")
	private Set<Flow> userFlow;
	
	public User(){}
	

	public Set<Flow> getUserFlow() {
		return userFlow;
	}


	public void setUserFlow(Set<Flow> userFlow) {
		this.userFlow = userFlow;
	}


	public User(int id, String firstname, String lastname,
			String password, String username, String role, boolean cancreate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.username = username;
		this.role = role;
		this.cancreate = cancreate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isCancreate() {
		return cancreate;
	}

	public void setCancreate(boolean cancreate) {
		this.cancreate = cancreate;
	}
	
	



}
