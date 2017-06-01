<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'goodsdisplay.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="admin/JS/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="admin/JS/goodsdisplay.js"></script>
    <link rel="stylesheet" href="admin/CSS/goodsdisplaycss.css" type="text/css">
  </head>
  
  <body>
  <div class="head">
       <div id="adminID">
   <%
            String AdminID = (String) session.getAttribute("AdminID");
			String AdminSession = (String) session.getAttribute("AdminSession");
            if(AdminID!=null){%>
			<a  class="a" >管理员：<%=AdminID %></a><a class="a1">&nbsp;| </a>
			
			<%
			}
			
   %>
   <a  class="a2" href="StoreInfoShowServlet">返回主页</a>
   </div>
   </div>
   
    <div class="Goodsform" align="center">
    <h2 align="center">首页商品一览表</h2>
    <div>
		   <label>商品ID</label>
		   <input type="text" id="GoodsID" class="GoodsID">
		   <input type="button" value="添加" id="add-btn" onclick="addGoods()">
	</div>
    <div class="At">
    <label class="item">商品ID</label>
     <label class="item">品 牌</label>
    <label class="item">管 理</label>
    <label class="item">商品图片</label>
     </div>
     
     <c:forEach var="goods" items = "${Goods }" varStatus="vs">
    <div class="At" align="center">
		<label class="item" align="center"><c:out value="${goods.GoodsID }"/></label>
		<label class="item" align="center"><c:out value ="${goods.Brand }"/></label>
		<label class="item" align="center"><input type="button" value="删除" id="destory-btn" onclick="deleteGoods('${goods.GoodsID}')"></label>

    </div>
		</c:forEach>

     
     </div>
     
     
     
  </body>
</html>
