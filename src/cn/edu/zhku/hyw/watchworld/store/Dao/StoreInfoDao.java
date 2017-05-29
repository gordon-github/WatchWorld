package cn.edu.zhku.hyw.watchworld.store.Dao;

import java.util.List;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.StoreInfo;

/**
 * 店铺信息的dao接口
 * @author Administrator
 *
 */
public interface StoreInfoDao {
	public boolean addStore(StoreInfo storeInfo);//注册用户
	public void deleteStore(int StoreID);//删除店铺
	public boolean updateStore(StoreInfo storeInfo);//修改店铺
	public List<StoreInfo>  findStore();//查找所有店铺
	public StoreInfo findStoreByID(int storeID);//查找单个店铺,通过ID
	public int checkLoginName(String loginName);//判断店铺登录名
	public boolean checkStoreName(String storeName);//判断店铺名
	public boolean checkTelephone(String telephone);//判断号码
	public boolean checkOwner(String owner); //判断店主
	public boolean checkLoginNameAndPwd(String loginName,String pwd);
	
}
