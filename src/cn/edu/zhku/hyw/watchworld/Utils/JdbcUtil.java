package cn.edu.zhku.hyw.watchworld.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	/**
	 * 抽取获取连接对象的方法
	 */

	private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	private static final String DBURL = 
			"jdbc:mysql://localhost:3306/watch-world?autoReconnection=true&useSSL=false&useUnicode=true&characterEncoding=utf8";
	private static final String DBUSER = "root";
	private static final String DBPWD = "";
	
	public static Connection getConn(){
		try {
			Class.forName(DRIVERNAME);
			Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 释放资源的方法
	 */
	public static void close(Connection conn,Statement stmt){
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
	}
}
