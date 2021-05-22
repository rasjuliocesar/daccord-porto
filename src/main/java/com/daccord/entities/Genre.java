package com.daccord.entities;

import com.daccord.utils.Utils;

public class Genre {

	private String _id;
	private Integer id3;
	private String name;
	
	public Genre() {
	}
	
	public Genre(String id, Integer id3, String name) {
		Utils util = new Utils();
		this._id = util.geradorId();
		
		this.id3 = id3;
		this.name = name;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Integer getId3() {
		return id3;
	}

	public void setId3(Integer id3) {
		this.id3 = id3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		Genre other = (Genre) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}
}
