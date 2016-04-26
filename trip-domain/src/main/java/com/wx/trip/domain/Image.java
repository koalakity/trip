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


}