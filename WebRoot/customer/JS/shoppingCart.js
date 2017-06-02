var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	loadData();
});

var jsonData;

function loadData()
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/shoppingCart/getData",
		dataType: "json",
		success: function(json)
		{
			if(data.status==302)
			{
				location.href=hostpath+data.rdHref;
			}
			else
			{
				$.each(json, function(i,n)
						{
					$("#contentTable").append("<tr class='contentTr' name='goodsItem' id='"+n.goodsID+"'>" +
							"<td class='goodsNameTd'>" +
							"<img src='"+hostpath+"store/IMG/GoodsPicture/"+n.goodsPicturePath+"'>" +
							"<div class='goodsName'>" +
							"<a href='"+hostpath+"customer/goods.jsp?goodsID="+n.goodsID+"'>" +
							"<p>"+n.goodsName+"</p>" +
							"</a>" +
							"</div>" +
							"</td>" +
							"<td class='price' name='priceTd'>"+n.price+"</td>" +
							"<td class='amount' name='amountTd'><input type='number' min='1' max='"+n.number+"' class='amountVal' value='"+n.amount+"' onchange='calc()'><p>库存："+n.number+"</p></td>" +
							"<td class='money' name='moneyTd' >"+(n.price*n.amount)+"</td>" +
							"<td class='operate'><p onclick='delData("+n.goodsID+")'>删除</p></td>" +
							"<td class='selection'><input type='checkbox' name='selectIt' onchange='calcTotal()' value='"+n.goodsID+"'></td>" +
					"</tr>");
						});
				jsonData = json; //保存json数据为全局变量
			}
		}
	});
}

//验证购买数量是否大于库存
function checkAmount()
{
	var flag;
	var amountTdList=document.getElementsByName("amountTd");
	for(var j=0; j<amountTdList.length; j++)
	{
		if(parseInt($(amountTdList[j].childNodes[0]).val()) <= parseInt((amountTdList[j].childNodes[0]).max))
		{
			flag=true;
		}
		else
		{
			flag=false;
			break;
		}
	}
	return flag;
}

//提交数据以生成订单
function submitData()
{
	if(checkAmount() == false)
	{
		alert("购买数量不能大于库存。");
	}
	else
	{
		var dataArray = new Array();
		var list = document.getElementsByName("selectIt");
		for(var i=0; i<list.length; i++) //查找所有被选中的商品
		{
			if(list[i].checked)
			{
				var goodsID = list[i].getAttribute("value");
				var amount = $("#"+goodsID+" .amount input").val();
				var data = {"goodsID":goodsID,"amount":amount};
				dataArray.push(data);
			}
		}
		if(dataArray.length > 0)
		{
			var para = "[";
			for(var j=0; j<dataArray.length; j++)
			{
				para=para+JSON.stringify(dataArray[j]);
				if(j!=(dataArray.length-1))
				{
					para=para+",";
				}
			}
			para=para+"]";
			var form=document.createElement("form");
			form.action=hostpath+"customer/order/createOrder";
			form.method="post";
			form.id="submitForm";
			var input=document.createElement("input");
			input.type="text";
			input.name="para";
			input.value=para;
			form.appendChild(input);
			document.body.appendChild(form);
			$("#submitForm").submit();
		}
	}
}

// 计算单项商品的总价
function calc()
{
	var list = document.getElementsByName("goodsItem");
	for(var i=0; i<list.length; i++)
	{

		nodeList=list[i].childNodes;
		var price=nodeList[1].innerHTML;
		var amount=$((nodeList[2].childNodes)[0]).val();
		var money=price*amount;
		nodeList[3].innerHTML=money;
	}
	calcTotal();
}

//计算商品总数以及总价
function calcTotal()
{
	var itemList=document.getElementsByName("selectIt");
	var num=0; //选取的商品数
	var totalMoney=0; //总价
	for(var i=0; i<itemList.length; i++) //查找所有被选中的商品
	{
		if(itemList[i].checked)
		{
			num++;
			id=itemList[i].getAttribute("value");
			var money=$("#"+id+" .money").text();
			totalMoney=parseInt(totalMoney)+parseInt(money);
		}
	}
	$("#total").text("￥"+totalMoney);
	$("#selectedAccount").text(num);
}

//删除指定商品信息
function delData(goodsID)
{
	$.ajax({
		type: "post",
		url: hostpath+"customer/shoppingCart/delData?goodsID="+goodsID,
		success: function(data)
		{
			$(".contentTr").remove();
			loadData();
		}
	});
}