//生成二维码
function create_QRCode(id) {
	var qrcode = new QRCode(document.getElementById("qrcode"), {
		width: 96, // 设置宽高
		height: 96
	});
	qrcode.makeCode("http://www.baidu.com");
}

function sign_publish() {
	
	//构造签到发布数据	
	var info_name = document.getElementById("info_name");
	var info_locationRange = document.getElementById("info_locationRange");
	var info_effectineTime = document.getElementById("info_effectineTime");
	var tags = document.getElementsByClassName("info_tags");
	var defines = document.getElementsByClassName("info_defines");

	//normal value
	var str = "{";
	str += "\"name\":\"" + info_name.value + "\"," +
		"\"locationRange\":" + info_locationRange.value + "," +
		"\"effectiveTime\":" + info_effectineTime.value + ",";

	//tags
	if(tags.length > 0 || defines.length > 0) 
		str += "\"" + "signInfos" + "\"" + ":[";
	
	if(tags.length > 0) {
		for(var i = 0; i < tags.length; i++) {
			str += "{\"name\":\"" + tags[i].innerHTML + "\",";
			str += "\"flag\":2},";
		}
	}
	//defines
	if(defines.length > 0) {
		for(var i = 0; i < defines.length; i++) {
			str += "{\"name\":\"" + defines[i].innerHTML + "\",";
			str += "\"flag\":1},";
		}
	}	
	str = str.substr(0, str.length - 1);
		
	if(tags.length > 0 || defines.length > 0) {
		str += "]";
	}
	str += "}";

	//发布签到
	publish(str);
}

function sign_submit(signId) {
	
	var choosedTags = document.getElementsByClassName("ellipse_choosed");
	var defines = document.getElementsByClassName("defines");
	var defines_value = document.getElementsByName("defines_value");
	
	//构造签到提交数据
	var str = "{";
	str += "\"id\":" + signId + ",";
	
	//longitude
	str += "\"longitude\":" + getCookie("longitude") + ",";
	
	//latitude
	str += "\"latitude\":" + getCookie("latitude") + ",";
			
	if(choosedTags.length > 0 || defines.length > 0) {
		str += "\"" + "signInfos" + "\"" + ":[";
	}
	
	//tags	
	if (choosedTags.length > 0)
	{
		for(var i = 0 ; i< choosedTags.length ; i++)
		{
			str += "{\"name\":\"" + choosedTags[i].innerHTML + "\"},";			
		}
	}

	//defines	
	if (defines.length > 0)
	{
		for(var i = 0 ; i< defines.length ; i++)
		{
			//输入值合法
			if(defines_value[i].value != null)
				var s = defines[i].innerHTML.substr(0, defines[i].innerHTML.length - 1);
				str += "{\"name\":\"" + s + "\"" + ",\"value\":\"" + defines_value[i].value + "\"" + "},";	
		}
	}	
	
	str = str.substr(0, str.length - 1);
	
	if(choosedTags.length > 0 || defines.length > 0) {
		str += "]";
	}
	str += "}";
	
	//提交签到
	submit(str);
}

var flag; 
function submit(data) {
	
	//调用后台接口提交签到
	$.ajax({
		type: "post",
		url: "http://1663c6a809.iok.la:44045/com.sign/submit",
		contentType: "application/json; charset=utf-8",
		data: data,
		success: function(data) {
			flag = window.setInterval("redirectNotice()", 1000);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}


function getJsParamOfWeChat() {
	var data;
	//调用后台接口提交签到
	$.ajax({
		async: false,
		type: "get",
		url: "http://1663c6a809.iok.la:44045/com.sign/getJsParamOfWeChat",
		contentType: "application/json; charset=utf-8",
		data: '',
		success: function(value) {
			data = value;
		},	
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
	return data;
}

var mill = 5;
//页面跳转延时
function redirectNotice() {
	mill = mill - 1;
	mui.alert("签到成功！本页面将在" + mill + "秒后跳转至首页");
	if(mill == 0 ) {
		window.clearInterval(flag, 1000);
		window.location.href="http://1663c6a809.iok.la:44045/com.sign/sign.html";		
	}
}

//自动补零
function pad(num, n) {
	return(Array(n).join(0) + num).slice(-n);
}

function publish(data) {
	
	//调用后台接口发布签到
	$.ajax({
		type: "post",
		url: "http://1663c6a809.iok.la:44045/com.sign/publish",
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: data,
		success: function(data) {
			
			//获取签到二维码
			var QRcode = document.getElementById("QRcode");
			QRcode.src = "http://1663c6a809.iok.la:44045/com.sign/getQRcode?signId=" + data.id;
			mui.alert(data.messageContent + ",请妥善保管您的二维码，发送给您的好友扫码进行签到");
			
			//隐藏按钮
			$("#confirm").hide();
			$("#cancel").hide();
			var addbtn = document.getElementById("addBtn1");
			addbtn.parentNode.removeChild(addbtn);
			addbtn = document.getElementById("addBtn2");
			addbtn.parentNode.removeChild(addbtn);
			
			//设置提示
			document.getElementById("QRcodeNote").innerHTML = "您的签到二维码：";
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}

function addItemForPublish(e) {
	
	//动态增加控件
	if(e == "userInfos") {
		
		//增加输入的定义
		var btnArray = ['取消', '确定'];
		mui.prompt('', '例：(手机号)', '请输入您需要的用户信息', btnArray, function(e) {
			if(e.index == 1) {
				
				//add label for div
				var userInfosDiv = document.getElementById("userInfos");
				var newLable = document.createElement("label");
				newLable.innerHTML = e.value;
				newLable.className = "ellipse info_defines";
				
				//设置自适应宽度
				var width = 30 * newLable.innerHTML.length / window.innerWidth * 100;
				newLable.style.width = width + "%";
				userInfosDiv.appendChild(newLable);
			}
		})
	} else if(e == "tags") {
		
		//增加标签
		var btnArray = ['取消', '确定'];
		mui.prompt('', '例：(会议)', '请输入签到标签', btnArray, function(e) {
			if(e.index == 1) {
				
				//add label for div
				var userInfosDiv = document.getElementById("tags");
				var newLable = document.createElement("label");
				newLable.innerHTML = e.value;
				newLable.className = "ellipse info_tags";
				newLable.id = "submit_tags";
				
				//设置自适应宽度
				var width = 30 * newLable.innerHTML.length / window.innerWidth * 100;
				newLable.style.width = width + "%";
				userInfosDiv.appendChild(newLable);
			}
		})
	}
}

function conventLocation() {
	//转换坐标
	$.ajax({
		url: "http://api.map.baidu.com/geoconv/v1/?coords=" + getCookie("longitude") + "," + getCookie("latitude") + 
		"&from=1&to=5&ak=1KpCRD2fjrz6YWidWiadUuLMPIgSt72O",
		dataType: 'jsonp',
		data: '',
		jsonp: 'callback',
		success: function(res) {
			var point_bmap = new BMap.Point(res.result[0].x, res.result[0].y);
			set_bmap_point(point_bmap);				
		},
		error: function(xhr, type, errorThrown) {
			// 异常处理；
			mui.toast("error");
		},
		timeout: 3000
	});
}

function set_bmap_point(point_bmap) {
	//显示坐标
	var marker_bmap = new BMap.Marker(point_bmap);
	map.addOverlay(marker_bmap);
	var label = new BMap.Label("您的位置", {
		offset: new BMap.Size(20, -10)
	});
	
	//设置地图提示样式
	label.setStyle({
		width: "60px",
		color: '#fff',
		background: '#CD2626',
		border: '1px solid "#ff8355"',
		borderRadius: "5px",
		textAlign: "center",
		height: "26px",
		lineHeight: "26px"
	});
	
	// 添加百度label
	marker_bmap.setLabel(label); 
	
	// 初始化地图，设置中心点坐标和地图级别
	map.centerAndZoom(point_bmap, 17); 
}

function getPositionError(error) {
	//获取位置失败
	switch(error.code) {
		case error.TIMEOUT:
			mui.toast("连接超时，请重试");
			break;
		case error.PERMISSION_DENIED:
			mui.toast("您拒绝了使用位置共享服务，查询已取消");
			break;
		case error.POSITION_UNAVAILABLE:
			mui.toast("亲爱的火星网友，非常抱歉，我们暂时无法为您所在的星球提供位置服务");
			break;
	}
}

//写cookies 
function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

//读取cookies 
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

//cookies 超时
//这是有设定过期时间的使用示例： 
//s20是代表20秒 
//h是指小时，如12小时则是：h12 
//d是天数，30天则：d30 
function getsec(str)
{ 
   alert(str); 
   var str1=str.substring(1,str.length)*1; 
   var str2=str.substring(0,1); 
   if (str2=="s")
   { 
        return str1*1000; 
   }
   else if (str2=="h")
   { 
       return str1*60*60*1000; 
   }
   else if (str2=="d")
   { 
       return str1*24*60*60*1000; 
   } 
} 

//getSignUserInfo
function getSignUserInfo(){
	var id = getCookie("data"); 
	$.ajax({
		url: "http://1663c6a809.iok.la:44045/com.sign/getSignUserInfo?id=" + id,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: '',
		success: function(signUsers) {
			if(signUsers != null)
			{
				var html = $("#signUsersTmpl").render(signUsers);	
				$("#signUsers").append(html);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}

function readMessage(id){
	//调用后台接口发布签到
	alert(id);
	$.ajax({
		url: "http://1663c6a809.iok.la:44045/com.sign/readMessage?id="+ id,
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: '',
		success: function(res) {
			if(res = 'success')
			{
				$('#'+id)[0].onclick = null;
				$('#'+id).find("img").remove();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}

function getMySign(){
	//调用后台接口发布签到
	$.ajax({
		url: "http://1663c6a809.iok.la:44045/com.sign/getMySign",
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: '',
		success: function(signs) {
			var html = $("#signTmpl").render(signs);
			$("#cardsSign").append(html);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}

function getMyMessage(){
	//调用后台接口发布签到
	$.ajax({
		url: "http://1663c6a809.iok.la:44045/com.sign/getMyMessage",
		contentType: "application/json; charset=utf-8",
		dataType: 'json',
		data: '',
		success: function(messages) {		
			var html = $("#messageTmpl").render(messages);
			$("#cardsMessage").append(html);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
			alert(XMLHttpRequest.readyState);
			alert(textStatus);
		},
		timeout: 10000
	});
}