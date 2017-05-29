package cn.edu.zhku.hyw.watchworld.customer.JavaBean;

/**
 * 此类为购物车商品信息的映射
 *
 */
public class ShoppingCartInfo
{
	private int goodsID;
	private String goodsName;
	private String goodsPicturePath;
	private float price;
	private int amount; //购物车中商品的数量
	private int number; //库存
	

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getGoodsID()
	{
		return goodsID;
	}
	public void setGoodsID(int goodsID)
	{
		this.goodsID = goodsID;
	}
	public String getGoodsName()
	{
		return goodsName;
	}
	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}
	public String getGoodsPicturePath()
	{
		return goodsPicturePath;
	}
	public void setGoodsPicturePath(String goodsPicturePath)
	{
		this.goodsPicturePath = goodsPicturePath;
	}
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
