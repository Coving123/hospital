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
		<title>药品信息表单--后台管理模板</title>
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
		<form class="layui-form" action="">
		  <input type="hidden" id="mid" name="mid" value ="${requestScope.medicine.mid}" >
		  <div class="layui-form-item">
		    <label class="layui-form-label">药品名称</label>
		    <div class="layui-input-block">
		      <input type="text" id="name" value="${requestScope.medicine.name}" name="name" required  lay-verify="required" placeholder="请输入药品名称" autocomplete="off" class="layui-input" style="width: 500px;">
		    </div>
		  </div>
		 <div class="layui-form-item" style="width: 500px;">
			<label class="layui-form-label">药品类别</label>
			<div class="layui-input-block">
				<select id="category" name="category" class="category"
					lay-verify="required" lay-filter="category">
					<option value=""></option>
			        <option value="感冒药" 
			        	<c:if test="${requestScope.medicine.category == '感冒药' }">selected</c:if> 
			        >感冒药</option>
			        <option value="肠胃药" 
			        	<c:if test="${requestScope.medicine.category == '肠胃药' }">selected</c:if> 
			        >肠胃药</option>
			        <option value="外用药"
			        	<c:if test="${requestScope.medicine.category == '外用药' }">selected</c:if> 
			        >外用药</option>
			     </select>
			</div>
		 </div>	 
		<div class="layui-form-item">
		    <label class="layui-form-label">药品描述</label>
		    <div class="layui-input-block">
		      <textarea id="description" name="description" lay-verify="required" placeholder="请输入药品描述" class="layui-textarea" style="width: 500px;">${requestScope.medicine.description}</textarea>
		    </div>
		  </div>
		  <div class="layui-form-item" >
		    <label class="layui-form-label">单价</label>
			<div class="layui-input-block">
		      <input type="text" id="price" name="price" value="${requestScope.medicine.price}" lay-verify="required" placeholder="请输入单价" autocomplete="off" class="layui-input" style="width: 300px;">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">库存数量</label>
		    <div class="layui-input-inline">
		      <input type="text" id="count" name="count" value="${requestScope.medicine.count}" lay-verify="required" placeholder="请输入库存数量" autocomplete="off" class="layui-input" 
		      onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >
		    </div>
		    <div class="layui-form-mid layui-word-aux">仅允许输入正整数</div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="addMedicine">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/medList.js"></script>
	</body>
</html>
