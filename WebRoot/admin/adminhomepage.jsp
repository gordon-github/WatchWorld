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
    
    <title>管理员主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="admin/JS/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="admin/JS/adminhomepage.js"></script>
    <link rel="stylesheet" href="admin/CSS/homepagecss.css" type="text/css">
<!--     <script type="text/javascript">
    
   
      window.onload=function()
      { 
          /* document.forms.form1.search.focus(); */
          var search=document.getElementsByTagName("search");
         movetoEnd();
      };
      function movetoEnd(){
           var e=event.
          /*  if(typeof el.selectionStart=="number"){
               el.selectionStart=el.selectionEnd=el.value.length;
           }else if(typeof el.createTextRange!="undefined"){
               el.focus();
               var range =el.createTextRange();
               range.collapse(false);
               range.select();
           }
      } */
      
    </script> -->
  </head>
   <!-- onLoad="document.forms.form1.search.focus(); moveToEnd(search)" -->
  <body>
<!--   <p><img border="0" src="admin/IMG/bg05.jpg" width="120" height="151"></p> -->
    <div id="float">
        <%
            String AdminID = (String) session.getAttribute("AdminID");
			String AdminSession = (String) session.getAttribute("AdminSession");
            if(AdminID!=null){%>
			<a  class="a1">管理员：<%=AdminID %></a>
			<a class="aa"> &nbsp;|</a>
			<%
			}
	     if (AdminSession=="root") {
		%>
		<a  class="a" href="admin/register.jsp">创建新账号 </a><a class="aa">&nbsp;| </a>
		<a  class="a" href="AdminTableServlet">管理管理员 </a><a class="aa">&nbsp;| </a>
		<%
		}
		 %>
		 <a class ="a" href="ActivityAddServlet?operate=select">管理首页活动</a><a class="aa">&nbsp;|</a>
		 <a class ="a" href="GoodsShowServlet">管理首页商品展示</a><a class="aa">&nbsp;|</a>
		 <a  class="a" href="admin/login.jsp">退出登录</a><a class="aa">&nbsp;|</a>
		 <a  class="a" href="admin/modifyPWD.jsp">修改密码&nbsp;</a> 
		</div>
		
		<div class="div1">
		<h2  class="title" align="center">店铺信息</h2>
		<div class="Search" align="center">
		  <select id="choose" >
<!-- 		  <option  value="ID" ${choose=="ID"?'selected':""}> 店铺ID</option> -->
		  <option value="name" ${choose=="name"?'selected':""}> 店铺名</option>
		  <option value="owner" ${choose=="owner"?'selected':""}> 店主</option>
		  </select>

		  <input type="text" name="search" id="search" value="${search }" >
		  <input type="button" id="sbtn" value="搜索" onclick="storeSearch(search)">	
		  <a  class="a" href="StoreInfoShowServlet">显示所有店铺</a>	 
		 </div>
		<div class="StoreTable">
		
		<div class="Storetr">
		<label class="item" >店铺ID</label>
		<label class="item2" >店铺名</label>
		<label class="item"> 店主</label>
		<label class="item2"> 联系电话</label>
		<label class="item2"> 注册时间</label>
		<label class="item"> 网址</label>
		<label class="item"> 注销店铺</label>
		</div>
		
		<c:forEach var="store" items = "${pageBean.data }" varStatus="vs">
		<div class="Storetr">
		<label class="item" align="center"><c:out value ="${store.StoreID } "/></label>
		<label class="item2" align="center"><c:out value ="${store.StoreName }"/></label>
		<label class="item" align="center"><c:out value ="${store.Owner }"/></label>
		<label class="item2" align="center"><c:out value ="${store.Telephone }"/></label>
		<label class="item2" align="center"><c:out value ="${store.RegTime }"/></label>
		<label class="item" align="center"><a  class="a" href="<%=path %>/store/StoreGoodsDisplayServlet?StoreID=${store.StoreID}">进铺逛逛</a></label>
		<label class="item" align="center"><input type="button" value="封铺" id="destory" onclick="deleteStore('${store.StoreID }')"></label>
        </div>  
		</c:forEach>
		
		</div>

	    <div class="page">
	             <div class="page2">
		每页${pageBean.pageSize }行
		共${pageBean.totalRows }行
		页数${pageBean.curPage }/${pageBean.totalPages }
		<br/>
				</div>

		<c:choose>
	              <c:when test="${pageBean.curPage==1 }">首页 上一页</c:when>
		    <c:otherwise>
		           <a class="page3" href="StoreInfoShowServlet?page=1">首页</a>
                   <a class="page3" href="StoreInfoShowServlet?page=${pageBean.curPage-1 }">上一页</a>
		    </c:otherwise>
		</c:choose>
		<c:choose>
		         <c:when test="${pageBean.curPage==pageBean.totalPages }">下一页 尾页</c:when>
		    <c:otherwise>
		         <a class="page3" href="StoreInfoShowServlet?page=${pageBean.curPage+1 }">下一页</a>
		         <a class="page3" href="StoreInfoShowServlet?page=${pageBean.totalPages }">尾页</a>
		    </c:otherwise>
		</c:choose>

		</div>
		</div>

  </body>
</html>
