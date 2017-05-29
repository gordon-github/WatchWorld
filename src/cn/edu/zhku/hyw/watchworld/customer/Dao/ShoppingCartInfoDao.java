package cn.edu.zhku.hyw.watchworld.customer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.hyw.watchworld.Utils.JdbcUtil;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.ShoppingCartInfo;

public class ShoppingCartInfoDao
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ShoppingCartInfoDao()
	{
		conn = JdbcUtil.getConn();
	}
	
	/**
	 * 通过用户账号搜索其购物车信息
	 * @param userID
	 * @return
	 */
	public List<ShoppingCartInfo> findByUserID(String userID)
	{
		List<ShoppingCartInfo> dataList = new ArrayList<ShoppingCartInfo>();
		String sql = "select goods_info.GoodsID,goods_info.GoodsName,goods_info.GoodsPicturePath," +
				"goods_info.Price,goods_info.Number,user_goods.Number " +
				"from goods_info,user_goods " +
				"where user_goods.UserID=? and goods_info.GoodsID=user_goods.GoodsID";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				ShoppingCartInfo data = new ShoppingCartInfo();
				data.setGoodsID(rs.getInt("GoodsID"));
				data.setGoodsName(rs.getString("GoodsName"));
				data.setGoodsPicturePath(rs.getString("GoodsPicturePath"));
				data.setPrice(rs.getFloat("Price"));
				data.setNumber(rs.getInt("goods_info.Number"));
				data.setAmount(rs.getInt("user_goods.Number"));
				dataList.add(data);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, pstmt, rs);
		return dataList;
	}
}
