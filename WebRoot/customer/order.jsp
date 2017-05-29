<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>确认订单</title>
	<link rel="stylesheet" type="text/css" href="<%= path %>/customer/CSS/order.css">
	<script type="text/javascript" src="<%= path %>/customer/JS/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="<%= path %>/customer/JS/order.js"></script>
  </head>
  <body>
  	<jsp:include page="/customer/header.jsp"></jsp:include>
   	<div id="titleContainer">
  		<a href="<%= path %>/index.jsp">
  			<img id="logo" src="<%= path %>/customer/IMG/logo.png">
  		</a>
  		<p id="mainTitle">确认订单</p>
  	</div>
  	<div id="mainContainer">
  		<div id="addressContainer">
  			<div id="addressPanel">
  				<p class="minTitle">确认收货地址</p>
  				<input type="text" value="<c:out value='${ userData.address }'/>" id="addressInput">
  				<input type="button" value="确定" id="addressBtn" onclick="affirmAddress()">
  			</div>
  		</div>
  		<div id="goodsContainer">
  			<div id="tableContainer">
	  			<p class="minTitle">确认订单信息</p>
	  			<table>
	  				<tr>
	  					<th>商品名称</th>
	  					<th>商品属性</th>
	  					<th>单价</th>
	  					<th>数量</th>
	  					<th>小计</th>
	  				</tr>
	  				<c:forEach var="i" items="${ dataMap }">
	  					<c:forEach var="j" items="${ i.value }">
	  						<tr class="contentTr">
	  							<td style="width:400px">
	  								<img class="goodsPic" src="<%= path %>/store/IMG/GoodsPicture/<c:out value='${ j.goodsPicturePath }' />">
	  								<p class="goodsName"><a href="<%= path %>/customer/goods.jsp?goodsID=<c:out value='${ j.goodsID }' />"><c:out value="${ j.goodsName }" /></a></p>
	  							</td>
	  							<td style="width:200px">
	  								<p class="goodsAttr">品牌：<c:out value="${ j.brand  }" /></p>
	  								<p class="goodsAttr">颜色：<c:out value="${ j.color  }" /></p>
	  								<p class="goodsAttr">型号：<c:out value="${ j.model  }" /></p>
	  							</td>
	  							<td style="width: 100px">
	  								<p class="goodsPrice"><c:out value="${ j.price  }" /></p>
	  							</td>
	  							<td>
	  								<p class="goodsAmount"><c:out value="${ j.buyAmount  }" /></p>
	  							</td>
	  							<td>
	  								<p class="goodsTotal"><c:out value="${ j.price * j.buyAmount  }" /></p>
	  							</td>
	  						</tr>	
	  					</c:forEach>
	  				</c:forEach>
	  			</table>
  			</div>
  		</div>
  		<div id="submitContain">
  			<div id="submitPanel">
  				<p id="totalMoney">实付款：<span>￥<c:out value="${ totalMoney }" /></span></p>
  				<p id="addressBar">寄送至：<span id="targetAddress"><c:out value="${ userData.address }" /></span></p>
  				<p id="user">收货人：<c:out value="${ userData.userName }" /></p>
  				<p id="telephone">联系方式：<c:out value="${ userData.telephone }" /></p>
  				<input type="button" value="提交订单" id="submitBtn" onclick="submitOrder()">
  			</div>
  		</div>
  	</div>
  	<form id="paraForm" hidden="true" method="post" action="<%= path %>/customer/order/saveOrder">
  		<input value="<c:out value='${ para }' />" type="text" name="para">
  		<input value="<c:out value='${ userData.address }'/>" type="text" name="addressPara" id="addressPara">
  	</form>
 	<div id="footer">
		Copyright © 2017
  	</div>
  </body>
</html>
