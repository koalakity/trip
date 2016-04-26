package com.wx.trip.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// JPA实体类的标识
@Entity
@Table(name = "trip_seat")
@EntityListeners(AuditingEntityListener.class)
public class Image extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9142779731833586161L;

	private byte[] content;
	
	private String type;
	
	private String name;
	
	private String suffix;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public enum Type{
		MENU
	}
}