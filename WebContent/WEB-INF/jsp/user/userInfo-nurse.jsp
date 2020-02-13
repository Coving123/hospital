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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/one-css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form">
		<div class="user_left">
			<input type="hidden" id="nid" value="${sessionScope.nurse.nid }">
			<div class="layui-form-item">
			    <label class="layui-form-label">登录名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${sessionScope.user.username}" id="loginname" name="loginName" disabled class="layui-input layui-disabled" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">密码</label>
			    <div class="layui-input-block">
			    	<input type="password" value="${sessionScope.user.password}" id="password" placeholder="请输入密码" lay-verify="required" class="layui-input pwd" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">姓名</label>
			    <div class="layui-input-block">
			    	<input type="text" value="${sessionScope.nurse.name }" id="name" name="name" placeholder="请输入姓名" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item" pane="">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
			    	<input type="radio" name="sex" value="男" title="男"
				    	<c:if test="${sessionScope.nurse.sex=='男' }">
				    		checked
				    	</c:if>
			    	>
	     			<input type="radio" name="sex" value="女" title="女"
	     				<c:if test="${sessionScope.nurse.sex=='女' }">
				    		checked
				    	</c:if>
	     			>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">职称</label>
			    <div class="layui-input-block">
			    	<input type="tel" value="${sessionScope.nurse.titel }" id="titel" name="titel" placeholder="请输入职称" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">科室</label>
			    <div class="layui-input-block">
			    	<input type="tel" value="${sessionScope.nurse.subject }" id="subject" name="subject" placeholder="请输入科室" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">学历</label>
			    <div class="layui-input-block">
			    	<input type="tel" value="${sessionScope.nurse.education }" id="education" name="education" placeholder="请输入学历" lay-verify="required" class="layui-input" style="width: 500px;">
			    </div>
			</div>
		
		<div class="layui-form-item" style="margin-left: 5%;">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/address.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/user.js"></script>
</body>
</html>