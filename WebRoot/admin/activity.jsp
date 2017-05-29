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
    
    <title>活动信息页面</title>
    
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
    <link rel="stylesheet" href="admin/CSS/activitycss.css" type="text/css">
<script type="text/javascript">
    
   
      window.onbeforeunload=function()
      { 
           window.location.href=basePath+"ActivityServlet?operate=select";
      };  
    </script>
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
		
   <div class="Activityform" align="center">
    <h2 align="center">活动一览表</h2>
    <div class="At">
    <label class="item">活动名称</label>
    <label class="item">店铺ID</label>
    <label class="item">管 理</label>
    <label class="item">活动图片</label>
    </div>
  
		<c:forEach var="activity" items = "${Activity }" varStatus="vs">
    <div class="At" align="center">
		<label class="item" align="center"><c:out value="${activity.ActivityName }"/></label>
		<label class="item" align="center"><c:out value ="${activity.StoreID }"/></label>
		
		<label class="item" align="center"><input type="button" value="删除" id="destory-btn" onclick="deleteActivity('${activity.ActivityName}')"></label>
		<label class="item" id="pic" ><a href="<%=basePath %>/${activity.ActivityPicturePath }"><img alt="活动图片" src="<%=basePath %>/${activity.ActivityPicturePath }" height="42px "width="100px"></a></label>
    </div>
		</c:forEach>
		<div class="tianjia">
		        <a id="add" href="admin/addactivity.jsp">添 加 活 动</a>
		</div>
 </div>
  </body>
</html>
