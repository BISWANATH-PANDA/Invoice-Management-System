package com.highradius.action;

import java.util.List; 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.highradius.manager.Invoicemanager;
import com.highradius.pojo.Invoice;



public class Action {

	private List<Invoice> invoices;
	private List<String> invoiceno;
	private Invoice invoice;
	private int customerID;
	private String invoice_no;
	

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public List<String> getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(List<String> invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Invoicemanager invoicemanager;
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}



	public String addinvoiceof() {
		String s="ERROR";
		int a2=0;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		a2=invoicemanager.addinvoice(this.invoice);
		if (a2!=0)
			s = "Shown";
		System.out.println(s + "\n" + this.invoice);
		return s;
		
	}
	public String deleteinvoices() {
		String s="ERROR";
		int a=0;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		a=invoicemanager.deleteinvoices(invoiceno);
		if (a!=0)
			s = "Shown";
		System.out.println(s + "\n" + invoices);
		return s;

	}
	public String editinvoice() {
		String s="ERROR";
		int a3;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		a3=invoicemanager.editinvoice(this.invoice);
		if (invoice!=null)
			s = "Shown";
		System.out.println(s + "\n" +this.invoice);
		return s;

	}
	public String deletedinvoices() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.deletedinvoices();
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + this.invoices);
		return s;

	}
	public String advancesearch() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.advancedsearch(this.invoice);
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + this.invoices);
		return s;

	}
}
