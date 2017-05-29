<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'store_index_top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="store/CSS/index.css">
	<script type="text/javascript" src="store/JS/jquery-3.2.0.min.js"></script> 
	 <script type="text/javascript" src="store/JS/login.js"></script>

  </head>
  
  <body>
      	<div id="header">
      		<div class="headerFlag">
		  			<a href="javascript:void(0)" onclick="logout()">退出</a>
	  		</div>
			<div id="register" class="headerFlag">
				<a href="javascript:void(0)" onclick="registerStore()">店铺注册</a>
			</div>
		<%
	  		String LoginName = (String)session.getAttribute("LoginName");
	  		String StoreID=(String)session.getAttribute("StoreID");
	  		if(LoginName!=null&&StoreID!=null&&!("".equals(StoreID)))
	  		{
	  		
		%> 
			<div class="headerFlag">
				<a href="javascript:void(0)" onclick="myStore('<%=StoreID%>')">我的店铺</a>
			</div>
	  		<div class="headerFlag">
	  			欢迎回来！<font color="red"><%=LoginName %> </font>
	  		</div>
		  		
		<%  		
	  		}
	  	%>
  	</div>
  	  	<div id="titleContainer">
  			<img id="logo" src="<%= path %>/store/IMG/logo.png">
  		<p id="mainTitle">店铺管理</p>
  	</div>
  </body>
</html>
