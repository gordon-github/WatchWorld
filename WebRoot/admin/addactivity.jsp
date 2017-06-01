<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加活动页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="admin/JS/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="admin/JS/activity.js"></script>
    <link rel="stylesheet" href="admin/CSS/activityaddcss.css" type="text/css">
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
   <a  class="a2" href="ActivityAddServlet?operate=select">返回上页</a>
   </div>
    </div>    
        
  	<div class="addactivity">
  	 <h2 class="ah2" align="center">活 动 信 息 表</h2>
			<form id="form_Add" action="<%=basePath%>ActivityAddServlet?operate=add" method="post" enctype="multipart/form-data">
			    <div class="form-item">
			         <label>商铺编号</label><input type="text" name="StoreID" id="StoreID"/><br> 
			    </div>
			     <div class="form-item">
			         <label>活动名称</label><input type="text" name="ActivityName" id="ActivityName" /><br> 
			    </div>
				<div class="form-item"><label>活动图片</label> 
				      <input type="file" name="ActivityPicturePath" id="ActivityPicturePath" accept="image/*" /><br> 
			    <div class="form-item tip">
							<span><font	color="#459FCB" id="msg">${msg}</font></span>
				</div>
				     <input id="addbtn"	type="button" value="添 加" onclick="checkform(form_Add)" />
			    </div>
			</form>
		</div>
  </body>
</html>
