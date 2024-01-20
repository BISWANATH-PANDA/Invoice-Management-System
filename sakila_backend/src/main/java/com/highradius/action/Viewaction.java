package com.highradius.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.manager.Invoicemanager;
import com.highradius.pojo.Invoice;

public class Viewaction {
	public Invoicemanager invoicemanager;
	private int Total;
	private int start;
	private int limit;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}


	private List<Invoice> invoices;
	
	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}


	public String allinvoices() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.readAllinvoices(start, limit);
		Total= invoicemanager.totalRecords();
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + invoices);
		return s;

	}
	public String openinvoices() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.readopeninvoices(start, limit);
		Total= invoicemanager.totalopeninvoices();
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + invoices);
		return s;

	}
	public String closedinvoices() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.readclosedinvoices(start, limit);
		Total= invoicemanager.totalclosedinvoices();
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + invoices);
		return s;

	}
	public String deletedinvoices() {
		String s="ERROR";
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		invoicemanager = (Invoicemanager) context.getBean("invoiceManagerImpl");
		invoices=invoicemanager.readdeletedinvoices(start, limit);
		Total= invoicemanager.totaldeletedinvoices();
		if (invoices!=null)
			s = "Shown";
		System.out.println(s + "\n" + invoices);
		return s;

	}
}
