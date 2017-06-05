var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	loadData();
});

function loadData()
{
	var select = $("#selection").val();
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalOrder/getOrder",
		dataType: "json",
		data: "select="+select,
		success: function(json)
		{
			$(".orderItem").remove();
			$.each(json, function(i, n)
			{
				$("#contentContainer").append("<div class='orderItem' id='"+n.orderID+"'>" +
							"<div class='orderPart'>" +
								"<div class='outline'>" +
									"<p>订单编号："+n.orderID+"</p>" +
								"</div>" +
								"<div class='outline'>" +
									"<p>订单状态："+n.orderStatus+"</p>" +
								"</div>" +
								"<div class='btnContainer'>" +
									"<img class='drop' src='"+hostpath+"customer/IMG/drop.png' onclick='drop(\""+n.orderID+"\")'>" +
									"<input type='button' value='撤销' onclick='cancel(\""+n.orderID+"\")' id='cancel"+n.orderID+"'>" +
									"<input type='button' value='确认收货' onclick='signfor(\""+n.orderID+"\")' id='signFor"+n.orderID+"'>" +
								"</div>" +
								"<div class='particular'>" +
									"<div class='halfContainer'>" +
										"<p>成交时间："+n.paymentTime+"</p>" +
										"<p>发货时间："+n.sendGoodsTime+"</p>" +
										"<p>卖方："+n.storeName+"</p>" +
										"<p>买方："+n .userName+"</p>" +
									"</div>" +
									"<div class='halfContainer'>" +
										"<p>总价：￥"+n.totalPrice+"</p>" +
										"<p>收货地址："+n.address+"</p>" +
										"<p>联系方式："+n.telephone+"</p>" +
									"</div>" +
								"</div>" +

							"</div>" +
						"</div>");
				getGoodsInfo(n.orderID);
				if(n.orderStatus == "未发货")
				{
					$("#signFor"+n.orderID).attr("disabled", "true");
					$("#"+n.orderID+" .orderPart").css("background-color", "#B6F9F2");
				}
				if(n.orderStatus == "已发货")
				{
					$("#cancel"+n.orderID).attr("disabled", "true");
					$("#"+n.orderID+" .orderPart").css("background-color", "#99FC8F");
				}
				if(n.orderStatus == "已签收")
				{
					$("#cancel"+n.orderID).attr("disabled", "true");
					$("#signFor"+n.orderID).attr("disabled", "true");
					$("#"+n.orderID+" .orderPart").css("background-color", "#EEEEEE");
				}
			});
		}
	});
}

//获取指定订单编号的商品
function getGoodsInfo(orderID)
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalOrder/getGoods",
		dataType: "json",
		data: "orderID="+orderID,
		success: function(json)
		{
			$.each(json, function(i, n)
			{
				$("#"+orderID).append("<div class='goodsPart'>" +
							"<div class='goodsName'>" +
								"<img src='"+hostpath+"store/IMG/GoodsPicture/"+n.goodsPicturePath+"' />" +
								"<p>"+n.goodsName+"</p>" +
							"</div>" +
							"<div class='goodsAttr'>" +
								"<p>品牌："+n.brand+"</p>" +
								"<p>颜色："+n.color+"</p>" +
								"<p>型号："+n.model+"</p>" +
							"</div>" +
							"<div class='amount'>" +
								"<p>数量："+n.buyAmount+"</p>" +
							"</div>" +
							"<div class='price'>" +
								"<p>单价：￥"+n.price+"</p>" +
							"</div>" +
						"</div>");
			});
		}
	});
}

//撤销订单
function cancel(orderID)
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalOrder/delOrder",
		data: "orderID="+orderID,
		success: function(data)
		{
			location.reload(true);
		}
	});
}

//签收订单
function signfor(orderID)
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/personalOrder/signForOrder",
		data: "orderID="+orderID,
		success: function(data)
		{
			location.reload(true);
		}
	});
}

function drop(orderID)
{
	$("#"+orderID+" .particular").slideToggle();
}