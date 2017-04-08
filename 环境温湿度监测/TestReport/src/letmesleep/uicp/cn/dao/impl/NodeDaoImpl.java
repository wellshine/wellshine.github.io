package letmesleep.uicp.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Node;

import letmesleep.uicp.cn.dao.NodeDao;

public class NodeDaoImpl implements NodeDao {

	private Connection conn;
	
	public NodeDaoImpl(Connection con){
		this.conn = con;
	}
	
	public void addNodeData(Node node) {
		// TODO Auto-generated method stub
		boolean flag;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ResponseData(numid,temperature,humidity,date) VALUES (?,?,?,?) ";
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1,node.getNumid()); 
			pstmt.setInt(2,node.getTemperature()); 
			pstmt.setFloat(3,node.getHumidity()); 
//			pstmt.setFloat(4,node.getConcentration()); 
			pstmt.setString(4,node.getDate()); 
			if (pstmt.executeUpdate() > 0) {// 至少已经更新了一行
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
	
		
	}

	public Node getNodeData() {
		// TODO Auto-generated method stub
		
		List<Node> all = new ArrayList<Node>();
		PreparedStatement pstmt = null;
		String sql = "SELECT numid,temperature,humidity,date FROM ResponseData";
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Node node = new Node();
				node.setNumid(rs.getInt(1));
				node.setTemperature(rs.getInt(2));
				node.setHumidity(rs.getFloat(3));
//				node.setConcentration(rs.getFloat(4));
				node.setDate(rs.getString(4));
				all.add(node); // 所有的内容向集合中插入
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		//return all;
		if(all.size()==0)
			return null;
		return all.get(all.size()-1);
	}

	public void deleteNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	public void upDataNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	public List<Node> getAllNodeData() {
		// TODO Auto-generated method stub
		List<Node> all = new ArrayList<Node>();
		PreparedStatement pstmt = null;
		String sql = "SELECT numid,temperature,humidity,date FROM ResponseData";
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Node node = new Node();
				node.setNumid(rs.getInt(1));
				node.setTemperature(rs.getInt(2));
				node.setHumidity(rs.getFloat(3));
//				node.setConcentration(rs.getFloat(4));
				node.setDate(rs.getString(4));
				all.add(node); // 所有的内容向集合中插入
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		
		return all;
	}
	
}
