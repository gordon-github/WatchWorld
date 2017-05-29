<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>order_show</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="store/CSS/order.css" />


</head>

<body>
    <p class="tittle">已接受订单</p>
	<c:choose>
		<c:when test="${not empty requestScope.OrderInfo}">
			<c:forEach var="orderInfo" items="${requestScope.OrderInfo}">
		<table align="center" >
			<tr>
			<td class="order_head" colspan="10">
              <div> 订单编号:${orderInfo.orderID}</div>
  		      <div> 付款时间:${orderInfo.paymentTime}</div>
              <div>  发货时间:${orderInfo.sendGoodsTime}</div>
			</td>
			</tr>
			
   				<c:forEach var="orderGoods" items="${orderInfo.orderGoods}">
   				<tr >
   				<td class="pic" width="8%" style="text-align:center" >
                    <img alt="" src="<%=basePath %>store/IMG/GoodsPicture/${orderGoods. goodsPicturePath}">
                   </td>
                   <td width="6%">ID:${orderGoods.goodsID}</td> 
                    <td width="35%">
                    	${orderGoods.goodsName}   
                     </td>
                    <td width="5%">¥${orderGoods.price}</td>
                    <td width="5%">X${orderGoods.buyAmount}</td>
                    <td width="20%">
                    	品牌:${orderGoods.brand}<br>       
                   		 颜色:${orderGoods.color}<br>        
                   		型号: ${orderGoods.model}          
   					</td>
   				</tr>
  				 </c:forEach>
  				 <tr>
			<td class="foot" colspan="10">
                <div class="accept"> 收货信息:${orderInfo.userName},${orderInfo.telephone},${orderInfo.address}</div>
                <div class = "TotalPrice"> 总价格: ${orderInfo.totalPrice}</div>
			</td>
			</tr>
   </table>
   <br>
   </c:forEach>
		</c:when>
		<c:otherwise>
				<p class="noOrder">没有已发货订单</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
