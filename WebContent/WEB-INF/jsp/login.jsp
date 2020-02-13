<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>登录</title>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/res/layui/css/layui.css"
			media="all">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/res/font-awesome-4.7.0/css/font-awesome.css" />
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/res/css/one-css/login.css"
			media="all" />
	</head>

	<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
			<h1 style="font-family:STKaiti;color:#363636;">欢迎访问社区医疗服务系统</h1>
			</header>
			<div class="beg-login-main">
				<form action="/manage/login" class="layui-form" method="post">
					<input name="__RequestVerificationToken" type="hidden"
						value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />
					<div class="avatar">
						<img
							src="${pageContext.request.contextPath}/res/images/login/tmplog.png"
							width="100px" alt="">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon"> <i class="layui-icon">&#xe770;</i>
						</label> <input type="text" id ="username" name="userName" lay-verify="userName|required"
							autocomplete="off" placeholder="这里输入登录名" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon"> <i class="layui-icon">&#xe642;</i>
						</label> <input type="password" id = "password" name="password" lay-verify="password|required"
							autocomplete="off" placeholder="这里输入密码" class="layui-input">
					</div>					
					<div class="layui-form-item">
			            <label class="beg-login-icon"> <i class="layui-icon">&#xe672;</i>
						</label> <input type="text" name="imgVercode" id="imgVercode" lay-verify="imgVercode|required" 
						    autocomplete="off" placeholder="请输入验证码" style="width:155px;float: left;" class="layui-input">
			    	    <div id="get-vercode" class="layui-inline" style="float: right;height: 38px;"></div>
		            </div>
					<div class="layui-form-item">
						<div class="beg-pull-right">
							<button class="layui-btn Link_btn" lay-submit lay-filter="login">
								<i class="layui-icon">&#x1005;</i> 登录
							</button>
							<a class="layui-btn register"> <i class="layui-icon">&#xe61f;</i>患者注册</a>
						</div>
						<div class="beg-clear"></div>
					</div>
				</form>
			</div>
			<footer>
			<p style="font-family:STXingkai;letter-spacing:2px; font-size:16px;color:#666666;">Welcome to Community Health Service System !</p>
			</footer>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui_menu/layui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/imgcodeVerify.js"></script>
		<script>
		    var verifyCode = new GVerify("get-vercode");
			layui.use(['layer','form','jquery'], function() {
				var layer = layui.layer, $ = layui.jquery, form = layui.form();
	
				form.on('submit(login)', function(data) {
					var res = verifyCode.validate(document.getElementById("imgVercode").value);
 					if(res){
 						$.ajax({
							type : "POST",
							async: false,
							url : "${pageContext.request.contextPath}/user/doLogin.do",
							data : {
								username : $("#username").val(),
								password : $("#password").val()
							},
							contentType: "application/x-www-form-urlencoded; charset=utf-8", 
							dataType : "json",
							success : function(data) {
								if(data.path != undefined){
									layer.alert("点击确定跳转", {
										icon: 1,
										title: "登录成功"
									},function(){
										window.location.href="${pageContext.request.contextPath}/showIndex.do";
									});
								}
								if(data.error != undefined){
									var msg = ""; 
									if(data.error == "1"){
										msg = "用户名不存在";
										
									}else if(data.error == "2"){
										msg = "用户名与密码不匹配";
									
									}
									layer.alert(msg, {
										icon: 5,
										title: "登录失败"
									});
								}
							}
						});
					}else{
						layer.alert("验证码有误，请重新输入", {
							icon: 5,
							title: "ERROR!"
						});	
					}
					return false;
				});
	
				$(".register").click(function() {
					var index = layer.open({
						title : "患者注册",
						type : 2,
						area : [ "700px", "600px" ],
						content : "showRegist.do",
						success : function(layero, index) {
						}
					});
					//layui.layer.full(index);//全屏
				});
			});
		</script>
	</body>

</html>