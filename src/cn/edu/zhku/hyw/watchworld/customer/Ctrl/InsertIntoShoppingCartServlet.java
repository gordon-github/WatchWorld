package cn.edu.zhku.hyw.watchworld.customer.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.hyw.watchworld.customer.Service.ShoppingCartService;

/**
 * Servlet implementation class InsertIntoShoppingCartServlet
 */
@WebServlet("/customer/shoppingCart/insertIntoShoppingCart")
public class InsertIntoShoppingCartServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertIntoShoppingCartServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter(); 
		String userID = (String)request.getSession().getAttribute("customerId");
		if(userID == null || userID.equals("")) //未登录，无法加入购物车
		{
			out.print("refuse");
		}
		else //已登录
		{
			int goodsID = Integer.parseInt(request.getParameter("goodsID"));
			int amount = 1;
			String amountStr = request.getParameter("amount"); 
			if(amountStr == null || amountStr.equals("")) //如果数量参数不合法，则自动设置为1
			{
				amount = 1;
			}
			else 
			{
				amount = Integer.parseInt(amountStr);
			}
			ShoppingCartService service = ShoppingCartService.getInstance();
			boolean flag = service.InsertIntoShoppingCart(goodsID, userID, amount);
			if(flag == true)
			{
				out.print("{\"flag\":\"添加成功！\"}");
			}
			else
			{
				out.print("{\"flag\":\"添加失败！\"}");
			}
		}
	}

}
