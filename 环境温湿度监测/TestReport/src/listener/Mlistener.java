package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.net.mysever.server;

import letmesleep.uicp.cn.dao.proxy.NodeDaoProxy;

import beans.Node;

public class Mlistener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO ANouto-generated method stub
		new Thread(new Runnable(){
			public void run() {
				new server();
			}
		}).start();
		
	}

}
