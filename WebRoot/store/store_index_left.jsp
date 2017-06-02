<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>店铺后台左部</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="store/CSS/index_left.css" />

</head>
<%-- <%
	  		String LoginName = (String)session.getAttribute("LoginName");
	  		String StoreID=(String)session.getAttribute("StoreID");
		%>  --%>
<body>

	<div id="container">
		<div class="type">商品管理</div>
		<div class="content">
			<ul class="RM">
				<li><a href="<%=basePath %>store/GoodsShowServlet" target="main">商品列表</a>
				</li>
				<li><a href="<%=basePath %>store/goods_add.jsp" target="main">商品添加</a>
				</li>
			<%-- 	<li><a href="<%=basePath %>store/GoodsUpdateServlet" target="main">商品修改</a>
				</li> --%>
			</ul>
		</div>
		<div class="type">订单管理</div>
		<div class="content">
			<ul class="RM">
				<li><a href="<%=basePath %>store/OrderShowServlet?status=noaccepted"
					target="main">未接受订单</a>
				</li>
				<li><a href="<%=basePath %>store/OrderShowServlet?status=accepted"
					target="main">已接受订单</a>
				</li>
			</ul>
		</div>
		<div class="type">设置</div>
		<div class="content">
			<ul class="RM">
				<li><a href="<%=basePath %>store/FindStoreServlet" target="main">信息修改</a>
				</li>
			</ul>
		</div>
	</div>

</body>
</html>
