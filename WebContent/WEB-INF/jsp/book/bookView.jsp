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
<input type="hidden" id="oid" value="${requestScope.requireMent.oid}">
<input type="hidden" id="did" value="${requestScope.requireMent.nurse.nid}">
<input type="hidden" id="pid" value="${requestScope.requireMent.patient.pid}">
<input type="hidden" id="mid" value="${requestScope.requireMent.medicine.mid}">

 <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">患者姓名</label>
    <div class="layui-input-block">
    <c:if test="${sessionScope.patient == null }">
      <input type="text" id="pname" name="pname" value="${requestScope.requireMent.patient.name }" required  lay-verify="required" autocomplete="off" class="layui-input" readonly>
    </c:if>
    <c:if test="${sessionScope.patient != null }">
      <input type="text" id="pname" name="pname" value="${sessionScope.patient.name }" required  lay-verify="required" autocomplete="off" class="layui-input" readonly>
    </c:if>
    </div>
  </div>
  
   <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">创建时间</label>
    <div class="layui-input-block">
      <input type="text" id="createdate" name="createdate" value="${requestScope.requireMent.createDate }" required  lay-verify="required" class="layui-input" readonly>
    </div>
  </div>
  
  <div class="layui-form-item"  style="width: 500px;">
	  <label class="layui-form-label">预订数量</label>
	<div class="layui-input-block">
	   <input type="text" id="total" name="total" value="${requestScope.requireMent.total}" lay-verify="required" autocomplete="off" class="layui-input" readonly>
	</div>
  </div>
  
  <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">药品类别</label>
      <div class="layui-input-block">
	     <input type="text" id="cate" name="category" value="${requestScope.requireMent.medicine.category}" lay-verify="required" autocomplete="off" class="layui-input" readonly>
	  </div>
  </div>
  
   <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">药品名称</label>
    <div class="layui-input-block">
      <input type="text" id="mname" name="mname" value="${requestScope.requireMent.medicine.name}" lay-verify="required" autocomplete="off" class="layui-input" readonly>
    </div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
    	<c:if test="${requestScope.requireMent.flag == 1}">
	      	<a href="${pageContext.request.contextPath}/confirmBook.do?oid=${requestScope.requireMent.oid}&total=${requestScope.requireMent.total}" class="layui-btn" >接受预订</a>
    	</c:if>
    	<c:if test="${requestScope.requireMent.flag == 2}">
	     	<a class="layui-btn layui-btn-disabled">已处理</a>
    	</c:if>
    	<c:if test="${requestScope.requireMent.flag == 3}">
      		<a class="layui-btn  layui-btn-danger" style="cursor:not-allowed;">已失效</a>
      	</c:if>
    </div>
   </div>
</form>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/bookSeeList.js"></script>
</body>
</html>