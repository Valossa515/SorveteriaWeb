package br.com.felipe.sorveteria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContext implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().close();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		HibernateUtil.getSessionFactory().openSession();
	}
}
