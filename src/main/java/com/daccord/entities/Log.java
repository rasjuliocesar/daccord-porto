package com.daccord.entities;

import com.daccord.utils.Utils;
import com.google.cloud.Timestamp;

public class Log {

	private String _id;
	private Timestamp createdAt;
	private String description;
	private String userId;
	private String collection;
	
	public Log() {
	}

	public Log(String _id, Timestamp createdAt, String description, String userId, String collection) {

		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.createdAt = createdAt;
		this.description = description;
		this.userId = userId;
		this.collection = collection;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
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
		Log other = (Log) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}	
}
