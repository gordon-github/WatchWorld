var hostpath=location.protocol+"//"+location.host+"/WatchWorld/"; //获取url根目录
$(document).ready(function(){
	loadData(1);
});

//检查参数是否正确
function checkandGetData(pageIdx)
{
	var max=$("#pageIndexContainer").attr("totalPage");
	if(pageIdx > 0 && pageIdx <= max)
	{
		loadData(pageIdx);
	}
}

//获取目标页的数据
function loadData(pageIdx)
{
	var keywordPara=location.search;
	$.ajax({
		type: "post",
		url: hostpath+"customer/searchResult/getResult"+keywordPara+"&pageIdx="+pageIdx,
		dataType: "json",
		cache: false,
		success: function(json)
		{
			$("#resultContainer").empty(); //清空商品列表
			$.each(json.data, function(i, n)
			{
				$("#resultContainer").append(
					"<div class='goods'>" +
						"<a	href='"+hostpath+"customer/goods.jsp?goodsID="+n.goodsID+"' target='_blank'>" +
							"<img src='"+hostpath+"store/IMG/GoodsPicture/"+n.goodsPicturePath+"'>" +
						"</a>" +
						"<div class='goodsNameContainer'><p class='goodName'>"+n.goodsName+"</p></div>" +
						"<p class='goodPrice'>￥"+n.price+"</p>" +
					"</div>");
			});
			$("#pageIndexContainer").attr("totalPage",json.pageAmount);
			$("#prePage").attr("onclick", "checkandGetData("+(pageIdx-1)+")");
			$("#nextPage").attr("onclick", "checkandGetData("+(pageIdx+1)+")");
			$("#lastPage").attr("onclick", "checkandGetData("+(json.pageAmount)+")");
			$("#pageNum p").text(pageIdx + "/" + json.pageAmount);
			if(json.pageAmount == 0)
			{
				$("#resultContainer").append("<p>无相关数据！</p>");
			}
		}
	});
}