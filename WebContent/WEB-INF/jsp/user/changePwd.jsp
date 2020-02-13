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
	<title>修改密码--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/one-css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form changePwd" id="f1">
		<div style="margin:0 0 15px 110px;color:#f00;">请输入正确的旧密码，新密码必须两次输入一致才能提交</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		    	<input type="text" value="${sessionScope.user.username}" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="oldPassword" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="repassword" value="" placeholder="再次输入确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		    	<input type="submit" lay-submit="" lay-filter="i" value="确认修改" class="layui-btn layui-btn-primary"/>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
	<script type="text/javascript">
	layui.use(['form','layer','laydate','table','upload'],function(){
		 var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer,
	        $ = layui.jquery
	        
		 $("#f1").submit(function(){
			if($("#oldPassword").val()!="${sessionScope.user.password}"){
				layer.alert("原密码输入错误", {
					icon: 5,
					title: "修改失败"
				});
				return false;
			}
			console.log($("#password").val());
			console.log($("#repassword").val());
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			
			if(password!=repassword){
				layer.alert("两次输入密码不一致", {
					icon: 5,
					title: "修改失败"
				});
				return false;
			}
			
			$.ajax({
				type : "POST",
				async: false,
				url : "${pageContext.request.contextPath}/user/doUserModify.do",
				data : {
					password : $("#password").val()
				},
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				dataType : "json",
				success : function(data) {
					layer.msg('密码修改成功！！', {
				        time: 3000, //3s后自动关闭
				        btn: ['确定']
				      });
				}
			});
			
			setTimeout(function(){
	            layer.closeAll("iframe");
	            //刷新父页面
	            window.parent.location.href="${pageContext.request.contextPath}/showLogin.do";
	        },3000);
	        return false;		
		});
		
	});
	</script>
</body>
</html>