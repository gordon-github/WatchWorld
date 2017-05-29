package cn.edu.zhku.hyw.watchworld.store.JavaBean;

import java.util.List;

public class OrderInfo<E> {
	private    String            orderID      ;
	 private   String            orderStatus  ;
	 private   String         paymentTime  ;
	 private   String         sendGoodsTime;
	 private   String            storeName    ;
	 private   String            userName     ;
	 private   int               totalPrice   ;
	 private   String  				address;
	 private String  telephone;
	 private   List<OrderGoods>  orderGoods   ;
	 public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	 
	 
	 public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}
	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String string) {
		this.paymentTime = string;
	}
	public String getSendGoodsTime() {
		return sendGoodsTime;
	}
	public void setSendGoodsTime(String sendGoodsTime) {
		this.sendGoodsTime = sendGoodsTime;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderInfo [orderID=" + orderID + ", orderStatus=" + orderStatus
				+ ", paymentTime=" + paymentTime + ", sendGoodsTime="
				+ sendGoodsTime + ", storeName=" + storeName + ", userName="
				+ userName + ", totalPrice=" + totalPrice + ", address="
				+ address + ", telephone=" + telephone + ", orderGoods="
				+ orderGoods + "]";
	}
	
	
	
	
	
	
	
	
}
