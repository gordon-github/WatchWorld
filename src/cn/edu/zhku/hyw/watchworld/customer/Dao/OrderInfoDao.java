package cn.edu.zhku.hyw.watchworld.customer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.hyw.watchworld.Utils.JdbcUtil;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.OrderGoods;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.OrderInfo;

public class OrderInfoDao
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public OrderInfoDao()
	{
		conn = JdbcUtil.getConn();
	}
	
	public boolean doCreate(OrderInfo og)
	{
		boolean flag = false; //插入成功与否的标志
		String sql = "insert into order_info values (?,?,?,?,?,?,?,?,?,?)";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, og.getOrderID());
			pstmt.setString(2, og.getOrderStatus());
			pstmt.setString(3, og.getPaymentTime());	
			pstmt.setString(4, og.getSendGoodsTime());
			pstmt.setString(5, og.getStoreName());
			pstmt.setString(6, og.getUserName());
			pstmt.setInt(7, og.getTotalPrice());
			pstmt.setString(8, og.getAddress());
			pstmt.setString(9, og.getTelephone());
			pstmt.setString(10, og.getUserID());
			if(pstmt.executeUpdate() == 1)
			{
				flag = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt);
		return flag;
	}
	
	/**
	 * 通过userId查找OrderInfo
	 * @param userID
	 * @return
	 */
	public List<OrderInfo> findByUserId(String userID)
	{
		List<OrderInfo> oiList = new ArrayList<OrderInfo>();
		String sql = "select * from order_info where UserId=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				OrderInfo info = new OrderInfo();
				info.setOrderID(rs.getString("OrderID"));
				info.setOrderStatus(rs.getString("OrderStatus"));
				
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
				Timestamp paymentTime = rs.getTimestamp("PaymentTime");
				if(paymentTime == null)
				{
					info.setPaymentTime("-");
				}
				else
				{
					info.setPaymentTime(sdf.format(paymentTime));
				}
				Timestamp sendGoodsTime = rs.getTimestamp("SendGoodsTime");
				if(sendGoodsTime == null)
				{
					info.setSendGoodsTime("-");
				}
				else
				{
					info.setSendGoodsTime(sdf.format(sendGoodsTime));
				}
				
				info.setStoreName(rs.getString("StoreName"));
				info.setUserName(rs.getString("UserName"));
				info.setTotalPrice(rs.getInt("TotalPrice"));
				info.setAddress(rs.getString("Address"));
				info.setTelephone(rs.getString("Telephone"));
				info.setUserID(rs.getString("UserID"));
				oiList.add(info);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt, rs);
		return oiList;
	}
	
	/**
	 * 通过userID和orderStatus查找OrderInfo
	 * @param userID
	 * @param orderStatus
	 * @return
	 */
	public List<OrderInfo> findByUserIdandStatus(String userID, String orderStatus)
	{
		List<OrderInfo> oiList = new ArrayList<OrderInfo>();
		String sql = "select * from order_info where UserId=? and OrderStatus=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, orderStatus);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				OrderInfo info = new OrderInfo();
				info.setOrderID(rs.getString("OrderID"));
				info.setOrderStatus(rs.getString("OrderStatus"));
				info.setPaymentTime(rs.getString("PaymentTime"));
				info.setSendGoodsTime(rs.getString("SendGoodsTime"));
				info.setStoreName(rs.getString("StoreName"));
				info.setUserName(rs.getString("UserName"));
				info.setTotalPrice(rs.getInt("TotalPrice"));
				info.setAddress(rs.getString("Address"));
				info.setTelephone(rs.getString("Telephone"));
				info.setUserID(rs.getString("UserID"));
				oiList.add(info);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt, rs);
		return oiList;
	}
	
	public boolean delByOrderID(String orderID)
	{
		boolean flag = false; //删除成功与否的标志
		String sql = "delete from order_info where OrderID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderID);
			if(pstmt.executeUpdate() == 1)
			{
				flag = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt);
		return flag;
	}
	
	/**
	 * 更新订单状态
	 * @param orderStatus
	 * @param orderID
	 * @return
	 */
	public boolean updateStatusByOrderID(String orderStatus, String orderID)
	{
		boolean flag = false;
		String sql = "update order_info set OrderStatus=? where OrderID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderStatus);
			pstmt.setString(2, orderID);
			if(pstmt.executeUpdate() == 1)
			{
				flag = true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt);
		return flag;
	}
}
