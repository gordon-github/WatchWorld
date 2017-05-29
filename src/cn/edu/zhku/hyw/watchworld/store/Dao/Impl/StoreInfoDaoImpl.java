package cn.edu.zhku.hyw.watchworld.store.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import cn.edu.zhku.hyw.watchworld.Utils.JdbcUtil;
import cn.edu.zhku.hyw.watchworld.store.Dao.StoreInfoDao;
import cn.edu.zhku.hyw.watchworld.store.JavaBean.StoreInfo;

public class StoreInfoDaoImpl implements StoreInfoDao {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@Override
	public boolean  addStore(StoreInfo storeInfo) {
		
		try {
			
			// 获取连接
			conn = JdbcUtil.getConn();
			
			String sql = "INSERT INTO store_info(LoginName,Pwd,StoreName,Owner,RegTime,Telephone) VALUES(?,?,?,?,?,?)";
			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, storeInfo.getLoginName());
			stmt.setString(2, storeInfo.getPassword());
			stmt.setString(3, storeInfo.getStoreName());
			stmt.setString(4, storeInfo.getOwner());
			stmt.setString(5, storeInfo.getRegTime());
			stmt.setString(6, storeInfo.getTelephone());
			
			stmt.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(conn, stmt);
		}
		return false;
		
	
	}

	@Override
	public void deleteStore(int StoreID) {
		// TODO Auto-generated method stub
		try{
			//获取连接
			conn = JdbcUtil.getConn();
			
			String sql = "DELETE FROM store_info WHERE StoreID=?";
			
			//创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			//设置参数
			stmt.setInt(1,StoreID);
			//执行
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(conn, stmt);
		}
	}

	@Override
	public boolean updateStore(StoreInfo storeInfo) {
		// TODO Auto-generated method stub
		try{
			//获取连接
			conn = JdbcUtil.getConn();
			
			String sql = "UPDATE store_info SET Pwd=?,StoreName=?,Telephone=? WHERE StoreID=?";
			
			//创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			//设置参数
			stmt.setString(1,  storeInfo.getPassword());
			stmt.setString(2,  storeInfo.getStoreName());
			stmt.setString(3,  storeInfo.getTelephone());
			stmt.setInt(4,  storeInfo.getStoreID());
			//执行
			stmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(conn, stmt);
		}
		return false;
	}

	@Override
	public List<StoreInfo> findStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreInfo findStoreByID(int StoreID) {
		// TODO Auto-generated method stub
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT * FROM store_info where StoreID=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, StoreID);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				StoreInfo storeInfo = new StoreInfo();
				storeInfo.setStoreID(StoreID);
				storeInfo.setStoreName(rs.getString("StoreName"));
				storeInfo.setLoginName(rs.getString("LoginName"));
				storeInfo.setPassword(rs.getString("Pwd"));
				storeInfo.setTelephone(rs.getString("Telephone"));
				storeInfo.setOwner(rs.getString("Owner"));
				storeInfo.setRegTime(rs.getString("RegTime"));
				return storeInfo;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return null;
	}


	@Override
	public int checkLoginName(String loginName) {
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT StoreID FROM store_info where LoginName=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, loginName);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return 0;
	}

	@Override
	public boolean checkStoreName(String storeName) {
		// TODO Auto-generated method stub
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT * FROM store_info where StoreName=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, storeName);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return false;
	}

	@Override
	public boolean checkTelephone(String telephone) {
		// TODO Auto-generated method stub
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT * FROM store_info where Telephone=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, telephone);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return false;
	}

	@Override
	public boolean checkOwner(String owner) {
		// TODO Auto-generated method stub
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT * FROM store_info where Owner=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, owner);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return false;
	}

	@Override
	public boolean checkLoginNameAndPwd(String loginName, String pwd) {
		// TODO Auto-generated method stub
		try { // 获取连接
			conn = JdbcUtil.getConn();
			String sql = "SELECT Pwd FROM store_info where LoginName=? ";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, loginName);

			// 执行
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String pwd2=rs.getString(1);
				if(pwd.equals(pwd2)){
				return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return false;
	}
}
