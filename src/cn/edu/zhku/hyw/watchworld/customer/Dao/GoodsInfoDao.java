package cn.edu.zhku.hyw.watchworld.customer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zhku.hyw.watchworld.Utils.JdbcUtil;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.UserInfo;

public class GoodsInfoDao
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public GoodsInfoDao()
	{
		conn = JdbcUtil.getConn();
	}
	
	/**
	 * 获取通过商品名模糊查找的到的结果的数量
	 * @param goodsName
	 * @return
	 */
	public int getAmountOfDataByGoodsName(String goodsName)
	{
		int amount = 0;
		String sql = "select count(*) as amount from goods_info where GoodsName like ?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + goodsName + "%");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				amount = rs.getInt("amount");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt, rs);
		return amount;
	}
	
	/**
	 * 通过商品ID查找商品的信息
	 * @param goodsID
	 * @return
	 */
	public GoodsInfo findByGoodsID(int goodsID)
	{
		GoodsInfo info = null;
		String sql = "select * from goods_info where GoodsID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodsID);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				info = new GoodsInfo();
				info.setGoodsID(rs.getInt("GoodsID"));
				info.setGoodsName(rs.getString("GoodsName"));
				info.setGoodsPicturePath(rs.getString("GoodsPicturePath"));
				info.setPrice(rs.getFloat("Price"));
				info.setBrand(rs.getString("Brand"));
				info.setColor(rs.getString("Color"));
				info.setModel(rs.getString("Model"));
				info.setNumber(rs.getInt("Number"));
				info.setStoreID(rs.getInt("StoreID"));
				info.setSalesVolumes(rs.getInt("SalesVolumes"));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt, rs);
		return info;
	}
	
	/**
	 * 更新商品库存
	 * @param number
	 * @param goodsID
	 * @return
	 */
	public boolean updateNumberByGoodsID(int number, int goodsID)
	{
		boolean flag = false;
		String sql = "update goods_info set Number=? where GoodsID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, goodsID);
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
	 * 更新商品的销量
	 * @param sv
	 * @param goodsID
	 * @return
	 */
	public boolean updateSalesVolumesByGoodsID(int sv, int goodsID)
	{
		boolean flag = false;
		String sql = "update goods_info set SalesVolumes=? where GoodsID=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sv);
			pstmt.setInt(2, goodsID);
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
