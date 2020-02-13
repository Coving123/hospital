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
	<title>预约表单--layui后台管理模板 2.0</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/layui/css/layui.css" media="all" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/public.css" media="all" />
	<style>
		
	</style>
</head>
<body class="childrenBody">
<form class="layui-form" action="">
		  <input type="hidden" id="mid" name="mid" value ="${requestScope.medicine.mid}" >
		  <div class="layui-form-item">
		    <label class="layui-form-label">药品名称</label>
		    <div class="layui-input-block">
		      <input type="text" id="name" value="${requestScope.medicine.name}" name="name" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
		    </div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label">药品类别</label>
		    <div class="layui-input-block">
		      <input type="text" id="category" name="category" value="${requestScope.medicine.category}" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
		    </div>
		  </div>
		   <div class="layui-form-item">
		    <label class="layui-form-label">药品描述</label>
		    <div class="layui-input-block">
		      <textarea id="description" name="description" class="layui-textarea" style="width: 500px;" readonly>${requestScope.medicine.description}</textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">单价</label>
			<div class="layui-input-block">
		      <input type="text" id="price" name="price" value="${requestScope.medicine.price}" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">库存数量</label>
		    <div class="layui-input-block">
		      <input type="text" id="count" name="count" value="${requestScope.medicine.count}" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
		    </div>
		  </div>
		<div class="layui-form-item">
			<div class="layui-input-block">
			    <a href="javascript:;" onclick="jump()" class="layui-btn">前去购买</a>
<!-- 				<button class="layui-btn" lay-submit onclick="jump()">前去购买</button> -->
			</div>
		</div>

	</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/medSeeList.js"></script>
<script type="text/javascript">
	function jump() {
		window.location.href = "${pageContext.request.contextPath}/showBookManage.do";
	}
</script>

</body>
</html>