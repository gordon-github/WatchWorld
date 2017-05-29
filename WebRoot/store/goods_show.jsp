<%@page import="cn.edu.zhku.hyw.watchworld.store.JavaBean.GoodsInfo"%>
<%@page import="cn.edu.zhku.hyw.watchworld.store.JavaBean.PageBean"%>
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

<title>goods_show</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="store/CSS/goods_manage.css" />
<script type="text/javascript" src="store/JS/jquery-3.2.0.min.js"></script> 
 <script type="text/javascript" src="store/JS/goods_manage.js"></script>
</head>
 <%-- <c:if test="${not empty requestScope.msg}">
		<script>
		alert("${requestScope.msg}");
		</script>
		</c:if> --%>
<body>
	<div id="div_findgoods">
	<form  id="form_findgoods" action="<%=basePath%>store/FindGoodsServlet" method="post" >
	<input type="text" name="GoodsID" id="GoodsID" placeholder="输入正整数的商品编号">
	<input type="text"  style="display:none;" name="action" value="show">
	<input type="text"  style="display:none;"   name="StoreID" value="<%=session.getAttribute("StoreID")%>"/>
	<input type="button" value="搜索" onclick="findGoods()"><span ><font color="red" id="Mesg">${requestScope.msg}</font></span>
	</form>
	</div>
	<table border="1" width="90%" align="center" cellpadding="1"
		cellspacing="0" style="text-align: center;">
		<tr class="head">

			<th width="5%"> 商品编号 </th>
			<th width="30%">商品名   </th>
			<th width="7">商品图片</th>
			<th width="7%"> 价格</th>
			<th width="7%"> 品牌</th>
			<th width="8%">颜色 </th>
			<th width="9%"> 型号</th>
			<th width="6%">库存 </th>
			<th width="6%">销量</th>
			<th colspan="2" width="10%">操作</th>
		</tr>  
		<!-- 迭代数据 -->
		
		
		<c:choose>
			<c:when test="${not empty requestScope.pageBean.pageData}">
				<c:forEach var="goods" items="${requestScope.pageBean.pageData}">
					<tr>
						<td>${goods.goodsID}</td>
						<td>${goods.goodsName}</td>
						<td><img alt="" src="<%=basePath%>/store/IMG/GoodsPicture/${goods.goodsPicturePath}" height="70px" width="70px"></td>
						<td>${goods.price}</td>
						<td>${goods.brand}</td>
						<td><input type="color" value="${goods.color}"></td>
						<td>${goods.model}</td>
						<td>${goods.number}</td>
						<td>${goods.salesVolumes}</td>
						<td><a href="<%=basePath%>store/FindGoodsServlet?StoreID=${goods.storeID}&GoodsID=${goods.goodsID}&action=update">修改</a></td>
						<td><a href="<%=basePath %>store/DeleteGoodsServlet?GoodsID=${goods.goodsID}">下架</a></td>
					</tr>
				</c:forEach>
				<tr>
			<td colspan="11" align="center" class="foot">
				当前${requestScope.pageBean.currentPage
				}/${requestScope.pageBean.totalPage }页 &nbsp;&nbsp; <a
				href="<%=basePath %>/store/GoodsShowServlet?currentPage=1">首页</a>
				<a
				href="<%=basePath %>/store/GoodsShowServlet?currentPage=${requestScope.pageBean.currentPage-1}">上一页
			</a> <a
				href="<%=basePath %>/store/GoodsShowServlet?currentPage=${requestScope.pageBean.currentPage+1}">下一页
			</a> <a
				href="<%=basePath %>/store/GoodsShowServlet?currentPage=${requestScope.pageBean.totalPage}">末页</a>
			</td>
		</tr>
			</c:when>
			<c:when test="${not empty requestScope.goodsInfo}">
				<tr>
						<td>${requestScope.goodsInfo.goodsID}</td>
						<td>${requestScope.goodsInfo.goodsName}</td>
						<td><img alt="" src="<%=basePath%>/store/IMG/GoodsPicture/${requestScope.goodsInfo.goodsPicturePath}" height="70px" width="70px"></td>
						<td>${requestScope.goodsInfo.price}</td>
						<td>${requestScope.goodsInfo.brand}</td>
						<td><input type="color" value="${requestScope.goodsInfo.color}"></td>
						<td>${requestScope.goodsInfo.model}</td>
						<td>${requestScope.goodsInfo.number}</td>
						<td>${requestScope.goodsInfo.salesVolumes}</td>
						<td><a href="<%=basePath%>store/FindGoodsServlet?StoreID=${goods.storeID}&GoodsID=${goods.goodsID}&action=update">修改</a></td>
						<td><a href="<%=basePath %>store/DeleteGoodsServlet?GoodsID=${goods.goodsID}">下架</a></td>
					
					
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="14">对不起，没有你要找的数据</td>
				</tr>
			</c:otherwise>
		</c:choose>

		

	</table>
</body>
</body>
</html>
