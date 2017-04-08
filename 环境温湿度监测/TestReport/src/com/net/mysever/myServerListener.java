package com.net.mysever;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class myServerListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		new server();

	}

}
