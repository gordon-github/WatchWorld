<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>店铺信息更新</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="store/CSS/goods_manage.css" />
	<script type="text/javascript" src="store/JS/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="store/JS/store_update.js"></script>
  </head>
  
  <body>
  <p class="tittle">基本信息</p>
  <hr>
  <div class="itemContainer">
  <div class="data">
  	<div class="form-item"><label>用户名:</label>${StoreInfo.loginName}</div>
  	<div class="form-item"><label>店主:</label>${StoreInfo.owner}</div>
  	<div class="form-item"><label>注册时间:</label>${StoreInfo.regTime}</div>
	<form action="<%=basePath%>store/StoreUpdateServlet" method="post" id="form_updateStore">
	<input type="hidden" id="storeID" name="StoreID" value="${StoreInfo.storeID}">
	<input type="hidden" id="loginName" value="${StoreInfo.loginName}">
	<input type="hidden" id="f_storeName" value="${StoreInfo.storeName}">
	<input type="hidden" id="f_telephone" value="${StoreInfo.telephone}">
	<div class="form-item"><label>原密码:</label><input type="text" id="f_pwd"><br></div>
	<div class="form-item"><label>新密码:</label><input type="password" id="n_pwd" name="Pwd"><br></div>
	<div class="form-item"><label>店铺名称:</label><input type="text" id="storeName" name="StoreName" value="${StoreInfo.storeName}"><br></div>
	<div class="form-item"><label>手机号码:</label><input type="text" id="telephone" name="Telephone" value="${StoreInfo.telephone}"><br></div>
	 <div class="form-item tip"><span ><font color="red" id="msg">${requestScope.msg}</font></span></div>
	<div class="form-item tip"><input type="button" id="Butn" value="保存" onclick="updateStore()"></div>
	</form>
	</div>
	</div>
  </body>
</html>

