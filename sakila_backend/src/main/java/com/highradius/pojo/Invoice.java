package com.highradius.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author biswanath.panda
 *
 */
@Entity
@Table(name = "invoice_table")
public class Invoice {
	@Id
	@Column(name = "sl_no")
	private int slNo;
	@Column(name = "invoice_no")
	private String invoiceNo;
	@Column(name = "stock_code")
	private String stockCode;
	private String description;
	private int quantity;
	@Column(name = "invoice_date")
	private String invoiceDate;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "customer_id")
	private int customerID;
	private String country;
	@Column(name = "is_deleted")
	private String isDeleted;
	@Column(name = "is_open")
	private String isOpen;
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public String toString() {
		return "Invoice [slNo=" + slNo + ", invoiceNo=" + invoiceNo + ", stockCode=" + stockCode + ", description="
				+ description + ", quantity=" + quantity + ", invoiceDate=" + invoiceDate + ", unitPrice=" + unitPrice
				+ ", customerID=" + customerID + ", country=" + country + ", isDeleted=" + isDeleted + ", isOpen="
				+ isOpen + "]";
	}

	
}