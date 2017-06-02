package cn.edu.zhku.hyw.watchworld.store.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.hyw.watchworld.Utils.JdbcUtil;
import cn.edu.zhku.hyw.watchworld.store.Dao.GoodsInfoDao;
import cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.store.JavaBean.PageBean;

public class GoodsInfoDaoImpl implements GoodsInfoDao {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	@Override
	public boolean findAllGoodsBykey(int storeID,String key,PageBean<GoodsInfo> pb) {
		/**
		 * 查询总记录数;并设置到pb对象中 
		 */
		int totalCount = this.getTotalCount(key);
			pb.setTotalCount(totalCount);
		
		System.out.println("totalCount:"+pb.getTotalCount());
		
		/*
		 * 问题： jsp页面，
		 * 				如果中记录数为零,直接报错!
		 * 				如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0或者总记录数为0;    当前页设置当前页为1;
		 * 	   2. 如果当前页 >= 最大页数；  当前页设置为最大页数
		 */
		// 判断
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		// 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		System.out.println("index:"+index+"count:"+count);
		
		
		// 分页查询数据;  把查询到的数据设置到pb对象中
		String sql = "select * from goods_info where StoreID=? and GoodsName like ? limit ?,?";
		
		try {
			conn=JdbcUtil.getConn();
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, storeID);
			stmt.setString(2, "%"+key+"%");
			stmt.setInt(3, index);
			stmt.setInt(4, count);
			rs = stmt.executeQuery();
			List<GoodsInfo> list = new ArrayList<GoodsInfo>();
			while (rs.next()) {
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setGoodsID(rs.getInt("GoodsID"));
				goodsInfo.setGoodsName(rs.getString("GoodsName"));
				goodsInfo.setGoodsPicturePath(rs.getString("GoodsPicturePath"));
				goodsInfo.setPrice(rs.getInt("Price"));
				goodsInfo.setBrand(rs.getString("Brand"));
				goodsInfo.setColor(rs.getString("Color"));
				goodsInfo.setModel(rs.getString("Model"));
				goodsInfo.setNumber(rs.getInt("Number"));
				goodsInfo.setSalesVolumes(rs.getInt("SalesVolumes"));
				goodsInfo.setStoreID(rs.getInt("StoreID"));
				list.add(goodsInfo);
			}
			pb.setPageData(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			JdbcUtil.close(conn, stmt, rs);	 
		}
		
		return false;
	}
	
	@Override
	public boolean findAllGoods(int StoreID, PageBean<GoodsInfo> pb) {
		/**
		 * 查询总记录数;并设置到pb对象中 
		 */
		int totalCount = this.getTotalCount(StoreID);
			pb.setTotalCount(totalCount);
		
		System.out.println("totalCount:"+pb.getTotalCount());
		
		/*
		 * 问题： jsp页面，
		 * 				如果中记录数为零,直接报错!
		 * 				如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0或者总记录数为0;    当前页设置当前页为1;
		 * 	   2. 如果当前页 >= 最大页数；  当前页设置为最大页数
		 */
		// 判断
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		// 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		System.out.println("index:"+index+"count:"+count);
		
		
		// 分页查询数据;  把查询到的数据设置到pb对象中
		String sql = "select * from goods_info where StoreID=? limit ?,?";
		
		try {
			conn=JdbcUtil.getConn();
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, StoreID);
			stmt.setInt(2, index);
			stmt.setInt(3, count);
			rs = stmt.executeQuery();
			List<GoodsInfo> list = new ArrayList<GoodsInfo>();
			while (rs.next()) {
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setGoodsID(rs.getInt("GoodsID"));
				goodsInfo.setGoodsName(rs.getString("GoodsName"));
				goodsInfo.setGoodsPicturePath(rs.getString("GoodsPicturePath"));
				goodsInfo.setPrice(rs.getInt("Price"));
				goodsInfo.setBrand(rs.getString("Brand"));
				goodsInfo.setColor(rs.getString("Color"));
				goodsInfo.setModel(rs.getString("Model"));
				goodsInfo.setNumber(rs.getInt("Number"));
				goodsInfo.setSalesVolumes(rs.getInt("SalesVolumes"));
				goodsInfo.setStoreID(rs.getInt("StoreID"));
				list.add(goodsInfo);
			}
			pb.setPageData(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			JdbcUtil.close(conn, stmt, rs);	 
		}
		
		return false;
	}

	@Override
	public int getTotalCount(int StoreID) {
		// TODO Auto-generated method stub
		try {
			conn=JdbcUtil.getConn();
			String sql = "select count(*) from goods_info where StoreID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,StoreID);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally { 
			JdbcUtil.close(conn, stmt, rs);	 
		}
		
		
	}
	
	@Override
	public int getTotalCount(String key) {
		// TODO Auto-generated method stub
		try {
			conn=JdbcUtil.getConn();
			String sql = "select count(*) from goods_info where GoodsName like ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+key+"%");
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally { 
			JdbcUtil.close(conn, stmt, rs);	 
		}
		
		
	}

	@Override
	public boolean addGoods(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub

		try {

			// 获取连接
			conn = JdbcUtil.getConn();

			String sql = "INSERT INTO goods_info("
					+ "GoodsName,GoodsPicturePath,Price,Brand,"					+ "Color,"
					+ "Model,Number,StoreID,SalesVolumes) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, goodsInfo.getGoodsName());
			stmt.setString(2, goodsInfo.getGoodsPicturePath());
			stmt.setInt(3, goodsInfo.getPrice());
			stmt.setString(4, goodsInfo.getBrand());
			stmt.setString(5, goodsInfo.getColor());
			stmt.setString(6, goodsInfo.getModel());
			stmt.setInt(7, goodsInfo.getNumber());
			stmt.setInt(8, goodsInfo.getStoreID());
			stmt.setInt(9, goodsInfo.getSalesVolumes());

			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		return false;
	}

	@Override
	public boolean deleteGoods(int goodsID) {
		// TODO Auto-generated method stub
		try {
			// 获取连接
			conn = JdbcUtil.getConn();
			String sql = "DELETE FROM goods_info WHERE GoodsID=?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setInt(1, goodsID);

			// 执行
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
	public boolean updateGoods(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		try {
			// 获取连接
			conn = JdbcUtil.getConn();

			String sql = "UPDATE goods_info SET GoodsName=?,GoodsPicturePath=?,Price=?,Brand=?,Color=?,Model=?,Number=? WHERE GoodsID=?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setString(1, goodsInfo.getGoodsName());
			stmt.setString(2, goodsInfo.getGoodsPicturePath());
			stmt.setInt(3, goodsInfo.getPrice());
			stmt.setString(4, goodsInfo.getBrand());
			stmt.setString(5, goodsInfo.getColor());
			stmt.setString(6, goodsInfo.getModel());
			stmt.setInt(7, goodsInfo.getNumber());
			stmt.setInt(8, goodsInfo.getGoodsID());

			// 执行
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
	public GoodsInfo findGoodsByID(int GoodsID,int StoreID) {
		String sql = "select * from goods_info where GoodsID=? and StoreID=?";
		
		try {
			conn=JdbcUtil.getConn();
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, GoodsID);
			stmt.setInt(2, StoreID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setGoodsID(rs.getInt("GoodsID"));
				goodsInfo.setGoodsName(rs.getString("GoodsName"));
				goodsInfo.setGoodsPicturePath(rs.getString("GoodsPicturePath"));
				goodsInfo.setPrice(rs.getInt("Price"));
				goodsInfo.setBrand(rs.getString("Brand"));
				goodsInfo.setColor(rs.getString("Color"));
				goodsInfo.setModel(rs.getString("Model"));
				goodsInfo.setNumber(rs.getInt("Number"));
				goodsInfo.setSalesVolumes(rs.getInt("SalesVolumes"));
				goodsInfo.setGoodsID(rs.getInt("GoodsID"));
				return goodsInfo;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			JdbcUtil.close(conn, stmt, rs);	 
		}
		return null;
	}


	@Override
	public List<GoodsInfo> findGoodsByBrand(String GoodsName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateGoodsSalesVolumes(int GoodsID, int number) {
		try {
			// 获取连接
			conn = JdbcUtil.getConn();

			String sql = "UPDATE goods_info SET SalesVolumes=SalesVolumes+? WHERE GoodsID=?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setInt(1,number);
			stmt.setInt(2, GoodsID);

			// 执行
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
	public boolean updateGoodsNum(int GoodsID, int number) {
		// TODO Auto-generated method stub
		try {
			// 获取连接
			conn = JdbcUtil.getConn();

			String sql = "UPDATE goods_info SET Number=? WHERE GoodsID=?";

			// 创建PreparedStatement
			stmt = conn.prepareStatement(sql);

			// 设置参数
			stmt.setInt(1,number);
			stmt.setInt(2, GoodsID);

			// 执行
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt);
		}
		return false;
	}

	
}
