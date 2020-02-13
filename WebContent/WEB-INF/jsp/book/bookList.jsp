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
	<title>预约管理--layui后台管理模板 2.0</title>
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
				<a class="layui-btn layui-btn-normal addLink_btn">申请预订药品</a>
			</div>			           
		</form>
	</blockquote>
	<table id="linkList" lay-filter="linkListTab"></table>
<!-- 	<table class="layui-hide" id="test"></table> -->
	
	<script type="text/javascript" id="nuname">
		{{# if(d.nurse!=null){}}
			{{d.nurse.name}}
		{{# } }}
	</script>
	<script type="text/javascript" id="pname">
		{{# if(d.patient!=null){}}
			{{d.patient.name}}
		{{# } }}
	</script>
	<script type="text/javascript" id="mname">
		{{# if(d.medicine!=null){}}
			{{d.medicine.name}}
		{{# } }}
	</script>
	<script type="text/javascript" id="cate">
		{{# if(d.medicine!=null){}}
			{{d.medicine.category}}
		{{# } }}
	</script>
	<script type="text/javascript" id="desc">
		{{# if(d.medicine!=null){}}
			{{d.medicine.description}}
		{{# } }}
	</script>
	<script type="text/javascript" id="count">
		{{# if(d.medicine!=null){}}
			{{d.medicine.count}}
		{{# } }}
	</script>
	<script type="text/javascript" id="price">
		{{# if(d.medicine!=null){}}
			{{d.medicine.price.toFixed(2)}}
		{{# } }}
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/one-js/bookList.js"></script>

	<!-- 
	<script>
	layui.use([ 'layer', 'form' ], function() {
		var layer = layui.layer, $ = layui.jquery, form = layui.form();
	$(".aaa").click(function(obj) {
		var url="findRequireByPageByPatient.do";
		for(var data in map)
		    console.log("属性：" + data + ",值："+ map[data]);    
			})
	});
	</script>
	

	<script>
		layui.use('table', function() {
			var table = layui.table;
			table.render({
				elem : '#test',
				url : 'findRequireByPageByPatient.do',
				cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
				,
				cols : [[ {field : 'pname',title : 'NAME',templet : '<div><a>{{d.patient.name}}</a></div>'},
					{field : 'price',title : 'MONEY',templet : '<div><a>{{d.medicine.price}}</a></div>'} 
				//width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
				]]
			});
		});
	</script>
	-->

</body>
</html>