package com.mvc.forrest.service.domain;

import java.sql.Timestamp;

import lombok.Data;

//==>������ �𵨸�(�߻�ȭ/ĸ��ȭ)�� Bean
@Data
public class Coupon {
	
	///Field
	private int couponNo;
	private String couponName;
	private double discount;
	private Timestamp couponCreDate;
	private Timestamp couponDelDate;

}