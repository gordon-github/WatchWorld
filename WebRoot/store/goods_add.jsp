<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML ">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品上架</title>
    
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

  <p class="tittle">商品添加</p>
  <hr>
  <div class="itemContainer">
  	<div class="data">
			<form id="form_goodsAdd" action="<%=basePath%>store/GoodsAddServlet" method="post" enctype="multipart/form-data">
				<div class="form-item"><label>商品名:</label><input type="text" name="GoodsName" id="GoodsName" maxlength="15" /><br> </div>
				<div class="form-item"><label>商品图片:</label> <input type="file" name="GoodsPicturePath" id="GoodsPicturePath" accept="image/*" /><br> </div>
				<div class="form-item"><label>价格 :</label>¥<input type="text"	name="Price" id="Price" maxlength="9" /><br> </div>
				<div class="form-item"><label>品牌 :</label> <input type="text" name="Brand" id="Brand" maxlength="15" /><br></div>
				<div class="form-item"><label> 颜色 :</label> <input type="text" name="Color" id="Color"	maxlength="15" /><br> </div>
				<div class="form-item"><label> 型号 :</label> <input type="text"	name="Model" id="Model" maxlength="15" /><br> </div>
				<div class="form-item"><label>	库存:</label> <input type="text" name="Number" id="Number" maxlength="9" />件<br></div>
					<input type="text" style="display:none;" name="StoreID"	value="<%=session.getAttribute("StoreID")%>" /> 
				<div class="form-item tip"><span><font	color="red" id="msg">${msg}</font></span><br></div>
				<div class="form-item tip" ><input id="Butn" type="button" value="添加" onclick="checkform(form_goodsAdd)" /></div>
				
			</form>
		</div>
   </div>
  </body>
</html>
