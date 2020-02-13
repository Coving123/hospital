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
	<title>信息表单--layui后台管理模板 2.0</title>
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
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-block">
      <input type="text" id="name" name="name" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">登录名</label>
    <div class="layui-input-inline">
      <input type="text" id="loginName" name="loginName" required  lay-verify="required|lname" placeholder="请输入登录名" autocomplete="off" class="layui-input" >
    </div>
    <div class="layui-form-mid layui-word-aux">登录名长度：3-12位</div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="text" id="password" name="password" required  lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input" >
    </div>
    <div class="layui-form-mid layui-word-aux">密码长度：3-12位</div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">出生年月</label>
    <div class="layui-input-block">
      <input type="text" id="birth" name="birth" required  lay-verify="required" placeholder="请输入出生年月" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">家庭地址</label>
    <div class="layui-input-block">
      <input type="text" id="address" name="address" required  lay-verify="required" placeholder="请输入家庭地址" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">联系电话</label>
    <div class="layui-input-block">
      <input type="text" id="phone" name="phone" required  lay-verify="required" placeholder="请输入联系电话" autocomplete="off" class="layui-input" style="width: 500px;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" id="sexM" value="男" title="男" name="sex" lay-skin="primary">
      <input type="radio" id="sexW" value="女" title="女" name="sex" lay-skin="primary">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="register">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script>
		layui.use(['form','layer','laydate','table','upload'],function(){
        var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;
      
      //常规用法
      laydate.render({
        elem: '#birth'
      });
      
      form.verify({
    	    lname: function(value){
    	      if(value.length < 3){
    	        return '长度不合法，至少输入3个字符';
    	      }else if(value.length > 12){
    	    	 return '长度不合法，不要超过12字符';
    	      }else if(/^[A-Za-z0-9_-]+$/.test(value)==false){
    	    	  return '输入不合法，字符仅由数字和字母组成';
    	      }
    	    }
    	    ,pass: [
    	      /^[\S]{3,12}$/
    	      ,'密码长度在3到12位，不能含有空格'
    	    ]
    	    ,content: function(value){
    	      layedit.sync(editIndex);
    	    }
    	  });
      
        
    form.on("submit(register)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        //这里写ajax保存方法
       
		$.ajax({
				type : "POST",
				async: false,
				url : "${pageContext.request.contextPath}/user/doRegister.do",
				data : {
					username : $("#name").val(),
					loginName : $("#loginName").val(),
					password : $("#password").val(),
					birth : $("#birth").val(),
					address : $("#address").val(),
					phone : $("#phone").val(),
					sex :  $("input[type='radio']:checked").val()
				},
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				dataType : "json",
				success : function(data) {
					if(data.msg == 1){
						layer.alert("注册成功！", {
							icon: 1,
							title: "提示"
						});
						setTimeout(function(){
							layer.closeAll("iframe");
							 $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
						},1000);
					}else if(data.msg == 3){
						layer.alert("用户名已存在！", {
							icon: 5,
							title: "提示"
						});
					}else if(data.msg == 2){
						layer.alert("注册失败！", {
							icon: 5,
							title: "提示"
						});
					}
				}
		});
		
	    })
     });
	</script>
</body>
</html>