package letmesleep.uicp.cn.dao.proxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConn;

import beans.Node;


import letmesleep.uicp.cn.dao.NodeDao;
import letmesleep.uicp.cn.dao.impl.NodeDaoImpl;

public class NodeDaoProxy implements NodeDao{
	private Connection con;
	private NodeDao dao = null;
	
	
	public NodeDaoProxy(){
		//  ≥ı ºªØcon   ****
		this.con = JdbcConn.getConnection();
	}
	
	
	public void addNodeData(Node node){
		
		try {
			
			this.dao = new NodeDaoImpl(con);
			node.setDate("2016/11/29");
	System.out.println(node);
			dao.addNodeData(node);
			
		} catch (Exception e) {
			
		} finally {
			try {
				this.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public Node getNodeData(){
		
		Node node = null;
		try {
			
			this.dao = new NodeDaoImpl(con);
			node = dao.getNodeData();
			
		} catch (Exception e) {
			
		} finally {
			try {
				this.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return node;
	}

	public void deleteNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	public void upDataNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	public List<Node> getAllNodeData() {
		
		List<Node> list = new ArrayList<Node>();
		try {
			
			this.dao = new NodeDaoImpl(con);
			list = dao.getAllNodeData();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				this.con.close();
				
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	
	
}
