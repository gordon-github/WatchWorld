package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.OrderInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.OrderInfoService;
@WebServlet("/store/OrderShowServlet")
public class OrderShowServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		System.out.println("statue:"+status);
		OrderInfoService service = new OrderInfoService();
		HttpSession session = request.getSession(false);
		int storeID= Integer.parseInt((String) session.getAttribute("StoreID"));
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		if ("accepted".equals(status)){
			List<OrderInfo> list2 = new ArrayList<OrderInfo>();
			list = service.findOrderInfoByStoreID(storeID, "已发货");
			list2 =service.findOrderInfoByStoreID(storeID, "已签收");
			
			if (list==null) {
				request.setAttribute("OrderInfo",list2);
			}else if(list2==null){
				request.setAttribute("OrderInfo",list);
			}else {
				list.addAll(list2);
				request.setAttribute("OrderInfo",list);
			}
			request.getRequestDispatcher("/store/ordershow_accept.jsp").forward(request, response);
		} else if("noaccepted".equals(status)){
			
			list =service.findOrderInfoByStoreID(storeID, "未发货");
			
			request.setAttribute("msg", request.getAttribute("msg"));
			request.setAttribute("OrderInfo", list);
			request.getRequestDispatcher("/store/ordershow_noaccept.jsp").forward(request, response);
		}
	}

}
