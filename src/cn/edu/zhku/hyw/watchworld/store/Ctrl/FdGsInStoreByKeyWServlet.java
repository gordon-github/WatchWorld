package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.store.JavaBean.PageBean;
import cn.edu.zhku.hyw.watchworld.store.JavaBean.StoreInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.GoodsInfoService;
import cn.edu.zhku.hyw.watchworld.store.Service.StoreInfoService;
@WebServlet("/store/FdGsInStoreByKeyWServlet")
public class FdGsInStoreByKeyWServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		try {
			int storeID = Integer.parseInt(request.getParameter("StoreID"));
			String key =request.getParameter("Key");
			String currPage = request.getParameter("currentPage");
			System.out.println("key:"+key);
			// 判断
			if (currPage == null || "".equals(currPage.trim())){
				currPage = "0";  	// 第一次访问，设置当前页为0;后续在dao层设置为1;
			}
			int currentPage = Integer.parseInt(currPage);
			PageBean<GoodsInfo> pageBean =new PageBean<GoodsInfo>();
			pageBean.setCurrentPage(currentPage);
			pageBean.setPageCount(10);
			GoodsInfoService gInfoService = new GoodsInfoService(); // 【pageBean已经被dao填充了数据】
			boolean flag = gInfoService.findAllGoods(storeID,key, pageBean);
			StoreInfoService sInfoService = new StoreInfoService();
			StoreInfo storeInfo = sInfoService.findStoreByID(storeID);
			
			if(flag==true){
				request.setAttribute("Key", key);
				request.setAttribute("StoreName", storeInfo.getStoreName());
				request.setAttribute("StoreID", storeID);
				request.setAttribute("PageBean", pageBean);
				request.getRequestDispatcher("/store/store.jsp").forward(request, response);
			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendRedirect(request.getContextPath()+"/store/login.jsp");
//		}
		
	}

}
