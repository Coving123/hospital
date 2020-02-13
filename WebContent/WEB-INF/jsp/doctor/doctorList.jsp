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
	<title>医生管理--后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<!-- <div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入搜索的内容" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div> -->
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addLink_btn">添加医生</a>
			</div>
		</form>
	</blockquote>
	<table id="linkList" lay-filter="linkListTab">
		
	</table>
	<script type="text/javascript" id="username">
		{{# if(d.user!=null){}}
			{{d.user.username}}
		{{# } }}
	</script>
	
	<script type="text/javascript" id="password">
		{{# if(d.user!=null){}}
			{{d.user.password}}
		{{# } }}
	</script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/doctorList.js"></script>
</body>
</html>