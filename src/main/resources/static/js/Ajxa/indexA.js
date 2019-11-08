$(function() {
	$.get("/v1/open/studio/getStudioList",function(data){
//		console.log(data);
		for(var index in data){
//			console.log(data[index].slogoPath);
			if(index != 6){
				$("#listStudio").append("<li class='enterprise-item-wrap' onclick='link_dataS("+data[index].sid+")'><div class='enterprise-item'><img src="+data[index].slogoPath+" style='width: 150px;height: 66px;'></img></div></li>");
			}else {
				return;
			}
			
		}
	});
});