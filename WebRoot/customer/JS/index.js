var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	loadActivityImg(); //加载活动图片
	picCarousel(); //启动图片轮播
	loadBrand(); //加载热销产品商标
});

var amount=3; //轮播图片数量
var idx=1; //当前显示的活动图片索引
var interval; //定时器

function loadActivityImg()
{
	$.ajax({
		type: "post",
		url: hostpath+"index/activity",
		dataType: "json",
		success: function(json)
		{
			$.each(json, function(i, n)
			{
				$("#activityContainer").append(
						"<a href='store/StoreGoodsDisplayServlet?StoreID="+json[i].storeId+"'>" +
								"<img src='"+json[i].activityPicturePath+"' " +
										"id='activity"+(i+1)+"' " +
										"onmouseover='stopCarousel()' " +
										"onmouseout='picCarousel()'>" +
						"</a>");
				$("#picIndexContainer").append(
						"<i class='picIndex' " +
						"index='"+(i+1)+"'" +
						"onmouseover='selectActivity("+(i+1)+")'" +
						"onmouseout='picCarousel()'>");
				$("#activity"+(i+1)).hide();
			});
			$("#activity"+idx).show();
			$("[index="+idx+"]").css("background-color", "#FF0000");
		},
	});
}

//活动图片自动循环切换
function picSwitch()
{
	$("#activity"+idx).fadeOut(500);
	$("[index="+idx+"]").css("background-color", "#646464");
	idx++;
	if(idx>amount)
	{
		idx=1;
	}
	$("#activity"+idx).fadeIn(500);
	$("[index="+idx+"]").css("background-color", "#FF0000");
}

//启动图片轮播
function picCarousel()
{
	interval=window.setInterval("picSwitch()", 3000);
}

//停止图片轮播
function stopCarousel()
{
	window.clearInterval(interval);
}

//当选择图片下的标签索引时，停止计时器，并切换到与索引相应的图片
function selectActivity(i)
{
	stopCarousel();
	if(i!=idx)
	{
		$(".picIndex").css("background-color", "#646464");
		$("[index="+i+"]").css("background-color", "#FF0000");
		$("#activity"+idx).fadeOut(500);
		$("#activity"+i).fadeIn(500);
		idx=i;
	}
}

//获取首页需要展示的所有商标
function loadBrand()
{
	$.ajax({
		type: "post",
		url: hostpath+"index/findAllBrand",
		dataType: "json",
		success: function(json)
		{			
			$.each(json, function(i, n)
			{
				$("#displayContainer").append(
					"<div class='brandGoods' name='"+n+"'>" +
						"<div class='brand'>" + 
							"<h3>"+n+"</h3>" +
						"</div>" +
					"</div>");
				loadGoods(n);
			});
		}
	});
}

//加载热销商品
function loadGoods(brand)
{
	$.ajax({
		type: "post",
		url: hostpath+"index/goodsDisplayInfo?brand=" + brand,
		dataType: "json",
		data: brand,
		success: function(json)
		{
			$.each(json, function(i, n)
			{
				$("[name="+brand+"]").append(
					"<div class='goods'>" +
						"<a	href='customer/goods.jsp?goodsID="+n.goodsID+"' target='_blank'>" +
							"<img src='"+hostpath+"store/IMG/GoodsPicture/"+n.goodsPicturePath+"'>" +
						"</a>" +
						"<p class='goodName'>"+n.goodsName+"</p>" +
						"<p class='goodPrice'>￥"+n.price+"</p>" +
					"</div>");
			});
		}
	});
}

function checkEmpty()
{
	if($("#inputBox").val()=="")
	{
		return false;
	}
	else
	{
		return true;
	}
}