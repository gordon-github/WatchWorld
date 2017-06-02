var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录

function affirmAddress()
{
	var address=$("#addressInput").val();
	$("#targetAddress").text(address);
	$("#addressPara").text(address);
}

function submitOrder()
{
	$("#submitBtn").remove();
	$("#submitPanel").append("<div id='pwdContainer'><p>输入密码：</p>" +
			"<input type='password' id='pwd' />" +
			"<input type='button' id='pwdSubmit' value='确定' onclick='checkPwd()' />" +
			"</div>");
}

function checkPwd()
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/order/checkPwd",
		data: 'password='+$("#pwd").val(),
		success: function(data)
		{
			if(data.status==302)
			{
				location.href=hostpath+data.rdHref;
			}
			else
			{
				if(data == "true") //验证通过
				{
					$("#paraForm").submit();
				}
				else
				{
					alert("密码错误！");
				}
			}
		}
	});
}