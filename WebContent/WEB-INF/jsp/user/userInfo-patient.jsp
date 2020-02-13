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
	<title>个人资料--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="../../res/css/one-css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="user_left">
			<div class="layui-form-item">
			    <label class="layui-form-label">登录名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="xuyang" name="loginName" disabled class="layui-input layui-disabled" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">密码</label>
			    <div class="layui-input-block">
			    	<input type="password" value="" placeholder="请输入密码" lay-verify="required" class="layui-input pwd" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">姓名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="" name="name" placeholder="请输入姓名" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item" pane="">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			    	<input type="radio" name="sex" value="男" title="男" checked="">
	     			<input type="radio" name="sex" value="女" title="女">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">家庭地址</label>
			    <div class="layui-input-block">
			    	<input type="tel" value="" name="address" placeholder="请输入家庭地址" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">联系电话</label>
			    <div class="layui-input-block">
			    	<input type="tel" value="" name="phone" placeholder="请输入联系" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">出生年月</label>
			    <div class="layui-input-block">
			    	<input type="text" value="" placeholder="请输入出生年月" lay-verify="required" class="layui-input" >
			    </div>
			</div>
		
		<div class="layui-form-item" style="margin-left: 5%;">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="../../res/layui/layui.js"></script>
	<script type="text/javascript" src="../../res/js/one-js/address.js"></script>
	<script type="text/javascript" src="../../res/js/one-js/user.js"></script>
</body>
</html>