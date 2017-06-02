<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${requestScope.StoreName}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%-- 	
	<link rel="stylesheet" type="text/css" href="<%= path %>/store/CSS/index.css">
 	--%>	
 	<link rel="stylesheet" type="text/css" href="<%= path %>/store/CSS/searchResult.css">
	
  </head>
  <c:if test="${empty requestScope.StoreName}">
  <c:redirect url="${request.getContextPath()}/store/login.jsp"/>
  </c:if>
  <body>
   <jsp:include page="/customer/header.jsp"></jsp:include>
   <div id="headerContainer">
   <div id="storeAndSearch">
   <div id="storeNameContainter">
   <p>${requestScope.StoreName}</p>
   </div>
 	<div id="searcher">
	<form  id="" action="<%=basePath%>store/FdGsInStoreByKeyWServlet" method="post" >
	<input type="text" name="Key" id="inputBox" >
	<input type="text"  style="display:none;"   name="StoreID" value="${requestScope.StoreID}"/>
	<input type="submit" value="店内搜索" id="submitBtn" >
	</form>
	</div>
	</div>
	</div>
		<!-- 迭代数据 -->
		<!-- 展示该店铺所有商品 -->
		<div id="optionContainer">
		</div>
		<c:choose>
			<c:when test="${not empty requestScope.pageBean.pageData}">
				<div id="resultContainer">
				<c:forEach var="goods" items="${requestScope.pageBean.pageData}">
					<div class="goods">
						<a href="<%=basePath%>customer/goods.jsp?goodsID=${goods.goodsID}"><img alt="" src="./store/IMG/GoodsPicture/${goods.goodsPicturePath}" height="70px" width="70px"></a>
						<div class="goodName"><span >${goods.goodsName}</span></div>
						<p class="goodPrice">¥${goods.price}</p>
					</div>
				</c:forEach>
				</div>
			<div id="pageIndexLine">
			<div id="pageIndexContainer" >
				当前${requestScope.pageBean.currentPage}/${requestScope.pageBean.totalPage }页 &nbsp;&nbsp; 
				<a href="<%=basePath %>store/StoreGoodsDisplayServlet?currentPage=1&StoreID=${requestScope.pageBean.pageData[0].storeID}">首页</a>
				<a	href="<%=basePath %>store/StoreGoodsDisplayServlet?currentPage=${requestScope.pageBean.currentPage-1}&StoreID=${requestScope.pageBean.pageData[0].storeID}">上一页</a> 
				<a href="<%=basePath %>store/StoreGoodsDisplayServlet?currentPage=${requestScope.pageBean.currentPage+1}&StoreID=${requestScope.pageBean.pageData[0].storeID}">下一页</a>
				 <a	href="<%=basePath %>store/StoreGoodsDisplayServlet?currentPage=${requestScope.pageBean.totalPage}&StoreID=${requestScope.pageBean.pageData[0].storeID}">末页</a>
			</div>
		</div>
			</c:when>
			<c:when test="${not empty requestScope.PageBean.pageData}">
			<!-- 展示该店铺含有关键字Key的商品 -->
				<div id="resultContainer">
				<c:forEach var="goods" items="${requestScope.PageBean.pageData}">
					<div class="goods">
						<a href="<%=basePath%>customer/goods.jsp?goodsID=${goods.goodsID}"><img alt="" src="./store/IMG/GoodsPicture/${goods.goodsPicturePath}" height="70px" width="70px"></a>
						<div class="goodName"><span >${goods.goodsName}</span></div>
						<p class="goodPrice">¥${goods.price}</p>
					</div>
				</c:forEach>
				</div>
			<div id="pageIndexLine">
			<div id="pageIndexContainer" >
				当前${requestScope.PageBean.currentPage}/${requestScope.PageBean.totalPage }页 &nbsp;&nbsp; 
				<a href="<%=basePath %>store/FdGsInStoreByKeyWServlet?currentPage=1&StoreID=${requestScope.StoreID}&Key=${requestScope.Key}">首页</a>
				<a	href="<%=basePath %>store/FdGsInStoreByKeyWServlet?currentPage=${requestScope.PageBean.currentPage-1}&StoreID=${requestScope.StoreID}&Key=${requestScope.Key}">上一页</a> 
				<a href="<%=basePath %>store/FdGsInStoreByKeyWServlet?currentPage=${requestScope.PageBean.currentPage+1}&StoreID=${requestScope.StoreID}&Key=${requestScope.Key}">下一页</a>
				 <a	href="<%=basePath %>store/FdGsInStoreByKeyWServlet?currentPage=${requestScope.PageBean.totalPage}&StoreID=${requestScope.StoreID}&Key=${requestScope.Key}">末页</a>
			</div>
		</div>
			</c:when>
			<c:otherwise>
					<p align="center">对不起，没有数据</p>
			</c:otherwise>
		</c:choose>
  </body>
</html>
