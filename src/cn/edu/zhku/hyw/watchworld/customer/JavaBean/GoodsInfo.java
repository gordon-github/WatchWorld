package cn.edu.zhku.hyw.watchworld.customer.JavaBean;

public class GoodsInfo
{
	private int GoodsID;
	private String GoodsName;
	private String GoodsPicturePath;
	private float Price;
	private String Brand;
	private String Color;
	private String Model;
	private int Number; //库存
	private int StoreID;
	private int SalesVolumes;
	
	public int getGoodsID()
	{
		return GoodsID;
	}
	public void setGoodsID(int goodsID)
	{
		GoodsID = goodsID;
	}
	public String getGoodsName()
	{
		return GoodsName;
	}
	public void setGoodsName(String goodsName)
	{
		GoodsName = goodsName;
	}
	public String getGoodsPicturePath()
	{
		return GoodsPicturePath;
	}
	public void setGoodsPicturePath(String goodsPicturePath)
	{
		GoodsPicturePath = goodsPicturePath;
	}
	public float getPrice()
	{
		return Price;
	}
	public void setPrice(float price)
	{
		Price = price;
	}
	public String getBrand()
	{
		return Brand;
	}
	public void setBrand(String brand)
	{
		Brand = brand;
	}
	public String getColor()
	{
		return Color;
	}
	public void setColor(String color)
	{
		Color = color;
	}

	public String getModel()
	{
		return Model;
	}
	public void setModel(String model)
	{
		Model = model;
	}
	public int getNumber()
	{
		return Number;
	}
	public void setNumber(int number)
	{
		Number = number;
	}
	public int getStoreID()
	{
		return StoreID;
	}
	public void setStoreID(int storeID)
	{
		StoreID = storeID;
	}
	public int getSalesVolumes()
	{
		return SalesVolumes;
	}
	public void setSalesVolumes(int salesVolumes)
	{
		SalesVolumes = salesVolumes;
	}
}
