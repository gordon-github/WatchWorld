package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"/store/ChangeStatusServlet",
						"/store/DeleteGoodsServlet",
						"/store/FindGoodsServlet",
						"/store/FindStoreServlet",
						"/store/GoodsAddServlet",
						"/store/GoodsShowServlet",
						"/store/GoodsUpdateServlet",
						"/store/OrderShowServlet",
						"/store/StoreUpdateServlet",
						"/store/goods_add.jsp",
						"/store/goods_show.jsp",
						"/store/goods_update.jsp",
						"/store/ordershow_accept.jsp",
						"/store/ordershow_noaccept.jsp",
						"/store/store_index_left.jsp",
						"/store/store_index_top.jsp",
						"/store/store_index.jsp",
						"/store/store_update.jsp"
						
						})
public class PowerFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request =(HttpServletRequest)arg0;
		HttpServletResponse response =(HttpServletResponse)arg1;
		 if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) { 
	            // ajax请求
			 arg2.doFilter(arg0, arg1);
			 }
		HttpSession session= request.getSession();
		Object loginName = session.getAttribute("LoginName");
		 Object storeID = session.getAttribute("StoreID");
		if(loginName==null||storeID==null||"".equals(loginName)||"".equals(storeID)){
			response.sendRedirect(request.getContextPath()+"/store/login.jsp");
		}else{
			arg2.doFilter(arg0, arg1);
		}
	}

	


}
