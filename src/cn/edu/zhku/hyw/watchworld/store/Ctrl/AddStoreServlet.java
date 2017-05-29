package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.hyw.watchworld.store.JavaBean.StoreInfo;
import cn.edu.zhku.hyw.watchworld.store.Service.StoreInfoService;
@WebServlet("/store/AddStoreServlet")
public class AddStoreServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String LoginName=request.getParameter("LoginName");
		String Pwd      =request.getParameter("Pwd");
		String StoreName=request.getParameter("StoreName");
		String Owner    =request.getParameter("Owner");
		String Telephone=request.getParameter("Telephone");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
		String RegisterTime=df.format(new Date());// new Date()为获取当前系统时间

		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setLoginName(LoginName);
		storeInfo.setPassword(Pwd) ;   
		storeInfo.setStoreName(StoreName);
		storeInfo.setOwner    (Owner);
		storeInfo.setTelephone(Telephone);
		storeInfo.setRegTime(RegisterTime);
		
		
		StoreInfoService service=new StoreInfoService();
		int store = service.addStore(storeInfo);//注册成功返回StoreID
		if (store==0) {
			System.out.println("注册失败");
			response.sendRedirect(request.getContextPath()+"/store/login.jsp");
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("LoginName", LoginName);
			session.setAttribute("StoreID", store+"");
		}
		/*PrintWriter out = response.getWriter();
		out.print();
		out.flush();
		out.close();*/
	}

}
