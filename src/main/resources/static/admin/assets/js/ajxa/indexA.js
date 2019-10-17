$(function() {
	$.get("http://127.0.0.1:8080/v1/open/studio/getStudioList",function(data){
		console.log(data);
		for(var index in data){
				$("#tbodyS").append("<tr class='even gradeC'><td>"+data[index].sid+"</td><td>"+data[index].sname+"</td><td>"+data[index].slogoPath+"</td><td class='center'>"+data[index].saddress+"</td>" +
						"<td class='center'>"+data[index].sqq+"</td><td class='center'>"+data[index].ssummary+"</td><td class='center'><button class='btn btn-primary btn-sm'><i class='fa fa-edit '></i> Edit</button>&nbsp;<button class='btn btn-danger btn-sm'><i class='fa fa-pencil'></i> Delete</button></td></tr>");
		}
	});
});