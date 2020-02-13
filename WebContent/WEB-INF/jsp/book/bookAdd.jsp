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
<input type="hidden" id="count" lay-filter="count">

   <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">患者姓名</label>
    <div class="layui-input-block">
    <c:if test="${sessionScope.patient == null }">
      <input type="text" id="pname" name="pname" value="${requestScope.requireMent.patient.name }" required  lay-verify="required" placeholder="请输入患者姓名" autocomplete="off" class="layui-input">
    </c:if>
    <c:if test="${sessionScope.patient != null }">
      <input type="text" id="pname" name="pname" value="${sessionScope.patient.name }" required  lay-verify="required" placeholder="请输入患者姓名" autocomplete="off" class="layui-input">
    </c:if>
    </div>
  </div>
  
  <div class="layui-form-item"  style="width: 500px;">
	  <label class="layui-form-label">预订数量</label>
	<div class="layui-input-block">
	   <input type="text" id="total" name="total" value="${requestScope.requireMent.total}" lay-verify="required" placeholder="请输入自然数!!" autocomplete="off" class="layui-input" style="width: 300px;">
	</div>
  </div>
  
  <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">药品类别</label>
    <div class="layui-input-block">
      <select id="category" name="category" class="category" lay-verify="required" lay-filter="category">
        <option value=""></option>
        <option value="感冒药">感冒药</option>
        <option value="肠胃药">肠胃药</option>
        <option value="肠胃药">外用药</option>
      </select>
    </div>
  </div>
  
   <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label">药品名称</label>
    <div class="layui-input-block">
      <select id="mname" name="mname" lay-verify="required">
      </select>
    </div>
  </div>
  
  <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label"> 科室 </label>
    <div class="layui-input-block">
      <select id="subject" name="subject" lay-verify="required" lay-filter="subject">
        <option value=""></option>
        <option value="内科">内科</option>
        <option value="牙科">牙科</option>
        <option value="外科">外科</option>
      </select>
    </div>
  </div>
  
   <div class="layui-form-item" style="width: 500px;">
    <label class="layui-form-label"> 护士 </label>
    <div class="layui-input-block">
      <select id="nuname" name="nuname" lay-verify="required">
      </select>
    </div>
  </div>

  <div class="layui-form-item">
    <div class="layui-input-block">
    <c:choose>
        <c:when test="${requestScope.requireMent.flag == 1 }">
	      	<button class="layui-btn" lay-submit lay-filter="addBook">立即提交</button>
    	</c:when>
    	<c:when test="${requestScope.requireMent.flag == 2 }">
	     	<a class="layui-btn layui-btn-disabled" style="color: #fc4343;">请于3日内领取药品，逾期失效</a>
    	</c:when>
    	<c:when test="${requestScope.requireMent.flag == 3 }">
	     	<a class="layui-btn layui-btn-disabled">已失效，请重新创建预订</a>
    	</c:when>
    	<c:otherwise>
	      	<button class="layui-btn" lay-submit lay-filter="addBook">立即提交</button>
    	</c:otherwise>
    	</c:choose>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/bookList.js"></script>

</body>
</html>
