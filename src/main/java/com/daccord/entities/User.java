package com.daccord.entities;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.daccord.utilities.Utilities;

public class User {

	private String _id;
	private String name;
	private String login;
	private String password;
	private String email;
	private String phone;
	
	public User() {
	}

	public User(String name, String login, String password, String email, String phone) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		Utilities util = new Utilities();
		this._id = util.geradorId();
		this.password = util.criptografar(password);
		
		this.name = name;
		this.login = login;
		this.email = email;
		this.phone = phone;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
