package cn.edu.zhku.hyw.watchworld.store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.edu.zhku.hyw.watchworld.store.Service.StoreInfoService;
@WebServlet("/store/LoginStoreServlet")
public class LoginStoreServlet extends HttpServlet {

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


		PrintWriter out = response.getWriter();
		String LoginName=request.getParameter("LoginName");
		String Pwd=request.getParameter("Pwd");
		if (LoginName==null||Pwd==null) {
			response.sendRedirect(request.getContextPath()+"/store/login.jsp");
		} else {

		
		System.out.println(LoginName+":"+Pwd);
		StoreInfoService service=new StoreInfoService();
		JSONObject resultJson=new JSONObject();
		boolean flag=service.checkLoginName(LoginName);
		if (flag==false) {
			resultJson.put("flag", "LoginNameNoFound");
		} else {
			flag=service.checkLoginNameAndPwd(LoginName, Pwd);
			if (flag==true) {
				resultJson.put("flag", "PasswordCorrect");
				
				request.getSession().setAttribute("LoginName", LoginName);
				request.getSession().setAttribute("StoreID",service.findStoreID(LoginName)+"");
			}else{
				resultJson.put("flag", "PasswordWrong");
				}
			}
			out.print(resultJson);
			out.flush();
			out.close();
		}
	}

}

