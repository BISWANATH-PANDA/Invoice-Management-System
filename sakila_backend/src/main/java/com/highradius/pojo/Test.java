package com.highradius.pojo;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.highradius.dao.Invoicedao;



public class Test 
{
public static void main(String[] args) 
{
	Invoicedao invoicedao;

	Session session = null;
	Transaction transaction=null;
	try {
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		session= factory.openSession();
		transaction= session.beginTransaction();
		System.out.println("table created");
		
	
		transaction.commit();
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally 
	{
		session.close(); 
	}
}
}
