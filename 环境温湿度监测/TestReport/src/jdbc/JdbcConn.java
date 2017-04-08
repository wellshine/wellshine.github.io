package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//建立连接数据库的JDBC
public class JdbcConn {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
	private static String user="root";
//	private static String pass="gcy506067668";
	private static String pass="";

	
	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Statement st;
	
	
	//创建一个连接数据库的方法
	public static Connection getConnection(){
		
		//加载驱动
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pass);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
