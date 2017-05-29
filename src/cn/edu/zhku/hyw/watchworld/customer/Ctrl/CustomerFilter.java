package cn.edu.zhku.hyw.watchworld.customer.Ctrl;

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

import net.sf.json.JSONObject;

/**
 * Servlet Filter implementation class CustomerFilter
 */
@WebFilter({"/customer/accountSecurity/*",
			"/customer/order/*",
			"/customer/personalData/*",
			"/customer/personalOrder/*",
			"/customer/shoppingCart/*",
			"/customer/address/*",
			"/customer/logout/*",
			"/customer/accountSecurity.jsp",
			"/customer/address.jsp",
			"/customer/homepage.jsp",
			"/customer/order.jsp",
			"/customer/orderSubmitStatus.jsp",
			"/customer/personalData.jsp",
			"/customer/personalOrder.jsp",
			"/customer/shoppingCart.jsp"})
public class CustomerFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public CustomerFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String customerId = (String)httpRequest.getSession().getAttribute("customerId");
		if(customerId == null || customerId.equals(""))
		{
			if (httpRequest.getHeader("x-requested-with") != null && 
					 httpRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) 
			{ 
		         // ajax请求
				String st = "{'status':'302','rdHref':'customer/login.jsp'}"; 
				JSONObject json = JSONObject.fromObject(st);
				PrintWriter out = response.getWriter();
				out.print(json);
			}
			else
			{
				httpRequest.getRequestDispatcher("/customer/login.jsp").forward(httpRequest, httpResponse);
			}
		}
		else
		{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
