var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
function checkPassword(e)
{
	if(e.value!="")
	{
		var flag=e.value.match(/[\w]{6,}/);
		if(flag==null)
		{
			$("#passwordItem .errorLab").text("密码必须由6位以上的字母、数字或下划线构成！");
		}
		else
		{
			$("#passwordItem .errorLab").text("");
		}
	}
}

function checkConfirmPassword(e)
{
	if(e.value!="")
	{
		var flag=($("#passwordItem input").val()==e.value); 
		if(flag==false)
		{
			$("#confirmPasswordItem .errorLab").text("密码不一致！");
		}
		else
		{
			$("#confirmPasswordItem .errorLab").text("");
		}
	}
}

function submitForm()
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/accountSecurity/checkData",
		data: $("#passwordForm").serialize(),
		async: false,
		dataType: "json",
		success: function(data)
		{
			if(data.status==302)
			{
				location.href=hostpath+data.rdHref;
			}
			else
			{
				if(data.status=="true") //表单数据合法且已经保存到数据库中
				{
					alert(data.saved);
				}
				else
				{
					$("#passwordItem .errorLab").text(data.password1);
					$("#confirmPasswordItem .errorLab").text(data.password2);
				}
			}
		}
	});
}