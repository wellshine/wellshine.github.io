package letmesleep.uicp.cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Temperature;

import letmesleep.uicp.cn.dao.TempDao;

public class TempDaoImpl implements TempDao {
	private Connection conn;
	public TempDaoImpl(Connection con){
		this.conn=con;
	}
	public List<Temperature> getAllTempData() {
		// TODO Auto-generated method stub
		List<Temperature> all=new ArrayList<Temperature>();
		PreparedStatement pstmt=null;
		String sql="SELECT temperature,date FROM ResponseData";
		try {
			pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 执行查询操作
			while (rs.next()) {
				Temperature temp=new Temperature();
				temp.setTemperature(rs.getInt(1));
				temp.setDate(rs.getString(2));


				all.add(temp); // 所有的内容向集合中插入
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally { // 不管如何抛出，最终肯定是要进行数据库的关闭操作的
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
