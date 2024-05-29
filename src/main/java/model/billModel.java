package model;

import java.util.Date;  
import java.sql.*;
import java.time.LocalDateTime;

public class billModel {
private int idbill;
private String saledate;
private float totalcount;
private String customername;
private String customerphone;
public billModel(int idbill, String saledate, float totalcount, String customername, String customerphone) {
	super();
	this.idbill = idbill;
	this.saledate = saledate;
	this.totalcount = totalcount;
	this.customername = customername;
	this.customerphone = customerphone;
}
public billModel() {
	super();
	// TODO Auto-generated constructor stub
}
public int getIdbill() {
	return idbill;
}
public void setIdbill(int idbill) {
	this.idbill = idbill;
}
public String getSaledate() {
	return saledate;
}
public void setSaledate(String saledate) {
	this.saledate = saledate;
}
public float getTotalcount() {
	return totalcount;
}
public void setTotalcount(float totalcount) {
	this.totalcount = totalcount;
}
public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public String getCustomerphone() {
	return customerphone;
}
public void setCustomerphone(String customerphone) {
	this.customerphone = customerphone;
}
public Object[] toObject() {
	return new Object[]{"" + idbill, saledate, totalcount, customername, customerphone};
}
}
