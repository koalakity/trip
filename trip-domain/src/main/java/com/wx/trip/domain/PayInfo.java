package com.wx.trip.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// JPA实体类的标识
@Entity
@Table(name = "trip_pay_info")
@EntityListeners(AuditingEntityListener.class)
public class PayInfo extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8480631311268330957L;


}