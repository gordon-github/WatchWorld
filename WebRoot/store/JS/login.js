var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var server_context = localObj.protocol + "//" + localObj.host + "/" + contextPath;

function loginStore(){
	
	var LoginName=$("#LoginName").val();
	var Pwd=$("#Pwd").val();
	if(LoginName==""){
		$("#Meg").html("请输入用户名");
	}else if(Pwd==""){
		$("#Meg").html("请输入密码");
	}else {
		$.ajax({  
        type:"POST",//请求方式  
        url:server_context+"/store/LoginStoreServlet",//发送请求地址 
        data:/*$('#form_login').serialize(),*/
        {"LoginName":LoginName,"Pwd":Pwd} ,
        timeout:30000,//超时时间：30秒  
        dataType:"json",//设置返回数据的格式  
        async:false, //
        success:function(data){  //请求成功后的回调函数 data为json格式 
           var flag = data.flag;
//           alert(flag);
           if(flag == "LoginNameNoFound"){
        	   $("#Meg").html("用户名不存在");
           }else if(flag=="PasswordWrong"){
        	   $("#Meg").html("密码错误");
           }else if(flag=="PasswordCorrect"){
        	   window.location.href=server_context+"/store/store_index.jsp";
           }
       },  
       //请求出错的处理  
       error:function(){  
                 alert("服务器错误");  
       }  
    });
}

}
function logout(){
	$.ajax({  
        type:"POST",//请求方式  
        url:server_context+"/store/LogOutServlet",//发送请求地址 
        async:false, 
        success:function(){  
        	   top.location.href=server_context+"/store/login.jsp";
           },
       //请求出错的处理  
       error:function(){  
                 alert("服务器错误");  
       }  
    });
}
function registerStore(){
	top.location.href=server_context+"/store/register.jsp";
}
function myStore(StoreID){
	top.location.href=server_context+"/store/StoreGoodsDisplayServlet?StoreID="+StoreID;
}
