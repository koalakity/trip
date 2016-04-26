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
public class Seat extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9142779731833586161L;

	private String name;
	
	private String status;
	
	private Order order;
	
	private Integer seatNum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	
}