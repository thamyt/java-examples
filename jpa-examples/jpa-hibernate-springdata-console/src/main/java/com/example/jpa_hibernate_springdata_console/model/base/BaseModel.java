package com.example.jpa_hibernate_springdata_console.model.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenerationTime;

@MappedSuperclass
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@org.hibernate.annotations.Generated(value=GenerationTime.ALWAYS)
	@Column(name="last_update")
	private Timestamp lastUpdate;

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}	
}
