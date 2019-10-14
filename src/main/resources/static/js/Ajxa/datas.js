let str=window.location.search;
let id = str.slice(1);
var urlPath = "/v1/open/studio/findById/"+id
$(function() {
	$.get(urlPath,function(data){
		console.log(data)
		
		$("#liStudio").append("<div class='example-item'><div class='example-user-info'><img alt='' src='"+data.slogoPath+"' style='margin-left: -100px;margin-top: -10px; width: 150px;height: 66px;'>" +
				"<div style='margin-top: -20px; padding-left: 60px;'><div class='name'> "+data.snumber+"人</div>" +
				"<div class='company'>"+data.saddress+"</div></div></div><div class='example-slogan'>每天早上一分钟，告别加班，拥抱老板</div>" +
				"<div class='example-question'><span class='label'>简介：</span>"+data.ssummary+"</div><div class='learn-more'>" +
				"<a class='btn-learn-more' href='http://wpa.qq.com/msgrd?v=3&uin="+data.sqq+"&site=qq&menu=yes'' target='_blank'>联系负责人</a></div></div>")
	});
});