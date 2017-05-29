package cn.edu.zhku.hyw.watchworld.customer.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import cn.edu.zhku.hyw.watchworld.customer.Dao.GoodsInfoDao;
import cn.edu.zhku.hyw.watchworld.customer.Dao.OrderGoodsDao;
import cn.edu.zhku.hyw.watchworld.customer.Dao.OrderInfoDao;
import cn.edu.zhku.hyw.watchworld.customer.Dao.StoreInfoDao;
import cn.edu.zhku.hyw.watchworld.customer.Dao.UserDataDao;
import cn.edu.zhku.hyw.watchworld.customer.Dao.UserInfoDao;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.GoodsCounter;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.OrderGoods;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.OrderInfo;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.UserGoods;
import cn.edu.zhku.hyw.watchworld.customer.JavaBean.UserInfo;

public class OrderService
{
	private static OrderService service;
	
	private OrderService(){}
	
	public static OrderService getInstance()
	{
		if(service == null)
		{
			service = new OrderService();
		}
		return service;
	}
	
	/**
	 * 将json数据转换为GoodsCounter数据类型
	 * @param str
	 * @param userID
	 * @return
	 */
	public List<GoodsCounter> translateToGoodsCounter(String str)
	{
		List<GoodsCounter> list = new ArrayList<GoodsCounter>();
		JSONArray json = JSONArray.fromObject(str);
		list = (List<GoodsCounter>) JSONArray.toCollection(json, GoodsCounter.class);
		return list;
	}
	
	/**
	 * 通过商品ID和购买数量获取OrderGoods对象(未设置OrderID)
	 * @param paraList
	 * @return
	 */
	public List<OrderGoods> createOrderGoods(List<GoodsCounter> paraList)
	{
		List<OrderGoods> dataList = new ArrayList<OrderGoods>();
		for(GoodsCounter counter : paraList)
		{
			GoodsInfo info = (new GoodsInfoDao()).findByGoodsID(counter.getGoodsID());
			int amount = counter.getAmount();
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setGoodsID(info.getGoodsID());
			orderGoods.setGoodsName(info.getGoodsName());
			orderGoods.setStoreID(info.getStoreID());
			orderGoods.setPrice((int)info.getPrice());
			orderGoods.setBrand(info.getBrand());
			orderGoods.setColor(info.getColor());
			orderGoods.setModel(info.getModel());
			orderGoods.setGoodsPicturePath(info.getGoodsPicturePath());
			orderGoods.setBuyAmount(amount);
			dataList.add(orderGoods);
		}
		return dataList;
	}
	
	/**
	 * 获取按店铺分类的订单键值对
	 * @param paraList 源预订单数据
	 * @return 分类后的预订单数据
	 */
	public Map<String, List<OrderGoods>> createStoreGoodsMap(List<OrderGoods> paraList)
	{
		Map<String, List<OrderGoods>> dataMap = new HashMap<String, List<OrderGoods>>();
		Set<String> storeIDSet = new HashSet<String>();
		for(OrderGoods od : paraList) //抽取出paraList中所有商店的编号
		{
			storeIDSet.add(String.valueOf(od.getStoreID()));
		}
		List<String> storeIDList = new ArrayList<String>(storeIDSet);
		for(String id : storeIDList)
		{
			List<OrderGoods> odList = new ArrayList<OrderGoods>();
			for(OrderGoods od : paraList)
			{
				if(id.equals(String.valueOf(od.getStoreID())))
				{
					odList.add(od);
				}
			}
			dataMap.put(id, odList); //id是店铺的编号，odList是订单中该店铺下的商品信息
		}
		return dataMap;
	}
	
	/**
	 * 计算订单的总价
	 * @param paraList
	 * @return
	 */
	public int calcTotalMoney(List<OrderGoods> paraList)
	{
		int totalMoney = 0;
		for(OrderGoods od : paraList)
		{
			totalMoney = totalMoney + (od.getPrice() * od.getBuyAmount());
		}
		return totalMoney;
	}
	
	/**
	 * 检查下单时输入密码是否正确
	 * @param userID
	 * @param pwd
	 * @return
	 */
	public boolean checkPwd(String userID, String pwd)
	{
		boolean flag = false;
		UserInfo info = (new UserInfoDao()).findByUserID(userID);
		if(pwd.equals(info.getPwd()))
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 保存订单
	 * @param para
	 * @param userID
	 * @param address
	 * @return
	 */
	public boolean saveOrder(String para, String userID, String address)
	{
		boolean flag = true;
		List<GoodsCounter> gcList = this.translateToGoodsCounter(para);
		List<OrderGoods> ogList = this.createOrderGoods(gcList);
		Map<String, List<OrderGoods>> ogMap = this.createStoreGoodsMap(ogList);
		for(String storeID : ogMap.keySet())
		{
			String orderID = this.createOrderID();
			List<OrderGoods> saveOgList = this.updateOrderGoods(ogMap.get(storeID), orderID); //生成完整的OrderGoods对象的队列

			String paymentTime = this.createTimeStampStr(); //下单时间
			String storeName = (new StoreInfoDao()).findStoreNameByStoreID(Integer.parseInt(storeID)); //店铺名
			String userName = (new UserDataDao()).findByUserID(userID).getUserName(); //买方姓名
			int totalPrice = this.calcTotalMoney(saveOgList);
			String telephone = (new UserDataDao()).findByUserID(userID).getTelephone();
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrderID(orderID);
			orderInfo.setOrderStatus("未发货");
			orderInfo.setPaymentTime(paymentTime);
			orderInfo.setStoreName(storeName);
			orderInfo.setUserName(userName);
			orderInfo.setTotalPrice(totalPrice);
			orderInfo.setAddress(address);
			orderInfo.setTelephone(telephone);
			orderInfo.setUserID(userID);
			
			for(OrderGoods gi : saveOgList) //遍历所有订单中的商品，更新其中的库存量和销量
			{
				int goodsID = gi.getGoodsID();
				GoodsInfo inf = (new GoodsInfoDao()).findByGoodsID(goodsID); //根据goodsID查找指定商品
				int oldNumber = inf.getNumber(); //获取旧的库存量
				int oldSalesVolumes = inf.getSalesVolumes(); //获取旧的销量
				int newNumber = oldNumber - gi.getBuyAmount(); //计算新的库存
				int newSalesVolumes = oldSalesVolumes + gi.getBuyAmount(); //计算新的销量
				//更新库存
				if((new GoodsInfoDao()).updateNumberByGoodsID(newNumber, goodsID) == false)
				{
					flag = false;
				}
				//更新销量
				if((new GoodsInfoDao()).updateSalesVolumesByGoodsID(newSalesVolumes, goodsID) == false)
				{
					flag = false;
				}
			}
			
			if((new OrderInfoDao()).doCreate(orderInfo) == false) //将orderInfo存入数据库
			{
				flag = false;
			}
			
			for(OrderGoods od : saveOgList)  //依次将OrderGoods对象存入数据库
			{
				if((new OrderGoodsDao().doCreate(od)) == false)
				{
					flag = false;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 更新OrderGoods对象队列，为其填充订单编号OrderID
	 * @param paraList
	 * @return
	 */
	public List<OrderGoods> updateOrderGoods(List<OrderGoods> odList, String orderID)
	{
		for(OrderGoods od : odList)
		{
			od.setOrderID(orderID);
		}
		return odList;
	}
	
	/**
	 * 生成订单ID，订单ID由时间戳加1000到9999之间的随机数拼接生成
	 * @param userID
	 * @param storeID
	 * @return
	 */
	public String createOrderID()
	{
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("HHmmss"); 
		String str = sdf.format(ts);
		int rd = (int)(Math.random() * 8999) + 1000;
		String orderID = str + String.valueOf(rd);
		return orderID;
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public String createTimeStampStr()
	{
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		String str = sdf.format(ts);
		return str;
	}
}
