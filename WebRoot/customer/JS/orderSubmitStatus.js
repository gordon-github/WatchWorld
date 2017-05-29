var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	if($("#flag").text() == "true")
	{
		alert("订单提交成功！");
	}
	else
	{
		alert("订单提交失败！");
	}
	location.href=hostpath+"index.jsp";
});