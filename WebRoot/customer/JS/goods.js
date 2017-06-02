var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	getData();
});

function getData()
{
	var para=location.search;
	$.ajax({
		type: "post",
		url: hostpath+"customer/goods/getData"+para,
		dataType: "json",
		success: function(data)
		{
			document.title=data.goodsName+" - 商品详情";
			$("#picContainer img").attr("src",hostpath+"store/IMG/GoodsPicture/"+data.goodsPicturePath);
			$("#goodsName p").text(data.goodsName);
			$("#price p").text("￥"+data.price);
			$("#brand p").text(data.brand);
			$("#color p").text(data.color);
			$("#Model p").text(data.model);
			$("#Number p").text(data.number);
			$("#SalesVolumes p").text(data.salesVolumes);
			$("#storeName a").attr("href",(hostpath+"store/StoreGoodsDisplayServlet?StoreID="+data.storeID));
			$("#storeName a").text(data.storeName);
			$("#amount").attr("max",data.number);
		}
	});
}

function checkAmount()
{
	if(parseInt($("#amount").val())<=parseInt($("#Number p").text()))
	{
		return true;
	}
	else
	{
		return false;
	}
}

//添加到购物车
function insertIntoShoppingCart()
{
	if(checkAmount() == false)
	{
		alert("购买数量不能大于库存。");
	}
	else
	{
		var para=location.search;
		var amount=$("#amount").val();
		$.ajax({
			type: "post",
			url: hostpath+"customer/shoppingCart/insertIntoShoppingCart"+para+"&amount="+amount,
			dataType: "json",
			success: function(data)
			{
				if(data == "refuse")
				{
					alert("您还未登录，无法加入购物车！");
					location.href=hostpath+"customer/login.jsp";
				}
				else if(data.status==302)
				{
					alert("您还未登录，无法加入购物车！");
					location.href=hostpath+data.rdHref;
				}
				else
				{
					alert(data);
				}
			}
		});
	}
}

//立即购买
function buyNow()
{
	if(checkAmount() == false)
	{
		alert("购买数量不能大于库存。");
	}
	else
	{
		var para=location.search;
		var goodsID=para.replace(/\?[\w]{1,}\=/g, "");
		var amount=$("#amount").val();
		var p="[{'goodsID':"+goodsID+",'amount':"+amount+"}]";
		var form=document.createElement("form");
		form.action=hostpath+"customer/order/createOrder";
		form.method="post";
		form.id="submitForm";
		var input=document.createElement("input");
		input.type="text";
		input.name="para";
		input.value=p;
		form.appendChild(input);
		document.body.appendChild(form);
		$("#submitForm").submit();
	}
}