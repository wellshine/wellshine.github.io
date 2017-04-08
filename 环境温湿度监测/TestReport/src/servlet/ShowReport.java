package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import letmesleep.uicp.cn.dao.proxy.NodeDaoProxy;
import beans.Node;

//import service.Service;
//Servlet¿ØÖÆ²ã
public class ShowReport extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowReport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		//this.doGet(request, response);
		NodeDaoProxy pro = new NodeDaoProxy();
		Node node = new Node();
		node.setTemperature(new Random().nextInt(100));
		pro.addNodeData(node);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		request.setCharacterEncoding("utf-8");
		List list;
		NodeDaoProxy service = new NodeDaoProxy();
		list = service.getAllNodeData();
		
		request.getSession().setAttribute("NODE",list);
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
