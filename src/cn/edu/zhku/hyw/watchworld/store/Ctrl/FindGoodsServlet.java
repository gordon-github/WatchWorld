package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.GoodsInfoService;
@WebServlet("/store/FindGoodsServlet")
public class FindGoodsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String storeIDString = request.getParameter("StoreID");
	        String goodIDString= request.getParameter("GoodsID");
	        String action=request.getParameter("action");
	        System.out.println("StoreID:"+storeIDString);
	        int goodsID=Integer.parseInt(goodIDString);
	        int storeID=Integer.parseInt(storeIDString);
	        GoodsInfoService service = new GoodsInfoService();
	       GoodsInfo goodsInfo = service.findGoodsByID(goodsID, storeID);
	        if(goodsInfo!=null){
	        	request.setAttribute("goodsInfo", goodsInfo);
	        }
	        System.out.println(action);
	        if(action.equals("show")){
	        	
	        	request.getRequestDispatcher("/store/goods_show.jsp").forward(request, response);
	        	
	        }else if (action.equals("update")) {
	        	request.getRequestDispatcher("/store/goods_update.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/store/login.jsp");
		}
		
        
	}

}
