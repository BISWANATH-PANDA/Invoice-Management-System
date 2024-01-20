package com.highradius.managerimpl;

import java.util.List;

import com.highradius.dao.Invoicedao;
import com.highradius.manager.Invoicemanager;
import com.highradius.pojo.Invoice;

public class Invoicemanagerimpl implements Invoicemanager {

	Invoicedao invoicedao;
	
	public Invoicedao getInvoicedao() {
		return invoicedao;
	}

	public void setInvoicedao(Invoicedao invoicedao) {
		this.invoicedao = invoicedao;
	}

	@Override
	public List<Invoice> getallinvoices() {
		return invoicedao.getallinvoices();
	}

	@Override
	public List<Invoice> openinvoices() {
		// TODO Auto-generated method stub
		return invoicedao.openinvoices();
	}

	@Override
	public List<Invoice> closedinvoices() {
		// TODO Auto-generated method stub
		return invoicedao.closedinvoices();
	}

	@Override
	public List<Invoice> deletedinvoices() {
		// TODO Auto-generated method stub
		return invoicedao.deletedinvoices();
	}

	@Override
	public int addinvoice(Invoice a) {
		// TODO Auto-generated method stub
		return invoicedao.addinvoice(a);
	}

	@Override
	public int deleteinvoices(List<String> invoiceno) {
		// TODO Auto-generated method stub
		return invoicedao.deleteinvoices(invoiceno);
	}

	@Override
	public int editinvoice(Invoice a) {
		// TODO Auto-generated method stub
		return invoicedao.editinvoice(a);
	}

	@Override
	public List<Invoice> readAllinvoices(int start, int limit) {
		// TODO Auto-generated method stub
		return invoicedao.readAllinvoices(start, limit);
	}

	@Override
	public int totalRecords() {
		// TODO Auto-generated method stub
		return invoicedao.totalRecords();
	}

	@Override
	public List<Invoice> readopeninvoices(int start, int limit) {
		// TODO Auto-generated method stub
		return invoicedao.readopeninvoices(start, limit);
	}

	@Override
	public List<Invoice> readclosedinvoices(int start, int limit) {
		// TODO Auto-generated method stub
		return invoicedao.readclosedinvoices(start, limit);
	}

	@Override
	public List<Invoice> readdeletedinvoices(int start, int limit) {
		// TODO Auto-generated method stub
		return invoicedao.readdeletedinvoices(start, limit);
	}

	@Override
	public List<Invoice> advancedsearch(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoicedao.advancedsearch(invoice);
	}

	@Override
	public int totalopeninvoices() {
		// TODO Auto-generated method stub
		return invoicedao.totalopeninvoices();
	}

	@Override
	public int totalclosedinvoices() {
		// TODO Auto-generated method stub
		return invoicedao.totalclosedinvoices();
	}

	@Override
	public int totaldeletedinvoices() {
		// TODO Auto-generated method stub
		return invoicedao.totaldeletedinvoices();
	}

}
