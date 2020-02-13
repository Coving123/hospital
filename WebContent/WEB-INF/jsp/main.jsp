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
	<title>首页--社区医疗服务平台</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/one-css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/one-css/main.css" media="all" />
	
	<style>
		.childrenBody {
	/*
	background: url(${pageContext.request.contextPath}/res/images/login/rains.jpg) no-repeat center center fixed;
	text-align: center;
	color: white;
	font-size: 30px;
	padding-top: 60px;
	*/
	}
	.welcom-font{
		
	}
	.body{margin: 5px; }
    .demo-carousel{height: '400px'; line-height: 400px; text-align: center; font-size: 20px; }
	</style>
</head>
<body class="childrenBody" >
		<!--  
		<font class="welcom-font">温馨提示：<br>现在是感冒多发季，望社区居民要及时增减衣物、注重保暖，并保证营养均衡，适当运动！</font> -->

			<div class="layui-carousel" id="test1">
				<div carousel-item>
					<div>
						<p class="layui-bg-green demo-carousel">健康</p>
					</div>
					<div>
						<p class="layui-bg-red demo-carousel">快乐</p>
					</div>
					<div>
						<p class="layui-bg-blue demo-carousel">幸福</p>
					</div>
					<div>
						<p class="layui-bg-orange demo-carousel">平安</p>
					</div>
					<div>
						<p class="layui-bg-cyan demo-carousel">聪慧</p>
					</div>
				</div>
			</div>


	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script>
		layui.use([ 'carousel', 'form' ], function() {
			var carousel = layui.carousel, form = layui.form;

			//执行一个轮播实例
			carousel.render({
				elem : '#test1',
				width : '100%', //设置容器宽度
				height : '100%', //设置容器高度
				anim : 'fade'//切换动画方式
			});
		});
	</script>
 </body>
</html>