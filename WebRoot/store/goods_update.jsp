<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'goods_update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="store/CSS/goods_manage.css" />
	<script type="text/javascript" src="store/JS/jquery-3.2.0.min.js"></script> 
	<script type="text/javascript" src="store/JS/goods_manage.js"></script>
	
  </head>

  <body>
	<c:if test="${not empty requestScope.goodsInfo}">
  	<p class="tittle">商品修改</p>
  	<hr>
  	<div class="itemContainer">
  		<div class="data">
			<form id="form_goodsUpdate" action="<%=basePath %>store/GoodsUpdateServlet" method="post" enctype="multipart/form-data">
			<div class="form-item"><label>商品编号:</label><input type="text" name="GoodsID" id="GoodsID"  readonly="readonly" value="${requestScope.goodsInfo.goodsID}"><br></div>
			<div class="form-item"><label>商品名称:</label><input type="text" name="GoodsName" id="GoodsName"  value="${requestScope.goodsInfo.goodsName}"><br></div>
			<div class="form-item"><label>商品图片:</label><img alt="" src="./store/IMG/GoodsPicture/${requestScope.goodsInfo.goodsPicturePath}" height="70px" width="70px"><br>
			<input type="text"  align="middle" style="display:none;" name="f_GoodsPicturePath" value="${requestScope.goodsInfo.goodsPicturePath}">
			<input type="file" name="GoodsPicturePath" id="GoodsPicturePath" accept="image/*" ><br></div>
			<div class="form-item"><label>价格:</label><input type="text" name="Price" id="Price" value="${requestScope.goodsInfo.price}"><br></div>
			<div class="form-item"><label>品牌:</label><input type="text" name="Brand" id="Brand" value="${requestScope.goodsInfo.brand}"><br></div>
			<div class="form-item"><label>颜色:</label><input type="color" name="Color"id="Color" value="${requestScope.goodsInfo.color}"><br></div>
			<div class="form-item"><label>型号:</label><input type="text" name="Model" id="Model" value="${requestScope.goodsInfo.model}"><br></div>
			<div class="form-item"><label>库存:</label><input type="text" name="Number"id="Number" value="${requestScope.goodsInfo.number}"><br></div>
			<div class="form-item"><label>销量:</label><input type="text" name="SalesVolumes" id="SalesVolumes" readonly="readonly" value="${requestScope.goodsInfo.salesVolumes}"><br></div>
			<div class="form-item tip" ><input id="Butn" type="button" value="修改"  onclick="updateGoods(form_goodsUpdate)"><span ><font color="red" id="Mesg">${requestScope.msg}</font></span></div>
			</form>
		</div>
	</div>
	</c:if>
			
  </body>
</html>
