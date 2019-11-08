/**
 * 登录
 */
function sub(){
    var check = "check error";
    var user = "NOUser";
    var par = "Parameter error";
    var uname=document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var codes =document.getElementById("inputPassword3").value;;
    var uu = {
        "userName" : uname,
        "passWord" : password,
        "check" : codes
    };
    $.ajax({
        type: 'POST',
        url: "/v1/open/studio/doLogin",
        data: JSON.stringify(uu),
        success: function(data) {
            console.log(data);
            if (200200 != data.statusCode) {
                if (check == data.message) {
                    $("#bq").html("验证码错误");
                    $("#inputPassword3").val("");
                    // window.location.reload();
                }else if (user == data.message){
                    $("#bq").html("用户名密码错误");
                    defValue();
                    refresh();
                }else if (par == data.message){
                    $("#bq").html("用户名密码未填写");
                    refresh();
                }
            } else {
                linkHome(uname);
            }
        },
        error: function(e) {
            if(e.responseJSON.code === 48005000){
                window.alert("提交超时，刷新重试")
            }
        },
        dataType: 'json',
        contentType: "application/json;charset-UTF-8"
    });
}

/* 执行重新查找验证码 */
function refresh() {
    /* var url = $("#basePath").val() + "user/check.jpg";
    $("#img").attr("src",url); */
    var date = new Date();
    var captcha = document.getElementById("img");
    captcha.src = "/v1/open/studio/user/check.jpg?t=" + date.getTime();
}

/**
 * 登录成功验证
 * @param data
 */
function  linkHome(data) {
    window.location.href="conn.html?Sichuan%20Vocational%20College%20of%20Science%20and%20Technology%20student%20studio%20background%20administrator%20login%20name%20verification="+data;
}

/**
 * 清空输入框
 */
function defValue() {
    $("#username").val("");
    $("#password").val("");
    $("#inputPassword3").val("");
}