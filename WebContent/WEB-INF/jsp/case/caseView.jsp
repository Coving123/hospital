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
	<title>病历表单--layui后台管理模板 2.0</title>
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
  <div class="layui-form-item">
    <label class="layui-form-label">医生姓名</label>
    <div class="layui-input-block">
      <input type="text" name="dname" value="${requestScope.record.doctor.name }" placeholder="请输入医生姓名" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">患者姓名</label>
    <div class="layui-input-block">
      <input type="text" name="pname" value="${requestScope.record.patient.name }" placeholder="请输入患者姓名" autocomplete="off" class="layui-input" style="width: 500px;" readonly>
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">症状</label>
    <div class="layui-input-block">
      <textarea name="symptom" placeholder="请输入症状内容" class="layui-textarea" readonly>${requestScope.record.symptom }</textarea>
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">诊断</label>
    <div class="layui-input-block">
      <textarea name="diagnosis" placeholder="请输入诊断内容" class="layui-textarea" readonly>${requestScope.record.diagnosis }</textarea>
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">处方</label>
    <div class="layui-input-block">
      <textarea name="prescription" placeholder="请输入处方内容" class="layui-textarea" readonly>${requestScope.record.prescription }</textarea>
    </div>
  </div>
  <div class="layui-form-item layui-form-text" style="width: 500px;">
    <label class="layui-form-label">创建日期</label>
    <div class="layui-input-block">
       <input type="text"  id="test1" placeholder="请输入创建时间" value="${requestScope.record.createDate }" class="layui-input" readonly>
    </div>
  </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/caseSeeList.js"></script>
	<script>
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#test1'
	    ,type: 'datetime'//指定元素
	  });
	});
	</script>
</body>
</html>