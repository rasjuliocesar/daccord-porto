package com.daccord.entities;

import com.daccord.utils.Utils;

public class User {

	private String _id;
	private String name;
	private String email;
	private String password;
	
	public User() {
	}
	
	public User(String _id, String name, String email, String password) {
		
		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String get_id() {
		return this._id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
}
