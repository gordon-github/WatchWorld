var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	getData();
});

function getData()
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalData/getData",
		cache: false,
		dataType: "json",
		success: function(data)
		{
			if(data.status==302)
			{
				location.href=hostpath+data.rdHref;
			}
			else
			{
				$("#portraitIMG").attr("src","/WatchWorld/"+data.portrait);
				$("#userID").text(data.userID);
				$("#userNameItem input").val(data.userName);
				$("#ageItem input").val(data.age);
				if(data.sex == "男")
				{
					$("#sexItem [value='男']").attr("checked", "true");
				}
				else
				{
					$("#sexItem [value='女']").attr("checked", "true");
				}
				$("#telephoneItem input").val(data.telephone);
			}
		}
	});
}

function checkAge(e)
{
	if(e.value!="")
	{
		var flag=e.value.search(/[^(0-9)]/);
		if(flag > -1)
		{
			$("#ageItem .errorLab").text("年龄必须是纯数字！");
			e.value=e.value.replace(/[^(0-9)]/g,"");
		}
		else
		{
			$("#ageItem .errorLab").text("");
		}
	}
}

//提交表单
function submitForm()
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalData/checkData",
		data: $("#personalForm").serialize(),
		async: false,
		dataType: "json",
		success: function(data)
		{
			if(json.status==302)
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
					$("#userNameItem .errorLab").text(data.userName);
					$("#ageItem .errorLab").text(data.age);
					$("#telephoneItem .errorLab").text(data.telephone);
				}
			}
		}
	});
}