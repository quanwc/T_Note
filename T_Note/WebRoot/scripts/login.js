$(function(){
		
	//登录按钮单击处理
	$("#login").click(function(){
	
		//清空提示信息
		$("#name_msg").html("");
		$("#password_msg").html("");
	
		//获取请求要提交的数据
		var name = $("#name").val().trim();//获取文本框的值
		var password = $("#password").val().trim();//
		
		var flag = true;
		//检测数据格式合法性
		if(name==""){
			$("#name_msg").html("用户名不能为空");
			flag = false;  //未检测通过
		}
		if(password==""){
			$("#password_msg").html("密码不能为空");
			flag = false;  //未检测通过
		}
		
		
		//检测通过时发送Ajax请求
		//如果用户名和密码有空的，不能发送请求
		if(flag){
		
			//采用Http Basic Authentication模式传输用户名，密码
			var msg = name + ":" + password;
			var base64_msg = Base64.encode(msg); //加密，之后会将加密内容放到HTTP协议的head部分
		
			$.ajax({
				url: "http://localhost:8081/T_Note/user/login.do",
				type: "post",
				
				//参数名与Controller方法参数名相同才能将数据传递过去
				//data: {"name":name, "pass":password},
				
				dataType: "json",
				
				beforeSend: function(xhr){  //xhr: ajax底层封装的XmlHttpRequest对象
					xhr.setRequestHeader("Authorization", "Basic "+base64_msg);
				},
				success: function(result){
					//解析返回的json结果
					if(result.status==0){//成功
						
						//将用户身份信息放入cookie。因为一旦登录成功就要跳转到edit页面，
						//需要传入用户的id，此时才可以将id对应用户的笔记本信息加载出来。
						var token = result.data.userToken;
						var uid = result.data.userId;
						
						addCookie("userId", uid, 0.5);//需要引入外部的cookie_util.js文件
						addCookie("userToken", token, 0.5);
						//登录成功了，页面应该跳转
						//通过脚本实现页面跳转：window.location.href=""
						window.location.href="edit.html";
					}else if(result.status==1){ //用户名错误
						$("#name_msg").html(result.msg);
					}else if(result.status==2){  //密码错误
						$("#password_msg").html(result.msg);
					}
				},
				error:function(){
					alert("登录异常");
				}
			});
		}

	});
});