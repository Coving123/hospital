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
	<input type="hidden" id="rid" value="${requestScope.record.rid }">
	<input type="hidden" id="pid" value="${requestScope.record.patient.pid }">
	<input type="hidden" id="did" value="${requestScope.record.doctor.did }">
  <div class="layui-form-item">
    <label class="layui-form-label">医生姓名</label>
    <div class="layui-input-block">
      <c:if test="${sessionScope.doctor==null }">
	      <input type="text" id="dname" name="dname" value="${requestScope.record.doctor.name }" required  lay-verify="required" placeholder="请输入医生姓名" autocomplete="off" class="layui-input" style="width: 500px;">
      </c:if>
      <c:if test="${sessionScope.doctor!=null }">
	      <input type="text" id="dname" name="dname" value="${sessionScope.doctor.name }" required  lay-verify="required" placeholder="请输入医生姓名" autocomplete="off" class="layui-input" style="width: 500px;">
      </c:if>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">患者姓名</label>
    <div class="layui-input-block">
      <input type="text" id="pname" name="pname" required value="${requestScope.record.patient.name }" lay-verify="required" placeholder="请输入患者姓名" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">症状</label>
    <div class="layui-input-block">
      <textarea name="symptom" id="symptom" placeholder="请输入症状内容" class="layui-textarea">${requestScope.record.symptom }</textarea>
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">诊断</label>
    <div class="layui-input-block">
      <textarea name="diagnosis" id="diagnosis" placeholder="请输入诊断内容" class="layui-textarea">${requestScope.record.diagnosis }</textarea>
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">处方</label>
    <div class="layui-input-block">
      <textarea name="prescription" id="prescription" placeholder="请输入处方内容" class="layui-textarea">${requestScope.record.prescription }</textarea>
    </div>
  </div>
  <div class="layui-form-item layui-form-text" style="width: 500px;">
    <label class="layui-form-label">创建日期</label>
    <div class="layui-input-block">
       <input type="text" id="createDate" placeholder="请输入创建时间" value="${requestScope.record.createDate }" class="layui-input">
    </div>
  </div>
   
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="addDoctor">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/caseList.js"></script>
	<script>
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#createDate'
		,type: 'datetime'
	  });
	});
	</script>
</body>
</html>