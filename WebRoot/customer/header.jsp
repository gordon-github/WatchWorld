<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>网页头部母版</title>
	<link rel="stylesheet" type="text/css" href="<%= path %>/customer/CSS/header.css">
	<script type="text/javascript" src="<%= path %>/customer/JS/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="<%= path %>/customer/JS/header.js"></script>

  </head>
  
  <body>
      <div id="header">
  		<div id="adminCenter" class="headerFlag">
  			<a href="<%=path%>/admin/login.jsp">运营商中心</a>
  		</div>
  		<div class="headerFlag">|</div>
  		<div id="storeCenter" class="headerFlag">
  			<a href="<%=path%>/store/store_index.jsp">商家中心</a>
  		</div>
	 	<div class="headerFlag">|</div>
	 	<div id="shoppingCart" class="headerFlag">
	 		<a href="<%= path %>/customer/shoppingCart.jsp">购物车</a>
	 	</div>
		<div id="register" class="headerFlag">
				<a href="<%= path %>/customer/register.jsp">免费注册</a>
		</div>
		<%
	  		String customerId = (String)session.getAttribute("customerId");
	  		if(customerId == null)
	  		{
		%>
		  		<div id="loggingStatus" class="headerFlag">
		  			您好！<a href="<%= path %>/customer/login.jsp">请登录</a>
		  		</div>
		<%  		
	  		}
	  		else
	  		{
	  	%>
	  			<div id="loggingStatus" class="headerFlag">
	  				欢迎使用！<a href="<%= path %>/customer/homepage.jsp"><%= customerId %></a>&nbsp;&nbsp;<a style="cursor:pointer" onclick="logout()">注销</a>
	  			</div>
	  	<%
	  		}
	  	%>
	  	<div class="headerFlag">|</div>
	  	<div class="headerFlag">
  			<a href="<%= path %>/index.jsp">首页</a>
  		</div>
  	</div>
  </body>
</html>
