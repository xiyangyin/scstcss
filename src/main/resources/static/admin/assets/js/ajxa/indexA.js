let deleteSid = 0, str = window.location.search, id = str.slice(144), bases = new Base642();
console.log(id);
if("" === id){
    window.alert("未登录系统，点击确认返回登录管理系统");
    linkAdmin();
}
$(function () {
    $.get("/v1/open/studio/getUserSession/" + id,function(data){
        if(48001000 != data.code){
            getData();
        }else{
            window.alert("身份验证失效重新登录");
            linkAdmin();
        }
    });
});

function getData() {
    $.get("/v1/open/studio/getStudioList",function(data){
        //console.log(data);
        for(var index in data){
            $("#tbodyS").append("<tr class='even gradeC'><td>"+data[index].sid+"</td><td>"+data[index].sname+"</td><td class='center'>"+data[index].snumber+"</td><td style= 'overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[index].slogoPath+"</td><td class='center'>"+data[index].saddress+"</td>" +
                "<td class='center'>"+data[index].sqq+"</td><td class='center' style= 'overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+data[index].ssummary+"</td><td class='center'><button class='btn btn-primary btn-sm' data-toggle='modal' data-target='#myModal' onclick='update("+data[index].sid+")'><i class='fa fa-edit '></i> Edit</button>&nbsp;<button class='btn btn-danger btn-sm' data-toggle='modal' data-target='#myModals' onclick='deleteStudio("+data[index].sid+")'><i class='fa fa-pencil'></i> Delete</button></td></tr>");
        }
    });
};

/**
 * 修改按钮
 * @param id
 * @returns
 */
function update(id) {
    let b64id = bases.encode(id);
    var urlPath = "/v1/open/studio/findById/"+b64id;
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

/**
 * 修改
 * @returns
 */
function updataFrom() {
    var newData = {
        "sid" : $("#sid").val(),
        "sname" : $("#sname").val(),
        "saddress"  : $("#saddress").val(),
        "snumber" : $("#snumber").val(),
        "sqq" : $("#sqq").val(),
        "ssummary" : $("#ssummary").val()
    }
    if("" != $("#sqq").val() ){
        if(0 != $("#snumber").val()){
            if("" != $("#saddress").val() ){
                if("" != $("#ssummary").val() ){
                    $.ajax({
                        type: 'PUT',
                        url: "/v1/open/studio/updateStudio",
                        data: JSON.stringify(newData),
                        success: function(data) {
                            if(data.statusCode == 200200){
                                return;
                            }else {
                                console.log("修改错误")
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

/**
 * @param id 工作室id
 * @returns
 */
function deleteStudio(id) {
    deleteSid = id;
}
/**
 * 删除工作室
 * @returns
 */
function deleteStudioYes() {
    if(deleteSid != 0){
        urlPath = "/v1/open/studio/deleteStudio/"+deleteSid
        $.ajax({
            type: 'DELETE',
            url: urlPath,
            success: function(data) {
                if(data.statusCode == 200200){
                    linkAdmin();
                }else {
                    alert("Delete studio error");
                }
            },
            dataType: 'json',
            contentType: "application/json;charset-UTF-8"
        });
    }
}
function linkAdmin() {
    window.location.href="/admin/index.html"
}
