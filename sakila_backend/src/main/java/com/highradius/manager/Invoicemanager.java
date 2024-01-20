package com.highradius.manager;

import java.util.List;

import com.highradius.pojo.Invoice;

public interface Invoicemanager {
	public List<Invoice> getallinvoices();
	public List<Invoice> openinvoices();
	public List<Invoice> closedinvoices();
	public List<Invoice> deletedinvoices();
	public int addinvoice(Invoice a);
	public int deleteinvoices(List<String> invoiceno);
	public int editinvoice(Invoice a);
	public List<Invoice> readAllinvoices(int start, int limit);
	public List<Invoice> readopeninvoices(int start, int limit);
	public List<Invoice> readclosedinvoices(int start, int limit);
	public List<Invoice> readdeletedinvoices(int start, int limit);
	public List<Invoice> advancedsearch(Invoice invoice);
	public int totalRecords();
	public int totalopeninvoices();
	public int totalclosedinvoices();
	public int totaldeletedinvoices();
}
