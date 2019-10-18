$(function() {
	$.get("/v1/open/studio/getStudioList",function(data){
		//console.log(data);
		for(var index in data){
				$("#tbodyS").append("<tr class='even gradeC'><td>"+data[index].sid+"</td><td>"+data[index].sname+"</td><td style= 'overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[index].slogoPath+"</td><td class='center'>"+data[index].saddress+"</td>" +
						"<td class='center'>"+data[index].sqq+"</td><td class='center' style= 'overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[index].ssummary+"</td><td class='center'><button class='btn btn-primary btn-sm' data-toggle='modal' data-target='#myModal' onclick='update("+data[index].sid+")'><i class='fa fa-edit '></i> Edit</button>&nbsp;<button class='btn btn-danger btn-sm'><i class='fa fa-pencil'></i> Delete</button></td></tr>");
		}
	});
}
);

/**
 * 修改按钮
 * @param id
 * @returns
 */
function update(id) {
	var urlPath = "/v1/open/studio/findById/"+id
	$.get(urlPath,function(data){
		console.log(data);
		$("#sid").val(data.sid);
		$("#sname").val(data.sname);
		$("#saddress").val(data.saddress);
		$("#snumber").val(data.snumber);
		$("#sqq").val(data.sqq);
		$("#ssummary").val(data.ssummary);
	});
}

function updataFrom() {
	var newData = {
			"sid" : $("#sid").val(),
			"sname" : $("#sname").val(),
			"saddress"  : $("#saddress").val(),
			"snumber" : $("#snumber").val(),
			"sqq" : $("#sqq").val(),
			"ssummary" : $("#ssummary").val()
	}
		if("" != sqqs ){
			if(0 != snumers ){
				if("" != saddresss ){
					if("" != ssummarys ){
							$.ajax({
								  type: 'POST',
								  url: "/v1/open/studio/addStudio",
								  data: JSON.stringify(newData),
								  success: function(data) {
									  if(data.statusCode == 200200){
										  linkHome();
									  }else {
										return;
									}
									  
								  },
								  dataType: 'json',
								  contentType: "application/json;charset-UTF-8"
								});
					}else{
						alert("工作室简介未填写");
					}
				}else{
					alert("工作室地址未填写");
				}
			}else{
				alert("工作室人数未填写");
			}
		}else{
			alert("qq未填写");
		}
}
