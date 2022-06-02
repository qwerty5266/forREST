package com.mvc.forrest.service.domain;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Rental {
	
	private int tranNo;
	private String userId;
	private int prodNo;
	private String divyAddress;
	private String pickupAddress;
	private Date startDate;
	private Date endDate;
	private int period;
	private short tranCode;
	private String paymentNo;
	private String paymentWay;
	private String receiverPhone;
	private String receiverName;
	private String prodName;
	private String prodImg;
	private int originPrice;
	private double discount;
	private int resultPrice;
	private Product purchaseProd;
	private User buyer;

	
	public Rental(){
	}
	
	
}