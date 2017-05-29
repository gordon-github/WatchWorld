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
    
    <title>root管理员界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <link rel="stylesheet" href="admin/CSS/admindeletecss.css" type="text/css">

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
  <div id="header">
        <a href="#" id="logo"></a>
  </div>

  
   <div id="title">
		 <h2 align="center">管理员一览表</h2>
   </div>
   <div class="Admintr" align="center">
    <label class="item">管理员ID</label>
    <label class="item">密 码</label>
    <label class="item">管 理</label>
    </div>
  
		<c:forEach var="admin" items = "${Admin }" varStatus="vs">
  <div class="Admintr" align="center">
		<label class="item" align="center"><c:out value ="${admin.AdminID }"/></label>
		<label class="item" align="center"><c:out value ="${admin.Pwd }"/></label>
		<label class="item"   align="center"><input type="button" value="删除" id="destory-btn" ></label>
  </div>
		</c:forEach>
  
  </body>
</html>
