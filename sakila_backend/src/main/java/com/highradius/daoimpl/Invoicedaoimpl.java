package com.highradius.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.highradius.dao.Invoicedao;
import com.highradius.pojo.Invoice;


public class Invoicedaoimpl implements Invoicedao {

	@Override
	public List<Invoice> getallinvoices() {
		// TODO Auto-generated method stub
		Session s = null;
		List<Invoice> results = new ArrayList<Invoice>();
		String hql = "FROM Invoice";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			results = s.createQuery(hql).list();
			System.out.println("Got invoices");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return results;
	}

	@Override
	public List<Invoice> openinvoices() {
		Session s = null;
		List<Invoice> results = new ArrayList<Invoice>();
		String hql = "FROM Invoice WHERE isOpen='1'";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			Query query = s.createQuery(hql);
			results = query.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return results;
	}

	@Override
	public List<Invoice> closedinvoices() {
		Session s = null;
		List<Invoice> results = new ArrayList<Invoice>();
		String hql = "FROM Invoice WHERE isOpen='0'";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			Query query = s.createQuery(hql);
			results = query.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return results;
	}

	@Override
	public List<Invoice> deletedinvoices() {
		Session s = null;
		List<Invoice> results = new ArrayList<Invoice>();
		String hql = "FROM Invoice WHERE isDeleted='1'";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			Query query = s.createQuery(hql);
			results = query.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return results;
	}

	@Override
	public int addinvoice(Invoice a) {
		int result = 0;
		Session s = null;
		Transaction transaction = null;

		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			transaction = s.beginTransaction();

			s.save(a);
			transaction.commit();
			result = 1;

		} catch (Exception e) {
			result = 0;
			e.printStackTrace();

		} finally {
			if (s != null) {

				s.close();
			}

		}
		return result;
	}

	@Override
	public int deleteinvoices(List<String> invoiceno) {
		Session s = null;
		int results = 0;
		Transaction transaction = null;
		Invoice o = null;
		String hql = "UPDATE Invoice SET isDeleted ='1',isOpen ='0' WHERE invoiceNo  =:sl";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			transaction = s.beginTransaction();

			System.out.println(invoiceno);
			for (String x : invoiceno) {
				Query q = s.createQuery(hql);
				q.setParameter("sl", x);
				results = q.executeUpdate();

			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return results;
	}

	@Override
	public int editinvoice(Invoice a) {
		Session s = null;
		int result = 0;
		Transaction transaction = null;
		String hql = "UPDATE Invoice Set quantity =:q,description =:d WHERE invoiceNo =:sl";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			transaction = s.beginTransaction();
			Query q = s.createQuery(hql);
			q.setParameter("sl", a.getInvoiceNo());
			q.setParameter("d", a.getDescription());
			q.setParameter("q", a.getQuantity());
			result = q.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return result;
	}
	@Override
    public List<Invoice> readAllinvoices(int start, int limit) {
        List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("from Invoice")
                            .setFirstResult(start)
                            .setMaxResults(limit).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result;
    }
	@Override
    public int totalRecords() {
        List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("from Invoice").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result.size();
    }

	@Override
	public List<Invoice> readopeninvoices(int start, int limit) {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isOpen='1'")
                            .setFirstResult(start)
                            .setMaxResults(limit).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result;
	}

	@Override
	public List<Invoice> readclosedinvoices(int start, int limit) {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isOpen='0'")
                            .setFirstResult(start)
                            .setMaxResults(limit).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result;
	}

	@Override
	public List<Invoice> readdeletedinvoices(int start, int limit) {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isDeleted='1'")
                            .setFirstResult(start)
                            .setMaxResults(limit).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result;
	}

	@Override
	public List<Invoice> advancedsearch(Invoice invoice) {
		
		Session s = null;
		List<Invoice> result = new ArrayList<>();
		Transaction transaction = null;
		System.out.println(invoice.getCustomerID());
		System.out.println(invoice.getInvoiceNo());
		String hql = "From Invoice WHERE invoiceNo =:sl and customerID =:cust";
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			s = sf.openSession();
			transaction = s.beginTransaction();
			Query q = s.createQuery(hql);
			q.setParameter("sl",invoice.getInvoiceNo() );
			q.setParameter("cust", invoice.getCustomerID());
			result =q.list();
			
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (s != null)
				s.close();
		}
		return result;
	}

	@Override
	public int totalopeninvoices() {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isOpen='1'").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result.size();
	}

	@Override
	public int totalclosedinvoices() {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isOpen='0'").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result.size();
	}

	@Override
	public int totaldeletedinvoices() {
		List<Invoice> result = new ArrayList<>();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = configuration.buildSessionFactory();
            session = factory.openSession();
            result = session.createQuery("FROM Invoice WHERE isDeleted='1'").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    
        return result.size();
	}

}
