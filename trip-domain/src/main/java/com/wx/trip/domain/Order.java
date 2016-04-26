package com.wx.trip.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// JPA实体类的标识
@Entity
@Table(name = "trip_order")
@EntityListeners(AuditingEntityListener.class)
public class Order extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5481802525487992945L;
	
	private Map<Menu,Integer> menu;
	
	private Seat seat;
	
	private String status;//订单状态
	
	private PayInfo payInfo;

	public Map<Menu, Integer> getMenu() {
		return menu;
	}

	public void setMenu(Map<Menu, Integer> menu) {
		this.menu = menu;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}

}