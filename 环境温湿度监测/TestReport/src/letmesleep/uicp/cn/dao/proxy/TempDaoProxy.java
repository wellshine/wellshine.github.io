package letmesleep.uicp.cn.dao.proxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConn;

import beans.Temperature;
import letmesleep.uicp.cn.dao.TempDao;
import letmesleep.uicp.cn.dao.impl.TempDaoImpl;

public class TempDaoProxy implements TempDao{
	
	private Connection con;
	private TempDao dao = null;
	
	public TempDaoProxy(){
		//  ≥ı ºªØcon   ****
		this.con = JdbcConn.getConnection();
	}

	public List<Temperature> getAllTempData() {
		// TODO Auto-generated method stub

		List<Temperature> list = new ArrayList<Temperature>();
		try {
			
			this.dao = new TempDaoImpl(con);
			list = dao.getAllTempData();
			
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
