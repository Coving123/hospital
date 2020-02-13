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
	<title>信息管理--后台管理模板</title>
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
<form class="layui-form" action="" lay-filter="patientform">
  <input type="hidden" id="pid" name="pid" value ="${requestScope.patient.pid}" >
  <input type="hidden" id="uid" name="uid" value ="${requestScope.patient.user.id}" >

  <div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input type="text" id="name" name="name" value="${requestScope.patient.name}" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">登录名</label>
    <div class="layui-input-block">
      <input type="text" id="loginName" name="loginName" value="${requestScope.patient.user.username}" disabled autocomplete="off" class="layui-input layui-disabled" style="width: 500px;">
    </div>
  </div>
   <div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="oldPassword" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="password" value="" placeholder="请输入新密码" lay-verify="required|newPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item" style="width: 500px;">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" id="repassword" value="" placeholder="再次输入确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
   <div class="layui-form-item">
    <label class="layui-form-label">出生年月</label>
    <div class="layui-input-block">
      <input type="text" id="birth" name="birth" value="${requestScope.patient.birth }" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">家庭地址</label>
    <div class="layui-input-block">
      <input type="text" id="address" name="address" value="${requestScope.patient.address }" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-block">
      <input type="text" id="phone" name="phone" value="${requestScope.patient.phone }" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男" 
		<c:if test="${requestScope.patient.sex eq '男'}">
			 checked
		</c:if>
		>
	  <input type="radio" name="sex" value="女" title="女"
		<c:if test="${requestScope.patient.sex eq '女'}">
			 checked
		</c:if>
		>
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="addPatient">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/patientList.js"></script>
	<script>	
	layui.use(['form','laydate'],function(){
		  var form = layui.form,
		      laydate = layui.laydate;
	  
	   $.ajax({
		   type : "POST",
       	   async: false,
       	   url :  "findPatient.do",
       	   data:{
                 pid : $("#pid").val()  //将pid作为参数传入
       	   },
       	   contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       	   dataType : "json",
		   success: function(data){form.val('patientform', {
			     "loginname": "test" 
			    ,"password": "123456"
			    ,"phone": 3
			    ,"sex": "女"
			    ,"address": "aaaaaa"})}
       	   });
	  
	  //执行一个laydate实例
	  laydate.render({
	        elem: '#birth'
	  });
	});
	</script>
	
</body>
</html>