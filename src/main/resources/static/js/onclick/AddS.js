	//七牛token 七牛云平台安全标识
	let qiniutoken = '';
	//七牛云图片base64格式上传地址
	let uploadUrl = "http://upload.qiniup.com/putb64/-1";
	//需要上传的图片内容 base64格式
	let imgStr = '';
	let imgPath = '';
	//七牛云平台 配置的域名
	let urlHeader = "http://image.xiaoandx.club/";
	let tokenN = '';
	
	$(function() {
		getToken();
	});

	//获取七牛云token
    function qnToken() {
        $.ajax({
            url: '/v1/open/studio/getToken',
            type: 'get',
            dataType: 'json',
            //平台登录token 不重启30天过期
            async:false,
            success: function(data) {
                qiniutoken = data.uptoken;
            },
            error: function(e){
                alert("服务器错误");
            }
        })
    }

    //上传图片
    function uploadImg() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", uploadUrl, true);
        //文本类型
        xhr.setRequestHeader("Content-Type", "application/octet-stream");
        //七牛认证信息 注意空格
        xhr.setRequestHeader("Authorization", "UpToken " + qiniutoken);
        xhr.send(imgStr);
        //监听状态
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4) {
                var result = xhr.responseText;
                result = JSON.parse(result);
                //$("#mypic").attr("src", urlHeader + result.hash);
                if(result.hash != undefined){
                	if(result.hash != "Fto5o-5ea0sNMlW_75VgGJCv2AcJ"){
                    	imgPath = urlHeader + result.hash
                    	$("#ImagePath").val(urlHeader + result.hash);
                    }else {
                    	$("#ImagePath").val("上传出现错误,重新选择上传");
					}
                }else {
                	$("#ImagePath").val("上传出现错误,重新选择上传");
				}
            }
        }

    }

    //将图片转为base64格式的字符串
    $('input[type=file]').on('change', function() {
        var reader = new FileReader();
        //filses就是input[type=file]文件列表，files[0]就是第一个文件，这里就是将选择的第一个图片文件转化为base64的码
        reader.readAsDataURL(this.files[0]);
        reader.onload = function(e) {
            //或者 e.target.result都是一样的，都是base64码
            imgStr = reader.result.split(',')[1];
        }
    });
    //开始上传
    $("#sub").click(function(){
        //获取最新token
        qnToken();
        uploadImg();
    });
	
	
/**
  * [linkHome 跳转到首页]
  * @return 无返回值void
  */
 function linkHome(){
	window.location.href='index.html';
}
 
 function getToken() {
	 $.get("/v1/open/studio/redisToken",function(data){tokenN = data;});
}

 /**
  * 提交表单
  * @returns
  */
function submitAddS(){
	//获取填的qq号
	var sqqs = $("#SQQ").val();
	var snames = $("#Sname").val();
	var numbers = $("#SNumber").val();
	var snumers  = Number(numbers);
	var saddresss = $("#SAddress").val();
	var ssummarys = $("#summary").val();
	var slogoPaths = imgPath;
	var uu = {
		"sqq" : sqqs,
		"sname" : snames,
		"number" : numbers,
		"snumber" : snumers,
		"saddress" : saddresss,
		"ssummary" : ssummarys,
		"slogoPath" : slogoPaths
	};
	if ("" != snames ) {
		if("" != sqqs ){
			if(0 != snumers ){
				if("" != saddresss ){
					if("" != ssummarys ){
						if("" != slogoPaths ){
							$.ajax({
								  type: 'POST',
								  url: "/v1/open/studio/addStudio",
								  data: JSON.stringify(uu),
								  beforeSend: function (XMLHttpRequest) {
						         		XMLHttpRequest.setRequestHeader("token", tokenN);
						          },
								  success: function(data) {
									  if(data.statusCode == 200200){
										  linkHome();
									  }else {
										return;
									}
								  },
								  error: function(e) {
									  if(e.responseJSON.code == 48005000){
										  window.alert("提交超时，刷新重试")
									  }
								},
								  dataType: 'json',
								  contentType: "application/json;charset-UTF-8"
								});
						}else{
							alert("工作室logo未上传");
						}
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
	}else{
		alert("工作室名称未填写");
	}
}