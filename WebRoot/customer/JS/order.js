var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录

function affirmAddress()
{
	var address=$("#addressInput").val();
	$("#targetAddress").text(address);
	$("#addressPara").text(address);
}

function submitOrder()
{
	var id=$("#user span").text();
	$("#submitBtn").remove();
	$("#submitPanel").append("<div id='pwdContainer'><p>请输入密码：</p>" +
			"<div class='innerContainer'>" +
				"<input type='password' id='pwd' />" +
				"<input type='button' id='pwdSubmit' value='立即支付' onclick='checkPwd()' /><br>" +
			"</div>" +
				"<input type='radio' name='pay' checked>微信支付("+id+")<br>" +
				"<input type='radio' name='pay'>支付宝支付("+id+")<br>" +
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